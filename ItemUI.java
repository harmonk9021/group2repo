import java.util.Scanner;
import java.text.DecimalFormat;


public class ItemUI {
	String addAuction = "Adding an Item";
	Scanner scan = new  Scanner(System.in);
	//DecimalFormat df = new DecimalFormat();
	//df.setMaximumFractionDigits(2);
	
	void displayItemUI(Item item){
		System.out.println(item.getName()+"/tCondition: " + item.getCondition());
		System.out.println("Starting Bid: $" + String.format("%.2f", item.getStartingBid()));
		System.out.println(item.getDescription());
	}
	
	
	void header(String a){
		System.out.println("\n\nAuctionCentral: " + a);
		System.out.println("---------------------------------");
		
	}
	
	void isEqualOrBelowZeroErrorIU(String var){
		System.out.println("\n\n"+var+" needs to be a value above 0.");	
	}
	
	void isValidSizeErrorUI(){
		System.out.println("\n\nSize needs to be a 'small', 'medium', or 'large'.");	
	}
	
	void addBidAlreadyHasBidderErrorUI(){
		System.out.println("\n\n you have already placed a bid on this item.");
	}
}
