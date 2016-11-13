/**
 * This is the bidder class that represents what
 * a bidder can do. The unique features are that they 
 * can place and remove bids on items as well as view 
 * whats in auctions.  
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */
 
import java.util.Map;
 
public class Bidder implements java.io.Serializable extends User
{
	
	private String myName;
	
	private String myUsername;
	
	private String myPassword;
	
	private String myEmail;
	
	private int myPhoneNumber;
    
	private Map<Item, float> myBids
    
    /**
     * Constructor for Bidder
     */
	 
    public Bidder(String theName, String theUsername, String thePassword, String theEmail, int thePhoneNumber)
    {
		myName = theName;
        myUsername = theUsername;
        myEmail = thePassword;
        myPhoneNumber = thePhoneNumber;
		myBids = new Map<Item, float>();
    }
	
	public boolean placeBid(Item theItem, float theBid) {
		result = false;
		if (theItem.addBid(theBid)) {
			myBids.add(theItem, theBid);
			result = true;
		}
		return result;
	}
	
	public boolean removeBid(Item theItem) {
		result = false;
		if (myBids.remove(theItem)) {
			result = true;
		}
		return result;
	}
	
	public Map<Item, float> viewBids() {
		return myBids;
	}

}