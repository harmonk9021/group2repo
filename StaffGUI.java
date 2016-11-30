import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for users that are represented as staff in
 * the system.
 * 
 * @author Andrew Dinh
 * @version 11/22/2016
 */

public class StaffGUI {
	
	private Staff myStaff;
	
	private JFrame myFrame;
	
	private JPanel adminPanel;
	
	private JPanel staffPanel;
	
	/**
	 * Constructor class for the staff gui
	 * @param theUser The staff user object
	 */

	public StaffGUI(User theUser) {
		myStaff = (Staff) theUser;
		myFrame = new JFrame("Hello" + myStaff.getName());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Constructor class for testing purposes on staffGUI.
	 */

	public StaffGUI() {
		myFrame = new JFrame("Hello");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Starts setting up the layout of the staff gui.
	 */

	public void start() {
		CardLayout cLayout = new CardLayout();
		myFrame.setSize(400, 400);
		myFrame.setLayout(cLayout);
		
		staffScreen();
		
		myFrame.setVisible(true);
	}
	
	/**
	 * The main frame layout for the staff gui.
	 */

	private void staffScreen() {
		JPanel staffPanel = new JPanel();
		JButton viewCalender = new JButton("View Calender");
		JButton adminTools = new JButton("View Admin Tools");
		viewCalender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AuctionCalenderGUI auctionCalender = new AuctionCalenderGUI();
				auctionCalender.start();
			}
			
		});
		adminTools.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffPanel.setVisible(false);
				adminGUI();
			}
			
		});
		staffPanel.setLayout(new FlowLayout());
		staffPanel.add(viewCalender);
		staffPanel.add(adminTools);
		myFrame.add(staffPanel);
		staffPanel.setVisible(true);
	}
	
	/**
	 * The admin gui when the staff member wants to changes max number of auctions.
	 */
	
	private void adminGUI() {
		JPanel adminPanel = new JPanel();
		JPanel editPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton changeMaxAuctions = new JButton("Update Max Auctions");
		JButton cancel = new JButton("Cancel Changes");
		changeMaxAuctions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adminPanel.setVisible(false);
				staffScreen();
			}
			
		});
		adminPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new FlowLayout());
		JTextField numberOfAuction = new JTextField();
		adminPanel.add(editPanel, BorderLayout.CENTER);
		adminPanel.add(buttonPanel, BorderLayout.SOUTH);
		editPanel.add(numberOfAuction);
		buttonPanel.add(changeMaxAuctions);
		buttonPanel.add(cancel);
		myFrame.add(adminPanel);
		adminPanel.setVisible(true);
	}

}
