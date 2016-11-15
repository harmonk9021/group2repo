import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO Write a description of class Item here.
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */
public class User implements java.io.Serializable 
{
    
	private String myName;
	
	private String myUsername;
	
	private String myPassword;
	
	private String myEmail;
	
	private int myPhoneNumber;
	
	private String type;
	
	private Auction currentAuction;
        
        private Map<Item, Float> myBids;
        
        
    
    /**
     * Constructor for objects of class MyClass
     */
    public User()
    {
        myName = "";
        myUsername = "";
        myEmail = "";
        myPhoneNumber = 0;
        type = "";
        myBids = new HashMap<Item, Float>();
    }
    
    /**
     * Authenticates the user by checking their username and password.
     * @param username The string that represents the username
     * @param password The string that represents the password
     * @return Returns whether the authentication passed or failed
     */
    
    public  boolean authenticate(String username, String password) {
    	boolean result = false;
    	if (myUsername.equals(username) && myPassword.equals(password)) {
    		result = true;
    	}
    	return result;
    }
    
    public void setUserName(String theUsername){
    	myUsername = theUsername;
    }
    
    public String getUserName(){
    	return myUsername;
    }
    
    public void setPassword(String thePassword){
    	myPassword = thePassword;
    }
    
    public void setType(String theType){
    	type = theType;
    }
    
    public String getType(){
    	return type;
    }
    
    public void addItem(Auction currentAuction) 
	{
		boolean result = currentAuction.addItem();
	}
    
    public void submitAuctionRequest(Auction theAuction){
    	Date theDate = new Date();
    	
    	
    }
    
    /***Katie***/
    public void setCurrentAuction(Auction auction){
		currentAuction = auction;
	}
    
    public Auction getCurrentAuction() {
		return currentAuction;
	}
    
    public boolean placeBid(Item theItem, float theBid) {
		boolean result = false;
		if (theItem.addBid(theBid, myUsername)) {
			myBids.put(theItem, theBid);
			result = true;
		}
		return result;
	}
	
	public boolean removeBid(Item theItem) {
		float theBid = myBids.get(theItem);
		boolean result = false;
		if (myBids.remove(theItem, theBid)) {
			result = true;
		}
		return result;
	}
	
	public Map<Item, Float> viewBids() {
		return myBids;
	}
}
