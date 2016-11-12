/**
 * This is the bidder class that represents what
 * a bidder can do. The unique features are that they 
 * can place and remove bids on items as well as view 
 * whats in auctions.  
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */
public class Bidder implements java.io.Serializable extends User
{
    
	private List<Item> myItems;
	
	private List<float> myBids;
    
    /**
     * Constructor for objects of class MyClass
     */
    public Bidder()
    {
       myItem = new List<Item>();
	   myBids = new List<float>();
    }
	
	public boolean placeBid(Item theItem) {
		result = false;
		return result;
	}
	
	public boolean removeBid(Item theItem) {
		result = false;
		return result;
	}
	
	public List<Float> viewBids() {
		return myBids;
	}

}