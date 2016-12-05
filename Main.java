

public class Main {
	
	public static void main(String[] args) {

		AuctionDate myDate = new AuctionDate();
		AuctionDate aucDate = new AuctionDate(2016, 12, 6, 12);
		
		AuctionCalendar myCalendar = new AuctionCalendar(myDate, "Auctions.ser");
//		Auction testAuction = new Auction(aucDate, "Test Auction 1", "We're FOR Profit", "Jim Bob",
//										  "We take your money and make a profit", "The less you know, the better");
		System.out.println(myCalendar.createAndAddAuction(aucDate, "Auction in 1 day1", "Testing 1 days", "Jon yoo",
										  "We take your money and make a profit", "The less you know, the better"));
		
		Item item1 = new Item("Test Item 1", "Vettel", "This is ridiculous", 1, 10, "It's okay", "Massive", "Ping-Pong, or racing?");
		Item item2 = new Item("Test Item 2", "Raikkonen", "Bwoah", 7, 15, "It's the same for everyone", "Little", "I don't care");
		Item item3 = new Item("Test Item 3", "Button", "It's great, really", 23, 1, "Meh", "Huge", "Massive Understeer");
		
		myCalendar.getAuction("Testing 1 days").addItem(item1);
		myCalendar.getAuction("Testing 1 days").addItem(item2);
		myCalendar.getAuction("Testing 1 days").addItem(item3);
		
		
		
		System.out.println("Auction Count: " + myCalendar.getAuctions().size());
		Login myLogin = new Login("Users.ser");
		
		Auction hpAuction = new Auction(new AuctionDate().getAuctionDateXDaysAway(-4), "hp", "hp",
				"hp", "hp", "hp");	
		myCalendar.addPastAuction(hpAuction);
		myCalendar.Update("Auctions.ser");
		myLogin.loadUserInfo("Users.ser");
		
		
		
		
		GUI gui = new GUI(myLogin, myCalendar);
		gui.start();
		//database.Update("Auctions.ser", "Users.ser");
		System.out.println("GoodBye");
		
	}

}
