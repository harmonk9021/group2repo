/**
 * TODO Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class AuctionUI 
{
	
	Scanner scan = new  Scanner(System.in);
	
	public static void displayItemsForBidder(Auction theAuction) {
		
		System.out.println(theAuction.getName() + ", " + theAuction.getDate().getMonth() +
							theAuction.getDate().getDate() + ", " + 
							theAuction.getDate().getYear() + ", " +
							theAuction.getDate().getHour() + ":" +
							theAuction.getDate().getMinutes() + ".")
		System.out.println("Items offered for sale:");
		System.out.format("%-5s %-20s %-10s %-10s %-10s\n", "ID", "Item Name", "Condition",
				"Min bid", "My Bid");
		
	}
	
public static void displayItems(Auction theAuction) {
		
		System.out.println(theAuction.getName() + ", " + theAuction.getDate().getMonth() +
							theAuction.getDate().getDate() + ", " + 
							theAuction.getDate().getYear() + ", " +
							theAuction.getDate().getHour() + ":" +
							theAuction.getDate().getMinutes() + ".")
		System.out.println("Items offered for sale:");
		System.out.format("%-5s %-20s %-10s %-10s\n", "ID", "Item Name", "Condition",
				"Min bid");
	}
	
	public static String getUserOrgInput() {
		System.out.println("Enter your organization name: ");
		String myOrg = scan.nextLine();
		
		return myOrg;
	}
	
	public static String getUserContactPersonInput() {
		System.out.println("Enter your the name of your contact person: ");
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