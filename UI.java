import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UI {
	private static Scanner scan = new  Scanner(System.in);
	 User thisUser;
	 
	Database database = new Database();
	
	
	public void mainMenu(){
		database.Load("Auctions.ser", "Users.ser");
		System.out.println("Please Enter Name");
		String userName = scan.nextLine();//thisUser.setUserName(scan.nextLine());
		System.out.print("Are you a new User?(type Y or N)\n>");
		String newUser = scan.nextLine();
		if(newUser.equals("N") || newUser.equals("n")){
			thisUser = database.getUser(userName);
			if(thisUser.getType().equals("Bidder")) bidderMainMenu();
			else if(thisUser.getType().equals("Nonprofit")) nonProfitMainMenu();
			else staffMainMenu();
		}
		else{
			thisUser = new User();
			thisUser.setUserName(userName);
			System.out.println("Who are you?\n1 Bidder\n2 Nonprofit\n3 Staff");
			int type = scan.nextInt();
			scan.nextLine();
		
			if(type == 3){
				//thisUser = new Staff(userName);
				thisUser.setType("Staff");
				staffMainMenu();
			}
			else if(type == 2){
				//thisUser = new Nonprofit(userName);
				thisUser.setType("Nonprofit");
				nonProfitMainMenu();
			}
			else{
				//thisUser = new Bidder(userName);
				thisUser.setType("Bidder");
				bidderMainMenu();
			}
			database.addUserToDB(thisUser);
			
		}
		database.Update("Auctions.ser", "Users.ser");
		//if(type == 1) bidderMainMenu();
		//else if(type == 2) nonProfitMainMenu();
		//else staffMainMenu();
	}
	
	
	public void bidderMainMenu(){
	
	public void viewAuctionList(){
	
	public void auctionBidderMenu(Auction theAuction){
		AuctionUI.displayItemsForBidder(theAuction, thisUser.getUserName());
		
	}
	
	public void nonProfitMainMenu(){
		header();
		System.out.println("What would you like to do?");
		System.out.print("1 submit Auction Request\n2 view my Auction\n3 Exit Auction Central\n>");
		int choice = scan.nextInt();
		scan.nextLine();
		if(choice == 1) submitAuctionRequestForm();
		else if(choice == 2) auctionNonProfitMenu();
		else if(choice == 3) return;
		nonProfitMainMenu();
	}
	
	public void submitAuctionRequestForm(){
		Date date;
		Auction thisAuction;
		boolean check;
		String choice;
		header();
		
		//System.out.println(year + month + day + hour + min);
		date = getDate();
		
		thisAuction = new Auction(date, thisUser.getUserName());
		do{
			check= database.canAdd(thisAuction);
			if(!check){
				System.out.println("The Date and/or you have entered is not avaliable");
				System.out.print("Would you like to try another date or cancel(Y to try again, N to cancel\n>");
				choice = scan.nextLine();
				if(choice.equals("N") || choice.equals("n")) return;
				else date = getDate();
			}
		}while(check == false);	
		
		thisAuction.setName(AuctionUI.getUserNameInput());
		thisAuction.setContactPerson(AuctionUI.getUserContactPersonInput());
		thisAuction.setDescription(AuctionUI.getUserDescriptionInput());
		thisAuction.setComment(AuctionUI.getUserCommentInput());
		check = database.addAuctionToDB(thisAuction);
		if(check) {
			thisUser.setCurrentAuction(thisAuction);
			System.out.println("Your Auction Request was succesfully placed!!\n Here is your Auction");
			AuctionUI.displayAuctionRequest(thisAuction);
		}
		else System.out.println("Something Went wrong, your Auction request was not placed. Sorry about that :/");
		System.out.print("Press enter to continue\n>");
		scan.nextLine();
		System.out.println("\n");
	}
	
	private Date getDate(){

	public void auctionNonProfitMenu(){ 
		AuctionUI.displayItems(thisUser.getCurrentAuction());
		System.out.print("What would you like to do?\n1 add Item to Auction\n2 return to main menu\n>");
		int input = scan.nextInt();
		scan.nextLine();
		if(input == 1) thisUser.getCurrentAuction().addItem();
		else if(input == 2) return;
		auctionNonProfitMenu();
		
	}
	
	public void staffMainMenu(){
		header();
		System.out.println("What would you like to do?");
		System.out.print("1 view upcoming Auctions\n2 exit Auction Central\n>");
		int choice = scan.nextInt();
		scan.nextLine();
		if(choice == 1) viewCalendar();
		else if(choice == 2) return;
		staffMainMenu();
	}
	
	public void viewCalendar(){
		header();
		Date today = new Date();
		Calendar calendar = new Calendar(today);
		calendar.displayCalendar();
		
		System.out.println("What would you like to do?");
		System.out.print("type in the day of the Auctions you want to see(type MM DD)\n>");
		int month = scan.nextInt();
		int day = scan.nextInt();
		scan.nextLine();
		Date dateChosen = new Date(today.getYear(), month, day);
		calendar.viewAnAuction(dateChosen);
	}
	
	public void header(){
		System.out.println("AuctionCentral: the auctioneer for non-profit organizations.");
		System.out.print(thisUser.getUserName() + " logged in as ");
		System.out.println(thisUser.getType()+"\n");

	}
}
