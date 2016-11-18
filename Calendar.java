import java.util.*;
/**
 * This is a representation of a calendar for upcoming auctions.
 * 
 * @author Cody Arnold 
 * @version 11/17/16
 */
public class Calendar implements java.io.Serializable 
{	Date theDate;
	int theAuctionCount;
	Collection<Auction> myAuctions;
	
	public Calendar(Date startingDate){
		theDate = startingDate;
      myAuctions = new ArrayList<Auction>();
      theAuctionCount = 0;
	}
   
   
   public void addAuction(Auction theAuction) {
      
   }
   
   public Collection<Auction> getAuctions() {
      return myAuctions;
   }
   
   
  // public Collection<Auction> getAuctionsOnDate(AuctionDate date) {
    //  return myAuctions;
   //}
   
   
}
