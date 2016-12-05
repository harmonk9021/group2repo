import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The GUI for viewing the upcoming auctions in
 * the next month.
 * 
 * @author Andrew Dinh
 * @version 11/29/2016
 */

public class AuctionCalenderGUI {
	
	private JFrame myFrame;
	
	private AuctionCalendar aucCal;
	
	/**
	 * Constructor for the auction gui.
	 */

	public AuctionCalenderGUI() {
		myFrame = new JFrame("Calender");
		myFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		aucCal = new AuctionCalendar(new AuctionDate(), "Auctions.ser");
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
//		createDays();
		AuctionDate date = new AuctionDate();
		Calendar cal = Calendar.getInstance();
		int[] dates = date.getNextXDays(30);
	    int d = 0;
	    String[] days = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};
	    for (int i = 0; i < 7; i++)
	    {
	        myFrame.add(new JLabel(days[i]));
	    }
	    for (int i = 1; i < cal.get(Calendar.DAY_OF_WEEK); i++)
	    {
	        myFrame.add(new JPanel());
	    }
	    for (int x = 0; x < dates.length; x++)
	    {
            JPanel calDate = new JPanel();
            calDate.setBorder(BorderFactory.createTitledBorder("" + dates[d]));
            calDate.add(new JLabel(aucCal.getAuctionsOnDate(new AuctionDate(date.getYear(), date.getMonth(), dates[d], date.getHour())) + ""));
            myFrame.add(calDate);
            d++;
	    }
	}
}
