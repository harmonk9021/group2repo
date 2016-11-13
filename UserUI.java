/**
 * TODO Write a description of class Item here.
 * 
 * @author Andrew Dinh
 * @version 11/12/2016
 */
 
import java.util.Scanner;
 
public class UserUI 
{
	
	public UserUI() 
	{
		Scanner scan = new  Scanner(System.in);
		displayHomeScreen();
	}
	
	public static void displayHomeScreen() 
	{
		System.out.println("Welcome to Auction Central");
		System.out.println("What would you like to do?");
		System.out.println();
		System.out.println("1. Sign In");
		System.out.println("2. Sign Up");
		System.out.println("3. View Auctions");
		System.out.println("4. Exit");
		System.out.print(>);
		int choice = scan.nextInt();
		if (choice == 1) {
			signIn();
		} else if (choice == 2) {
			signUp();
		} else if (choice == 3) {
			viewAuction();
		} else if (choice == 4) {
			exit();
		} else {
			System.out.println("Invalid choice!");
			displayHomeScreen();
		}
	}
	
	public static void signIn() 
	(
		
	)
}