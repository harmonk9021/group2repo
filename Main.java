
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
		myLogin.loadUserInfo(myLogin.fileName);
		
		GUI gui = new GUI(myLogin);
		gui.start();
		//database.Update("Auctions.ser", "Users.ser");
		System.out.println("GoodBye");
		
	}

}
