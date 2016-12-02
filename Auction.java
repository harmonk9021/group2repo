import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * This class is used to add, remove and store Item objects currently 
 * available for bidding in this auction. Also stores all the relevant 
 * information regarding this specific auction.
 * 
 * @author Kyle Phan
 * @version 11/20/16
 */
public class Auction implements java.io.Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2835612177821340774L;
	
	
	public  transient Scanner input = new Scanner(System.in);	// Probably unecessary moving into GUI stage
	private AuctionDate aucDate;
	private List<Item> myItems;
	private String auctionName;
	private String myComment;
	private String myContactPerson;
	private String myDescription;
	private String myOrg;
	private int itemCount;
	private Boolean isCurrent;
    
    /**
     * Constructor for Auction objects, initializes an Auction with
     * a date and the name of the Nonprofit organization.
     * 
     * @param theDate is the AuctionDate object that the auction is scheduled for.
     * @param theOrgName is the String containing the Nonprofit organization's name.
     */
    public Auction(AuctionDate theDate, String theOrgName)
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
    
      public Auction(AuctionDate theDate, String theAuctionName, String theOrgName,
    		String theContactPerson, String theDescription, String theComment)
    {
    	aucDate = theDate;
    	myItems = new ArrayList<Item>();
		auctionName = theAuctionName;//theName;
		itemCount = 0;
		myOrg = theOrgName;
		myContactPerson = theContactPerson;
		myDescription = theDescription;
		myComment = theComment;
		isCurrent = true;
		
//		Scanner input = new Scanner(System.in);
 
    }
    
    /**
     * Checks if the Item object passed in already exists in this
     * Auction's stored list of items, if not it appends the Item
     * object to the Auction ArrayList and returns a boolean if
     * the item was added or not.
     * 
     * @param theItem is an Item object constructed in NonProfit class.
     * @return is true/false if item succeeds or fails to add to the list.
     */ 
    public Boolean addItem(Item theItem) {
    	Boolean itemExists;
    	itemExists = compareItemNames(theItem.getName());
    	if (!itemExists) {
    		myItems.add(theItem);
    		itemCount = myItems.size();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * @returns 0 if Item was successfully removed
     * @returns 1 if Item was not removed due to being within two days before auction date 
     * @returns 2 if Item was not removed because it did not exist
     */
    public int removeItem(Item theItem){
    	AuctionDate today = new AuctionDate();
    	if (!myItems.contains(theItem)) return 2;
    	if (aucDate.isWithin2Days(today)) return 1;
    	myItems.remove(theItem);
    	return 0;
    }
    
    /**
     * @return 0 if the bid was successfully removed
     * @return 1 if the bid was not removed due to being within two days before auction date 
     * @return 2 if the bid was not removed because it did not exist
     * @return 3 if the item does not exist
     */
    public int removeBid(Item theItem, String theName){
    	AuctionDate today = new AuctionDate();
    	if (!myItems.contains(theItem)) return 3;
    	if (aucDate.isWithin2Days(today)) return 1;
    	boolean responce = theItem.deleteBid(theName);
    	if (responce == false) return 2;
    	return 0;
    	
    }
        
    /**
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<< NOTE >>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * 
     * SHOULD BE REMOVED!
     * 
     * This method is used to set the information fields in
     * an Item object to be added to the list of stored Items
     * in this Auction class.
     * 
     * Requires input on specified fields.
     * 
     * @param theItem is the Item object with empty fields.
     */
    
//   private void setItemParams(Item theItem){
//	   boolean temp;
//	   
//    	theItem.setDonorName(ItemUI.getUserItemDonorNameInput());
//    	theItem.setDescription(ItemUI.getUserItemDescriptionInput());
//    	
//    	do{
//    		temp = theItem.setQuantity(ItemUI.getUserItemQuantityInput());
//    		if(temp == false) ItemUI.isEqualOrBelowZeroErrorIU("Quantity");
//    	} while (temp == false);
//    	
//    	do{
//    		temp = theItem.setStartingBid(ItemUI.getUserItemStartingBidInput());
//    		if(temp == false) ItemUI.isEqualOrBelowZeroErrorIU("Starting Bid");
//    	} while (temp == false);
//    	
//    	theItem.setCondition(ItemUI.getUserItemConditionInput());
//    	
//    	do{
//    		temp = theItem.setSize(ItemUI.getUserItemSizeInput());
//    		if(temp == false) ItemUI.isValidSizeErrorUI();
//    	} while (temp == false);
//    	
//    	theItem.setComments(ItemUI.getUserItemCommentsInput());
//    	
//    }
    
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
    
   /**
    * Checks for duplicate items by comparing the input Item's name
    * to existing Item names in the ArrayList.
    * 
    * @param theName is the String Item Name from the Item object
    * @return is a boolean representing if the Item Name was found in the stored Item ArrayList
    */
   private Boolean compareItemNames(String theItemName) {
    	Boolean itemExists = false;
    	theItemName = theItemName.toLowerCase();
    	int i;
    	if (itemCount == 0) {
    		
    		return itemExists;
    	} else {
    		for (i = 0; i < myItems.size(); i++) {
    			String currentItemName = myItems.get(i).getName().toLowerCase();
    			if (theItemName.equals(currentItemName)) {
    				itemExists = true;
    			}
    		}
    		
    		return itemExists;
    	}
    }
        
    /**
     * Getter for the stored Item ArrayList.
     * 
     * @return is the ArrayList containing all of the Items
     */
    public List<Item> getItems() {
    	return myItems;
    }
    
    
    
    /**
     * Getter for the date this Auction was scheduled for.
     * 
     * @return the auctionDate object that represents this Auction's scheduled date
     */
    public AuctionDate getDate() {
    	return aucDate;
    }
    
    /**
     * setter for the date this Auction was scheduled for.
     * 
     * @param theDate is the new date of the Auction
     */
    public void setDate(AuctionDate theDate) {
    	aucDate = theDate;
    }
    
    /**
     * Getter for the name of the Nonprofit/Auction name.
     * 
     * @return is the String containing the name of the Nonprofit/Auction name.
     */   
    public String getOrg() {
    	return myOrg;
    }
    
    /**
     * Setter for the name of the Nonprofit/Auction name.
     * 
     * @param theInput is a String that will contain the Nonprofit's name.
     */
    public void setOrg(String theInput) {
    	myOrg = theInput;
    }
    
    /**
     * Getter for the name of the Auction's contact person.
     * 
     * @return is the String containing the contact person's name.
     */
    public String getContactPerson() {
    	return myContactPerson;
    }
    
    /**
     * Setter for the name of the Auctionn's contact person.
     * 
     * @param theInput is a String that will contain the contact person's name.
     */
    public void setContactPerson(String theInput) {
    	myContactPerson = theInput;
    }
    
    /**
     * Getter for the Auction's description.
     * 
     * @return is a String that contains the Auction's description
     */
    public String getDescription() {
    	return myDescription;
    }
    
    /**
     * Setter for the Auction's description.
     * 
     * @param theInput is the String that contains the Auction's description
     */
    public void setDescription(String theInput) {
    	myDescription = theInput;
    }
    
    /**
     * Getter for the Auction's extra comments.
     * 
     * @return is the String that contains any extra commentss about the Auction.
     */
    public String getComment() {
    	return myComment;
    }
    
    /**
     * Setter for the Auction's extra comments.
     * 
     * @param theInput is the String that contains any comments about the Auction.
     */
    public void setComment(String theInput) {
    	myComment = theInput;
    }
    
    /**
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<< NOTE >>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * <<<<<<<<<<<<<<<<<<<<------>>>>>>>>>>>>>>>>>>>>
     * 
     * 
     * Compares this Auction's scheduled date with another date.
     * If same date or before then this auction has now past
     * 
     * @param theDate is the Date object being compared to this Auction's Date.
     * @return is true if this Auction's date is after the date passed in, false
     * if the date is the same or before the date passed in.
     */
//    public Boolean checkCurrent(Date theDate) {
//    	if (aucDate.equals(theDate)) {
//    		isCurrent = false;
//    	} else if (aucDate.before(theDate)) {
//    		isCurrent = false;
//    	}
//    	
//    	return isCurrent;
//    	
//    }
    
}
