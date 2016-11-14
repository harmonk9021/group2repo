/**
 * TODO Write a description of class Item here.
 * 
 * @author Andrew Dinh
 * @version 11/12/2016
 */
 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class UserUI 
{
	static Scanner scan = new  Scanner(System.in);
	User thisUser;
	static Database database = new Database();
	
	public  UserUI() 
	{
		thisUser = new User();
		displayHomeScreen();
	}
	
	public void displayHomeScreen() 
	{
		System.out.println("Welcome to Auction Central");
		System.out.println("What would you like to do?");
		System.out.println();
		System.out.println("1. Sign In");
		System.out.println("2. Sign Up");
		System.out.println("3. View Auctions");
		System.out.println("4. Exit");
		System.out.print(">");
		int choice = scan.nextInt();
		scan.nextLine();
		if (choice == 1) {
			signIn();
		} else if (choice == 2) {
			//signUp();
		} else if (choice == 3) {
			//viewAuction();
		} else if (choice == 4) {
			//exit();
		} else {
			System.out.println("Invalid choice!");
			displayHomeScreen();
		}
		database.addUserToDB(thisUser);
	}
	
	public  void signIn() 
	{
		System.out.println("Sign In");
		System.out.println("Enter your username");
		System.out.print(">");
		String username = scan.nextLine();
		System.out.println("Enter your password");
		System.out.print(">");
		String password = scan.nextLine();
		if (thisUser.authenticate(username, password)) {
			System.out.println("Valadated");
		} else {
			System.out.println("Failed, try again");
			System.out.println();
			signIn();
		}
	}
	
	/***public void signUp(){
		boolean check;
		String name;
		int type;
		ArrayList<User> users = (ArrayList<User>) database.getUserList(); 
		do{
			check = true;
			System.out.print("Please Enter a user name:\n>");
			name = scan.nextLine();
			for(int i = 0;i<users.size();i++){
				if(users.get(i).getUserName().equals(name)) {
					check = false;
					System.out.println("This user name is already taken.");
				}
			}
		}while(check == false);
		
		thisUser.setUserName(name);
		
		System.out.print("Please Type in password\n>");
		thisUser.setPassword(scan.nextLine());
		System.out.print("Are you a:\n1 Staff Person for Auction Central\n2 Non Profit Organization\n3 Bidder\n>");
		type = scan.nextInt();
		scan.nextLine();
		if(type == 1){
			thisUser.setType("Staff");
			UI.staffMainMenu();
		}
		else if(type == 2){
			thisUser.setType("Nonprofit");
			UI.nonProfitMainMenu();
		}
		else{
			thisUser.setType("Bidder");
			UI.bidderMainMenu();
		}
	}***/
}
