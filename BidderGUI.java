import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * The GUI for users that are represented as bidders in
 * the system.
 * 
 * @author Kyle Phan
 * @version 12/5/2016
 */
public class BidderGUI {
	
	final static int IDWIDTH = 20;
	final static int NAMEWIDTH = 100;
	final static int CONDITIONWIDTH = 30;
	final static int MINBIDWIDTH = 30;
	final static int MYBIDWIDTH = 30;
	final static int COLUMNNUMBERS = 5;
	
	final static String THEFILENAME = "Auctions.ser";
	final static String USERFILE = "Users.ser";
	
	final static String INPUTPANEL = "Login Page";
	final static String BIDDERCARD = "Bidder Card";
	final static String BIDDERPANEL = "Bidder Welcome Page";
	final static String BIDDERVIEWAUCS = "Bidder Auctions Page";
	final static String BIDDERVIEWITEMS = "Bidder Items Page";	
	final static String BIDDERVIEWITEMDETAILS = "Bidder Item Details Page";
	
	final static String[] COLUMNNAMES = {"ID #",
            							 "Item Name",
            							 "Condition",
            							 "Min. Bid",
            							 "My Bids"};
	private Object[][] myTableData;
	private JTable myItemTable;
	
	private Login myLogin;
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
	private JPanel myViewItemDetailsScreen;
	
	private JTextField placedBid;
	private JTextArea auctionInfo;
	
	private ButtonBuilder myOptionButtons;
	
	private List<Item> itemsFromAuction;
	private Auction chosenAuction;
	private Item chosenItem;
	private JScrollPane scrollPane;
	
	private NumberFormat currencyFormatter;
	private String rawInput;
	private float theBid;
	private float currentBid;
	
	private static final String AUCCENTRALMOTTO = "Auction Central: The Auctioneer for Non-Profit Organizations";
	private static final String LOGGEDINASBIDDER = " logged in as a Bidder";
	private static final String ITEMCURRENTBID = "Your bid: ";
	private static final String ITEMMINBID = "Starting Bid: ";
	private static final String ITEMCONDITION = "\nCondition: ";
	private static final String ITEMQUANTITY = "\nQuantity: ";
	private static final String ITEMDESCRIPTION = "\nDescription: ";
	

	private JTextArea myWelcomeText = new JTextArea(AUCCENTRALMOTTO);
	/**
	 * Constructor for BidderGUI
	 * @param theUser is the current User of class Bidder
	 * @param theContainer is the JPanel that all of the different GUIs will be added to
	 * @param theCLayout is the CardLayout that is used to switch between different JPanels
	 */
	public BidderGUI(User theUser, Login theLogin, AuctionCalendar theCalendar, JPanel theContainer, CardLayout theCLayout) {
		
		myLogin = theLogin;
		myBidder = (Bidder) theUser;
		myCalendar = theCalendar;
		myMainContainer = theContainer;
        myMainCLayout = theCLayout;
        
        
        System.out.println(myBidder.viewBids().keySet());
        
        
        currencyFormatter = NumberFormat.getCurrencyInstance();
        
        myLocalContainer = new JPanel();
        myLocalCLayout = new CardLayout();
        myMainScreen = new JPanel();
        myWelcomeScreen = new JPanel();
        placedBid = new JTextField();
        
	}

	
	/**
	 * This method sets up the GUI to run on the master JFrame.
	 */
	public void start() {
		myOptionButtons = new ButtonBuilder(new String[] {"View Auctions", "View Item List", "View Item Details", "Place a Bid", "Remove Bid", "Logout", });

		BidderScreenController();
		
		myMainContainer.add(myMainScreen, BIDDERCARD);
		myMainCLayout.show(myMainContainer, BIDDERCARD);
		
	}

