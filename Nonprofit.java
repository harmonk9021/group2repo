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
	
	public boolean submitAuctionRequest(Auction theAuction)
	{
		boolean result = false;
		Auction auction = new Auction(theAuction);
		if (auction != null) {
			currentAuction = auction;
			activeAuction = true;
			result = true;
	}
	
	public void addItem(Item theItem) 
	{
		currentAuction.addItem(theItem);
	}
	
	public void fillItem() 
	{
		// Is this necesary?
	}
	
	/**
	 * Sets the name of the contact person.
	 * 
	 * @param theName The name being passed in.
	 */
	 
	public void setContactPerson(String theName) {
		myContactPerson = theName;
	}
	
	/**
	 * Gets the name of the contact person.
	 * 
	 * @return myContactPerson The name of the contact person.
	 */
	
	public String getContactPerson() {
		return myContactPerson;
	}
	
}