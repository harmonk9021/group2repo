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
	
	/**
     * Constructor for objects of class Nonprofit.
     * @param theName Name of the Nonprofit organization (NPO).
     * @param theUsername The username that the NPO uses to log in.
     * @param thePassword The password that the NPO uses to log in.
     * @param theEmail the email of the NPO.
     * @param thePhoneNumber The phone number to contact the NPO with.
     */
	
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
	
	/**
	 * Submits a new auction for the NPO.
	 * @param theAution the auction that the NPO wants to set up.
	 * @return Returns if the auction request was successful.
	 */
	
	
	public boolean submitAuctionRequest(Auction theAuction)
	{
		boolean result = false;
		if (theAuction != null) {
			currentAuction = theAuction;
			activeAuction = true;
			result = true;
		}
		return result;
	}
	
	
	/**
	 * Adds an item into the auction that the NPO is hosting.
	 * 
	 */
	
	public boolean addItem(String theName, String theDonorName, String theDescription,
			int theQuantity, float theStartingBid, String theCondition,
			String theSize, String theComments) 
	{
		boolean result = false;
		Item item = new Item(theName, theDonorName, theDescription, theQuantity, theStartingBid, 
				theCondition, theSize, theComments);
		if (currentAuction.addItem(item)) {
			result = true;
		}
		return result;
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
	 * Gets the name of the contact person for the NPO
	 * @return Returns the name of the contact person.
	 */
	
	public String getContactPerson() {
		return myContactPerson;
	}
	
	/**
	 * Checks to see if the NPO has an upcoming auction.
	 * @return Returns if the NPO has an upcoming auction.
	 */
	
	public boolean hasCurrentAuction() {
		return activeAuction;
	}	
}
