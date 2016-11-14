
public class Main {
	//public static Database mainDatabase = new Database();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database database = new Database();
		//database.Load("Auctions.ser", "Users.ser");
		//UserUI userUI = new UserUI();
		//UserUI.displayHomeScreen();
		
		UI ui = new UI();
		ui.mainMenu();
		//database.Update("Auctions.ser", "Users.ser");
		System.out.println("GoodBye");
		
	}

}
