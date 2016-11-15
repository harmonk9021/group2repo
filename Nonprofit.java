/**
 * This is the Nonprofit class that defines what a 
 * nonprofit organization may do. The unique features are
 * the ability to submit an auction request and add items to it.
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */
public class Nonprofit extends User implements java.io.Serializable
{
	
	private String myName;
	
	private String myUsername;
	
	private String myPassword;
	
	private String myEmail;
	
	private String myPhoneNumber;
	
	private String myContactPerson;
	
	private boolean activeAuction; 
	
	private Auction currentAuction;
	
	
	public Nonprofit(String theName, String theUsername, String thePassword, String theEmail, String thePhoneNumber) 
	{
		myName = theName;
        myUsername = theUsername;
        myEmail = thePassword;
        myPhoneNumber = thePhoneNumber;
		myContactPerson = "";
		activeAuction = false;
		currentAuction = null;
	}
	/*
	public boolean submitAuctionRequest(Auction theAuction)
	{
		boolean result = false;
		if (theAuction) {
			currentAuction = auction;
			activeAuction = true;
			result = true;
		}
	}
	*/
	public void addItem(Item theItem) 
	{
		currentAuction.addItem();
	}
	
	public void fillItem() 
	{
		
	}
	
	/**
	 * Sets the name of the contact person.
	 * 
	 * @param theName The name being passed in.
	 */
	 
	public void setContactPerson(String theName) {
		myContactPerson = theName;
	}

	public String getOrgName() {
		return myName;
	}
	public boolean hasCurrentAuction() {
		return activeAuction;
	}
	public String getContactPerson() {
		return myContactPerson;
	}
	
}
