/**
 * TODO Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.Scanner;

public class AuctionUI 
{
	
	static Scanner scan = new  Scanner(System.in);
	
	public static void displayItemsForBidder(Auction theAuction, String name) {
		
		System.out.println(theAuction.getName() + ", " + theAuction.getDate().toString() + ".");
		System.out.println("Items offered for sale:");
		System.out.format("%-5s %-50s %-10s %-10s %-10s\n", "ID", "Item Name", "Condition",
				"Min bid", "My Bid");
                format(theAuction);
		
	}
	
public static void displayItems(Auction theAuction) {
		
		System.out.println(theAuction.getName() + ", " + theAuction.getDate().toString() + ".");
		System.out.println("Items offered for sale:");
		System.out.format("%-5s %-50s %-10s %-10s\n", "ID", "Item Name", "Condition",
				"Min bid");
                format(theAuction);
                
	}

private static void format(Auction theAuction)
{
    List<Item> items = theAuction.getItems();
    for (int i = 0; i < theAuction.itemCount; i++) // Since nobody cares enough to make this private, I'm just going to abuse it
                {
                    System.out.format("%-5s %-50s %-10s %-10s\n", i+1, items.get(i).getName(), items.get(i).getCondition(),
				items.get(i).getStartingBid());
                }
}
	
	public static void displayAuctionRequest(Auction theAuction){
		System.out.println("the Auction "+theAuction.getName()+" for Organization " + theAuction.getOrg() + ":");
		System.out.println("Date and Time:"+ theAuction.getDate().toString() + ".");
		System.out.println("ContactPerson: "+theAuction.getContactPerson() + "\nDescription: " + theAuction.getDescription());
		System.out.println("Comments:" + theAuction.getComment() + "\n\n");
		
	}

	public static String getUserNameInput() {
		System.out.println("Enter the name of your Auction: ");
		String myOrg = scan.nextLine();
		
		return myOrg;
	}
	
	public static String getUserContactPersonInput() {
		System.out.println("Enter the name of your contact person: ");
		String myContact = scan.nextLine();
		
		return myContact;
	}
	
	public static String getUserDescriptionInput() {
		System.out.println("Enter your auction description: ");
		String myDescription = scan.nextLine();

		return myDescription;
	}
	
	public static String getUserCommentInput() {
		System.out.println("Enter your comments about your auction: ");
		String myComment = scan.nextLine();

		return myComment;
	}
}
