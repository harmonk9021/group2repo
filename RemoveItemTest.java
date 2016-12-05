import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for remove an item user story.
 * User story: As a contact person for a non-profit organization,
 *  I want to submit an auction request.
 * 
 * @author Katie Harmon
 * @version 12/4/16
 */

public class RemoveItemTest {
	private Item myItem;
	private Auction  myAuction;
	
	@Before
	public void setup() {
		myItem = new Item("Item name", "donor", "expectedItemDesc",
				   1, 100, "good",
				   "large", "expectedItemComment");
		myAuction = new Auction(new AuctionDate().getAuctionDateXDaysAway(11), "myName");
	}
	
	@Test
	public void testRemoveItemOnItemExists() {
		myAuction.addItem(myItem);
		assertEquals(0, myAuction.removeItem(myItem));
	}

	@Test
	public void testRemoveItemOnItemDoesNotExists() {
		assertEquals(2, myAuction.removeItem(myItem));
	}
	
	@Test
	public void testRemoveItemOnItemExistsWithin2Days() {
		AuctionDate today = new AuctionDate();
		myAuction.setDate(today);
		myAuction.addItem(myItem);
		assertEquals(1, myAuction.removeItem(myItem));
	}
	
	@Test
	public void testRemoveItemOnItemDoesNotExistsWithin2Days() {
		AuctionDate today = new AuctionDate();
		myAuction.setDate(today);
		assertEquals(2, myAuction.removeItem(myItem));
	}
}
