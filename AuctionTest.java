/**
 * TODO Write a description of class Item here.
 * 
 * @author Kyle Phan
 * @version 11/12/16
 */

/* ***********************************************
 * ***********************************************
 * ***********************************************
 * 
 * CURRENTLY JUST A WAY TO GET FAMILIAR WITH JUNIT 
 * 
 * ***********************************************
 * ***********************************************
 * ***********************************************
 * */

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
public class AuctionTest
{
	
	public Date myDate;
	public String myName;
	Auction myAuction;
	
	@Before
	public void setup() {
		myDate = new Date();
		myName = "Group 2";
	}
	
	@Test
	public void testGetName() {
		myDate = new Date();
		myName = "Group 2";
		Auction myAuction = new Auction(myDate, myName);
		
		String theName = myAuction.getName();
		
		assertEquals("Group 2", theName);
	}
	
	@Test
	public void testGetItemCount() {
		myDate = new Date();
		myName = "Group 2";
		Auction myAuction = new Auction(myDate, myName);
		
//		int theCount = myAuction.getItemCount();
		
//		assertEquals(0, theCount);
	}
	
	@Test
	public void testAddItemNoDuplicates() {
		myDate = new Date();
		myName = "Group 2";
		
		Auction myAuction = new Auction(myDate, myName);
		Boolean testBoolean = myAuction.addItem();
		
		assertTrue(testBoolean);
	}
	
	@Test
	public void testCheckCurrentSameDate() {
		Auction myAuction = new Auction(myDate, myName);
		Boolean isCurrent = myAuction.checkCurrent(myDate);
		
		assertFalse(isCurrent);
	}
	
	@Test
	public void testCheckCurrentBeforeDate() {
		Date testDate = new Date();
		testDate.setDate(25);
		
		Auction myAuction = new Auction(myDate, myName);
		Boolean isCurrent = myAuction.checkCurrent(testDate);
		
		assertTrue(isCurrent);
	}
}

