
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
 * @version 11.11.2016.002A
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