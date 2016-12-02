import static org.junit.Assert.*;

/**
 * This is a test class for Bidder.
 * 
 * @author Cody Arnold 
 * @version 11/30/16
 */

import org.junit.Before;
import org.junit.Test;

public class BidderTest {
	Item theItem;
	float theBid;
	Bidder bidder;
	
	@Before
	public void setUp() throws Exception {
		theItem = new Item("book");
		bidder = new Bidder("jo", "jo", "jo", "jo", "jo");
		theItem.setStartingBid(1);
	}
	
	@Test
	public void testPlaceBidOfZero() {
		theBid = 0;
		
		assertEquals(bidder.placeBid(theItem, theBid), 1);
		assertEquals(theItem.getBid("jo"), -1, 0);
	}
	
	@Test
	public void testPlaceBidOfNegatative() {
		theBid = -1;
		assertEquals(bidder.placeBid(theItem, theBid), 2);
		assertEquals(theItem.getBid("jo"), -1, 0);
	}
	@Test
	public void testPlaceBidOfPositive() {
		theBid = 2;
		assertEquals(bidder.placeBid(theItem, theBid), 0);
		assertEquals(theItem.getBid("jo"), 2, 0);
	}
	
	@Test
	public void testPlaceBidLessThanStartingBid() {
		theItem.setStartingBid(3);
		theBid = 2;
		assertEquals(bidder.placeBid(theItem, theBid), 3);
		assertEquals(theItem.getBid("jo"), -1, 0);
	}
	
	@Test
	public void testPlaceBidGreaterThanStartingBid() {
		theItem.setStartingBid(1);
		theBid = 2;
		assertEquals(bidder.placeBid(theItem, theBid), 0);
		assertEquals(theItem.getBid("jo"), 2, 0);
	}

}
