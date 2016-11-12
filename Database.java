
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
 * @version 11.12.2016.001A
 */
public class Database implements java.io.Serializable 
{
    
    private Map<Date, Auction> myAuctionList;
    private Map<String, User> myUserList;
    private Date myDate;
    
    /**
     * Constructor for Database objects. Initializes an
     * empty database.
     */
    public Database()
    {
        // initialise instance variables
        myAuctionList = new HashMap();
        myUserList = new HashMap();
        myDate = new Date();
        
    }
    
    /**
     * This will add an auction to the database for long term storage, management,
     * and easy passing between other objects. Will reject auctions in the following
     * conditions:
     *  - The non-profit host of the auction has one or more upcoming auctions
     *  - The non-profit host of the auction has had an auction in the past year (less than 365 days)
     *  - The auction submitted is for a day that already has 2 or more auctions hosted
     *  - The number of upcoming auctions is 25 or greater
     *  - The submitted auction is scheduled for more than one month in the future
     *  - The submitted auction is scheduled to take place less than a week from the current date
     * 
     * This function is intended to be used in combination with the submission
     * (creation) of a new auction. Object will be stored in a hash with the
     * auction date as the key.
     * 
     * @param theAuction The auction object to be stored
     * @return Whether the item was added or rejected
     */
    public boolean addAuctionToDB(Auction theAuction)
    {
        
        myAuctionList.put(theAuction.getDate(), theAuction); 
        
        return true;
    }
    
    /**
     * This function will return a single auction from the database hash.
     * Provide the auction's starting date for the function.
     * 
     * @param key The auction date
     * @return The auction object that matches the key
     */
    public Auction getAuction(Date key)
    {
        return myAuctionList.get(key);
    }
    
    /**
     * This function will return the full list of Auctions stored in the database.
     * 
     * @return List containing all the Auctions in the hash
     */
    public List<Auction> getAuctionList()
    {
        return null;
    }
    
    public void addUserToDB(User theUser)
    {
        myUserList.put(theUser.getUsername(), theUser); // placeholder based on assumed getter name
    }
    
    public User getUser(String key)
    {
        return myUserList.get(key);
    }
    
    public List<User> getUserList()
    {
        return null;
    }
    
    /**
     * This function will package up the user and auction hashes into .ser
     * files. Call this whenever you want to save all changes. Will return
     * a boolean based on if the update worked (true) or if there was a
     * problem (false).
     * 
     * @return A true/false value based on if the files saved correctly
     */
    public boolean Update()
    {
        return false;
    }
    
    private boolean dumpAuctionsToFile()
    {
        boolean didItWork = false;
        try
      {
         FileOutputStream fileOut = new FileOutputStream("/data/auctions.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(myAuctionList);
         out.close();
         fileOut.close();
         didItWork = true;
         //System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }
        catch(IOException i)
      {
          //return false;
      }
        return didItWork;
    }
    
    private boolean dumpUsersToFile()
    {
        boolean didItWork = false;
        try
      {
         FileOutputStream fileOut = new FileOutputStream("/data/users.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(myUserList);
         out.close();
         fileOut.close();
         didItWork = true;
         //System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }
        catch(IOException i)
      {
          //return false;
      }
        return didItWork;
    }
}