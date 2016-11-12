/**
 * This is the Nonprofit class that defines what a 
 * nonprofit organization may do. The unique features are
 * the ability to submit an auction request and add items to it.
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */
public class Nonprofit implements java.io.Serializable extends User 
{
	
	private String myContactPerson;
	
	private boolean activeAuction; 
	
	private Auction currentAuction;
	
	
	public Nonprofit() 
	{
		myContactPerson = "";
		activeAuction = false;
		currentAuction = null;
	}
	
	public void submitAuctionRequest(Auction theAuction)
	{
		
	}
	
	public void addItem(Item theItem) 
	{
		currentAuction.addItem();
	}
	
	public void fillItem() 
	{
		
	}
	
}