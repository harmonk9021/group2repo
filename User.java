/**
 * This is the user class that the Bidder, Nonprofit, and staff
 * classes inherit from. It will be used to to contain all of the 
 * getter and setters that each class shares. 
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */

public class User implements java.io.Serializable 
{
    
	/**
	 * Generated serial ID.
	 */
	
	private static final long serialVersionUID = -4482170880572973916L;

	String myName;
	
	String myUsername;
	
	String myPassword;
	
	String myEmail;
	
	String myPhoneNumber;
    	
	AuctionCalendar calendar;
    /**
     * Constructor for objects of class User.
     */        
    
    public User()
    {
        myName = "";
        myUsername = "";
        myPassword = "";
        myEmail = "";
        myPhoneNumber = "";
	calendar = new AuctionCalendar(new AuctionDate(), "String");
    }
    
    /**
     * Authenticates the user by checking their username and password.
     * @param username The string that represents the username
     * @param password The string that represents the password
     * @return Returns whether the authentication passed or failed
     */
    
    public  boolean authenticate(String username, String password) {
    	boolean result = false;
    	if (myUsername.equals(username) && myPassword.equals(password)) {
    		result = true;
    	}
    	return result;
    }
    
    /**
     * Sets the name of the user.
     * @param theUsername String of the name to use.
     */
    
    public void setName(String theName) {
    	myName = theName;
    }
    
    /**
     * Gets the name of the user.
     * @return Returns the name.
     */
    
    public String getName() {
    	return myName;
    }
    
    /**
     * Sets the username of the user.
     * @param theUsername String of the username to use.
     */
    
    public void setUserName(String theUsername){
    	myUsername = theUsername;
    }
    
    /**
     * Gets the username of the user.
     * @return Returns the username.
     */
    
    public String getUserName(){
    	return myUsername;
    }
    
    /**
     * Sets the password of the user.
     * @param thePassword String of the password.
     */
    
    public void setPassword(String thePassword){
    	myPassword = thePassword;
    }
    
    /**
     * Gets the password of the user.
     * @return Returns the password.
     */
    
    public String getPassword() {
    	return myPassword;
    }
    
    /**
     * Sets the email of the user.
     * @param theUsername String of the email to use.
     */
    
    public void setEmail(String theEmail) {
    	myEmail = theEmail;
    }
    
    /**
     * Gets the email of the user.
     * @return Returns the email.
     */
    
    public String getEmail() {
    	return myEmail;
    }
    
    /**
     * Sets the phone number of the user.
     * @param theUsername String of the phone number to use.
     */
    
    public void setPhoneNumber(String thePhoneNumber) {
    	myPhoneNumber = thePhoneNumber;
    }
    
    /**
     * Gets the phone number of the user.
     * @return Returns the phone number.
     */
    
    public String getPhoneNumber() {
    	return myPhoneNumber;
    }
}