	/**
	 * This method initializes all the buttons as well as starts up and sets
	 * up the initial JPanels.
	 */
	private void BidderScreenController() {
		myMainScreen.setLayout(new BorderLayout());
		myOptionButtons.buildButtons();

		myMainScreen.add(myOptionButtons, BorderLayout.SOUTH);
		myOptionButtons.getButton(0).setEnabled(false);
		myOptionButtons.getButton(0).addActionListener(new ViewAuctions());
		myOptionButtons.getButton(1).setVisible(false);
		myOptionButtons.getButton(1).addActionListener(new ViewItemList());		
		myOptionButtons.getButton(2).setEnabled(false);
		myOptionButtons.getButton(2).addActionListener(new SelectedItem());
		myOptionButtons.getButton(3).setVisible(false);
		myOptionButtons.getButton(3).addActionListener(new PlaceBid());
		myOptionButtons.getButton(4).setVisible(false);
		myOptionButtons.getButton(4).addActionListener(new RemoveBid());
		myOptionButtons.getButton(4).setVisible(false);
		myOptionButtons.getButton(5).addActionListener(new LogOut());
		
		BidderWelcomeScreen();
		
		myLocalContainer.setLayout(myLocalCLayout);
		
		myViewAuctionsScreen = new JPanel();
		BidderViewAuctionsScreen();
		myLocalContainer.add(myViewAuctionsScreen, BIDDERVIEWAUCS);
		myLocalCLayout.show(myLocalContainer, BIDDERVIEWAUCS);
		
		myMainScreen.add(myLocalContainer, BorderLayout.CENTER);
		myLocalContainer.setBorder( new EmptyBorder( 20, 20, 20, 20 ) );
	}
	
	/**
	 * This helper method creates the header text.
	 */
	private void BidderWelcomeScreen() {
		myWelcomeScreen.setLayout(new BorderLayout());
		myWelcomeScreen.add(myWelcomeText, BorderLayout.NORTH);
		myWelcomeText.append("\n" + myBidder.getName() + LOGGEDINASBIDDER);
		myMainScreen.add(myWelcomeScreen, BorderLayout.NORTH);
	}
	
	/**
	 * This method initializes the Auction List screen that displays
	 * all of the current auctions in the system.
	 */
	private void BidderViewAuctionsScreen() {
		for (Auction auc : myCalendar.getAuctions()) {
			if (auc.getItems().size() > 0) {
				JButton button = new JButton(auc.getAuctionName());
				
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myViewItemsScreen = new JPanel();
						setAuction(auc);
						BidderViewItemsScreen();
						setAuctionInfo(auc);
						myOptionButtons.getButton(2).setEnabled(true);
						myOptionButtons.getButton(0).setEnabled(true);
					}
				});
				
