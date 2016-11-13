import java.util.Scanner;
import java.text.DecimalFormat;


public class ItemUI {
	String addAuction = "Adding an Item";
	static Scanner scan = new  Scanner(System.in);
	//DecimalFormat df = new DecimalFormat();
	//df.setMaximumFractionDigits(2);
	
	void displayItemUI(Item item){
		System.out.println(item.getName()+"/tCondition: " + item.getCondition());
		System.out.println("Starting Bid: $" + String.format("%.2f", item.getStartingBid()));
		System.out.println(item.getDescription());
	}
	
	
		public static String getUserItemNameInput(){
		System.out.println("\n\nPlease Enter the name of the Item");
		return scan.nextLine();
	}
	
	public static String getUserItemDonorNameInput(){
		System.out.println("\n\nPlease Enter the name of the Donor");
		return scan.nextLine();
	}
	
	public static String getUserItemDescriptionInput(){
		System.out.println("\n\nPlease enter a descpription of the item: ");
		return scan.nextLine();
	}
	
	public static int getUserItemQuantityInput(){
		System.out.println("\n\nPlease enter quantity: ");
		return scan.nextInt();
	}
	
	public static float getUserItemStartingBidInput(){
		System.out.println("\n\nPlease enter starting bid: ");
		return scan.nextFloat();
	}
	
	public static String getUserItemConditionInput(){
		System.out.println("\n\nPlease enter condition: ");
		return scan.nextLine();
	}
	
	public static String getUserItemSizeInput(){
		System.out.println("\n\nPlease enter size(enter small, medium, or large): ");
		return scan.nextLine();
	}
	
	public static String getUserItemCommentsInput(){
		System.out.println("\n\nPlease enter any comments(say none if you have no comments): ");
		return scan.nextLine();
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
