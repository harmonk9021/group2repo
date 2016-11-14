
import java.util.Date;
import java.util.List;

/**
 * TODO Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Calendar implements java.io.Serializable 
{	Date theDate;
	int AuctionCount;
	Database theDatabase;
	List auctions;
	
	public Calendar(Date startingDate){
		theDatabase = new Database();
		theDate = startingDate;
		auctions = (List) theDatabase.getAuctionList();
	}
	
	public void displayCalendar(){
		System.out.println("desplayCalendar TODO");
	}
	
	public void viewAnAuction(Date date){
		System.out.println("chooseAuction TODO");
        }	

}
