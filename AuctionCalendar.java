import java.util.*;
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
import java.util.Random;
/**
 * This is a representation of a calendar for upcoming auctions.
 * 
 * @author Cody Arnold 
 * @version 11/17/16
 */
public class AuctionCalendar implements java.io.Serializable {	
	
	private AuctionDate theDate;
	private int theAuctionCount;
	private Map<String, Auction> myAuctions;
//	private Map<AuctionDate, Auction> myAuctionsDate;
//	private Collection<Auction> myAuctions;
//	private Collection<Auction> myPastAuctions;
	private int maxAuctions = 24;
	
	/**
	 * Constructs and AuctionCalendar with a starting date.
	 * @param startingDate the starting date of the calendar.
	 */
	public AuctionCalendar(AuctionDate startingDate) {
	    theDate = startingDate;
	    myAuctions = new HashMap<String, Auction>();
       // myAuctions = new ArrayList<Auction>();
 //       myPastAuctions = new ArrayList<Auction>();
        Load("Auctions.ser");
//        movePastAuctions();
     //  int dates[] = startingDate.getNextXDays(31);
        theAuctionCount = myAuctions.size();
	}
	
//	private void movePastAuctions(){
//		AuctionDate today = new AuctionDate();
//		int todaysDate = today.getYear()+today.getMonth()+today.getDay();
//		int auctionDate;
//		Auction temp;
//		Iterator<Auction> itr = myAuctions.iterator();
//		while(itr.hasNext()){
//			temp = (Auction) itr.next();
//        	auctionDate =  temp.getDate().getYear()+temp.getDate().getMonth()+temp.getDate().getDay();
//        	if(auctionDate < todaysDate) myAuctions.remove(temp);
//        }
//	}
	
	public int createAndAddAuction(AuctionDate theDate, String theAuctionName, String theOrgName,
    		String theContactPerson, String theDescription, String theComment){
		
		Auction temp = new Auction(theDate, theAuctionName, theOrgName,
    		theContactPerson, theDescription, theComment);
		return addAuction(temp);
		
	}
	
	
   /**
    * Adds an auction to the calendar.
    * @param theAuction is the auction being added.
    * @return 0 if successfully added.
    * @return 1 if the Auction was not added due to the max number of auctions already exist
   	* @return 2 if the Auction already exists
   	* @return 3 if the Max number of auctions is already met
    */
   public int addAuction(Auction theAuction) {
	   if (theAuctionCount >= maxAuctions) return 1;
	   if (myAuctions.containsKey(theAuction.getOrg())) return 2;
	   if (theAuctionCount >= maxAuctions) return 3;
	   myAuctions.put(theAuction.getOrg(), theAuction);
	   theAuctionCount++;
	   return 0;
   }
   /**
    * @return 0 if the Auction is successfully removed
    * @return 1 if the Auction did not exist
    * @retrun 2 if the date of deletion is within 2 days of the Auction date
    */
   public int removeAuction(Auction theAuction){
	   AuctionDate today = new AuctionDate();
	   if(!myAuctions.containsKey(theAuction.getOrg())) return 1;
	   if(!theAuction.getDate().isTwoOrMoreDaysBefore(today)) return 2;
	   myAuctions.remove(theAuction);
	   theAuctionCount++;
	   return 0;
   }
   
   /**
    * Returns a collection of all auctions in the calendar.
    * @return a collection of auctions.
    */
   public List<Auction> getAuctions() {
	   List<Auction> auctionList = new ArrayList<Auction>();
	   Set<String> temp = myAuctions.keySet();
	   Iterator<String> itr = temp.iterator();
	   String tempKey;
	   Auction buffer;
      while(itr.hasNext()){
    	  tempKey = itr.next();
    	  buffer = myAuctions.get(tempKey);
    	  if(buffer.getDate().isSameOrAfterDate(theDate)){
    		  auctionList.add(buffer);
    	  }
      }
      
      return auctionList;
    }
   
   public Auction getAuction(String OrgName){
	   return myAuctions.get(OrgName);
   }
   
   /**
	 * @return true if the New Maximum was set
	 * @return false if the newMax is less than 1
	 */
	public boolean setMaxAuctions(int theNewMax){
		if (theNewMax<1) return false;
		maxAuctions = theNewMax;
		return true;
	}
	
	public int getMaxAuctions(){
		return maxAuctions;
	}
	
   
   /**
    * This function will package up the user and auction hashes into .ser
    * files. Call this whenever you want to save all changes. Will return
    * a boolean based on if the update worked (true) or if there was a
    * problem (false).
    * 
    * @return A true/false value based on if the files saved correctly
    */
   public boolean Update(String auctionFileName)
   {
       boolean didItWork;
       didItWork = (dumpAuctionsToFile(auctionFileName));
       return didItWork;
   }
   
   public boolean Load(String auctionFileName)
   {
       boolean didItWork;
       didItWork = (loadAuctionsFromFile(auctionFileName));
       return didItWork;
   }
   
   private boolean dumpAuctionsToFile(String auctionFileName)
   {
       boolean didItWork = false;
       try
     {
        FileOutputStream fileOut = new FileOutputStream(auctionFileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(myAuctions);
        out.writeInt(maxAuctions);
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
   
   private boolean loadAuctionsFromFile(String auctionFileName)
   {
       try
     {
        FileInputStream fileIn = new FileInputStream(auctionFileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        myAuctions = (Map<String, Auction>) in.readObject();
        maxAuctions = in.readInt();
        in.close();
        fileIn.close();
     }catch(IOException i)
     {
        //i.printStackTrace();
        return false;
         //Update(auctionFileName);
     }catch(ClassNotFoundException c)
     {
        System.out.println("Auction class not found");
        c.printStackTrace();
        return false;
     }
       return true;
   }
   
   
  // public Collection<Auction> getAuctionsOnDate(AuctionDate date) {
    //  return myAuctions;
   //}
   
   
}
