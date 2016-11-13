/**
 * TODO Write a description of class Item here.
 * 
 * @author Kyle Phan
 * @version 11/11/16
 */


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
    
    /**
     * Constructor for objects of class MyClass
     */
    public Auction(Date theDate, String theName)
    {
    	aucDate = theDate;
		auctionName = theName;
		itemCount = 0;
		myOrg = "";
		myContactPerson = "";
		myDescription = "";
		myComment = "";
		
//		Scanner input = new Scanner(System.in);
 
    }
    
    public void setup() {
    	
    }
    
    public Boolean addItem() {
    	Boolean itemExists;
    	String theName = AuctionUI.getUserItemNameInput();
    	itemExists = checkForDuplicate(theName);
    	if (!itemExists) {
    		Item theItem = new Item(theName);
    		
    		myItems.add(theItem);
    		itemCount = myItems.size();
    		return true;
    	} else {
    		return false;
    	}
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
    	
    	for (i = 0; i < myItems.size(); i++) {
    		if (myItems.get(i).getName().equals(theName)) {
    			itemExists = true;
    		}
    	}
    	
    	return itemExists;
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
    
    public void setOrg(String theInput) {
    	myOrg = theInput;
    }
    
    public String getContactPerson() {
    	return myContactPerson;
    }
    
    public void setContactPerson() {
    	myContactPerson = input.nextLine();
    }
    
    public String getDescription() {
    	return myDescription;
    }
    
    public void setDescription() {
    	myDescription = input.nextLine();
    }
    
    public String getComment() {
    	return myComment;
    }
    
    public void setComment() {
    	myComment = input.nextLine();
    }
    
}


