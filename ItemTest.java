import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**	This class Tests the methods found in the Item class
 * 
 * @author Katie Harmon
 * @version 11-20-2016
 */
public class ItemTest {
	Item generalItem;
	
	@Before
	public void setUp() throws Exception {
		//generalItem = new Item("a", "b", "c", 1, 100,"d", "small","e");
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
	
	@Test
	public void testGetBidOnBidderNameDoesNotExists(){
		assertEquals((float)-1, generalItem.getBid("Steven"), 0);
	}
	
	@Test
	public void testGetBidOnBidderNameExists(){
		assertTrue(generalItem.addBid((float)200.00, "Steven"));
		assertEquals((float)200.00, generalItem.getBid("Steven"), 0);
	}
	
	@Test
	public void testSetQuantityOnLessThanZero() {
		assertFalse(generalItem.setQuantity(-2));
	}
	
	@Test
	public void testSetQuantityOnEqualToZero() {
		assertFalse(generalItem.setQuantity(0));
	}
	
	@Test
	public void testSetQuantityOnGreaterThanZero() {
		assertTrue(generalItem.setQuantity(3));
	}
	
	@Test
	public void testSetStartingBidOnLessThanZero() {
		assertFalse(generalItem.setStartingBid(-2));
	}
	
	@Test
	public void testSetStartingBidOnEqualToZero() {
		assertFalse(generalItem.setStartingBid(0));
	}
	
	@Test
	public void testSetStartingBidOnGreaterThanZero() {
		assertTrue(generalItem.setStartingBid(3));
	}
	
	@Test
	public void testSetSizeOnSmall(){
		assertTrue(generalItem.setSize("small"));
	}
	
	@Test
	public void testSetSizeOnMedium(){
		assertTrue(generalItem.setSize("medium"));
	}
	
	@Test
	public void testSetSizeOnLarge(){
		assertTrue(generalItem.setSize("large"));
	}
	
	@Test
	public void testSetSizeOnInvalidSize(){
		assertFalse(generalItem.setSize("shirt"));
	}
	
	@Test
	public void testDeleteBidOnBidExists(){
		assertTrue(generalItem.addBid((float)200.00, "Steven"));
		assertTrue(generalItem.deleteBid("Steven"));
	}
	
	@Test
	public void testDeleteBidOnBidDoesNotExists(){
		assertFalse(generalItem.deleteBid("Steven"));
	}
}