				myViewAuctionsScreen.add(button);
			}
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
	private void BidderViewItemsScreen() {		
		myTableData = new Object[itemsFromAuction.size()][COLUMNNUMBERS];
		int itemID = 1;

		for (Item i : itemsFromAuction) {
			for (int j = 0; j < COLUMNNUMBERS; j++) {
				if (j == 0) myTableData[itemID-1][j] = itemID;
				if (j == 1) myTableData[itemID-1][j] = i.getName();
				if (j == 2) myTableData[itemID-1][j] = i.getCondition();
				if (j == 3) myTableData[itemID-1][j] = currencyFormatter.format(i.getStartingBid());
				if (j == 4) {
					for (Item k : myBidder.viewBids().keySet()) {
						if (k.getName().equals(i.getName())) {
							myTableData[itemID-1][j] = currencyFormatter.format(myBidder.viewBids().get(k));
						}
					}
				}
			}
			itemID++;
		}
		
		myItemTable = new JTable(myTableData, COLUMNNAMES);		
		scrollPane = new JScrollPane(myItemTable);
		
		JLabel selectAnItem = new JLabel("Please select an item from the list below.");
		
		myViewItemsScreen.setLayout(new BorderLayout());
		myViewItemsScreen.add(selectAnItem, BorderLayout.NORTH);
		myViewItemsScreen.add(scrollPane, BorderLayout.CENTER);
		
		myLocalContainer.add(myViewItemsScreen, BIDDERVIEWITEMS);
		myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
	}
	
	/**
	 * Theoretically, this method should create/overwrite the previous instance of myViewItemDetailsScreen,
	 * Should create a new JPanel with all the new information and replace the previous one, seems like it's not.
	 * 
	 * 
	 * @param theItem is the item being viewed
	 * @param theAuction the auction that the item came from
	 */
	private void BidderViewItemDetailsScreen(Item theItem, Auction theAuction) {
		myViewItemDetailsScreen = new JPanel(new BorderLayout());
		JTextArea itemName = new JTextArea(theItem.getName());
		Font titleFont = new Font(itemName.getFont().getFontName(), Font.BOLD, itemName.getFont().getSize()+6);
		itemName.setFont(titleFont);
		itemName.setBorder( new EmptyBorder(15, 15, 20, 15));
		JTextArea itemDetails = new JTextArea();
		itemDetails.setBorder( new EmptyBorder(0, 15, 0, 15));
		
		/*
		 * If the bidder has a bid, print it in the description
		 */
		if (theItem.getBid(myBidder.getUserName()) != -1) {
			currentBid = theItem.getBid(myBidder.getUserName());
			itemDetails.append(ITEMCURRENTBID + currencyFormatter.format(theItem.getBid(myBidder.getUserName())) + "\n");
			myOptionButtons.getButton(3).setEnabled(false);
			myOptionButtons.getButton(4).setEnabled(true);
		} else if (theItem.getBid(myBidder.getUserName()) == -1) {
			myOptionButtons.getButton(3).setEnabled(true);
			myOptionButtons.getButton(4).setEnabled(false);
		}
		
		/*
		 * Print the item's information.
		 */
		itemDetails.append(ITEMMINBID + currencyFormatter.format(theItem.getStartingBid()));
		itemDetails.append(ITEMCONDITION + theItem.getCondition());
		itemDetails.append(ITEMQUANTITY + theItem.getQuantity());
		itemDetails.append(ITEMDESCRIPTION + theItem.getDescription());
		
		JPanel enterBidPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		JLabel placeBidText = new JLabel("Enter a bid: ");
		
		c.gridx = 0;
		c.gridy = 0;
		
		enterBidPane.add(placeBidText, c);
		
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		enterBidPane.add(placedBid, c);
		
		myOptionButtons.getButton(3).setVisible(true);
		myOptionButtons.getButton(4).setVisible(true);
		myViewItemDetailsScreen.add(enterBidPane, BorderLayout.SOUTH);
		myViewItemDetailsScreen.add(itemName, BorderLayout.NORTH);
		myViewItemDetailsScreen.add(itemDetails, BorderLayout.CENTER);
		
		setItem(theItem);
		
		/*
		 * If the auction date is within 2 days, set Remove Bid button to disabled -- Business Rule
		 */
		if (!theAuction.getDate().isTwoOrMoreDaysBefore(new AuctionDate())) {
			myOptionButtons.getButton(4).setEnabled(false);
		}
		
		myLocalContainer.add(myViewItemDetailsScreen, BIDDERVIEWITEMDETAILS);
		myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMDETAILS);
	}
	
	/**
	 * This method clears the JTextField that is used to enter bids.
	 */
	private void clearTextField() {
		placedBid.setText("");
	}
	
	/**
	 * This method sets the chosenAuction variable to whatever Auction
	 * is selected in the Auction List JPanel.
	 * @param theAuction is the Auction selected via the Auction List JPanel
	 */
	private void setAuction(Auction theAuction) {
		chosenAuction = theAuction;
		itemsFromAuction = theAuction.getItems();
	}
	
	/**
	 * This method sets the chosenItem variable to whatever Item is selected
	 * in the View Items List after selecting an Auction.
	 * @param theItem is the Item that the user wishes to bid on.
	 */
	private void setItem(Item theItem) {
		chosenItem = theItem;
	}
	
	/**
	 * This method helps set the header info with the name of the Auction
	 * as well as the month, day, year and time that it will occur.
	 * @param theAuction is the Auction selected by the User in the Auction List JPanel
	 */
	private void setAuctionInfo(Auction theAuction) {
		auctionInfo = new JTextArea();
		auctionInfo.setBorder( new EmptyBorder(15, 0, 0, 0));
		auctionInfo.append(theAuction.getAuctionName() + ", ");
		if (theAuction.getDate().getMonth() == 1) auctionInfo.append("January");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("February");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("March");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("April");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("May");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("June");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("July");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("August");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("September");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("October");
		else if (theAuction.getDate().getMonth() == 2) auctionInfo.append("November");
		else auctionInfo.append("December");
		auctionInfo.append(" " + theAuction.getDate().getDay() + ", " + theAuction.getDate().getYear() +
						   ", " + theAuction.getDate().getHour());
		if (theAuction.getDate().getHour() > 0 && theAuction.getDate().getHour() < 12) {
			auctionInfo.append("AM");
		} else {
			auctionInfo.append("PM");
		}
		myWelcomeScreen.add(auctionInfo, BorderLayout.SOUTH);
	}
	
	/**
	 * This is a helper method that redraws the JTable after information has been updated.
	 */
	private void redrawTable() {
		myViewItemsScreen.remove(scrollPane);
		myTableData = new Object[itemsFromAuction.size()][COLUMNNUMBERS];
		int itemID = 1;

		for (Item i : itemsFromAuction) {
			for (int j = 0; j < COLUMNNUMBERS; j++) {
				if (j == 0) myTableData[itemID-1][j] = itemID;
				if (j == 1) myTableData[itemID-1][j] = i.getName();
				if (j == 2) myTableData[itemID-1][j] = i.getCondition();
				if (j == 3) myTableData[itemID-1][j] = currencyFormatter.format(i.getStartingBid());
				if (j == 4) {
					for (Item k : myBidder.viewBids().keySet()) {
						if (k.getName().equals(i.getName())) {
							myTableData[itemID-1][j] = currencyFormatter.format(myBidder.viewBids().get(k));
						}
					}
				}
			}
			itemID++;
		}
		
		myItemTable = new JTable(myTableData, COLUMNNAMES);
		scrollPane = new JScrollPane(myItemTable);
		myViewItemsScreen.add(scrollPane, BorderLayout.CENTER);
		myViewItemsScreen.repaint();
		
	}
	
	/**
	 * This ActionListener class is used to return to the list of Auctions
	 * for the User to choose from.
	 * @author Kyle Phan
	 *
	 */
	class ViewAuctions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Component c : myLocalContainer.getComponents()) {
				if (c.equals(BIDDERVIEWAUCS)) {
					System.out.println("Removed View Auc Screen");
					myLocalContainer.remove(myViewAuctionsScreen);
				}
			}
			System.out.println(myLocalContainer.getComponents());
			myViewAuctionsScreen = new JPanel();
			BidderViewAuctionsScreen();
			myLocalContainer.add(myViewAuctionsScreen, BIDDERVIEWAUCS);
			clearTextField();
			myWelcomeScreen.remove(auctionInfo);
			myOptionButtons.getButton(0).setEnabled(false);
			myOptionButtons.getButton(2).setEnabled(false);
			myOptionButtons.getButton(2).setVisible(true);
			myOptionButtons.getButton(3).setVisible(false);
			myOptionButtons.getButton(4).setVisible(false);
			myLocalCLayout.show(myLocalContainer, BIDDERVIEWAUCS);
		}
	}
	
	/**
	 * This ActionListener class is used to return to the list of Items
	 * available in the chosen Auction.
	 * @author Kyle Phan
	 *
	 */
	class ViewItemList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			redrawTable();
			myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
			myOptionButtons.getButton(1).setVisible(false);
			myOptionButtons.getButton(2).setEnabled(true);
			myOptionButtons.getButton(3).setVisible(false);
			myOptionButtons.getButton(4).setVisible(false);
		}
	}
	
	/**
	 * This ActionListener class is used to place a bid on a selected item.
	 * Gets the input entered and checks if it is valid, if not creates JOptionPane 
	 * the issue.
	 * If valid bid, places bid on item and creates JOptionPane confirming, then
	 * updates View Item List JPanel.
	 * @author Kyle Phan
	 *
	 */
	class PlaceBid implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rawInput = placedBid.getText();
			if (rawInput != null && !rawInput.isEmpty()) {
				theBid = Float.parseFloat(rawInput);
			}
			
			if (theBid < 0) {
				System.out.println("Bid is below 0");
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please enter a valid bid:\nYour bid was a negative number!",
		                "Bid Error!",
		                JOptionPane.ERROR_MESSAGE);
			} else if (theBid == 0) {
				System.out.println("Bid is 0");
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please enter a valid bid:\nYour bid was zero!",
		                "Bid Error!",
		                JOptionPane.ERROR_MESSAGE);
			} else if (theBid < chosenItem.getStartingBid()) {
				System.out.println("Bid is less than starting bid");
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please enter a valid bid:\nYour bid was less than the minimum bid!",
		                "Bid Error!",
		                JOptionPane.ERROR_MESSAGE);
			} else {
				if (myBidder.placeBid(chosenItem, theBid)) {
					System.out.println("Bid accepted");
					myItemTable.validate();
					myLogin.writeUserInfo(USERFILE);
					myCalendar.Update(THEFILENAME);
					clearTextField();
//					myLocalContainer.remove(myViewAuctionsScreen);
//					myLocalContainer.remove(myViewItemsScreen);
					myLocalContainer.remove(myViewItemDetailsScreen);
					redrawTable();
					myOptionButtons.getButton(1).setVisible(false);
					myOptionButtons.getButton(2).setEnabled(true);
					myOptionButtons.getButton(3).setVisible(false);
					myOptionButtons.getButton(4).setVisible(false);
					myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
					JOptionPane.showMessageDialog(myMainScreen,
			                "Your bid of " + currencyFormatter.format(theBid) + " has been placed on " +
			                chosenItem.getName(),
			                "Congratulations!",
			                JOptionPane.INFORMATION_MESSAGE);
				}  else {
					System.out.println("Bid already exists");
					JOptionPane.showMessageDialog(myMainScreen,
			                "You have already placed a bid on this item!",
			                "Bid Error!",
			                JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
	}
	
	/**
	 * This ActionListener class is used to remove a bid on a selected item.
	 * Creates a JOptionPane confirming that the bid has been removed then
	 * updates View Item List JPanel.
	 * @author Kyle Phan
	 *
	 */
	class RemoveBid implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Bid removed...?");
			
			myBidder.removeBid(chosenItem);
//			theItem.deleteBid(myBidder.getUserName());
			myItemTable.validate();
			myLogin.writeUserInfo(USERFILE);
			myCalendar.Update(THEFILENAME);
			clearTextField();
			myLocalContainer.remove(myViewItemDetailsScreen);
			redrawTable();
			myOptionButtons.getButton(1).setVisible(false);
			myOptionButtons.getButton(2).setEnabled(true);
			myOptionButtons.getButton(3).setVisible(false);
			myOptionButtons.getButton(4).setVisible(false);
			myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
			JOptionPane.showMessageDialog(myMainScreen,
	                "Your bid of " + currencyFormatter.format(currentBid) + " has been removed from " +
	                chosenItem.getName(),
	                "Bid Removed!",
	                JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * This ActionListener class is used to get the Item selected from the
	 * View Items List Jtable and create a JPanel that will display the Item's
	 * information.
	 * @author Kyle Phan
	 *
	 */
	class SelectedItem implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (myItemTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(myMainScreen,
		                "Please select an item to view",
		                "No Item Selected",
		                JOptionPane.ERROR_MESSAGE);
			} else {
				BidderViewItemDetailsScreen(itemsFromAuction.get(myItemTable.getSelectedRow()), chosenAuction);
				myOptionButtons.getButton(1).setVisible(true);
				myOptionButtons.getButton(2).setEnabled(false);
			}
		}
	}
	
	/**
	 * This ActionListener is used to log the current user out and return
	 * back to the Login page.
	 * 
	 * Removes this GUI from the main GUI's CardLayout.
	 * @author Kyle
	 *
	 */
	class LogOut implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myMainCLayout.show(myMainContainer, INPUTPANEL);
			myMainContainer.remove(myMainScreen);
		}
	}
}
