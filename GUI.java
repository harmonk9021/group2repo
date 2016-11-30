
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * This class creates the initial User Log-In GUI.
 * @author Kyle Phan
 * @version 11/29/16
 *
 */
public class GUI {
	
	/*
	 * Static names for all the different panels used in the CardLayout
	 * for myFrame
	 */
	final static String INPUTPANEL = "Login Page";
	final static String REGISPANEL = "Registration Page";
	
	/*
	 * 
	 */
	private JFrame myFrame;
	private Login myUserLogin;
	private JPanel inputPanel;
	private JPanel buttonPanel;
	private JPanel regisInputPanel;
	private JPanel regisButtonPanel;
	
	private JPanel loginPanel;
	private JPanel regisPanel;
	
	private JPanel containerPanel;
	
	private JLabel noUserFoundLabel;
	private JLabel emptyFieldsLabel;
	
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField regisUsernameField;
	private JTextField regisPasswordField;
	private JTextField emailField;
	private JTextField phoneNumField;
	
	private JRadioButton bidderButton;
	private JRadioButton nonprofitButton;
	private JRadioButton staffButton;
	private ButtonGroup buttonGroup;
	
	private GridBagConstraints c;
	
	private CardLayout cLayout;
	
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 500;
	private final int BTN_WIDTH = 100;
	private final int REG_BTN_WIDTH = 150;
	private final int BTN_HEIGHT = 30;
	private final int TEXTFIELD_WIDTH = 15;
	
	public GUI(Login theUserLogin) {
		myUserLogin = theUserLogin;
		inputPanel = new JPanel();
		buttonPanel = new JPanel();
		regisInputPanel = new JPanel();
		regisButtonPanel = new JPanel();
		
		loginPanel = new JPanel();
		regisPanel = new JPanel();
		
		
		containerPanel = new JPanel();
		
		emptyFieldsLabel = new JLabel("All fields must be filled out.");
		
		nameField = new JTextField(TEXTFIELD_WIDTH);
		usernameField = new JTextField(TEXTFIELD_WIDTH);
		passwordField = new JTextField(TEXTFIELD_WIDTH);
		regisUsernameField = new JTextField(TEXTFIELD_WIDTH);
		regisPasswordField = new JTextField(TEXTFIELD_WIDTH);
		emailField = new JTextField(TEXTFIELD_WIDTH);
		phoneNumField = new JTextField(TEXTFIELD_WIDTH);
		
		bidderButton = new JRadioButton("Bidder");
		nonprofitButton = new JRadioButton("Contact Person");
		staffButton = new JRadioButton("Staff");
		buttonGroup = new ButtonGroup();
		
		c = new GridBagConstraints();
		
		cLayout = new CardLayout();
		
		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
//		BorderLayout bLayout = new BorderLayout();
//		CardLayout cLayout = new CardLayout();

		myFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//		myFrame.setLayout(bLayout);
		
//		myFrame.setLayout(cLayout);

		containerPanel.setLayout(cLayout);
			
		creatLoginPanel();
		createRegisterPanel();
		
//		myFrame.add(loginPanel, INPUTPANEL);
//		myFrame.add(regisPanel, REGISPANEL);
		
		containerPanel.add(loginPanel, INPUTPANEL);
		containerPanel.add(regisPanel, REGISPANEL);
		
		myFrame.add(containerPanel);
		
		myFrame.setVisible(true);
	}
	
	private void creatLoginPanel() {
		BorderLayout bordLayout = new BorderLayout();
		loginPanel.setLayout(bordLayout);
		
		loginInputPanel();
		loginButtonPanel();
		
		loginPanel.add(inputPanel, BorderLayout.CENTER);
		loginPanel.add(buttonPanel, BorderLayout.PAGE_END);
		
		loginPanel.setVisible(true);
		
	}
	
	private void loginInputPanel() {
		
		GridBagLayout gridbag = new GridBagLayout();
        
        inputPanel.setLayout(gridbag);
//        c.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel usernameLabel = new JLabel("Username: ");
        c.gridwidth = 1;
        c.gridx = 0;	//Adding constraints for username label position
        c.gridy = 0;
        inputPanel.add(usernameLabel, c);
        
        JLabel passwordLabel = new JLabel("Password: ");
        c.gridx = 0;
		c.gridy = 1;
		inputPanel.add(passwordLabel, c);

        c.ipadx = 50;	//Adding constraints for username textfield position
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
		inputPanel.add(usernameField, c);

		c.gridx = 1;
		c.gridy = 1;
		inputPanel.add(passwordField, c);
		c.ipadx = 30;
		c.gridy = 2;
		
		noUserFoundLabel = new JLabel("The username entered was not found!");
		c.gridy = 3;
		noUserFoundLabel.setVisible(false);
		inputPanel.add(noUserFoundLabel, c);
		
	}
	
