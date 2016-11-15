/**
 * The User test class to test the methods that
 * are in the User class and all of its subclasses.
 * 
 * @author Andrew Dinh
 * @version 11/12/2016
 */
 
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
 
public class UserTest 
{
	
	public Bidder testBidder;
	
	public Nonprofit testNonprofit;
	
	public Staff testStaff;
	
	public Database testDatabase;
	
	@Before
	public void setUp() {
		testBidder = new Bidder("John", "bidguy", "pass123", "John@email.com", "555-1234");
		testNonprofit = new Nonprofit("Toys4Kids", "Nonprof", "word123", "Toys4Kids@email.com", "999-8765");
		testStaff = new Staff("Kim", "staffperson", "pard123", "Kim@email.com", "111-2345");
		testDatabase = new Database();
		testDatabase.addUserToDB(testBidder); 
		testDatabase.addUserToDB(testNonprofit); 
		testDatabase.addUserToDB(testStaff); 
	}
	
	@Test
	public void testAuthenticate() {
		AssertTrue(testBidder.authenticate("pass123"));
		AssertFalse(testBidder.authenticate("badpass"));
	}
	
	@Test
	public void testViewAuction() {
		testStaff.viewAuctions();
	}
	
	@Test
	public void testViewPastAuction() {
		testStaff.viewPastAuctions();
	}
	
	@Test
	public void testViewCurrentAuction() {
		testStaff.viewCurrentAuctions();
	}
	
	@Test
	public void testSubmitAuctionRequest() {
		Auction testAuction = new Auction();
		testNonprofit.submitAuctionRequest(testAuction);
	}
	
	@Test
	public void testAddItem() {
		Item testItem = new Item();
		testNonprofit.addItem(testItem);
	}
	
	@Test
	public void testSetContactPerson() {
		testNonprofit.setContactPerson("Paul Meyer");
		AssertEquals("Paul Meyer", testNonprofit.getContactPerson());
	}
	
	@Test
	public void testPlaceBid() {
		Item testItem = new Item();
		bidder.placeBid(testItem, 101.50);
	}
	
	@Test
	public void testRemoveBid() {
		Item testItem = new Item();
		bidder.placeBid(testItem, 101.50);
		bidder.removeBid(testItem);
	}
	
}