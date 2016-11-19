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
	
	public AuctionCalendar(AuctionDate startingDate) {
	    theDate = startingDate;
        myAuctions = new ArrayList<Auction>();
        theAuctionCount = 0;
	}
   
   
   public boolean addAuction(Auction theAuction) {
      return true;
   }
   
   public Collection<Auction> getAuctions() {
      return myAuctions;
   }
   
   
  // public Collection<Auction> getAuctionsOnDate(AuctionDate date) {
    //  return myAuctions;
   //}
   
   
}
