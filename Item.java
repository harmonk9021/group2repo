import java.io.*;
import java.util.*;
import java.lang.Float;

/**	This class Holds all of the information about an Item that will be Auctioned for an auction.
 * 
 * @author Katie Harmon
 * @version 11-20-2016
 */
public class Item implements Serializable {
	private static final long serialVersionUID = -5193649884823674863L;
	private String name;
	private String donorName;
	private HashMap<String, Float> myBids;
	private String description;
	private int quantity;
	private float startingBid;
	private String condition;
	private String size;
	private String comments;
	
	/** Constructor for the Item class */
	public Item(String theName){
		myBids = new HashMap<String, Float>();
		name = theName;
		donorName = "";
		description = "";
		quantity = 0;
		startingBid = 0;
		condition = "";
		size = "";
		comments = "";
		
	}
	
	public Item(String theName, String theDonorName, String theDescription,
				int theQuantity, float theStartingBid, String theCondition,
				String theSize, String theComments){
		myBids = new HashMap<String, Float>();
		name = theName;
		donorName = theDonorName;
		description = theDescription;
		quantity = theQuantity;
		startingBid = theStartingBid;
		condition = theCondition;
		size = theSize;
		comments = theComments;
	}
	
	/** This function adds a new bid from bidder to myBids if
	 * 	the bidder has not made a bid before and if the bid is 
	 *  greater than startingBid.
	 * 	@returns true if the bid entered is added to myBids*/
	public boolean addBid(float bidAmount, String bidderName){
		if(myBids.containsKey(bidderName)) return false;
		else if(bidAmount < startingBid) return false;
		myBids.put(bidderName, bidAmount);
		return true;
	}
	
	/** @returns -1 if bidderName is not found in myBids,
	 * otherwise returns The bid amount that bidderName has placed */
	public float getBid(String bidderName){
		if(!myBids.containsKey(bidderName)) return -1;
		return myBids.get(bidderName);
	}
	
	/** Displays relevant information from this Item */
	public void displayItem(){
		ItemUI.displayItemUI(this);
	}
	
	/**@return true if the bid was successfully removed
	 * @return false if the name was not found in the bid list
	 */
	public boolean deleteBid(String bidderName){
		if(myBids.containsKey(bidderName)){
			myBids.remove(bidderName);
			return true;
		}
		else return false;
	}
	
	/** Setters */
	public void setName(String theName){
		name = theName;
	}
	public void setDonorName(String theDonorName){
		donorName = theDonorName;
	}
	public void setDescription(String theDescription){
		description = theDescription;
	}
	public boolean setQuantity(int theQuantity){
		if(isEqualOrBelowZero((float)theQuantity)) return false;
		quantity = theQuantity;
		return true;
	}
	public boolean setStartingBid(float theStartingBid){
		if(isEqualOrBelowZero(theStartingBid)) return false;
		startingBid = theStartingBid;
		return true;
	}
	public boolean setCondition(String theCondition){
		if (isEmptyString(theCondition)) return false;
		condition = theCondition;
		return true;
	}
	public boolean setSize(String theSize){
		if(!isValidSize(theSize)) return false;
		size = theSize;
		return true;
	}
	public void setComments(String theComments){
		comments = theComments;
	}
	
	
	/** Getters */
	public String getName(){
		return name;
	}
	public String getDonorName(){
		return donorName;
	}
	public String getDescription(){
		return description;
	}
	public int getQuantity(){
		return quantity;
	}
	public float getStartingBid(){
		return startingBid;
	}
	public String getCondition(){
		return condition;
	}
	public String getSize(){
		return size;
	}
	public String getComments(){
		return comments;
	}
	
	/** Helper for setQuantity and setStartingBid */
	private boolean isEqualOrBelowZero(float value){
		if(value <= 0) return true; 
		else return false;
	}
	
	/** helper for SetSize */
	private boolean isValidSize(String theSize){
theSize.toLowerCase();
		if(theSize.equals("small") || theSize.equals("medium") || theSize.equals("large")) return true;
		else return false;
	}
	
	/** helper for setCondition */
	private boolean isEmptyString(String theInput) {
		String blankString = "";
		if (theInput.equals(blankString)) return true;
		else return false;
	}
	
}

