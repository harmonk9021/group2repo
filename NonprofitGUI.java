import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The GUI for users that are represented as Nonprofit
 * organizations in the system.
 * 
 * @author Jacob Ackerman
 * @version 11.29.2016.001A
 */

public class NonprofitGUI {
	
	private Nonprofit myNPO;
	
	private JFrame myFrame;
        
        private JPanel myMainScreen;
        private JPanel myRequestScreen;
        
        private static final JTextArea NO_AUCTION_WELCOME = new JTextArea("Welcome, $User\n"
                + "\nYou currently have no upcoming auction in our system.\n"
                + "Please click \"Request Auction\" if you would like to request an auction.");
        private static final JTextArea HAS_AUCTION_WELCOME = new JTextArea("Welcome, $User\n"
                + "\nYour auction is scheduled to be held on $AuctionDate.\n"
                + "Click \"View Auction\" if you wish to review or update any\n"
                + "information or item listings.");
        private static final JTextArea AUCTION_REQUEST_HELP = new JTextArea("These are the next 30 days starting from today."
                + "\nDays that are available will have a clickable button."
                + "\nPlease select an available day to continue.");
        
        private ButtonBuilder myOptionButtons;

	public NonprofitGUI(User theUser) {
		myNPO = (Nonprofit) theUser;
		myFrame = new JFrame("Hello" /*+ myNPO.getName()*/);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myMainScreen = new JPanel();
                myRequestScreen = new JPanel();
                NO_AUCTION_WELCOME.setEditable(false);
                HAS_AUCTION_WELCOME.setEditable(false);
	}

	public void start() {
		BorderLayout bLayout = new BorderLayout();
		myFrame.setSize(400, 400);
		myFrame.setLayout(bLayout);
                myOptionButtons = new ButtonBuilder(new String[] {"Request Auction", "View Auction", "Logout"});
		
		NonprofitMainScreen();
		
		myFrame.setVisible(true);
	}

	private void NonprofitMainScreen() {
		// TODO Auto-generated method stub
		myMainScreen.setLayout(new FlowLayout());
                myOptionButtons.buildButtons();
                myMainScreen.add(myOptionButtons);
                myOptionButtons.getButton(1).setEnabled(false);
                myFrame.add(myMainScreen, BorderLayout.SOUTH);
                myFrame.add(NO_AUCTION_WELCOME, BorderLayout.CENTER);
                myOptionButtons.getButton(0).addActionListener(new RequestAuction());
	}
        
        private void NonprofitAuctionRequestScreen()
        {
            myFrame.remove(NO_AUCTION_WELCOME);
            myOptionButtons.getButton(0).setEnabled(false);
            myFrame.add(myRequestScreen, BorderLayout.CENTER);
            myFrame.add(AUCTION_REQUEST_HELP, BorderLayout.NORTH);
            InitializeRequestScreen();
            myFrame.repaint();
            myRequestScreen.setVisible(true);
            myRequestScreen.repaint();
        }
        
        private void InitializeRequestScreen()
        {
            GridLayout gLayout = new GridLayout(0, 7);
            //gLayout.setColumns(7);
            //gLayout.setRows(6);
            myRequestScreen.setLayout(gLayout);
            Calendar cal = Calendar.getInstance();
            AuctionDate aucCal = new AuctionDate();
            int[] dates = aucCal.getNextXDays(30);
            int d = 0;
            String[] days = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};
            for (int i = 0; i < 7; i++)
            {
                myRequestScreen.add(new JLabel(days[i]));
            }
            for (int i = 1; i < cal.get(Calendar.DAY_OF_WEEK); i++)
            {
                myRequestScreen.add(new JPanel());
            }
            for (int x = cal.get(Calendar.DAY_OF_WEEK); x < dates.length; x++)
            {
                // set up first row of a visual calendar
                JPanel calDate = new JPanel();
                calDate.setBorder(BorderFactory.createTitledBorder("" + dates[d]));
                myRequestScreen.add(calDate);
                d++;
                calDate.setVisible(true);
                calDate.repaint();
            }
            
            
        }

        class RequestAuction implements ActionListener
        {

        @Override
        public void actionPerformed(ActionEvent e) {
            NonprofitAuctionRequestScreen();
            myFrame.repaint();
        }
            
        }
}
