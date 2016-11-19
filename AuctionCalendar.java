import java.util.*;
/**
 * This is a representation of a calendar for upcoming auctions.
 * 
 * @author Cody Arnold 
 * @version 11/17/16
 */
public class AuctionCalendar implements java.io.Serializable {	
	
	private AuctionDate theDate;
	private int theAuctionCount;
	private Collection<Auction> myAuctions;
	
	/**
	 * Constructs and AuctionCalendar with a starting date.
	 * @param startingDate the starting date of the calendar.
	 */
	public AuctionCalendar(AuctionDate startingDate) {
	    theDate = startingDate;
        myAuctions = new ArrayList<Auction>();
        theAuctionCount = 0;
	}
   
   /**
    * Adds an auction to the calendar.
    * @param theAuction is the auction being added.
    * @return true if successfully added.
    */
   public boolean addAuction(Auction theAuction) {
      return true;
   }
   
   /**
    * Returns a collection of all auctions in the calendar.
    * @return a collection of auctions.
    */
   public Collection<Auction> getAuctions() {
      return myAuctions;
   }
   
   
  // public Collection<Auction> getAuctionsOnDate(AuctionDate date) {
    //  return myAuctions;
   //}
   
   
}
