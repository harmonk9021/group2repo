import java.io.*;
import java.util.*;
import java.lang.Float;


public class Item extends ItemUI implements Serializable {
	private String name;
	private String donorName;
	private HashMap<String, Float> myBids;
	private String description;
	private int quantity;
	private float startingBid;
	private String condition;
	private String size;
	private String comments;
	
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
	
	
	public boolean addBid(float bidAmount, String bidderName){
		if(myBids.containsKey(bidderName)) return false;
		else if(bidAmount < startingBid) return false;
		myBids.put(bidderName, bidAmount);
		return true;
	}
	
	public float getBid(String bidderName){
		return myBids.get(bidderName);
	}
	public void displayItem(){
		displayItemUI(this);
	}
	
	
	public boolean setName(String theName){
		if (isEmptyString(theName)) return false;
		name = theName;
		return true;
		
	}
	public void setDonorName(String theDonorName){
		donorName = theDonorName;
	}
	public void setDescription(String theDescription){
		description = theDescription;
	}
	public boolean setQuantity(int theQuantity){
		if(isEqualOrBelowZero(theQuantity)) return false;
		quantity = theQuantity;
		return true;
	}
	public boolean setStartingBid(float theStartingBid){
		if(isEqualOrBelowZero(theStartingBid)) return false;
		startingBid = theStartingBid;
		return true;
	}
	public boolean setCondition(String theCondition){
		if (theCondition.isEmpty()) return false;
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
	
	private boolean isEmptyString(String theInput) {
		String blankString = "";
		if (theInput.equals(blankString)) return true;
		else return false;
	}
	
	private boolean isEqualOrBelowZero(float value){
		if(value <= 0) return true; 
		else return false;
	}
	
	private boolean isValidSize(String theSize){
		theSize.toLowerCase();
		if(theSize.equals("small") || theSize.equals("medium") || theSize.equals("large")) return true;
		else return false;
	}
	
}

