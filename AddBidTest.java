import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for add an item user story.
 * User story: As a bidder, 
 *   I want to bid for an item in an auction.
 * 
 * @author Katie Harmon
 * @version 12/4/16
 */

public class AddBidTest {
Item generalItem;
	
	@Before
	public void setUp() throws Exception {
		generalItem = new Item("name");
		generalItem.setStartingBid((float)100.00);
	}
	
	@Test
	public void testAddBidOnMapDoesNotAlreadyHaveBidder() {
		assertTrue( generalItem.addBid((float)200.00, "Steven"));
		assertNotSame((float)-1, generalItem.getBid("Steven"));
	}


	@Test
	public void testAddBidOnMapAlreadyHasBidder() {
	 assertTrue(generalItem.addBid((float)200.00, "Steven"));
	 assertFalse(generalItem.addBid((float)200.00, "Steven"));
		
	}
	
	@Test
	public void testAddBidOnNegativeBid(){
		assertFalse(generalItem.addBid((float)-10, "Steven"));
	}
	
	@Test
	public void testAddBidOnBidEqualToZero(){
		assertFalse(generalItem.addBid((float)0, "Steven"));
	}
	
	@Test
	public void testAddBidOnBidLowerThanStartingBid(){
		assertFalse(generalItem.addBid((float)10, "Steven"));
	}
	
	@Test
	public void testAddBidOnBidEqualToStartingBid(){
		assertTrue(generalItem.addBid((float)100.00, "Steven"));
	}
	
	@Test
	public void testAddBidOnBidHigherThanStartingBid(){
		assertTrue(generalItem.addBid((float)1000.00, "Steven"));
	}
}
