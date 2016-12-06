//import static BidderGUI.COLUMNNUMBERS;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
    private final static String INPUTPANEL = "Login Page";
    private final static String NONPROFITCARD = "Nonprofit Welcome Card";
	private final static String NONPROFITPANEL = "Nonprofit Page";
	private final static String NONPROFITREQUESTPANEL = "Nonprofit Auction Request Page";
        private final static String NONPROFIT_AUCTION_FORM = "Nonprofit Auction Request Form";
        private static final String NP_CONFIRMATION_SCREEN = "NP Confirmation Screen";
        private static final String NP_AUCTION_VIEW_SCREEN = "NP Auction View";
        private static final String NP_ITEM_ADD_FORM = "NP Item Add Form";
	
	private Nonprofit myNPO;
        
        final static int IDWIDTH = 20;
	final static int NAMEWIDTH = 100;
	final static int CONDITIONWIDTH = 30;
	final static int MINBIDWIDTH = 30;
	final static int MYBIDWIDTH = 30;
	final static int COLUMNNUMBERS = 4;	
	
	final static String[] COLUMNNAMES = {"ID #",
            							 "Item Name",
            							 "Condition",
            							 "Min. Bid",
        };
	
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
    private JPanel myConfirmation;
    private JPanel myViewAuctionScreen;
    private JPanel myAddItemForm;
    private JScrollPane scrollPane;
        
    private JTextArea NO_AUCTION_WELCOME;
    private JTextArea HAS_AUCTION_WELCOME;
    private static final JTextArea AUCTION_REQUEST_HELP = new JTextArea("These are the next 30 days starting from today."
                + "\nDays that are available will have a clickable button."
                + "\nPlease select an available day to continue.");
    private JTextArea CONFIRMATION_MESSAGE;
    
    private JTextField myAuctionName;
    private JTextField myContactPerson;
    private JTextField myItemCount;
    private JTextField myDescription;
    private JTextField myComments;
    private JComboBox myStartHour;
    private JTable myItemTable;
    /*
    name = theName;
		donorName = "";
		description = "";
		quantity = 0;
		startingBid = 0;
		condition = "";
		size = "";
		comments = "";
    */
    
    private JTextField myItemName;
    private JTextField myItemDonor;
    private JTextField myItemDesc;
    private JSpinner myItemQty;
    private JSpinner myItemStartBid;
    private JComboBox myItemCnd;
    private JComboBox myItemSize;
    private JTextField myItemComments;
        
    private ButtonBuilder myOptionButtons;
    private ButtonBuilder viewAuctionButtons;
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
        viewAuctionButtons = new ButtonBuilder(new String[] {"Cancel Auction", "Add Item", "Remove Item"});
        myCal = new AuctionCalendar(new AuctionDate(), "Auctions.ser");
        //CONFIRMATION_MESSAGE = new JTextArea();
        myConfirmation = new JPanel();
        myViewAuctionScreen = new JPanel();
        myAddItemForm = new JPanel();
        
        NO_AUCTION_WELCOME = new JTextArea("Welcome, " + myNPO.getName() + "\n"
                + "\nYou currently have no upcoming auction in our system.\n"
                + "Please click \"Request Auction\" if you would like to request an auction.");
        
        myItemName = new JTextField();
        myItemDonor = new JTextField();
        myItemDesc = new JTextField();
        myItemQty = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        myItemStartBid = new JSpinner(new SpinnerNumberModel(0, 0, 9999.99, 1));
        myItemCnd = new JComboBox(new String[] {"Select Condition", "-----------", "New", "Like new", "Good", "Fair", "Poor", "Bad"});
        myItemSize = new JComboBox(new String[] {"Select Size", "------------", "Tiny", "Small", "Medium", "Large", "Huge"});
        myItemComments = new JTextField();
        
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
            //ArrayList<Auction> auctionList;
            //auctionList = (ArrayList<Auction>) myCal.getAuctions();
            String auctionDate;
            //Auction currAuction = myCal.getAuction(myNPO.getUserName());
            Auction currAuction = myCal.getAuction(myNPO.getUserName());
            System.out.println("result: " + myCal.getAuction(myNPO.getUserName()));
            if (currAuction == null)
            {
                auctionDate = null;
            }
            else
            {
                auctionDate = currAuction.getDate().toString();
                myOptionButtons.getButton(0).setEnabled(false);
                myOptionButtons.getButton(1).setEnabled(true);
            }
            
            
            
            HAS_AUCTION_WELCOME = new JTextArea("Welcome, " + myNPO.getName() + "\n"
                + "\nYour auction is scheduled to be held on " + auctionDate + ".\n"
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
        myOptionButtons.getButton(1).addActionListener(new ViewAuction());
        myOptionButtons.getButton(2).addActionListener(new LogOut());
        
        NonprofitWelcomeScreen();
        NonprofitAuctionRequestScreen();
        initializeAuctionRequestForm();
        if (myCal.getAuction(myNPO.getUserName()) !=  null)
        {
            initializeViewAuction();
        }
        initializeAddItemForm();
        
		
		myLocalContainer.setLayout(myLocalCLayout);
		myLocalContainer.add(myWelcomeScreen, NONPROFITPANEL);
		myLocalContainer.add(myRequestScreen, NONPROFITREQUESTPANEL);
                myLocalContainer.add(myRequestFormScreen, NONPROFIT_AUCTION_FORM);
                myLocalContainer.add(myConfirmation, NP_CONFIRMATION_SCREEN);
                myLocalContainer.add(myViewAuctionScreen, NP_AUCTION_VIEW_SCREEN);
                myLocalContainer.add(myAddItemForm, NP_ITEM_ADD_FORM);
                
		myLocalCLayout.show(myLocalContainer, NONPROFITPANEL); // Inital Screen
                
		
		myMainScreen.add(myLocalContainer, BorderLayout.CENTER);
        
	}
	
	/**
	 * This method creates the JPanel which should contain the Welcome text areas.
	 */
	private void NonprofitWelcomeScreen() {
            //System.out.println(myNPO.getAuction());
            System.out.println("result: " + myCal.getAuction(myNPO.getUserName()));
            //System.out.println(myNPO.getUserName());
            if (myCal.getAuction(myNPO.getUserName()) == null)
		myWelcomeScreen.add(NO_AUCTION_WELCOME, BorderLayout.CENTER);
            else
            {
                initializeHasAuctionMessage();
                myWelcomeScreen.add(HAS_AUCTION_WELCOME, BorderLayout.CENTER);
            }
	}
        
    private void initializeViewAuction()
    {
        myViewAuctionScreen.setLayout(new BorderLayout());
        Auction auction = myCal.getAuction(myNPO.getUserName());
        JTextArea info = new JTextArea("Here is the details of your upcoming auction, " + auction.getAuctionName() + ".\n"
                + "You may review and make changes here.");
        info.setEditable(false);
        myViewAuctionScreen.add(info, BorderLayout.NORTH);
        //ButtonBuilder viewAuctionButtons = new ButtonBuilder(new String[] {"Cancel Auction", "Add Item", "Remove Item"});
        viewAuctionButtons.buildButtons();
        myViewAuctionScreen.add(viewAuctionButtons, BorderLayout.SOUTH);
        boolean result = NPViewItemsScreen(auction/*, viewAuctionButtons*/);
        viewAuctionButtons.getButton(2).setEnabled(result);
        
        viewAuctionButtons.getButton(0).addActionListener(new RemoveAuction());
        viewAuctionButtons.getButton(1).addActionListener(new AddItemForm());
        viewAuctionButtons.getButton(2).addActionListener(new RemoveItem());
    }
    
    private void initializeAddItemForm()
    {
        myAddItemForm.setLayout(new BorderLayout());
        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        myAddItemForm.add(form, BorderLayout.CENTER);
        JLabel info = new JLabel("This is the item submission form. Fields marked with a * are required.");
        myAddItemForm.add(info, BorderLayout.NORTH);
        GridBagConstraints c = new GridBagConstraints();
        JButton submitButton = new JButton("Submit Item");
        submitButton.addActionListener(new SubmitItem());
        
        c.gridwidth = 1;
        c.gridx = 0;	
        c.gridy = 0;
        form.add(new JLabel("Item name: *"), c);
        c.gridx = 0;	
        c.gridy = 1;
        form.add(new JLabel("Donor: "), c);
        c.gridx = 0;	
        c.gridy = 2;
        form.add(new JLabel("Description: *"), c);
        c.gridx = 0;	
        c.gridy = 3;
        form.add(new JLabel("Quantity: "), c);
        c.gridx = 0;	
        c.gridy = 4;
        form.add(new JLabel("Starting bid: *"), c);
        c.gridx = 0;	
        c.gridy = 5;
        form.add(new JLabel("Condition: *"), c);
        c.gridx = 0;	
        c.gridy = 6;
        form.add(new JLabel("Size: *"), c);
        c.gridx = 0;	
        c.gridy = 7;
        form.add(new JLabel("Comments: "), c);
        
        c.ipadx = 200;
        c.gridx = 1;	
        c.gridy = 0;
        form.add(myItemName, c);
        c.gridx = 1;	
        c.gridy = 1;
        form.add(myItemDonor, c);
        c.gridx = 1;	
        c.gridy = 2;
        form.add(myItemDesc, c);
        c.gridx = 1;	
        c.gridy = 3;
        c.ipadx = 50;
        form.add(myItemQty, c);
        c.gridx = 1;	
        c.gridy = 4;
        //c.ipadx = 100;
        form.add(myItemStartBid, c);
        c.gridx = 1;	
        c.gridy = 5;
        c.ipadx = 0;
        form.add(myItemCnd, c);
        c.gridx = 1;	
        c.gridy = 6;
        form.add(myItemSize, c);
        c.gridx = 1;	
        c.gridy = 7;
        c.ipadx = 200;
        form.add(myItemComments, c);
        
        c.gridx = 1;	
        c.gridy = 8;
        c.ipadx = 0;
        c.ipady = 50;
        form.add(new JPanel(), c);
        c.gridx = 1;	
        c.gridy = 9;
        c.ipady = 0;
        form.add(submitButton, c);
    }
    
    private boolean NPViewItemsScreen(Auction theAuction/*, ButtonBuilder theButtons*/) {
		List<Item> myItems = theAuction.getItems();
		
		Object[][] data = new Object[myItems.size()][COLUMNNUMBERS];
		int itemID = 1;
		//for (int k = 0; k < COLUMNNUMBERS; k++) {
		//	data[0][k] = COLUMNNAMES[k];
		//}
		for (Item i : myItems) {
			for (int j = 0; j < COLUMNNUMBERS; j++) {
				if (j == 0) data[itemID-1][j] = itemID;
				if (j == 1) data[itemID-1][j] = i.getName();
				if (j == 2) data[itemID-1][j] = i.getCondition();
				if (j == 3) data[itemID-1][j] = "$" + i.getStartingBid();
				/*
                                if (j == 4) {
					if (myBidder.viewBids().containsKey(i)) {
						data[itemID][j] = myBidder.viewBids().get(i);
					} else {
						data[itemID][j] = null;
					}
				}
                                */
			}
			itemID++;
		}
		//if (myItems.size() == 0)
                    //theButtons.getButton(2).setEnabled(false);
		myItemTable = new JTable(data, COLUMNNAMES);
//		myViewItemsScreen.setLayout(new BorderLayout());
//		myViewItemsScreen.add(myItemTable, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane(myItemTable);
		//myViewItemsScreen.setLayout(new BorderLayout());
		myViewAuctionScreen.add(scrollPane, BorderLayout.CENTER);
                
                return (myItems.size() > 0);
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
            if (x >= 7)
            {
            JButton button = new JButton("X");
            
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    Calendar cal = Calendar.getInstance();
                    myDate[0] = cal.get(Calendar.YEAR);
                    
                    if (test >= endCurrMonth)
                    {
                        if (cal.get(Calendar.MONTH)+2 <= 12)
                            myDate[1] = cal.get(Calendar.MONTH)+2;
                        else
                        {
                            myDate[1] = 1;
                            myDate[0] = cal.get(Calendar.YEAR)+1;
                        }
                        System.out.println(myDate[0]);
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
            //cal = Calendar.getInstance();
                    myDate[0] = cal.get(Calendar.YEAR);
                    //System.out.println(cal.get(Calendar.YEAR));
                    if (test >= endCurrMonth)
                    {
                        if (cal.get(Calendar.MONTH)+2 <= 12)
                            myDate[1] = cal.get(Calendar.MONTH)+2;
                        else
                        {
                            myDate[1] = 1;
                            myDate[0] = cal.get(Calendar.YEAR)+1;
                        }
                    }
                    else
                    {
                        myDate[1] = cal.get(Calendar.MONTH)+1;
                    }
                    //System.out.println(myDate[1]);
                    
                    myDate[2] = i;
                    //System.out.println(i);
            if (isTwoAuctionsOnDay(new AuctionDate(myDate[0], myDate[1], myDate[2], 11)))
                button.setEnabled(false);
            
            calDate.add(button);
            }
        }
            
            
    }
    
    private boolean isTwoAuctionsOnDay(AuctionDate theDate)
    {
        List<Auction> auctions = myCal.getAuctions();
        int aucCount = 0;
        for (int i = 0; i < auctions.size(); i++)
        {
            if (theDate.isSameDay(auctions.get(i).getDate()))
                aucCount++;
            
        }
        if (aucCount > 1)
            return true;
        else
            return false;
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
        JButton submitButton = new JButton("Submit Request");
        
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
        
        c.gridx = 1;	
        c.gridy = 6;
        c.ipady = 50;
        myRequestFormScreen.add(new JPanel(), c);
        c.gridx = 1;	
        c.gridy = 7;
        c.ipady = 0;
        myRequestFormScreen.add(submitButton, c);
        
        submitButton.addActionListener(new Submit());
        
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
    
    private void setUpConfirmation() 
        {
            Auction theAuction = myNPO.getAuction();
            CONFIRMATION_MESSAGE = new JTextArea("You have successfully submitted an auction request!\n"
                    + "\nYour auction details are:\n"
                    + "     -Name: " + theAuction.getAuctionName() + "\n"
                    + "     -Date: " + theAuction.getDate().toString() + "\n"
                    + "     -Contact: " + theAuction.getContactPerson() + "\n"
                    + "     -Description: " + theAuction.getDescription() + "\n\n"
                    + "Thank you for choosing Auction Central.");
            CONFIRMATION_MESSAGE.setEditable(false);
            myConfirmation.setLayout(new BorderLayout());
            JButton button = new JButton("OK");
            //myConfirmation.add(button, BorderLayout.SOUTH);
            myConfirmation.add(CONFIRMATION_MESSAGE, BorderLayout.CENTER);
           // button.addActionListener(new ReturnToNPMainMenu());
        }
    

    class RequestAuction implements ActionListener
    {

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		myLocalCLayout.show(myLocalContainer, NONPROFITREQUESTPANEL); //Switches to Auction Request panel via static String name
    		myOptionButtons.getButton(0).setEnabled(false);
                
    	}
            
    }
    
    class LogOut implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myMainCLayout.show(myMainContainer, INPUTPANEL);
			myMainContainer.remove(myMainScreen);
                        
		}
	}
    
    class Submit implements ActionListener
    {

        /*
        private JTextField myAuctionName;
    private JTextField myContactPerson;
    private JTextField myItemCount;
    private JTextField myDescription;
    private JTextField myComments;
        */
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String auctionName = "";
            String contact = "";
            String itemCount = "";
            String desc = "";
            String comments = "";
            AuctionDate theDate = null;
            boolean problem = false;
            if (myStartHour.getSelectedIndex() == 0 || myStartHour.getSelectedIndex() == 1)
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please select a start time to submit.",
                "Time issue",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            else
            {
                theDate = new AuctionDate(myDate[0], myDate[1], myDate[2], myStartHour.getSelectedIndex()-2);
                System.out.println(theDate.toString());
            }
            if (myAuctionName.getText().matches(""))
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please enter a name for this auction.",
                "Name issue",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            else
            {
                auctionName = myAuctionName.getText();
            }
            if (myContactPerson.getText().matches(""))
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please enter who will be the contact person for this auction.",
                "Missing contact",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            else
            {
                contact = myContactPerson.getText();
            }
            if (myItemCount.getText().matches(""))
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please enter an estimate for the number of items expected.",
                "Item count issue",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            else
            {
                itemCount = myItemCount.getText();
            }
            if (myDescription.getText().matches(""))
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "You are missing a description of this auction.",
                "Missing description",
                JOptionPane.WARNING_MESSAGE);
                problem = true;
            }
            else
            {
                desc = myDescription.getText();
            }
            
            if(!problem)
            {
                int moreProblem = myNPO.submitAuctionRequest(theDate, auctionName, myNPO.getUserName(), contact, desc, comments, myCal);
                if (moreProblem == 1)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                "We have too many auctions to handle right now.\nPlease try your request at a later time.",
                "Sorry!", JOptionPane.UNDEFINED_CONDITION);
                }
                else if (moreProblem == 2)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                "You seem to have somehow broken our system.\nPlease contact our site admin at bugreport@auctioncentral.org\nand explain everything you were doing when this problem arised.",
                "Error Code 2!", JOptionPane.ERROR_MESSAGE);
                }
                else if (moreProblem == 3)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                "You seem to have somehow broken our system.\nPlease contact our site admin at bugreport@auctioncentral.org\nand explain everything you were doing when this problem arised.",
                "Error Code 3!", JOptionPane.ERROR_MESSAGE);
                }
                else if (moreProblem == 4)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                "Our records show you've had an auction within the past year.\nPlease try again later.",
                "Sorry!", JOptionPane.UNDEFINED_CONDITION);
                }
                else if (moreProblem == 0)
                {
                    initializeHasAuctionMessage();
                    myCal.Update("Auctions.ser");
                    initializeViewAuction();
                    setUpConfirmation();
                    myLocalCLayout.show(myLocalContainer, NP_CONFIRMATION_SCREEN);
                }
            }
            
        }

        
        
    }
    /*
    class ReturnToNPMainMenu implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            NonprofitWelcomeScreen();
            myLocalCLayout.show(myLocalContainer, NONPROFITPANEL);
        }
        
    }
    */
    
    class ViewAuction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            myLocalCLayout.show(myLocalContainer, NP_AUCTION_VIEW_SCREEN);
        }
        
    }
    
    class AddItemForm implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            myLocalCLayout.show(myLocalContainer, NP_ITEM_ADD_FORM);
            viewAuctionButtons.getButton(2).setEnabled(true);
        }
        
    }
    
    class SubmitItem implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean problem = false;
            if (myItemName.getText().matches(""))
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please enter a name for this item.",
                "No name",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            if (myItemDesc.getText().matches(""))
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please give this item a description.",
                "No description",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            try {
                myItemStartBid.commitEdit();
            } catch (ParseException ex) {
                //Logger.getLogger(NonprofitGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((double) myItemStartBid.getValue() <= 0)
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please enter a starting bid for this item.",
                "No starting bid",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            //System.out.println((double) myItemStartBid.getValue());
            if (myItemCnd.getSelectedIndex() == 0 || myItemCnd.getSelectedIndex() == 1)
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please select a condition level for this item.",
                "Condition issue",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            if (myItemSize.getSelectedIndex() == 0 || myItemSize.getSelectedIndex() == 1)
            {
                JOptionPane.showMessageDialog(myMainScreen,
                "Please select an approximate size for this item.",
                "Size issue",
                JOptionPane.ERROR_MESSAGE);
                problem = true;
            }
            
            if (!problem)
            {
                String itemName = myItemName.getText();
                String itemDonor = myItemDonor.getText();
                String itemDesc = myItemDesc.getText();
                int itemQty = (int) myItemQty.getValue();
                double itemPrice = (double) myItemStartBid.getValue();
                String itemCnd = (String) myItemCnd.getSelectedItem();
                String itemSize = (String) myItemSize.getSelectedItem();
                String itemComment = myItemComments.getText();
                //System.out.println(itemCnd);
                /*
                boolean addItem(String theName, String theDonorName, String theDescription,
			int theQuantity, float theStartingBid, String theCondition,
			String theSize, String theComments, AuctionCalendar calendar) 
                */
                boolean noProblem = myNPO.addItem(itemName, itemDonor, itemDesc, itemQty, (float) itemPrice, itemCnd, itemSize, itemComment, myCal);
                if(noProblem)
                {
                        JOptionPane.showMessageDialog(myMainScreen,
                "Your item has been successfully entered into our system.\nYou may continue entering items or click View Auction to review your item list.",
                "Success!",
                JOptionPane.PLAIN_MESSAGE);
                        System.out.println(myCal.getAuction(myNPO.getUserName()).getItems());
                        myCal.Update("Auctions.ser");
                        //myViewAuctionScreen = new JPanel();
                        //myLocalContainer.add(myViewAuctionScreen, NP_AUCTION_VIEW_SCREEN);
                        //initializeViewAuction();
                        myViewAuctionScreen.remove(scrollPane);
                        NPViewItemsScreen(myCal.getAuction(myNPO.getUserName()));
                        //viewAuctionButtons.getButton(2).setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                "It seems we already have an item of that name in our system.\nPlease try again with a different item.",
                "Whoops!",
                JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }
    
    class RemoveItem implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            id = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID number of the item you wish to delete. Integer numbers only."));
            id--;
            if (id < 0)
                JOptionPane.showMessageDialog(myMainScreen,
                    "Invalid entry. Can not be 0 or negative.",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            else if (id < myItemTable.getRowCount())
            {
            String item = (String) myItemTable.getModel().getValueAt(id, 1);
            List<Item> items = myCal.getAuction(myNPO.getUserName()).getItems();
            int result = 0;
            
            for (int i = 0; i < items.size(); i++)
            {
                if (items.get(i).getName().equals(item))
                {
                    result = myCal.getAuction(myNPO.getUserName()).removeItem(items.get(i));
                    break;
                }
            }
                if (result == 0)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                    "Item " + item + " has been removed from the auction.",
                    "Success!",
                    JOptionPane.PLAIN_MESSAGE);
            
                    myCal.Update("Auctions.ser");
                    myViewAuctionScreen.remove(scrollPane);
                    NPViewItemsScreen(myCal.getAuction(myNPO.getUserName()));
                    myLocalCLayout.show(myLocalContainer, NP_ITEM_ADD_FORM);
                    myLocalCLayout.show(myLocalContainer, NP_AUCTION_VIEW_SCREEN);
                    
                    if (myCal.getAuction(myNPO.getUserName()).getItems().isEmpty())
                    {
                        viewAuctionButtons.getButton(2).setEnabled(false);
                    }
                }
                if (result == 1)
                {
                JOptionPane.showMessageDialog(myMainScreen,
                    "Item " + item + " was not able to be removed.\nReason: too close to auction start date.",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                id++;
                JOptionPane.showMessageDialog(myMainScreen,
                    "Item does not exist at ID "+id+".",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    class RemoveAuction implements ActionListener
    {
        // This should never be possible to call when the NP doesn't have an auction
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = JOptionPane.showConfirmDialog(myViewAuctionScreen, "This will cancel your upcoming auction and remove all your items!"
                    + "\nAre you sure you wish to do this?");
            if (selected == JOptionPane.YES_OPTION)
            {
                int result = myCal.removeAuction(myNPO.getUserName());
                if (result == 0)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                    "Your auction has been canceled. You may request a new auction.",
                    "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
                    myOptionButtons.getButton(1).setEnabled(false);
                    myOptionButtons.getButton(0).setEnabled(true);
                    //HAS_AUCTION_WELCOME = new JTextArea();
                    myWelcomeScreen = new JPanel(new BorderLayout());
                    NonprofitWelcomeScreen();
                    myLocalContainer.add(myWelcomeScreen, NONPROFITPANEL);
                    
                    myLocalCLayout.show(myLocalContainer, NONPROFITPANEL);
                    myCal.Update("Auctions.ser");
                }
                if (result == 1)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                    "Auction was not found. Please contact site admin at webmaster@auctioncentral.org",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
                }
                if (result == 2)
                {
                    JOptionPane.showMessageDialog(myMainScreen,
                    "Your auction may not be removed at this time.\nReason: auction too close to start date.",
                    "Sorry",
                    JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
        
    }
}
