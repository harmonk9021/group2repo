import java.util.Scanner;

public class ItemUI {
	String addAuction = "Adding an Item";
	Scanner scan = new  Scanner(System.in);
	
	public static void displayItemUI(Item item){
		System.out.println(item.getName()+"/tCondition: " + item.getCondition());
		System.out.println("Starting Bid: $" + String.format("%.2f", item.getStartingBid()));
		System.out.println(item.getDescription());
	}
	
	
	
	public static void isEqualOrBelowZeroErrorIU(String var){
		System.out.println("\n\n"+var+" needs to be a value above 0.");	
	}
	
	public static void isValidSizeErrorUI(){
		System.out.println("\n\nSize needs to be a 'small', 'medium', or 'large'.");	
	}
	
	public static void addBidAlreadyHasBidderErrorUI(){
		System.out.println("\n\n you have already placed a bid on this item.");
	}
}
