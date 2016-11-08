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
    
    /**
     * Constructor for objects of class MyClass
     */
    public Staff()
    {
        // initialise instance variables
 
    }
    
    /**
     * Views all auctions past and future.
     * @Return Returns all of the auctions from database.
     */
    
    public Calender viewAuctions() {
    	return null;
    }
    
    /**
     * Views all auctions past and future.
     * @Return Returns all past auctions from database.
     */
    
    public Calender viewPastAuctions() {
    	return null;
    }
    
    /**
     * Views all auctions past and future.
     * @Return Returns all upcoming auctions from database.
     */
    
    public Calender viewCurrentAuctions() {
    	return null;
    }

}