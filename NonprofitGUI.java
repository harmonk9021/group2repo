import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The GUI for users that are represented as Nonprofit
 * organizations in the system.
 * 
 * @author Jacob Ackerman
 * @version 12.1.2016.001A
 */

public class NonprofitGUI {
	
	/*
	 * These static Strings are the titles of each of the cards, must make a new one
	 * for every new page you intend on making.
	 */
	final static String NONPROFITCARD = "Nonprofit Welcome Card";
	final static String NONPROFITPANEL = "Nonprofit Page";
	final static String NONPROFITREQUESTPANEL = "Nonprofit Auction Request Page";
        final static String NONPROFIT_AUCTION_FORM = "Nonprofit Auction Request Form";
	
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
    private JPanel myRequestFormScreen;
        
    private JTextArea NO_AUCTION_WELCOME;
    private JTextArea HAS_AUCTION_WELCOME;
    private static final JTextArea AUCTION_REQUEST_HELP = new JTextArea("These are the next 30 days starting from today."
                + "\nDays that are available will have a clickable button."
                + "\nPlease select an available day to continue.");
    
    private JTextField myAuctionName;
    private JTextField myContactPerson;
    private JTextField myItemCount;
    private JTextField myDescription;
    private JTextField myComments;
    private JComboBox myStartHour;
        
    private ButtonBuilder myOptionButtons;
    private AuctionCalendar myCal;
    private int[] myDate;     //Used to capture the date the user picks on the calendar. 0 = year, 1 = month, 2 = day

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
        myDate = new int[3];
        
        myLocalContainer = new JPanel();
        myLocalCLayout = new CardLayout();
		myMainScreen = new JPanel();
		myWelcomeScreen = new JPanel(new BorderLayout());
        myRequestScreen = new JPanel();
        myRequestCalendarScreen = new JPanel();
        myRequestFormScreen = new JPanel();
        
        myCal = new AuctionCalendar(new AuctionDate());
        
        NO_AUCTION_WELCOME = new JTextArea("Welcome, " + myNPO.getName() + "\n"
                + "\nYou currently have no upcoming auction in our system.\n"
                + "Please click \"Request Auction\" if you would like to request an auction.");
        
        initializeHasAuctionMessage();
        
        NO_AUCTION_WELCOME.setEditable(false);
        
        myAuctionName = new JTextField();
        myContactPerson = new JTextField();
        myItemCount = new JTextField();
        myDescription = new JTextField();
        myComments = new JTextField();
        myStartHour = new JComboBox(new String[] {"Select Time", "------------", "Midnight", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am", "11am", "Noon", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm"});
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
        
