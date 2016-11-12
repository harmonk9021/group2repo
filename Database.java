
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;



/**
 * This class is intended for data management, serving as a central repository
 * for the various lists and maps that Auction, User, and Item will create. The
 * class will also handle reading and writing said lists and maps to serialized
 * files.
 * 
 * @author Jacob Ackerman
 * @version 11.12.2016.003A
 */
public class Database implements java.io.Serializable 
{
    
    private Map<Date, Auction> myAuctionList;
    private Map<String, User> myUserList;
    private Date myDate;
    private static final int STANDARD_YEAR = 365;
    private static final int STANDARD_MONTH = 30;
    private static final int STANDARD_WEEK = 7;
    
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
        boolean canAdd = false;
        
        canAdd = (
        checkForExistingAuction(theAuction) &&
        checkForAuctionFromYearAgo(theAuction) &&
        checkForTooManyAuctionsOnDay(theAuction) &&
        checkUpcomingAuctionCount()
           );
        
        if (canAdd)
        {
            myAuctionList.put(theAuction.getDate(), theAuction); 
        }
        
        return canAdd;
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
        return new ArrayList<>(myAuctionList.values());
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
        return new ArrayList<>(myUserList.values());
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
        boolean didItWork;
        didItWork = (dumpAuctionsToFile() && dumpUsersToFile());
        return didItWork;
    }
    
    public boolean Load(String auctionFileName, String userFileName)
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
    
    private boolean loadAuctionsFromFile()
    {
        try
      {
         FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         e = (Auction) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
      {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
    }
    
    private boolean checkForAuctionFromYearAgo(Auction theAuction)
    {
        ArrayList<Auction> searchDB = new ArrayList<>(myAuctionList.values());
        int i = 0;
        Calendar yearAgo = Calendar.getInstance();
        yearAgo.add(Calendar.DATE, -1 * STANDARD_YEAR);
        while (i <= searchDB.size() && searchDB.get(i).getDate().after(yearAgo.getTime()))
        {
            if (searchDB.get(i).getOrg().equals(theAuction.getOrg()))
            {
                return false;
            }
            i++;
        }
        return true;
    }
    
    private boolean checkForExistingAuction(Auction theAuction)
    {
        ArrayList<Auction> searchDB = new ArrayList<>(myAuctionList.values());
        int i = 0;
        while (i <= searchDB.size() && searchDB.get(i).getDate().after(myDate))
        {
            if (searchDB.get(i).getOrg().equals(theAuction.getOrg()))
            {
                return false;
            }
            i++;
        }
        return true;
    }
    
    private boolean checkForTooManyAuctionsOnDay(Auction theAuction)
    {
        
    }
    
    private boolean checkUpcomingAuctionCount()
    {
        ArrayList<Auction> searchDB = new ArrayList<>(myAuctionList.values());
        int auctionCount = 0;
        Calendar oneMonth = Calendar.getInstance();
        oneMonth.add(Calendar.DATE, STANDARD_MONTH);
        for (int i = 0; i < searchDB.size(); i++)
        {
            if (searchDB.get(i).getDate().after(myDate))
            {
                auctionCount++;
            }
        }
        if (auctionCount >= 25)
            return false;
        else
            return true;
    }
    
     
}
