/**
 * Test cases for Auction class.
 * 
 * @author Kyle Phan
 * @editor Katie Harmon
 * @version 12/4/16
 */


import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class AuctionTest
{
	
	private AuctionDate myCurrentDate;
	private String myName;
	private Auction myAuction;
	
	private String expectedItemName = "Driver 1";
	private String expectedItemDonor = "Sebastian Vettel";
	private String expectedItemDesc = "Racing, or ping pong?";
	private int expectedItemQuantity = 5;
	private float expectedStartingBid = 197;
	private String expectedItemCond = "Unbelievable";
	private String expectedItemSize = "Small";
	private String expectedItemComment = "BLUE FLAG! BLUE FLAG!";
	
	private String expectedItemName2 = "Driver 2";
	private String expectedItemDonor2 = "Kimi Raikkonen";
	private String expectedItemDesc2 = "Bwoah";
	private int expectedItemQuantity2 = 7;
	private float expectedStartingBid2 = 178;
	private String expectedItemCond2 = "It's the same for everyone.";
	private String expectedItemSize2 = "Small";
	private String expectedItemComment2 = "Leave me alone, I know what I'm doing.";
	
	private Item myItem;
	private Item myItem2;
	private Item myItemAgain;
	
	@Before
	public void setup() {
		myCurrentDate = new AuctionDate(2016, 12, 21, 12);
		myName = "Group 2";
		
		myItem = new Item(expectedItemName, expectedItemDonor, expectedItemDesc,
				   expectedItemQuantity, expectedStartingBid, expectedItemCond,
				   expectedItemSize, expectedItemComment);
		myItemAgain = new Item(expectedItemName, expectedItemDonor, expectedItemDesc,
				   expectedItemQuantity, expectedStartingBid, expectedItemCond,
				   expectedItemSize, expectedItemComment);
		myItem2 = new Item(expectedItemName2, expectedItemDonor2, expectedItemDesc2,
				   expectedItemQuantity2, expectedStartingBid2, expectedItemCond2,
				   expectedItemSize2, expectedItemComment2);

		myAuction = new Auction(myCurrentDate, myName);
	}
	
	@Test
	public void testAddItemNoDuplicatesCheckingArrayListAndReturnBoolean() {
		Boolean testBoolean = myAuction.addItem(myItem);
		Boolean testBoolean2 = myAuction.getItems().contains(myItem);
		
		assertTrue(testBoolean);
		assertTrue(testBoolean2);
	}
	
	@Test
	public void testAddItemMultipleUnique() {
	
		String expectedItemName3 = "Test Driver";
		String expectedItemDonor3 = "Jean-Eric Vergne";
		String expectedItemDesc3 = "French guy";
		int expectedItemQuantity3 = 25;
		float expectedStartingBid3 = 25;
		String expectedItemCond3 = "Used";
		String expectedItemSize3 = "Small";
		String expectedItemComment3 = "N/A";
		
		Boolean testBoolean = myAuction.addItem(myItem); //Boolean returned by function
		Boolean testBoolean7 = myAuction.getItems().contains(myItem); //Boolean returned if array contains Item
		assertTrue(testBoolean7); //Check if Item is added immediately after add
		

		Boolean testBoolean2 = myAuction.addItem(myItem2); //Boolean returned by function
		Boolean testBoolean8 = myAuction.getItems().contains(myItem2); //Boolean returned if array contains Item
		assertTrue(testBoolean8); //Check if Item is added immediately after add
		
		Item myItem3 = new Item(expectedItemName3, expectedItemDonor3, expectedItemDesc3,
				   expectedItemQuantity3, expectedStartingBid3, expectedItemCond3,
				   expectedItemSize3, expectedItemComment3);
		
		Boolean testBoolean3 = myAuction.addItem(myItem3); //Boolean returned by function
		Boolean testBoolean9 = myAuction.getItems().contains(myItem3); //Boolean returned if array contains Item
		assertTrue(testBoolean9); //Check if Item is added immediately after add
		
		
		Boolean testBoolean4 = myAuction.getItems().contains(myItem);
		Boolean testBoolean5 = myAuction.getItems().contains(myItem2);
		Boolean testBoolean6 = myAuction.getItems().contains(myItem3);
		
		assertTrue(testBoolean); 
		assertTrue(testBoolean2);
		assertTrue(testBoolean3);
		assertTrue(testBoolean4);
		assertTrue(testBoolean5);
		assertTrue(testBoolean6);
	}
	
	@Test
	public void testAddItemDuplicateItemsSameNameSameInfo() {
		Boolean testBoolean = myAuction.addItem(myItem);
		Boolean testBoolean2 = myAuction.addItem(myItemAgain); //Katies change

		Boolean testBoolean4 = myAuction.getItems().contains(myItem);
		Boolean testBoolean5 = myAuction.getItems().contains(myItemAgain); //Katies change

		assertTrue(testBoolean);
		assertFalse(testBoolean2);

		assertTrue(testBoolean4);
		assertFalse(testBoolean5);
	}
	
	@Test
	public void testAddItemDuplicateItemsSameNameDiffInfo() {
		myItem2.setName(expectedItemName);
		Boolean testBoolean = myAuction.addItem(myItem);
		Boolean testBoolean2 = myAuction.addItem(myItem2);

		Boolean testBoolean4 = myAuction.getItems().contains(myItem);
		Boolean testBoolean5 = myAuction.getItems().contains(myItem2);

		
		assertTrue(testBoolean);
		assertFalse(testBoolean2);

		assertTrue(testBoolean4);
		assertFalse(testBoolean5);
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
//	@Test
//	public void testCheckCurrentSameDate() {
//		Auction myAuction = new Auction(myDate, myName);
//		Boolean isCurrent = myAuction.checkCurrent(myDate);
//		
//		assertFalse(isCurrent);
//	}
//	
//	@Test
//	public void testCheckCurrentBeforeDate() {
//		Date testDate = new Date();
//		testDate.setDate(25);
//		
//		Auction myAuction = new Auction(myDate, myName);
//		Boolean isCurrent = myAuction.checkCurrent(testDate);
//		
//		assertTrue(isCurrent);
//	}
//	
//	@Test
//	public void testCheckCurrentAfterDate() {
//		Date testDate = new Date();
//		testDate.setDate(1);
//		
//		Auction myAuction = new Auction(myDate, myName);
//		Boolean isCurrent = myAuction.checkCurrent(testDate);
//		
//		assertTrue(isCurrent);
//	}
}
