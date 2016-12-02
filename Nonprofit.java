import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5388228290817876502L;

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
        myPassword = thePassword;
        myEmail = theEmail;
        myPhoneNumber = thePhoneNumber;
		myContactPerson = "";
		activeAuction = false;
		currentAuction = null;
	}
	
	/**
	 * Submits a new auction for the NPO.
	 * @return 0 if successfully added.
   	 * @return 1 if the Auction was not added due to the max number of auctions already exist
   	* @return 2 if the Auction already exists
   	* @return 3 if the NonProfit Already has an Auction
   	* @return 4 if the NonProfit has had an Auction Within the last year
   	* @return 5 if the Date of the Auction is less than one week away from the day of submition
   	* @return 6 if the date of the auction is farther than one month out from the day of submition
   	* @return 7 if there is already 2 Auctions happening on that date
	 */
	public int submitAuctionRequest(AuctionDate theDate, String theAuctionName, String theOrgName,
    		String theContactPerson, String theDescription, String theComment)
	{
		AuctionDate today = new AuctionDate();
		Auction activeAuction = calendar.getAuction(myUsername);
		
		if(activeAuction != null ) return 3;
		
		Auction temp = calendar.getPastAuction(myUsername);
		if( temp != null){
			if(today.isWithinYear(temp.getDate())) return 4;
		}
		
//		LocalDate oneWeek = today.toLocalDate().plusDays(7);
//		long daysBetween = ChronoUnit.DAYS.between(oneWeek, theDate.toLocalDate());
		long daysBetween = ChronoUnit.DAYS.between(today.toLocalDate(), theDate.toLocalDate());
		if(daysBetween < 7) return 5;
		
//		LocalDate oneMonth = today.toLocalDate().plusDays(31);
//		daysBetween = ChronoUnit.DAYS.between(oneWeek, theDate.toLocalDate());
		daysBetween = ChronoUnit.DAYS.between(today.toLocalDate(), theDate.toLocalDate());
		if(daysBetween > 31) return 6;
		
		List<Auction> allAuctions = calendar.getAuctions();
		LocalDate buffer;
		boolean oneAuction = false;
		for(int i = 0; i < allAuctions.size(); i++){
			buffer = allAuctions.get(i).getDate().toLocalDate(); 
			if(buffer.equals(theDate.toLocalDate())){
				if(oneAuction == true) return 7;
				else oneAuction = true;
			}
		}
//		if(calendar.getAuctionsOnDate(theDate) == 2) return 7;
		return calendar.createAndAddAuction(theDate, theAuctionName, this.myUsername, theContactPerson, theDescription, theComment);
		
		
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
	
	/**
	 * FOR TESTING PURPOSES ONLY
	 * PLEASE DO NOT USE
	 */
	public AuctionCalendar getCalendar(){
		return calendar;
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY
	 * PLEASE DO NOT USE
	 */
	public void setCalendar(AuctionCalendar theCalendar){
		calendar = theCalendar;
	}
}
