/**
 * The User test class to test the methods that
 * are in the User class and all of its subclasses.
 * 
 * @author Andrew Dinh
 * @version 11/12/2016
 */
 
import static org.junit.Assert.*;

import java.util.Date;
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
		testAuction = new Auction(new Date(), testNonprofit.getOrgName());
		testDatabase = new Database();
		testDatabase.addUserToDB(testBidder); 
		testDatabase.addUserToDB(testNonprofit); 
		testDatabase.addUserToDB(testStaff); 
		testDatabase.addAuctionToDB(testAuction);
	}
	
	@Test
	public void testAuthenticate() {
		assertTrue(testBidder.authenticate("bidguy", "pass123"));
		assertFalse(testBidder.authenticate("bidguy", "badpass"));
	}
	
	@Test
	public void testSubmitAuctionRequest() {
		Auction testAuction = new Auction(new Date(), testNonprofit.getOrgName());
		testNonprofit.submitAuctionRequest(testAuction);
		assertTrue(testNonprofit.hasCurrentAuction());
	}
	
	@Test
	public void testAddItem() {
		Item testItem = new Item("Shoes");
		testNonprofit.addItem(testAuction);
		assertEquals(testAuction.itemCount, 1);
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
