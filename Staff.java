import java.util.Date;

/**
 * This is the staff class used to define people that
 * are staff members of Action Central. The unique feature
 * that staff members can do is view all of the auctions from 
 * the database.
 * 
 * @author Andrew Dinh
 * @version 11/7/2016
 */
public class Staff implements java.io.Serializable extends User
{
    
	private String myName;
	
	private String myUsername;
	
	private String myPassword;
	
	private String myEmail;
	
	private String myPhoneNumber;
	
    /**
     * Constructor for objects of class MyClass
     */
    public Staff(String theName, String theUsername, String thePassword, String theEmail, String thePhoneNumber)
    {
        myName = theName;
        myUsername = theUsername;
        myEmail = thePassword;
        myPhoneNumber = thePhoneNumber;
    }
    
    /**
     * Views all auctions past and future.
     * @Return Returns all of the auctions from database.
     */
    
    public Calendar viewAuctions() {
		Calendar calender = new Calendar();
    	return calender;
    }
    
    /**
     * Views all auctions past and future.
     * @Return Returns all past auctions from database.
     */
    
    public Calendar viewPastAuctions(Date theDate) {
		Calendar calender = new Calendar(theDate);
    	return calender;
    }
    
    /**
     * Views all auctions past and future.
     * @Return Returns all upcoming auctions from database.
     */
    
    public Calendar viewCurrentAuctions(Date theDate) {
		Calendar calender = new Calendar(theDate);
    	return calender;
    }

}