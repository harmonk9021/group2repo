import java.util.*;
/**
 * This is a representation of a calendar for upcoming auctions.
 * 
 * @author Cody Arnold 
 * @version 1
 */
public class Calendar implements java.io.Serializable 
{	Date theDate;
	int AuctionCount;
	Database theDatabase;
	List<Auction> auctions;
	
	public Calendar(Date startingDate){
		theDatabase = new Database();
		theDate = startingDate;
		auctions = theDatabase.getAuctionList();
      auctions.add(new Auction(theDate, "Auction1"));
      Date newDate = new Date();
      newDate.setDate(29);
      auctions.add(new Auction(newDate, "Auction2"));
	}
	
	public void displayCalendar(){
		CalendarUI.displayCalendar(theDate, auctions);
	}
	
	public void viewAnAuction(Date date){
		for (Auction a : auctions) {
         if (a.getDate().getDate() == date.getDate()) {
            System.out.println(a.getName());
         }
      }
	}

}
