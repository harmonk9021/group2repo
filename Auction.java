/**
 * TODO Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
	List<Item> myItems;
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
//		Scanner input = new Scanner(System.in);
 
    }
    
    public void setup() {
    	
    }
    
    public Boolean addItem(Item theItem) {
    	Boolean itemExists;
    	itemExists = checkForDuplicate(theItem);
    	if (!itemExists) {
    		myItems.add(theItem);
    		itemCount = myItems.size();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public Boolean removeItem(Item theItem) {
        	Boolean itemRemoved = false;
        	int i;
        	
        	for (i = 0; i < myItems.size(); i++) {
        		if (myItems.get(i).getName().equals(theItem.getName())) {
        			myItems.remove(i);
        			itemRemoved = true;
        		}
        	}
        	
        	return itemRemoved;
    }
    
    public Boolean checkForDuplicate(Item theItem) {
    	Boolean itemExists = false;
    	int i;
    	
    	for (i = 0; i < myItems.size(); i++) {
    		if (myItems.get(i).getName().equals(theItem.getName())) {
    			itemExists = true;
    		}
    	}
    	
    	return itemExists;
    }
    
    public List<Item> getAuctions() {
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
    
    public void setOrg() {
    	myOrg = input.nextLine();
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


