/**
 * Test cases for Add an auction user story.
 * User story: As a contact person for a non-profit organization,
 *  I want to submit an auction request.
 * 
 * @author Katie Harmon
 * @version 12/4/16
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddAuctionTest {

	private AuctionCalendar calendar;
	private AuctionDate today;
	private AuctionDate elevenDays;
	private Auction auction1;
	private Auction auction2;
	private Nonprofit testNonprofit;
	
	@Before
	public void setup(){
		today = new AuctionDate();
		elevenDays = today.getAuctionDateXDaysAway(11);
		
		testNonprofit = new Nonprofit("theName", "testUsername", "thePassword", "theEmail", "thePhone");
		calendar = new AuctionCalendar(new AuctionDate(), "Tests.ser");
		
		auction1 = new Auction(today.getAuctionDateXDaysAway(10), "catsforkids", "username1",
	    		"sarah", "rad", "none");
		auction2 = new Auction(elevenDays, "catsforadults", "username2",
	    		"Brandon", "pretty cool", "comment");
		
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
	public void testSubmitAuctionRequestOnDoesNotHaveAFutureAuction() {
		assertEquals(0, testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnHasAFutureAuction() {
		testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar);
		assertEquals(3, testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"blah", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnHasNoPastAuctions() {
		assertEquals(0, testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnNoAuctionsOnSchedualedDay() {
		assertEquals(0, testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnOneOtherAuctionOnSchedualedDay() {
		Nonprofit nonprofit1 = new Nonprofit("CatsForKids", "lolol", "word123", 
				"Toys4Kids@email.com", "999-8765");
		nonprofit1.submitAuctionRequest(elevenDays, "groober", "theOrgName",
				"theContactPerson", "theDescription", "theComment", calendar);
		assertEquals(0, testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test 
	public void testSubmitAuctionRequestOnTwoOtherAuctionsOnSchedualedDay() {
		testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar);
		Nonprofit nonprofit1 = new Nonprofit("CatsForKids", "lolol", "word123", 
				"Toys4Kids@email.com", "999-8765");
		Nonprofit nonprofit2 = new Nonprofit("change", "change", "word123", 
				"Toys4Kids@email.com", "999-8765");
		nonprofit1.submitAuctionRequest(elevenDays, "a", 
				"OrgName2", "blah", "blah", "blah", calendar);
		assertEquals(7, nonprofit2.submitAuctionRequest(elevenDays, "ab", 
				"OrgName3", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionCountIsLessThanMax() {
		assertEquals(0, testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionCountIsAtMaxMinusOne() {
		testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar);
		Nonprofit nonprofit1 = new Nonprofit("CatsForKids", "lolol", "word123", 
				"Toys4Kids@email.com", "999-8765");
		calendar.setMaxAuctions(2);
		assertEquals(0, nonprofit1.submitAuctionRequest(elevenDays, "a", 
				"OrgName2", "blah", "blah", "blah", calendar));
		
		
	}
	
	@Test 
	public void testSubmitAuctionRequestOnAuctionCountIsAtMax() {
		
		testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar);
		Nonprofit nonprofit1 = new Nonprofit("CatsForKids", "lolol", "word123", 
				"Toys4Kids@email.com", "999-8765");
		calendar.setMaxAuctions(1);
		assertEquals(1, nonprofit1.submitAuctionRequest(elevenDays, "a", 
				"OrgName2", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionCountIsGreaterThanMax() {
		testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar);
		Nonprofit nonprofit1 = new Nonprofit("CatsForKids", "lolol", "word123", 
				"Toys4Kids@email.com", "999-8765");
		Nonprofit nonprofit2 = new Nonprofit("change", "change", "word123", 
				"Toys4Kids@email.com", "999-8765");
		nonprofit1.submitAuctionRequest(today.getAuctionDateXDaysAway(14), "a", 
				"OrgName2", "blah", "blah", "blah", calendar);
		calendar.setMaxAuctions(1);
		assertEquals(1, nonprofit2.submitAuctionRequest(elevenDays, "ab", 
				"OrgName3", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedOneMonthAway() {
		assertEquals(0, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(31), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
		
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedLessThanOneMonthAway() {
		assertEquals(0, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(20), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedOneMonthPlusOneDayAway() {
		assertEquals(6, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(32), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedMoreThanOneMonthAway() {
		assertEquals(6, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(40), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedOneWeekAway() {
		assertEquals(0, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(7), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedMoreThanOneweekAway() {
		assertEquals(0, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(20), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedSixDaysAway() {
		assertEquals(5, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(6), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedLessThanSixDaysAway() {
		assertEquals(5, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(3), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedOnTheDateOfSubmittion() {
		assertEquals(5, testNonprofit.submitAuctionRequest(today, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
	public void testSubmitAuctionRequestOnAuctionIsSchedualedBeforeTheDateOfSubmittion() {
		assertEquals(5, testNonprofit.submitAuctionRequest(today.getAuctionDateXDaysAway(-2), "blah", 
				"OrgName1", "blah", "blah", "blah", calendar));
	}
	
	@Test
    public void testIsWithinYear()
    {
        AuctionDate easyCase = new AuctionDate(2000, 4, 10, 15);
        AuctionDate lessEasyCaseA = new AuctionDate(1999, 5, 16, 15);
        AuctionDate lessEasyCaseB = new AuctionDate(1999, 6, 10, 15);
        AuctionDate falseCase = new AuctionDate(1999, 5, 14, 15);
        AuctionDate test = new AuctionDate(2000, 5, 15, 15);
        boolean easyExpected = true;
        boolean lessEasyExpectedA = true;
        boolean lessEasyExpectedB = true;
        boolean falseExpected = false;
        boolean testFlag;
        testFlag = test.isWithinYear(easyCase);
        assertEquals("Easy case fails!", easyExpected, testFlag);
        testFlag = test.isWithinYear(lessEasyCaseA);
        assertEquals("Hard case A fails!", lessEasyExpectedA, testFlag);
        testFlag = test.isWithinYear(lessEasyCaseB);
        assertEquals("Hard case B fails!", lessEasyExpectedA, testFlag);
        testFlag = test.isWithinYear(falseCase);
        assertEquals("False case fails!", falseExpected, testFlag);
    }
	
	
}
