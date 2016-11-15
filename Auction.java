/**
 * TODO Write a description of class Item here.
 * 
 * @author Kyle Phan
 * @version 11/11/16
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
	
	
	public  transient Scanner input = new Scanner(System.in);
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
    public Auction(Date theDate, String theOrgName)
    {
    	aucDate = theDate;
    	myItems = new ArrayList<Item>();
		auctionName = "";//theName;
		itemCount = 0;
		myOrg = theOrgName;
		myContactPerson = "";
		myDescription = "";
		myComment = "";
		isCurrent = true;
		
//		Scanner input = new Scanner(System.in);
 
    }
    
    public void setup() {
    	
    }
    
    public Boolean addItem() {
    	Boolean itemExists;
    	String theName = ItemUI.getUserItemNameInput();
    	itemExists = checkForDuplicate(theName);
    	if (!itemExists) {
    		Item theItem = new Item(theName);
    		setItemParams(theItem);
    		myItems.add(theItem);
    		itemCount = myItems.size();
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
    	
    	theItem.setCondition(ItemUI.getUserItemConditionInput());
    	
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
    		
    		return itemExists;
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
    
    public void setName(String theName){
    	auctionName = theName;
    }
    
    public String getOrg() {
    	return myOrg;
    }
    
    public void setOrg(String theInput) {
    	myOrg = theInput;
    }
    
    public String getContactPerson() {
    	return myContactPerson;
    }
    
    public void setContactPerson(String theInput) {
    	myContactPerson = theInput;
    }
    
    public String getDescription() {
    	return myDescription;
    }
    
    public void setDescription(String theInput) {
    	myDescription = theInput;
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
    	}
    	
    	return isCurrent;
    	
    }
    
}

