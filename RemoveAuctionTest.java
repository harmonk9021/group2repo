/**
 * Test cases for Remove an auction user story.
 * User story: As a contact person for a non-profit organization, 
 * I want to cancel an auction request.

 * 
 * @author Katie Harmon
 * @version 12/4/16
 */

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class RemoveAuctionTest {
	private Auction myAuction;
	private AuctionCalendar calendar;
	private AuctionDate today;
	
	@Before
	public void setup(){
		today = new AuctionDate();
		calendar = new AuctionCalendar(today, "Auctions.ser");
	}
	
	@Test
	public void testRemoveAuctionOnRemovingBeforeTwoDaysBeforeAuctionDate() {
		calendar.createAndAddAuction(today.getAuctionDateXDaysAway(-3), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(today.getAuctionDateXDaysAway(-3), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(0, calendar.removeAuction("KatiesOrg"));
	}
	
	@Test
	public void testRemoveAuctionOnRemovingExactlyTwoDaysBeforeAuctionDate(){
		calendar.createAndAddAuction(today.getAuctionDateXDaysAway(-2), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(today.getAuctionDateXDaysAway(-2), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(0, calendar.removeAuction("KatiesOrg"));
	}
	
	@Test
	public void testRemoveAuctionOnRemoveWithinTwoDaysOfAuctionDate(){
		calendar.createAndAddAuction(today.getAuctionDateXDaysAway(-1), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(today.getAuctionDateXDaysAway(-1), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(2, calendar.removeAuction("KatiesOrg"));
	}
	
	@Test
	public void testRemoveAuctionOnRemoveAfterAuctionDate(){
		calendar.createAndAddAuction(today.getAuctionDateXDaysAway(1), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(today.getAuctionDateXDaysAway(1), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(2, calendar.removeAuction("KatiesOrg"));
	}
	
	@Test
	public void testRemoveAuctionOnRemoveOnAuctionDate(){
		calendar.createAndAddAuction(today, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(today, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(2, calendar.removeAuction("KatiesOrg"));
	}
	
	@Test
	public void testRemoveAuctionOnAuctionDoesNotExist(){
		myAuction = new Auction(today.getAuctionDateXDaysAway(-3), "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(1, calendar.removeAuction("KatiesOrg"));
	}

	@Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsBefore2Days(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 10, 20, 13);
    	assertTrue(date1.isTwoOrMoreDaysBefore(date2));	
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIs2Days(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 11, 19, 12);
    	assertTrue(date1.isTwoOrMoreDaysBefore(date2));
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsWithin2Days(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 11, 21, 11);
    	assertFalse(date1.isTwoOrMoreDaysBefore(date2));
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsAfterDate(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 01, 12);
    	assertFalse(date1.isTwoOrMoreDaysBefore(date2));
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsBefore2DaysSwitchBetweenYears(){
    	AuctionDate date1 = new AuctionDate(2017, 01, 01, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 29, 12);
    	assertTrue(date1.isTwoOrMoreDaysBefore(date2));
    } 
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsWithin2DaysSwitchBetweenYears(){
    	AuctionDate date1 = new AuctionDate(2017, 01, 01, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 31, 12);
    	assertFalse(date1.isTwoOrMoreDaysBefore(date2));
    } 
}
