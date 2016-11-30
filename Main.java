

public class Main {
	//public static Database mainDatabase = new Database();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//database.Load("Auctions.ser", "Users.ser");
		//UserUI userUI = new UserUI();
		//UserUI.displayHomeScreen();
		
		//UI ui = new UI();
	//	ui.mainMenu();
		
		Login myLogin = new Login("Users.ser");
//		Bidder theTestBidder = new Bidder("Test Bidder", "testbidder1", "1234", 
//										  "Bidder@test.com", "1231231234");
//		myLogin.addUser(theTestBidder);
//		myLogin.writeUserInfo("Users.ser");
		myLogin.loadUserInfo("Users.ser");
		
		GUI gui = new GUI(myLogin);
		gui.start();
		//database.Update("Auctions.ser", "Users.ser");
		System.out.println("GoodBye");
		
	}

}
