import java.awt.BorderLayout;
import java.awt.CardLayout;
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
	
	/*
	 * These static Strings are the titles of each of the cards, must make a new one
	 * for every new page you intend on making.
	 */
	final static String NONPROFITCARD = "Nonprofit Welcome Card";
	final static String NONPROFITPANEL = "Nonprofit Page";
	final static String NONPROFITREQUESTPANEL = "Nonprofit Auction Request Page";
	
	private Nonprofit myNPO;
	
	/*
	 * Local container is a JPanel with CardLayout that will hold the various different JPanels.
	 * This JPanel container will be added to the myMainScreen CENTER and will change the views
	 * while retaining the buttons along the bottom.
	 */
	private JPanel myLocalContainer;
	private CardLayout myLocalCLayout;
	
	/*
	 * These containers and CardLayout are from the main GUI and are to be used when logging out
	 * and when first entering this GUI only.
	 */
	private JPanel myMainContainer;
	private CardLayout myMainCLayout;
        
    private JPanel myMainScreen;	//Contains myLocalContainer in BorderLayout.CENTER, myOptionButtons stay along the bottom
    private JPanel myWelcomeScreen;	//JPanel that should contain the various Welcome JTextAreas. To be added in myLocalContainer only.
    private JPanel myRequestScreen;	//JPanel that contains the Submit Auction Request form. To be added in myLocalContainer only.
    private JPanel myRequestCalendarScreen;	//JPanel that helps create myRequestScreen. To be added to myRequestScreen only.
        
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

    /**
     * Constructor for NonprofitGUI.
     * @param theUser is the Nonprofit user.
     * @param theContainer is the JPanel passed in from the main GUI, allows this GUI to use the same JFrame.
     * @param theCLayout is the CardLayout from the main GUI, allows this GUI to use the same JFrame.
     */
	public NonprofitGUI(User theUser, JPanel theContainer, CardLayout theCLayout) {
		myNPO = (Nonprofit) theUser;
        myMainContainer = theContainer;
        myMainCLayout = theCLayout;
        
        myLocalContainer = new JPanel();
        myLocalCLayout = new CardLayout();
		myMainScreen = new JPanel();
		myWelcomeScreen = new JPanel(new BorderLayout());
        myRequestScreen = new JPanel();
        myRequestCalendarScreen = new JPanel();
        NO_AUCTION_WELCOME.setEditable(false);
        HAS_AUCTION_WELCOME.setEditable(false);
	}

	/**
	 * This method creates the Nonprofit GUI.
	 * 
	 * Creates the buttons with ButtonBuilder.
	 * Calls NonprofitScreenController which creates the main screen, adds all panels to the local CardLayout.
	 * @see NonprofitScreenController()
	 * 
	 * Once myMainScreen is made, adds it to the Main Container and Main CardLayout for use with the main JFrame.
	 */
	public void start() {
        myOptionButtons = new ButtonBuilder(new String[] {"Request Auction", "View Auction", "Logout"});
		
		NonprofitScreenController();
		
		myMainContainer.add(myMainScreen, NONPROFITCARD);
		myMainCLayout.show(myMainContainer, NONPROFITCARD);
		
	}

	
	/**
	 * This is the main method that creates the structure for NonprofitGUI.
	 * 
	 * myMainScreen is a JPanel that is always showing. Contains myOptionButtons in BorderLayout.SOUTH.
	 * Contains myLocalContainer in BorderLayout.CENTER.
	 * 
	 * myLocalContainer holds all of the different panels that will need to change in this GUI.
	 * myLocalCLayout is used to swap between the different panels in myLocalContainer so that myMainScreen can
	 * stay the same and allow the buttons to always be present.
	 * 
	 * When making new JPanels, you MUST create a static String that represents the new JPanel,
	 * and you must ONLY add the new JPanel to myLocalContainer.
	 * 
	 * To Add a JPanel to myLocalContainer,
	 * myLocalContainer.add(XXXX, YYYY)		XXXX is the variable for the JPanel
	 * 										YYYY is the static String created to describe the panel.
	 * 
	 * For ActionListeners, to switch to a specific JPanel, you must call
	 * myLocalCLayout.show(myLocalContainer, XXXXX)		XXXXX is the static String you created to describe the panel.
	 */
	private void NonprofitScreenController() {
		myMainScreen.setLayout(new BorderLayout());
        myOptionButtons.buildButtons();
        myMainScreen.add(myOptionButtons, BorderLayout.SOUTH);
        myOptionButtons.getButton(1).setEnabled(false);
//        myFrame.add(myMainScreen, BorderLayout.SOUTH);
        myOptionButtons.getButton(0).addActionListener(new RequestAuction());
        
        NonprofitWelcomeScreen();
        NonprofitAuctionRequestScreen();
		
		myLocalContainer.setLayout(myLocalCLayout);
		myLocalContainer.add(myWelcomeScreen, NONPROFITPANEL);
		myLocalContainer.add(myRequestScreen, NONPROFITREQUESTPANEL);
		myLocalCLayout.show(myLocalContainer, NONPROFITPANEL);
		
		myMainScreen.add(myLocalContainer, BorderLayout.CENTER);
        
	}
	
	/**
	 * This method creates the JPanel which should contain the Welcome text areas.
	 */
	private void NonprofitWelcomeScreen() {
		myWelcomeScreen.add(NO_AUCTION_WELCOME, BorderLayout.CENTER);
	}
        
	/**
	 * This method creates the Auction Request JPanel.
	 * Puts the text area in BorderLayout.NORTH with the prompt,
	 * Puts the calendar in BorderLayout.CENTER
	 */
    private void NonprofitAuctionRequestScreen()
    {
        myRequestScreen.setLayout(new BorderLayout());
        
        InitializeRequestScreen();
        myRequestScreen.add(AUCTION_REQUEST_HELP, BorderLayout.NORTH);
        myRequestScreen.add(myRequestCalendarScreen, BorderLayout.CENTER);
        myRequestScreen.setVisible(true);
    }
        
    /**
     * Basically the same as what you wrote,
     * Instead of adding to myRequestScreen directly, it adds to a separate panel
     * so that myRequestScreen can be formatted properly
     */
    private void InitializeRequestScreen()
    {
        GridLayout gLayout = new GridLayout(0, 7);
        //gLayout.setColumns(7);
        //gLayout.setRows(6);
        myRequestCalendarScreen.setLayout(gLayout);
        Calendar cal = Calendar.getInstance();
        AuctionDate aucCal = new AuctionDate();
        int[] dates = aucCal.getNextXDays(30);
        int d = 0;
        String[] days = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};
        for (int i = 0; i < 7; i++)
        {
        	myRequestCalendarScreen.add(new JLabel(days[i]));
        }
        for (int i = 1; i < cal.get(Calendar.DAY_OF_WEEK); i++)
        {
        	myRequestCalendarScreen.add(new JPanel());
        }
        for (int x = cal.get(Calendar.DAY_OF_WEEK); x < dates.length; x++)
        {
            // set up first row of a visual calendar
            JPanel calDate = new JPanel();
            calDate.setBorder(BorderFactory.createTitledBorder("" + dates[d]));
            myRequestCalendarScreen.add(calDate);
            d++;
            calDate.setVisible(true);
            calDate.repaint();
        }
            
            
    }

    class RequestAuction implements ActionListener
    {

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		myLocalCLayout.show(myLocalContainer, NONPROFITREQUESTPANEL); //Switches to Auction Request panel via static String name
    		//This ActionListener should also disable the buttons
    	}
            
    }
}