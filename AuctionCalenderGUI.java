import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The GUI for viewing the upcoming auctions in
 * the next month.
 * 
 * @author Andrew Dinh
 * @version 11/29/2016
 */

public class AuctionCalenderGUI {
	
	private JFrame myFrame;
	
	/**
	 * Constructor for the auction gui.
	 */

	public AuctionCalenderGUI() {
		myFrame = new JFrame("Calender");
		myFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	/**
	 * Starts setting up the layout of the calender.
	 */
	
	public void start() {
		GridLayout gLayout = new GridLayout(6, 7);
		myFrame.setSize(600, 400);
		myFrame.setLayout(gLayout);
		
		CalenderScreen();
		
		myFrame.setVisible(true);
	}
	
	/**
	 * Adds in the days and auctions within those days
	 * for the upcoming month.
	 */

	private void CalenderScreen() {
		createDays();
		for (int i = 0; i < 35; i++) {
			myFrame.add(new JLabel("day"));
		}
	}
	
	/**
	 * Short method to add the days of the week at the
	 * top of the calender.
	 */

	private void createDays() {
		myFrame.add(new JLabel("Sunday"));
		myFrame.add(new JLabel("Monday"));
		myFrame.add(new JLabel("Tuesday"));
		myFrame.add(new JLabel("Wednesday"));
		myFrame.add(new JLabel("Thursday"));
		myFrame.add(new JLabel("Friday"));
		myFrame.add(new JLabel("Saturday"));
	}
}
