/**
 * This is the bidder class that represents what
 * a bidder can do. The unique features are that they 
 * can place and remove bids on items as well as view 
 * whats in auctions.  
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */
 
import java.util.HashMap;
import java.util.Map;
 
public class Bidder extends User implements java.io.Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6430847179779764843L;

	private String myName;
	
	private String myUsername;
	
	private String myPassword;
	
	private String myEmail;
	
	private String myPhoneNumber;
    
	private Map<Item, Float> myBids;
    
    /**
     * Constructor for Bidder
     */
	 
    public Bidder(String theName, String theUsername, String thePassword, String theEmail, String thePhoneNumber)
    {
		myName = theName;
        myUsername = theUsername;
        myEmail = thePassword;
        myPhoneNumber = thePhoneNumber;
		myBids = new HashMap<Item, Float>();
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