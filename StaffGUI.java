import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	final static String STAFFPANEL = "Staff Page";
	
	private AuctionCalendar aucCal;

	private Staff myStaff;
	
	private JPanel myContainer;

	private CardLayout myLayout;
	
	private JPanel staffPanel;
	
	private JPanel adminPanel;
	
	private JPanel mainPanel;

	private JTextField numberOfAuction;
	
	/**
	 * Constructor class for the staff gui
	 * @param theUser The staff user object
	 * @param cLayout 
	 * @param containerPanel 
	 */

	public StaffGUI(User theUser, JPanel containerPanel, CardLayout cLayout) {
		myStaff = (Staff) theUser;
		myContainer = containerPanel;
		myLayout = cLayout;
		aucCal = new AuctionCalendar(new AuctionDate());
		staffPanel = new JPanel();
	}

	/**
	 * Starts setting up the layout of the staff gui.
	 */

	public void start() {
		staffScreen();
		myContainer.add(staffPanel, STAFFPANEL);
		myLayout.show(myContainer, STAFFPANEL);
	}
	
	/**
	 * The main frame layout for the staff gui.
	 */

	private void staffScreen() {
		JPanel mainPanel = new JPanel();
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
				mainPanel.setVisible(false);
				adminGUI();
			}
			
		});
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(viewCalender);
		mainPanel.add(adminTools);
		mainPanel.add(new JLabel("Max Auctions: " + aucCal.getMaxAuctions()));
		myContainer.add(mainPanel);
		mainPanel.setVisible(true);
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
		JLabel errorLabel = new JLabel();
		editPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		numberOfAuction = new JTextField("Change max auctions");
		c.ipadx = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        editPanel.add(numberOfAuction, c);
		changeMaxAuctions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String string = "";
				if (numberOfAuction.getText() != null) {
					string = numberOfAuction.getText();
				}
				 try {
				      int i = Integer.parseInt(string.trim());
				      aucCal.setMaxAuctions(i);
				      adminPanel.setVisible(false);
				      staffScreen();
				      myContainer.repaint();
				    }
				    catch (NumberFormatException nfe)
				    {
				      errorLabel.setText("Not a valid input");
				      errorLabel.setVisible(true);
				      myContainer.repaint();
				    }
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
		adminPanel.add(editPanel, BorderLayout.CENTER);
		adminPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(changeMaxAuctions);
		buttonPanel.add(cancel);
		myContainer.add(adminPanel);
		adminPanel.setVisible(true);
	}

}
