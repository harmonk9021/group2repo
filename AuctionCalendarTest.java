/**
 * Test cases for AuctionCalendar class.
 * 
 * @author Cody Arnold
 * @version 11/19/16
 */


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AuctionCalendarTest {
	private Auction myAuction;
	private AuctionCalendar calendar;
	private AuctionDate today;
	private LocalDate todayDate;
	
	private Auction auction1, auction2;
	private Nonprofit testUser;
	
	private List<Auction> testList;

	
	@Before
	public void setup() {
		testUser = new Nonprofit("theName", "testUsername", "thePassword", "theEmail", "thePhone");
		today = new AuctionDate();
		calendar = testUser.getCalendar();
		
		todayDate = LocalDate.of(today.getYear(), Month.of(today.getMonth()),today.getDay());
		auction1 = new Auction(today.getAuctionDateXDaysAway(10), "catsforkids", "username1",
	    		"sarah", "rad", "none");
		auction2 = new Auction(today.getAuctionDateXDaysAway(11), "catsforadults", "username2",
	    		"Brandon", "pretty cool", "comment");
		
		testList = new ArrayList<Auction>();
	}
	
	@Test
	public void testAuctionCalendarAddAuctionOnAuctionCountIsSetToBelowMax() {
		assertEquals(0, calendar.addAuction(auction1));
	}
	
	@Test
	public void testAuctionCalendarAddAuctionOnAuctionDoesExistOnFile() {
		calendar.addAuction(auction1);
		assertEquals(2, calendar.addAuction(auction1));
	}
	
	@Test
	public void testAuctionCalendarAddAuctionOnAuctionCountIsSetToMax() {
		calendar.setMaxAuctions(2);
		calendar.addAuction(auction1);
		assertEquals(0, calendar.addAuction(auction2));
	}
	
	@Test
	public void testAuctionCalendarAddAuctionOnAuctionCountIsSetToOverMax() {
		calendar.setMaxAuctions(1);
		calendar.addAuction(auction1);
		assertEquals(1, calendar.addAuction(auction2));
	}
	
	
	@Test
	public void testRemoveAuctionOnRemovingBeforeTwoDaysBeforeAuctionDate() {
		LocalDate threeDaysBeforeDate = todayDate.minusDays(3);
		AuctionDate threeDaysBefore = new AuctionDate(threeDaysBeforeDate.getYear(), threeDaysBeforeDate.getMonthValue(), threeDaysBeforeDate.getDayOfMonth(), today.getHour());
		calendar.createAndAddAuction(threeDaysBefore, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(threeDaysBefore, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(0, calendar.removeAuction(myAuction));
	}
	
	@Test
	public void testRemoveAuctionOnRemovingExactlyTwoDaysBeforeAuctionDate(){
		LocalDate twoDaysBeforeDate = todayDate.minusDays(2);
		AuctionDate twoDaysBefore = new AuctionDate(twoDaysBeforeDate.getYear(), twoDaysBeforeDate.getMonthValue(), twoDaysBeforeDate.getDayOfMonth(), today.getHour());
		calendar.createAndAddAuction(twoDaysBefore, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(twoDaysBefore, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(0, calendar.removeAuction(myAuction));
	}
	
	@Test
	public void testRemoveAuctionOnRemoveWithinTwoDaysOfAuctionDate(){
		LocalDate oneDaysBeforeDate = todayDate.minusDays(1);
		AuctionDate oneDaysBefore = new AuctionDate(oneDaysBeforeDate.getYear(), oneDaysBeforeDate.getMonthValue(), oneDaysBeforeDate.getDayOfMonth(), today.getHour());
		calendar.createAndAddAuction(oneDaysBefore, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(oneDaysBefore, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(2, calendar.removeAuction(myAuction));
	}
	
	@Test
	public void testRemoveAuctionOnRemoveAfterAuctionDate(){
		LocalDate oneDaysAfterDate = todayDate.plusDays(1);
		AuctionDate oneDaysAfter = new AuctionDate(oneDaysAfterDate.getYear(), oneDaysAfterDate.getMonthValue(), oneDaysAfterDate.getDayOfMonth(), today.getHour());
		calendar.createAndAddAuction(oneDaysAfter, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(oneDaysAfter, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(2, calendar.removeAuction(myAuction));
	}
	
	@Test
	public void testRemoveAuctionOnRemoveOnAuctionDate(){
		calendar.createAndAddAuction(today, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		myAuction = new Auction(today, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(2, calendar.removeAuction(myAuction));
	}
	
	@Test
	public void testRemoveAuctionOnAuctionDoesNotExist(){
		LocalDate threeDaysBeforeDate = todayDate.minusDays(3);
		AuctionDate threeDaysBefore = new AuctionDate(threeDaysBeforeDate.getYear(), threeDaysBeforeDate.getMonthValue(), threeDaysBeforeDate.getDayOfMonth(), today.getHour());
		myAuction = new Auction(threeDaysBefore, "KatiesAuction", "KatiesOrg", "Katie", "lolol", "none brah");
		assertEquals(1, calendar.removeAuction(myAuction));
	}
	
	@Test
	public void testGetMaxAuctionsOnSetMaxToNegative(){
		assertFalse(calendar.setMaxAuctions(-1));
	}
	
	@Test
	public void testGetMaxAuctionsOnSetMaxToZero(){
		assertFalse(calendar.setMaxAuctions(0));
	}
	
	@Test
	public void testGetMaxAuctionsOnSetMaxToOne(){
		assertTrue(calendar.setMaxAuctions(1));
	}
	
	@Test
	public void testGetMaxAuctionsOnSetMaxAboveOne(){
		assertTrue(calendar.setMaxAuctions(25));
	}
	
	@Test
	public void testGetAuctionsOnNoExistingAuctions(){
		assertEquals(testList, calendar.getAuctions());
	}
	
	@Test
	public void testGetAuctionsOnOneAuctionExist(){
		calendar.addAuction(auction1);
		testList.add(auction1);
		assertEquals(testList, calendar.getAuctions());
		
	}
	
	@Test
	public void testGetAuctionsOnTwoAuctionsExist(){
		calendar.addAuction(auction1);
		testList.add(auction1);
		calendar.addAuction(auction2);
		testList.add(auction2);
		List<Auction> testList1 = new ArrayList<Auction>();
		testList1 = calendar.getAuctions();
//		assertEquals(testList, testList1);
		assertTrue(testList.containsAll(testList1));
	}
	
	@Test
	public void testgetAuctionsOnDateOnNoAuctionsOnThatDate(){
		assertEquals(0, calendar.getAuctionsOnDate(today.getAuctionDateXDaysAway(10)));
	}
	
	@Test
	public void testgetAuctionsOnDateOn1AuctionOnThatDate(){
		calendar.addAuction(auction1);
		assertEquals(1, calendar.getAuctionsOnDate(today.getAuctionDateXDaysAway(10)));
	}
	
	@Test
	public void testgetAuctionsOnDateOn2AuctionsOnThatDate(){
		myAuction = new Auction(today.getAuctionDateXDaysAway(10), "KatiesAuction", 
				"KatiesOrg", "Katie", "lolol", "none brah");
		calendar.addAuction(auction1);
		calendar.addAuction(myAuction);
		assertEquals(2, calendar.getAuctionsOnDate(today.getAuctionDateXDaysAway(10)));
	}
}
