
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class is intended for data management, serving as a central repository
 * for the various lists and maps that Auction, User, and Item will create. The
 * class will also handle reading and writing said lists and maps to serialized
 * files.
 * 
 * @author Jacob Ackerman
 * @version 11.11.2016.001A
 */
public class Database implements java.io.Serializable 
{
    
    private Map<String, Auction> myAuctionList;
    private Map<String, User> myUserList;
    private Date myDate;
    
    /**
     * Constructor for objects of class MyClass
     */
    public Database()
    {
        // initialise instance variables
        myAuctionList = new HashMap();
        myUserList = new HashMap();
        myDate = new Date();
    }
    
    public void addAuctionToDB(Auction theAuction)
    {
        myAuctionList.put(theAuction.getName(), theAuction); // placeholder based on assumed getter name
    }
    
    public Auction getAuction(String key)
    {
        return myAuctionList.get(key);
    }
    
    public List<Auction> getAuctionList()
    {
        return null;
    }
    
    public void addUserToDB(User theUser)
    {
        
    }
    
    public User getUser(String key)
    {
        return myUserList.get(key);
    }
    
    public List<User> getUserList()
    {
        return null;
    }
}