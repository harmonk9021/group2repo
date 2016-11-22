/**
 * The User test class to test the methods that
 * are in the User class and all of its subclasses.
 * 
 * @author Andrew Dinh
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
	
	public Bidder testBidder;
	
	public Nonprofit testNonprofit;
	
	public Staff testStaff;
	
	public Auction testAuction;
	
	public Database testDatabase;
	
	@Before
	public void setUp() {
		testBidder = new Bidder("John", "bidguy", "pass123", "John@email.com", "555-1234");
		testNonprofit = new Nonprofit("Toys4Kids", "Nonprof", "word123", "Toys4Kids@email.com", "999-8765");
		testStaff = new Staff("Kim", "staffperson", "pard123", "Kim@email.com", "111-2345");
		testAuction = new Auction(new AuctionDate(), testNonprofit.getName());
		testDatabase = new Database();
		testDatabase.addUserToDB(testBidder); 
		testDatabase.addUserToDB(testNonprofit); 
		testDatabase.addUserToDB(testStaff); 
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
	public void testSubmitAuctionRequest() {
		Auction testAuction = new Auction(new AuctionDate(), testNonprofit.getName());
		testNonprofit.submitAuctionRequest(testAuction);
		assertTrue(testNonprofit.hasCurrentAuction());
	}
	
	@Test
	public void testAddItem() {
//		testNonprofit.addItem("Shoes", "John", "Gently Used", 1, (float) 25.00, "Good", "Medium", "None");
		assertTrue(testAuction.getItems().contains("Shoes"));
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
		testBidder.placeBid(testItem, (float) 101.50);
		assertEquals(testItem.getBid(testBidder.getUserName()), "bidguy");
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
