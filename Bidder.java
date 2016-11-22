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
	 * Generated serial ID.
	 */
	
	private static final long serialVersionUID = -6430847179779764843L;
    
	private Map<Item, Float> myBids;
    
	/**
     * Constructor for objects of class Bidder.
     * @param theName Name of the Bidder.
     * @param theUsername The username that the Bidder uses to log in.
     * @param thePassword The password that the Bidder uses to log in.
     * @param theEmail the email of the Bidder.
     * @param thePhoneNumber The phone number to contact the Bidder with.
     */
	 
    public Bidder(String theName, String theUsername, String thePassword, String theEmail, String thePhoneNumber)
    {
		myName = theName;
        myUsername = theUsername;
        myPassword = thePassword;
        myEmail = thePassword;
        myPhoneNumber = thePhoneNumber;
		myBids = new HashMap<Item, Float>();
    }
    
    /**
     * Places a bid on an item.
     * @param theItem The item to be bid on.
     * @param theBid The amount that the bidder wants to bid for.
     * @return Return if the bid was successful.
     */
	
	public boolean placeBid(Item theItem, float theBid) {
		boolean result = false;
		if (theItem.addBid(theBid, myUsername)) {
			myBids.put(theItem, theBid);
			result = true;
		}
		return result;
	}
	
	/**
	 * Removes a bid on an item.
	 * @param theItem The item that the bidder want to remove their bid from.
	 * @return Returns if the removal was successful.
	 */
	
	public boolean removeBid(Item theItem) {
		float theBid = myBids.get(theItem);
		boolean result = false;
		if (myBids.remove(theItem, theBid)) {
			result = true;
		}
		return result;
	}
	
	/**
	 * Returns a list of the items a bidder has bid on.
	 * @return Returns the items the bidder has bid on and the amount the bid for. 
	 */
	
	public Map<Item, Float> viewBids() {
		return myBids;
	}

}