        private void initializeHasAuctionMessage()
        {
            ArrayList<Auction> auctionList;
            auctionList = (ArrayList<Auction>) myCal.getAuctions();
            
            
            
            HAS_AUCTION_WELCOME = new JTextArea("Welcome, " + myNPO.getName() + "\n"
                + "\nYour auction is scheduled to be held on $AuctionDate.\n"
                + "Click \"View Auction\" if you wish to review or update any\n"
                + "information or item listings.");
            
            HAS_AUCTION_WELCOME.setEditable(false);
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
        initializeAuctionRequestForm();
		
		myLocalContainer.setLayout(myLocalCLayout);
		myLocalContainer.add(myWelcomeScreen, NONPROFITPANEL);
		myLocalContainer.add(myRequestScreen, NONPROFITREQUESTPANEL);
                myLocalContainer.add(myRequestFormScreen, NONPROFIT_AUCTION_FORM);
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
        
        
        //ArrayList<Auction> auctions = (ArrayList<Auction>) myCal.getAuctions();
        
        int[] dates = aucCal.getNextXDays(30);
        int endCurrMonth = findMonthEnd(dates);
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
        for (int x = 0; x < dates.length; x++)
        {
            // set up first row of a visual calendar
            JPanel calDate = new JPanel();
            calDate.setBorder(BorderFactory.createTitledBorder("" + dates[d]));
            myRequestCalendarScreen.add(calDate);
            final int i = dates[d];
            final int test = d;
            d++;
            calDate.setVisible(true);
            calDate.repaint();
            
            JButton button = new JButton("X");
            
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    Calendar cal = Calendar.getInstance();
                    myDate[0] = cal.get(Calendar.YEAR);
                    System.out.println(cal.get(Calendar.YEAR));
                    if (test >= endCurrMonth)
                    {
                        myDate[1] = cal.get(Calendar.MONTH)+2;
                    }
                    else
                    {
                        myDate[1] = cal.get(Calendar.MONTH)+1;
                    }
                    System.out.println(myDate[1]);
                    
                    myDate[2] = i;
                    System.out.println(i);
                    
                    myLocalCLayout.show(myLocalContainer, NONPROFIT_AUCTION_FORM);
                }
                
            });
            calDate.add(button);
        }
            
            
    }
    
    /*
    private int findAuctionOnDay(int theDay)
    {
        int count = 0;
        AuctionDate currDay = new AuctionDate();
        
    }
    */
    
    private void initializeAuctionRequestForm()
    {
        myRequestFormScreen.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridwidth = 1;
        c.gridx = 0;	
        c.gridy = 0;
        myRequestFormScreen.add(new JLabel("Auction name: "), c);
        c.gridx = 0;	
        c.gridy = 1;
        myRequestFormScreen.add(new JLabel("Contact person: "), c);
        c.gridx = 0;	
        c.gridy = 2;
        myRequestFormScreen.add(new JLabel("Auction description: "), c);
        c.gridx = 0;	
        c.gridy = 3;
        myRequestFormScreen.add(new JLabel("Comments: "), c);
        c.gridx = 0;	
        c.gridy = 4;
        myRequestFormScreen.add(new JLabel("Estimated item count: "), c);
        c.gridx = 0;	
        c.gridy = 5;
        myRequestFormScreen.add(new JLabel("Auction start time: "), c);
        
        c.ipadx = 200;
        c.gridx = 1;	
        c.gridy = 0;
        myRequestFormScreen.add(myAuctionName, c);
        c.gridx = 1;	
        c.gridy = 1;
        myRequestFormScreen.add(myContactPerson, c);
        c.gridx = 1;	
        c.gridy = 2;
        myRequestFormScreen.add(myDescription, c);
        c.gridx = 1;	
        c.gridy = 3;
        myRequestFormScreen.add(myComments, c);
        c.gridx = 1;	
        c.gridy = 4;
        c.ipadx = 50;
        myRequestFormScreen.add(myItemCount, c);
        c.gridx = 1;	
        c.gridy = 5;
        c.ipadx = 0;
        myRequestFormScreen.add(myStartHour, c);
        
        
        //JPanel textTags = new JPanel();
        //JPanel formBoxes = new JPanel();
        //formBoxes.setLayout(new BoxLayout(formBoxes, BoxLayout.Y_AXIS));
        //textTags.setLayout(new BoxLayout(textTags, BoxLayout.Y_AXIS));
        
        //myRequestFormScreen.add(textTags, BorderLayout.WEST);
        //myRequestFormScreen.add(formBoxes, BorderLayout.EAST);
        /*
        formBoxes.add(myAuctionName);
        formBoxes.add(myContactPerson);
        formBoxes.add(myDescription);
        formBoxes.add(myComments);
        formBoxes.add(myItemCount);
        formBoxes.add(myStartHour);
        
        textTags.add(new JLabel("Auction name: "));
        textTags.add(new JLabel("Contact person: "));
        textTags.add(new JLabel("Auction description: "));
        textTags.add(new JLabel("Comments: "));
        textTags.add(new JLabel("Estimated item count: "));
        textTags.add(new JLabel("Auction start time: "));
        */
    }
    
    private int findMonthEnd(int[] dates)
    {
        if (dates[0] == 1)
            return 99;
        for (int i = 1; i < dates.length; i++)
        {
            if (dates[i] < dates[i-1])
            {
                //System.out.println("Found: " + i);
                return i;
            }
        }
        return 99;
    }
    

    class RequestAuction implements ActionListener
    {

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		myLocalCLayout.show(myLocalContainer, NONPROFITREQUESTPANEL); //Switches to Auction Request panel via static String name
    		myOptionButtons.getButton(0).setEnabled(false);
                
    	}
            
    }
}