import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Canceling a bid user story.
 * User story: As a bidder, 
 *   I want to cancel my bid for an item in an auction.
 * 
 * @author Katie Harmon
 * @version 12/4/16
 */

public class RemoveBidTest {
	Item myItem;
	Bidder testBidder;
	Auction myAuction;
	
	@Before
	public void setUp() throws Exception {
		myItem = new Item("name");
		myItem.setStartingBid((float)100.00);
		testBidder = new Bidder("John", "bidguy", "pass123", "John@email.com", "555-1234");
		myAuction = new Auction(new AuctionDate().getAuctionDateXDaysAway(11), "Steven");
	}
	
	@Test
	public void testItemDeleteBidOnBidExists(){
		assertTrue(myItem.addBid((float)200.00, "Steven"));
		assertTrue(myItem.deleteBid("Steven"));
	}
	
	@Test
	public void testItemDeleteBidOnBidDoesNotExists(){
		assertFalse(myItem.deleteBid("Steven"));
	}
	
	@Test
	public void testRemoveBidOnBidExists(){
		myAuction.addItem(myItem);
		myItem.addBid(1000, "Steven");
		assertEquals(0, myAuction.removeBid(myItem, "Steven"));
	}
	
	@Test
	public void testRemoveBidOnBidDoesNotExists(){
		myAuction.addItem(myItem);
		assertEquals(2, myAuction.removeBid(myItem, "Steven"));
	}
	
	@Test
	public void testRemoveBidOnBidExistsWithin2Days(){
		AuctionDate today = new AuctionDate();
		myAuction.setDate(today);
		myAuction.addItem(myItem);
		myItem.addBid(1000, "Steven");
		assertEquals(1, myAuction.removeBid(myItem, "Steven"));
	}
	
	@Test
	public void testRemoveBidOnBidDoesNotExistsWithin2Days(){
		AuctionDate today = new AuctionDate();
		myAuction.setDate(today);
		myAuction.addItem(myItem);
		assertEquals(1, myAuction.removeBid(myItem, "Steven"));
	}
	
	@Test
	public void testRemoveBidOnItemDoesNotExist(){
		assertEquals(3, myAuction.removeBid(myItem, "Steven"));
	}
}
