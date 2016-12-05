import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
 * @version 11/30/2016
 */
public class BidderGUI {
	
	final static int IDWIDTH = 20;
	final static int NAMEWIDTH = 100;
	final static int CONDITIONWIDTH = 30;
	final static int MINBIDWIDTH = 30;
	final static int MYBIDWIDTH = 30;
	final static int COLUMNNUMBERS = 5;
	
	final static String THEFILENAME = "Auctions.ser";
	
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
	
	private ButtonBuilder myOptionButtons;
	
	private NumberFormat currencyFormatter;
	private String rawInput;
	private float theBid;
	
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

	public void start() {
		myOptionButtons = new ButtonBuilder(new String[] {"View Auctions", "View Item Details", "Place a Bid", "Remove Bid", "Go Back", "Logout", });

		BidderScreenController();
		
		myMainContainer.add(myMainScreen, BIDDERCARD);
		myMainCLayout.show(myMainContainer, BIDDERCARD);
		
	}

	private void BidderScreenController() {
		myMainScreen.setLayout(new BorderLayout());
		myOptionButtons.buildButtons();
		myOptionButtons.getButton(1).setVisible(false);
		myOptionButtons.getButton(2).setEnabled(false);
		myOptionButtons.getButton(2).setVisible(false);
		myOptionButtons.getButton(3).setEnabled(false);
		myOptionButtons.getButton(3).setVisible(false);
		myOptionButtons.getButton(4).setVisible(false);
		myMainScreen.add(myOptionButtons, BorderLayout.SOUTH);
		myOptionButtons.getButton(0).addActionListener(new ViewAuctions());
		myOptionButtons.getButton(4).addActionListener(new GoBack());
		myOptionButtons.getButton(5).addActionListener(new LogOut());
		
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
			if (auc.getItems().size() > 0) {
				JButton button = new JButton(auc.getOrg());
				
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						myViewItemsScreen = new JPanel();
						BidderViewItemsScreen(auc);
						myOptionButtons.getButton(1).setEnabled(true);
						myOptionButtons.getButton(0).setEnabled(true);
						myLocalContainer.add(myViewItemsScreen, BIDDERVIEWITEMS);
						myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
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
	private void BidderViewItemsScreen(Auction theAuction) {
		List<Item> myItems = theAuction.getItems();
		
		myTableData = new Object[myItems.size()][COLUMNNUMBERS];
		int itemID = 1;
//		for (int k = 0; k < COLUMNNUMBERS; k++) {
//			myTableData[0][k] = COLUMNNAMES[k];
//		}
		for (Item i : myItems) {
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
//					
//					} else {
//						myTableData[itemID-1][j] = null;
//					}
				}
			}
			itemID++;
		}
		
		myItemTable = new JTable(myTableData, COLUMNNAMES);		
		JScrollPane scrollPane = new JScrollPane(myItemTable);
		
		JLabel selectAnItem = new JLabel("Please select an item from the list below.");
		
		myOptionButtons.getButton(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (myItemTable.getSelectedRow() == -1) {
					selectAnItem.setForeground(Color.RED);
					myViewItemsScreen.repaint();
				} else {
					BidderViewItemDetailsScreen(myItems.get(myItemTable.getSelectedRow()), theAuction);
					myOptionButtons.getButton(1).setEnabled(false);
				}
			}
		});
		
		myViewItemsScreen.setLayout(new BorderLayout());
		myViewItemsScreen.add(selectAnItem, BorderLayout.NORTH);
		myViewItemsScreen.add(scrollPane, BorderLayout.CENTER);
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
			itemDetails.append(ITEMCURRENTBID + currencyFormatter.format(theItem.getBid(myBidder.getUserName())) + "\n");
			myOptionButtons.getButton(2).setEnabled(false);
			myOptionButtons.getButton(3).setEnabled(true);
		} else if (theItem.getBid(myBidder.getUserName()) == -1) {
			myOptionButtons.getButton(2).setEnabled(true);
			myOptionButtons.getButton(3).setEnabled(false);
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
		
		
//		enterBidPane.setBorder(new EmptyBorder(0, 300, 0, 300));
		JLabel placeBidText = new JLabel("Enter a bid: ");
//		JTextField placedBid = new JTextField();
		
		c.gridx = 0;
		c.gridy = 0;
		
		enterBidPane.add(placeBidText, c);
		
		c.ipadx = 50;
		c.gridx = 1;
		c.gridy = 0;
		enterBidPane.add(placedBid, c);
		
		myOptionButtons.getButton(2).setVisible(true);
		myOptionButtons.getButton(3).setVisible(true);
		myViewItemDetailsScreen.add(enterBidPane, BorderLayout.SOUTH);
		myViewItemDetailsScreen.add(itemName, BorderLayout.NORTH);
		myViewItemDetailsScreen.add(itemDetails, BorderLayout.CENTER);
		
		/*
		 * Actionlistener for Place Bid button, gets the bid input and converts to float
		 * check if the float entered is valid.
		 * 
		 *  If valid, call myBidder.placebid -- seems to work properly.
		 */
		myOptionButtons.getButton(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rawInput = placedBid.getText();
				if (rawInput != null && !rawInput.isEmpty()) {
					theBid = Float.parseFloat(rawInput);
				}
				
				if (theBid < 0) {
					System.out.println("Bid is below 0");
				} else if (theBid == 0) {
					System.out.println("Bid is 0");
				} else if (theBid < theItem.getStartingBid()) {
					System.out.println("Bid is less than starting bid");
				} else {
					if (myBidder.placeBid(theItem, theBid)) {
						System.out.println("Bid accepted");
						myItemTable.repaint();
						myLogin.writeUserInfo("Users.ser");
						myCalendar.Update(THEFILENAME);
						clearTextField();
//						myLocalContainer.remove(myViewAuctionsScreen);
//						myLocalContainer.remove(myViewItemsScreen);
						myLocalContainer.remove(myViewItemDetailsScreen);
						myViewItemsScreen.repaint();
						myOptionButtons.getButton(1).setEnabled(true);
						myOptionButtons.getButton(2).setVisible(false);
						myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
						
					} else {
						System.out.println("Bid already exists");
					}
				}
				
			}
		});
		
		/*
		 * If the auction date is within 2 days, set Remove Bid button to disabled -- Business Rule
		 */
		if (theAuction.getDate().isTwoOrMoreDaysBefore(new AuctionDate())) {
			myOptionButtons.getButton(3).setEnabled(false);
		}
		
		/*
		 * Remove Bid actionlistener, if the user has a bid, call myBidder.removeBid to remove bid
		 * 
		 * Occasionally it doesn't remove the bid from the Bidder's myBid map, but DOES remove from Item
		 * Bidder map.
		 * 
		 */
		myOptionButtons.getButton(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Bid removed...?");
				
				myBidder.removeBid(theItem);
//				theItem.deleteBid(myBidder.getUserName());
				myItemTable.repaint();
				myLogin.writeUserInfo("Users.ser");
				myCalendar.Update(THEFILENAME);
				clearTextField();
				myLocalContainer.remove(myViewItemDetailsScreen);
				myViewItemsScreen.repaint();
				myOptionButtons.getButton(1).setEnabled(true);
				myOptionButtons.getButton(2).setVisible(false);
				myOptionButtons.getButton(3).setVisible(false);
				myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMS);
			}
		});
		
		myLocalContainer.add(myViewItemDetailsScreen, BIDDERVIEWITEMDETAILS);
		myLocalCLayout.show(myLocalContainer, BIDDERVIEWITEMDETAILS);
	}
	
	private void clearTextField() {
		placedBid.setText("");
	}
	
	class ViewAuctions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myViewAuctionsScreen = new JPanel();
			BidderViewAuctionsScreen();
			myLocalContainer.add(myViewAuctionsScreen, BIDDERVIEWAUCS);
			clearTextField();
			myOptionButtons.getButton(0).setEnabled(false);
			myOptionButtons.getButton(1).setEnabled(false);
			myOptionButtons.getButton(1).setVisible(true);
			myOptionButtons.getButton(2).setVisible(false);
			myOptionButtons.getButton(3).setVisible(false);
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