	private void loginButtonPanel() {
		GridLayout btnGridLayout = new GridLayout();
		btnGridLayout.setHgap(5);
//		buttonPanel.setLayout(btnGridLayout);
		JButton authenticate = new JButton("Login");
		authenticate.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		JButton register = new JButton("Register");
		register.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		buttonPanel.add(authenticate);
		buttonPanel.add(register);
		
		authenticate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enteredUsername = usernameField.getText();
				String enteredPassword = passwordField.getText();
				if (myUserLogin.getUser(enteredUsername) == null) {
					System.out.println("User is null");
					
//					FIXME
//					Label shows up only when JFrame is resized. Don't remember how to fix this.
					noUserFoundLabel.setVisible(true);
					myFrame.repaint();
					
				} else {					
					if (!myUserLogin.isValidPassword(enteredUsername, enteredPassword)) {
						JLabel noInvalidPassword = new JLabel("The username or password entered is incorrect.");
						c.gridy = 3;
						inputPanel.add(noInvalidPassword, c);
						inputPanel.repaint();
					} else {
						User user = myUserLogin.getUser(usernameField.getText());
						if (user instanceof Bidder) {
							BidderGUI bidGUI = new BidderGUI(user);
							bidGUI.start();
						} else if (user instanceof Nonprofit) {
							NonprofitGUI npoGUI = new NonprofitGUI(user);
							npoGUI.start();
						} else if  (user instanceof Staff) {
							StaffGUI staffGUI = new StaffGUI(user);
							staffGUI.start();
						}
					}
				}
			}
		});
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				inputPanel.setVisible(false);
//				regisPanel.setVisible(true);
//				myFrame.repaint();
				
				cLayout.show(containerPanel, REGISPANEL);
			}
		});

		
	}
	
	private void createRegisterPanel() {
		BorderLayout registerBLayout = new BorderLayout();
		regisPanel.setLayout(registerBLayout);
		regisPanel.setVisible(false);
		
		registerInputPanel();
		registerButtonPanel();
		
		regisPanel.add(regisInputPanel, BorderLayout.CENTER);
		regisPanel.add(regisButtonPanel, BorderLayout.PAGE_END);
		
	}
	
	private void registerInputPanel() {
		GridBagLayout gbLayout = new GridBagLayout();
		
		regisInputPanel.setLayout(gbLayout);
		
		JLabel nameLabel = new JLabel("Name: ");
		c.ipadx = 0;
        c.gridwidth = 1;
        c.gridx = 0;	//Adding constraints for username label position
        c.gridy = 1;
        regisInputPanel.add(nameLabel, c);
        
        JLabel usernameLabel = new JLabel("Username: ");
        c.gridy = 2;
        regisInputPanel.add(usernameLabel, c);
        
        JLabel passwordLabel = new JLabel("Password: ");
		c.gridy = 3;
		regisInputPanel.add(passwordLabel, c);
        
		JLabel emailLabel = new JLabel("Email: ");
		c.gridy = 4;
		regisInputPanel.add(emailLabel, c);

		JLabel phoneNumLabel = new JLabel("Phone Number: ");
		c.gridy = 5;
		regisInputPanel.add(phoneNumLabel, c);
		
		c.ipadx = 50;
        c.gridx = 1;
        c.gridy = 0;
        
        regisInputPanel.add(emptyFieldsLabel, c);
        
        c.gridy = 1;
        regisInputPanel.add(nameField, c);
        
        c.gridy = 2;
        regisInputPanel.add(regisUsernameField, c);
		
        c.gridy = 3;
        regisInputPanel.add(regisPasswordField, c);
		
        c.gridy = 4;
        regisInputPanel.add(emailField, c);
		
        c.gridy = 5;
        regisInputPanel.add(phoneNumField, c);
        
        c.gridy = 6;
        bidderButton.setActionCommand("1");
        buttonGroup.add(bidderButton);
        regisInputPanel.add(bidderButton, c);
        
        c.gridy = 7;
        nonprofitButton.setActionCommand("2");
        buttonGroup.add(nonprofitButton);
        regisInputPanel.add(nonprofitButton, c);

        c.gridx = 1;
        c.gridy = 8;
        staffButton.setActionCommand("3");
        buttonGroup.add(staffButton);
        regisInputPanel.add(staffButton, c);
        
        
        
	}
	
	private void registerButtonPanel() {
		JButton createAccount = new JButton("Create Account");
		createAccount.setPreferredSize(new Dimension(REG_BTN_WIDTH, BTN_HEIGHT));
		
		regisButtonPanel.add(createAccount);
		createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Checks for empty fields, if so 'All fields' label turns red.
				if (!nameField.getText().equals("") &&
						!regisUsernameField.getText().equals("") &&
						!regisPasswordField.getText().equals("") &&
						!emailField.getText().equals("") &&
						!phoneNumField.getText().equals("")) {
					
					if (!myUserLogin.createUser(nameField.getText(), regisUsernameField.getText(), regisPasswordField.getText(), emailField.getText(), 
							   phoneNumField.getText(), buttonGroup.getSelection().getActionCommand())) {
						
					}
					
				} else {
					emptyFieldsLabel.setForeground(Color.RED);
				}
			}
		});
	}
}
