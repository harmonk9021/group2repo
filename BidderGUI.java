import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * The GUI for users that are represented as bidders in
 * the system.
 * 
 * @author Kyle Phan
 * @version 11/30/2016
 */
public class BidderGUI {
	
	final static int IDWIDTH = 20;
	final static int NAMEWIDTH = 100;
	final static int CONDITIONWIDTH = 30;
	final static int MINBIDWIDTH = 30;
	final static int MYBIDWIDTH = 30;
	final static int COLUMNNUMBERS = 5;
	
	final static String INPUTPANEL = "Login Page";
	final static String BIDDERCARD = "Bidder Card";
	final static String BIDDERPANEL = "Bidder Welcome Page";
	final static String BIDDERVIEWAUCS = "Bidder Auctions Page";
	final static String BIDDERVIEWITEMS = "Bidder Items Page";	
	
	final static String[] COLUMNNAMES = {"ID #",
            							 "Item Name",
            							 "Condition",
            							 "Min. Bid",
            							 "My Bids"};
	
	private Bidder myBidder;
	private AuctionCalendar myCalendar;
	
	private JPanel myLocalContainer;
	private CardLayout myLocalCLayout;
	
	private JPanel myMainContainer;
	private CardLayout myMainCLayout;
	
	private JPanel myMainScreen;
	private JPanel myWelcomeScreen;
	private JPanel myViewAuctionsScreen;
	private JPanel myViewItemsScreen;
	private JPanel myBidScreen;
	
	private ButtonBuilder myOptionButtons;
	
	private static final String AUCCENTRALMOTTO = "Auction Central: The Auctioneer for Non-Profit Organizations";
	private static final String LOGGEDINASBIDDER = " logged in as a Bidder";

	private JTextArea myWelcomeText = new JTextArea(AUCCENTRALMOTTO);
	/**
	 * Constructor for BidderGUI
	 * @param theUser is the current User of class Bidder
	 * @param theContainer is the JPanel that all of the different GUIs will be added to
	 * @param theCLayout is the CardLayout that is used to switch between different JPanels
	 */
	public BidderGUI(User theUser, AuctionCalendar theCalendar, JPanel theContainer, CardLayout theCLayout) {
		
		myBidder = (Bidder) theUser;
		myCalendar = theCalendar;
		myMainContainer = theContainer;
        myMainCLayout = theCLayout;
        
        myLocalContainer = new JPanel();
        myLocalCLayout = new CardLayout();
        myMainScreen = new JPanel();
        myWelcomeScreen = new JPanel();
        myBidScreen = new JPanel();
        
	}

	public void start() {
		myOptionButtons = new ButtonBuilder(new String[] {"View Auctions", "Bid on an Item", "Go Back", "Logout", });

		BidderScreenController();
		
		myMainContainer.add(myMainScreen, BIDDERCARD);
		myMainCLayout.show(myMainContainer, BIDDERCARD);
		
	}

	private void BidderScreenController() {
		myMainScreen.setLayout(new BorderLayout());
		myOptionButtons.buildButtons();
		myOptionButtons.getButton(1).setVisible(false);
		myOptionButtons.getButton(2).setVisible(false);
		myMainScreen.add(myOptionButtons, BorderLayout.SOUTH);
		myOptionButtons.getButton(0).addActionListener(new ViewAuctions());
		myOptionButtons.getButton(2).addActionListener(new GoBack());
		myOptionButtons.getButton(3).addActionListener(new LogOut());
		
		BidderWelcomeScreen();
		
		myLocalContainer.setLayout(myLocalCLayout);
		
		myLocalContainer.add(myWelcomeScreen, BIDDERPANEL);
		myLocalCLayout.show(myLocalContainer, BIDDERPANEL);
		
		myMainScreen.add(myLocalContainer, BorderLayout.CENTER);
		myLocalContainer.setBorder( new EmptyBorder( 20, 20, 20, 20 ) );
	}
	
	private void BidderWelcomeScreen() {
		myWelcomeScreen.setLayout(new BorderLayout());
		myWelcomeText.append("\n" + myBidder.getName() + LOGGEDINASBIDDER);
		myMainScreen.add(myWelcomeText, BorderLayout.NORTH);
	}
	
	private void BidderViewAuctionsScreen() {
		for (Auction auc : myCalendar.getAuctions()) {
			JButton button = new JButton(auc.getOrg());
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					myViewItemsScreen = new JPanel();
					BidderViewItemsScreen(auc);
					myLocalContainer.add(myViewItemsScreen, BIDDERVIEWITEMS);
					myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
				}
			});
			
			myViewAuctionsScreen.add(button);
			
		}
	}
	
	/**
	 * This method creates a JTable that lists all of the items in the auction.
	 * Doesn't sort the items by name, but by order of which they were added.
	 * 
	 * JTables work as a 2D array, 
	 * 
	 * @param theAuction is the Auction you wish to get items from
	 */
	private void BidderViewItemsScreen(Auction theAuction) {
		List<Item> myItems = theAuction.getItems();
		
		Object[][] data = new Object[myItems.size() + 1][COLUMNNUMBERS];
		int itemID = 1;
		for (int k = 0; k < COLUMNNUMBERS; k++) {
			data[0][k] = COLUMNNAMES[k];
		}
		for (Item i : myItems) {
			for (int j = 0; j < COLUMNNUMBERS; j++) {
				if (j == 0) data[itemID][j] = itemID;
				if (j == 1) data[itemID][j] = i.getName();
				if (j == 2) data[itemID][j] = i.getCondition();
				if (j == 3) data[itemID][j] = i.getStartingBid();
				if (j == 4) {
					if (myBidder.viewBids().containsKey(i)) {
						data[itemID][j] = myBidder.viewBids().get(i);
					} else {
						data[itemID][j] = null;
					}
				}
			}
			itemID++;
		}
		
		JTable myItemTable = new JTable(data, COLUMNNAMES);
//		myViewItemsScreen.setLayout(new BorderLayout());
//		myViewItemsScreen.add(myItemTable, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(myItemTable);
		myViewItemsScreen.setLayout(new BorderLayout());
		myViewItemsScreen.add(scrollPane, BorderLayout.CENTER);
	}
	
	class ViewAuctions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myViewAuctionsScreen = new JPanel();
			BidderViewAuctionsScreen();
			myLocalContainer.add(myViewAuctionsScreen, BIDDERVIEWAUCS);
			myOptionButtons.getButton(0).setVisible(false);
			myOptionButtons.getButton(1).setVisible(true);
			myOptionButtons.getButton(2).setVisible(true);
			myLocalCLayout.show(myLocalContainer, BIDDERVIEWAUCS);
		}
	}
	
	class GoBack implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myLocalContainer.add(myViewAuctionsScreen, BIDDERVIEWAUCS);
			myLocalCLayout.previous(myLocalContainer);
		}
	}
	
	class LogOut implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myMainCLayout.show(myMainContainer, INPUTPANEL);
			myMainContainer.remove(myMainScreen);
		}
	}
}
