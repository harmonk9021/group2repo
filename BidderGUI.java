import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The GUI for users that are represented as bidders in
 * the system.
 * 
 * @author Kyle Phan
 * @version 11/30/2016
 */
public class BidderGUI {
	
	final static String BIDDERPANEL = "Bidder Page";
	
	private Bidder myBidder;
	
	private JPanel myContainer;
	private CardLayout cLayout;
	
	private JPanel bidderPanel;
	

	/**
	 * Constructor for BidderGUI
	 * @param theUser is the current User of class Bidder
	 * @param theContainer is the JPanel that all of the different GUIs will be added to
	 * @param theCLayout is the CardLayout that is used to switch between different JPanels
	 */
	public BidderGUI(User theUser, JPanel theContainer, CardLayout theCLayout) {
		myBidder = (Bidder) theUser;
		myContainer = theContainer;
		cLayout = theCLayout;
		
		bidderPanel = new JPanel();
//		myFrame = new JFrame("Hello" + myBidder.getName());
//		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() {
//		myFrame.setSize(400, 400);
//		myFrame.setLayout(bLayout);
		
		bidderScreen();
		myContainer.add(bidderPanel, BIDDERPANEL);
		cLayout.show(myContainer, BIDDERPANEL);
		
//		myFrame.setVisible(true);
	}

	private void bidderScreen() {
		JButton testButton = new JButton("Test Button");
		bidderPanel.add(testButton);
		
	}

}
