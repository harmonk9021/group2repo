/**
 * TODO Write a description of class Item here.
 * 
 * @author Andrew Dinh
 * @version 11/11/2016
 */
 
import java.util.Scanner;
 
public class User implements java.io.Serializable 
{
    
	private String myName;
	
	private String myUsername;
	
	private String myPassword;
	
	private String myEmail;
	
	private String myPhoneNumber;
    
    /**
     * Constructor for User class
     */
	 
    public User(String theName, String theUsername, String thePassword, String theEmail, String thePhoneNumber)
    {
        myName = theName;
        myUsername = theUsername;
        myPassword = thePassword;
		myEmail = theEmail;
        myPhoneNumber = thePhoneNumber;
    }
    
    /**
     * Authenticates the user by checking their username and password.
     * @param username The string that represents the username
     * @param password The string that represents the password
     * @return Returns whether the authentication passed or failed
     */
    
    public boolean authenticate(String password) 
	{
    	boolean result = false;
    	if (myPassword.equals(password)) 
		{
    		result = true;
    	}
    	return result;
    }

}