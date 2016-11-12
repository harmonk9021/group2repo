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
	
	
	public void addBid(float bidAmount, String bidderName){
		if(myBids.containsKey(bidderName)) {
			addBidAlreadyHasBidderErrorUI();
			return;
		}
		myBids.put(bidderName, bidAmount);
	}
	
	public float getBid(String bidderName){
		return myBids.get(bidderName);
	}
	public void displayItem(){
		displayItemUI(this);
	}
	
	
	public void setName(String theName){
		name = theName;
	}
	public void setDonorName(String theDonorName){
		donorName = theDonorName;
	}
	public void setDescription(String theDescription){
		description = theDescription;
	}
	public void setQuantity(int theQuantity){
		quantity = theQuantity;
	}
	public void setStartingBid(float theStartingBid){
	/*	if(isEqualOrBelowZero(theStartingBid)){
			isEqualOrBelowZeroErrorIU("Starting Bid");
			return -1;
		}*/
		startingBid = theStartingBid;
	}
	public void setCondition(String theCondition){
		condition = theCondition;
	}
	public void setSize(String theSize){
		size = theSize;
	}
	public void setComments(String theComments){
		comments = theComments;
	}
	
	
	
	public String getName(){
		return donorName;
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
	public String sgetSize(){
		return size;
	}
	public String getComments(){
		return comments;
	}
	
	
	
	private boolean isEqualOrBelowZero(float value){
		if(value <= 0) return true; 
		else return false;
	}
	
	private boolean isValidSize(String theSize){
		theSize.toLowerCase();
		if(theSize == "small" || theSize == "medium" || theSize == "large") return true;
		else return false;
	}
	
}
