import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The GUI for users that are represented as bidders in
 * the system.
 * 
 * @author Andrew Dinh
 * @version 11/22/2016
 */
public class BidderGUI {
	
	private Bidder myBidder;
	
	private JFrame myFrame;

	public BidderGUI(User theUser) {
		myBidder = (Bidder) theUser;
		myFrame = new JFrame("Hello" + myBidder.getName());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() {
		BorderLayout bLayout = new BorderLayout();
		myFrame.setSize(400, 400);
		myFrame.setLayout(bLayout);
		
		bidderScreen();
		
		myFrame.setVisible(true);
	}

	private void bidderScreen() {

		
	}

}
