import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The GUI for users that are represented as bidders in
 * the system.
 * 
 * @author Kyle Phan
 * @version 11/30/2016
 */
public class BidderGUI {
	
	final static String INPUTPANEL = "Login Page";
	final static String BIDDERCARD = "Bidder Card";
	final static String BIDDERPANEL = "Bidder Welcome Page";
	final static String BIDDERVIEWAUCS = "Bidder Auctions Page";
	
	
	
	private Bidder myBidder;
	private AuctionCalendar myCalendar;
	
	private JPanel myLocalContainer;
	private CardLayout myLocalCLayout;
	
	private JPanel myMainContainer;
	private CardLayout myMainCLayout;
	
	private JPanel myMainScreen;
	private JPanel myWelcomeScreen;
	private JPanel myViewAuctionsScreen;
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
		myOptionButtons = new ButtonBuilder(new String[] {"Bid on an Item", "Go Back", "Logout"});

		BidderScreenController();
		
		myMainContainer.add(myMainScreen, BIDDERCARD);
		myMainCLayout.show(myMainContainer, BIDDERCARD);
		
	}

	private void BidderScreenController() {
		myMainScreen.setLayout(new BorderLayout());
		myOptionButtons.buildButtons();
		myOptionButtons.getButton(1).setVisible(false);
		myMainScreen.add(myOptionButtons, BorderLayout.SOUTH);
		myOptionButtons.getButton(0).addActionListener(new ViewAuctions());
		myOptionButtons.getButton(2).addActionListener(new LogOut());
		
		BidderWelcomeScreen();
		
		myLocalContainer.setLayout(myLocalCLayout);
		
		myLocalContainer.add(myWelcomeScreen, BIDDERPANEL);
		myLocalCLayout.show(myLocalContainer, BIDDERPANEL);
		
		myMainScreen.add(myLocalContainer, BorderLayout.CENTER);
	}
	
	private void BidderWelcomeScreen() {
		myWelcomeScreen.setLayout(new BorderLayout());
		myWelcomeText.append("\n" + myBidder.getName() + LOGGEDINASBIDDER);
		myWelcomeScreen.add(myWelcomeText, BorderLayout.NORTH);
	}
	
	private void BidderViewAuctionsScreen() {
		for (Auction auc : myCalendar.getAuctions()) {
			JButton button = new JButton(auc.getOrg());
			myViewAuctionsScreen.add(button);
		}
	}
	
	class ViewAuctions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myViewAuctionsScreen = new JPanel();
			BidderViewAuctionsScreen();
			myLocalContainer.add(myViewAuctionsScreen, BIDDERVIEWAUCS);
			myLocalCLayout.show(myLocalContainer, BIDDERVIEWAUCS);
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
