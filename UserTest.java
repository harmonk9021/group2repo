/**
 * The User test class to test the methods that
 * are in the User class and all of its subclasses.
 * 
 * @author Andrew Dinh, Katherine Harmon
 * @version 11/12/2016
 */
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
 
public class UserTest
{
	
	private Bidder testBidder;
	
	private Nonprofit testNonprofit;
	
	private Staff testStaff;
	
	private Auction testAuction, auction1;
	
	private AuctionCalendar calendar;
	
	private AuctionDate today;
	
	private AuctionDate elevenDays;
	
	
	@Before
	public void setUp() {
		testBidder = new Bidder("John", "bidguy", "pass123", "John@email.com", "555-1234");
		testNonprofit = new Nonprofit("Toys4Kids", "Nonprof", "word123", "Toys4Kids@email.com", "999-8765");
		testStaff = new Staff("Kim", "staffperson", "pard123", "Kim@email.com", "111-2345");
		testAuction = new Auction(new AuctionDate(), testNonprofit.getName());
		today = new AuctionDate();
		calendar = new AuctionCalendar(new AuctionDate(), "Tests.ser");
		elevenDays = today.getAuctionDateXDaysAway(11);
		
		
		
	}
	
	@Test
	public void testAuthenticatePass() {
		assertTrue(testBidder.authenticate("bidguy", "pass123"));
		assertTrue(testNonprofit.authenticate("Nonprof", "word123"));
		assertTrue(testStaff.authenticate("staffperson", "pard123"));
	}
	
	@Test
	public void testAuthenticateFail() {
		assertFalse(testBidder.authenticate("bidguy", "badpass"));
		assertFalse(testNonprofit.authenticate("Nonprof", "badpass"));
		assertFalse(testStaff.authenticate("staffperson", "badpass"));
		assertFalse(testBidder.authenticate("baduser", "pass123"));
		assertFalse(testNonprofit.authenticate("baduser", "word123"));
		assertFalse(testStaff.authenticate("baduser", "pard123"));
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
	public void testAddItem() {
		testNonprofit.submitAuctionRequest(elevenDays, "blah", 
				"OrgName1", "blah", "blah", "blah", calendar);
		assertTrue(testNonprofit.addItem("Shoes", "John", 
				"Gently Used", 1, (float) 25.00, "Good", "Medium", "None", calendar));
	}
	
	@Test
	public void testSetContactPerson() {
		testNonprofit.setContactPerson("Paul Meyer");
		assertEquals("Paul Meyer", testNonprofit.getContactPerson());
	}
	
	@Test
	public void testPlaceBid() {
		Item testItem = new Item("shoes");
		testItem.setStartingBid(100);
		assertTrue(testBidder.placeBid(testItem, (float) 101.50));
	}
	
	@Test
	public void testRemoveBid() {
		Item testItem = new Item("Shoes");
		testBidder.placeBid(testItem, (float) 101.50);
		testBidder.removeBid(testItem);
		Map<Item, Float> bids = testBidder.viewBids();
		assertEquals(bids.size(), 0);
	}
	
}
