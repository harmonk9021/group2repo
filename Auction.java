/**
 * This auction class is used to store information about
 * a non profit's auction as well as store and access all 
 * the items that are available in the auction.
 * 
 * @author Kyle Phan
 * @version 11/13/16
 */


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Auction implements java.io.Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2835612177821340774L;
	
	
	public Scanner input = new Scanner(System.in);
	public Date aucDate;
	private List<Item> myItems;
	public String auctionName;
	public String myOrg;
	public String myContactPerson;
	public String myDescription;
	public String myComment;
	public int itemCount;
	public Boolean isCurrent;
    
    /**
     * Constructor for objects of class MyClass
     */
    public Auction(Date theDate, String theName)
    {
    	aucDate = theDate;
    	myItems = new ArrayList<Item>();
		auctionName = theName;
		itemCount = 0;
		myOrg = "";
		myContactPerson = "";
		myDescription = "";
		myComment = "";
		isCurrent = true;
		
//		Scanner input = new Scanner(System.in);
 
    }
    
    public void fillOutInfo() {
    	boolean temp;
    	
    	do{
    		temp = setOrg(AuctionUI.getUserOrgInput());
    		if(temp == false) AuctionUI.isEmptyStringUI("organization name");
    	} while (temp == false);
    	
    	do{
    		temp = setContactPerson(AuctionUI.getUserContactPersonInput());
    		if(temp == false) AuctionUI.isEmptyStringUI("contact person name");
    	} while (temp == false);
    	
    	do{
    		temp = setDescription(AuctionUI.getUserDescriptionInput());
    		if(temp == false) AuctionUI.isEmptyStringUI("auction description");
    	} while (temp == false);
    	
    	
    }
    
    public Boolean addItem() {
    	Boolean itemExists;
    	Boolean temp;
    	String theName;
    	
    	do{
    		theName = ItemUI.getUserItemNameInput();
    		temp = isEmptyString(theName);
    		if(temp == true) ItemUI.isEmptyStringUI("item name");
    	} while (temp == true);
    	
    	
//    	String theName = ItemUI.getUserItemNameInput();
    	itemExists = checkForDuplicate(theName);
    	if (itemExists == false) {
    		Item theItem = new Item(theName);
    		setItemParams(theItem);
    		myItems.add(theItem);
    		itemCount += 1;
    		return true;
    	} else {
    		return false;
    	}
    }
    
   private void setItemParams(Item theItem){
	   boolean temp;
	   
    	theItem.setDonorName(ItemUI.getUserItemDonorNameInput());
    	theItem.setDescription(ItemUI.getUserItemDescriptionInput());
    	
    	do{
    		temp = theItem.setQuantity(ItemUI.getUserItemQuantityInput());
    		if(temp == false) ItemUI.isEqualOrBelowZeroErrorIU("Quantity");
    	} while (temp == false);
    	
    	do{
    		temp = theItem.setStartingBid(ItemUI.getUserItemStartingBidInput());
    		if(temp == false) ItemUI.isEqualOrBelowZeroErrorIU("Starting Bid");
    	} while (temp == false);
    	
    	do{
    		temp = theItem.setCondition(ItemUI.getUserItemConditionInput());
    		if(temp == false) ItemUI.isEmptyStringUI("condition");
    	} while (temp == false);
    	
    	
    	do{
    		temp = theItem.setSize(ItemUI.getUserItemSizeInput());
    		if(temp == false) ItemUI.isValidSizeErrorUI();
    	} while (temp == false);
    	
    	theItem.setComments(ItemUI.getUserItemCommentsInput());
    	
    }
    
//    public Boolean removeItem(String theName) {
//        	Boolean itemRemoved = false;
//        	int i;
//        	
//        	for (i = 0; i < myItems.size(); i++) {
//        		if (myItems.get(i).getName().equals(theName)) {
//        			myItems.remove(i);
//        			itemRemoved = true;
//        		}
//        	}
//        	
//        	return itemRemoved;
//    }
    
    public Boolean checkForDuplicate(String theName) {
    	Boolean itemExists = false;
    	int i;
    	if (itemCount == 0) {
    		
    		return false;
    	} else {
    		for (i = 0; i < myItems.size(); i++) {
    			if (myItems.get(i).getName().equals(theName)) {
    				itemExists = true;
    			}
    		}
    		
    		return itemExists;
    	}
    	
    	
    }
    
    public List<Item> getItems() {
    	return myItems;
    }
    
    public Date getDate() {
    	return aucDate;
    }
    
    public String getName() {
    	return auctionName;
    }
    
    public String getOrg() {
    	return myOrg;
    }
    
    public Boolean setOrg(String theInput) {
    	if (isEmptyString(theInput)) return false;
    	myOrg = theInput;
    	return true;
    }
    
    public String getContactPerson() {
    	return myContactPerson;
    }
    
    public Boolean setContactPerson(String theInput) {
    	if (isEmptyString(theInput)) return false;
    	myContactPerson = theInput;
    	return true;
    }
    
    public String getDescription() {
    	return myDescription;
    }
    
    public Boolean setDescription(String theInput) {
    	if (isEmptyString(theInput)) return false;
    	myDescription = theInput;
    	return true;
    }
    
    public String getComment() {
    	return myComment;
    }
    
    public void setComment(String theInput) {
    	myComment = theInput;
    }
    
    public Boolean checkCurrent(Date theDate) {
    	if (aucDate.equals(theDate)) {
    		isCurrent = false;
    	} else if (aucDate.after(theDate)) {
    		isCurrent = false;
    	} else {
    		isCurrent = true;
    	}
    	
    	return isCurrent;
    	
    }
    
    private boolean isEmptyString(String theInput) {
		String blankString = "";
		if (theInput.equals(blankString)) return true;
		else return false;
	}
    
}

