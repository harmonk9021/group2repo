import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UI {
	private static Scanner scan = new  Scanner(System.in);
	 User thisUser;
	 
	Database database = new Database();
	
	
	public void mainMenu(){
		database.Load("Auctions.ser", "Users.ser");
                System.out.println("Users in system: " + database.getUserList().toString());
                System.out.println("Auctions in system: " + database.getAuctionList().toString());
		System.out.println("Please Enter Name");
		String userName = scan.nextLine();//thisUser.setUserName(scan.nextLine());
		System.out.print("Are you a new User?(type Y or N)\n>");
		String newUser = scan.nextLine();
		if(newUser.equals("N") || newUser.equals("n")){
			thisUser = database.getUser(userName);
			if (thisUser == null) {
				System.out.println("This User is not in database.");
			} else {
			if(thisUser.getType().equals("Bidder")) bidderMainMenu();
			else if(thisUser.getType().equals("Nonprofit")) nonProfitMainMenu();
			else staffMainMenu();
			}
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
				//thisUser = new Bidder(userName, userName, "", "", 0); // Just to satisfy constructor requirements
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
            //System.out.println("If you're reading this, it means you were recognized as a bidder");
            int choice;
            do
            {
            header();
            System.out.println("What would you like to do?");
            System.out.println("1 View available auctions\n2 Exit Auction Central\n>");
            choice = scan.nextInt();
            if (choice == 1)
            {
                viewAuctionList();
            }
            else if (choice == 2)
            {
                return;
            }
            
                //bidderMainMenu();
                } while(choice != 2);
        }
	public void viewAuctionList(){
            List<Auction> aucList = database.getAuctionList();
            if (aucList == null || aucList.isEmpty())
            {
                System.out.println("Sorry, there are no upcoming auctions for the current\nmonth right now. Please check back later.");
                //bidderMainMenu();
            }
            else
            {
                //while (true)
                {
                System.out.println("Here are the available auctions coming in the next month:");
                for (int i = 0; i < aucList.size(); i++)
                {
                    if (aucList.get(i).getDate().after(new Date()))
                    {
                        System.out.println("" + (i+1) + " " + aucList.get(i).getName() + ", hosted " + aucList.get(i).getDate().toString());
                    }
                }
                System.out.println("Enter a number to view the auction details, or press \"0\" to return to the main menu: \n>");
                int choice = scan.nextInt();
                if (choice == 0)
                {
                    //bidderMainMenu();
                    return;
                }
                else
                {
                    auctionBidderMenu(aucList.get(choice-1));
                }
                }
            }
        }
	public void auctionBidderMenu(Auction theAuction){
            int option;    
            do
                {
		AuctionUI.displayItemsForBidder(theAuction, thisUser.getUserName());
                System.out.println("Enter the ID number of the item you would like to bid on or enter \"0\" to return to the main menu.\n>");
                int choice = scan.nextInt();
                if (choice == 0)
                {
                    //bidderMainMenu();
                    return;
                }
                List<Item> items = theAuction.getItems();
                choice--;
                items.get(choice).displayItem();
                System.out.println("What would you like to do?\n\n1 Bid on this item.\n2Go back\n>");
                option = scan.nextInt();
                if (option == 1)
                {
                    System.out.println("Submit a bit of at least the minimum bid ($" + items.get(choice).getStartingBid() + "0)\n>");
                    float bid = 0;
                    while (bid < items.get(choice).getStartingBid())
                    {
                        System.out.println("Submit a bit of at least the minimum bid ($" + items.get(choice).getStartingBid() + "0)\n>");
                        bid = scan.nextFloat();
                    }
                    
                    thisUser.placeBid(items.get(choice), bid);
                    System.out.println("Congratulations. You have placed a bid of $" + bid + "0 on item \"" + items.get(choice).getName() + "\".");
                    //bidderMainMenu();
                    return;
                }
                //else if (option == 2)
                
                    //auctionBidderMenu(theAuction);
                    
                } while (option != 2);
                
		
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
                database.Update("Auctions.ser", "Users.ser");
	}
	
	private Date getDate(){
            System.out.println("Enter the date you would like your auction on (MM/DD/YYYY HH:MM am/pm): ");
                
		String theDate = scan.nextLine();
                Date date = new Date();
                int parser = 0;
                //date.setMonth(0);
                if(theDate.charAt(1) == '1')
                {
                    //date.setMonth(1);
                    parser = 1;
                }
                else if(theDate.charAt(1) == '2')
                {
                    //date.setMonth(2);
                    parser = 2;
                }
                else if(theDate.charAt(1) == '3')
                {
                    //date.setMonth(3);
                    parser = 3;
                }
                else if(theDate.charAt(1) == '4')
                {
                    //date.setMonth(4);
                    parser = 4;
                }
                else if(theDate.charAt(1) == '5')
                {
                    //date.setMonth(5);
                    parser = 5;
                }
                else if(theDate.charAt(1) == '6')
                {
                    //date.setMonth(6);
                    parser = 6;
                }
                else if(theDate.charAt(1) == '7')
                {
                    //date.setMonth(7);
                    parser = 7;
                }
                else if(theDate.charAt(1) == '8')
                {
                    //date.setMonth(8);
                    parser = 8;
                }
                else if(theDate.charAt(1) == '9')
                {
                    //date.setMonth(9);
                    parser = 9;
                }
                if(theDate.charAt(0) == '1')
                {
                    //date.setMonth(date.getMonth()+10);
                    parser += 10;
                }
                date.setMonth(parser);
                parser = 0;
                
                if(theDate.charAt(4) == '1')
                {
                    //date.setMonth(1);
                    parser = 1;
                }
                else if(theDate.charAt(4) == '2')
                {
                    //date.setMonth(2);
                    parser = 2;
                }
                else if(theDate.charAt(4) == '3')
                {
                    //date.setMonth(3);
                    parser = 3;
                }
                else if(theDate.charAt(4) == '4')
                {
                    //date.setMonth(4);
                    parser = 4;
                }
                else if(theDate.charAt(4) == '5')
                {
                    //date.setMonth(5);
                    parser = 5;
                }
                else if(theDate.charAt(4) == '6')
                {
                    //date.setMonth(6);
                    parser = 6;
                }
                else if(theDate.charAt(4) == '7')
                {
                    //date.setMonth(7);
                    parser = 7;
                }
                else if(theDate.charAt(4) == '8')
                {
                    //date.setMonth(8);
                    parser = 8;
                }
                else if(theDate.charAt(4) == '9')
                {
                    //date.setMonth(9);
                    parser = 9;
                }
                if(theDate.charAt(3) == '1')
                {
                    //date.setMonth(date.getMonth()+10);
                    parser += 10;
                }
                else if(theDate.charAt(3) == '2')
                {
                    //date.setMonth(date.getMonth()+10);
                    parser += 20;
                }
                else if(theDate.charAt(3) == '3')
                {
                    //date.setMonth(date.getMonth()+10);
                    parser += 30;
                }
                date.setDate(parser);

		return date;
        }
	public void auctionNonProfitMenu(){ 
		if (thisUser.getCurrentAuction() == null) {
			System.out.println("You have no current auctions.");
			nonProfitMainMenu();
		} else {
		AuctionUI.displayItems(thisUser.getCurrentAuction());
		System.out.print("What would you like to do?\n1 add Item to Auction\n2 return to main menu\n>");
		int input = scan.nextInt();
		scan.nextLine();
		if(input == 1) {
			thisUser.getCurrentAuction().addItem();
			database.addAuctionToDB(thisUser.getCurrentAuction());
		}
		else if(input == 2) return;
		auctionNonProfitMenu();
		}
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
		AuctionCalendar calendar = new AuctionCalendar(today);
		//calendar.displayCalendar();
		
		System.out.println("What would you like to do?");
		System.out.print("type in the day of the Auctions you want to see(type MM DD)\n>");
		int month = scan.nextInt();
		int day = scan.nextInt();
		scan.nextLine();
		Date dateChosen = new Date(today.getYear(), month, day);
		//calendar.viewAnAuction(dateChosen);
	}
	
	public void header(){
		System.out.println("AuctionCentral: the auctioneer for non-profit organizations.");
		System.out.print(thisUser.getUserName() + " logged in as ");
		System.out.println(thisUser.getType()+"\n");

	}
}
