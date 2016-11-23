import java.awt.BorderLayout;
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
 * 
 */

/**
 * @author Andrew Dinh
 *
 */
public class GUI {
	
	private JFrame myFrame;
	private Login myUserLogin;
	
	public GUI(Login theUserLogin) {
		myUserLogin = theUserLogin;
		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		BorderLayout bLayout = new BorderLayout();
		myFrame.setSize(400, 400);
		myFrame.setLayout(bLayout);
		
		loginInputPanel();
		
		myFrame.setVisible(true);
	}
	
	public void loginInputPanel() {
		JPanel inputPanel = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        inputPanel.setLayout(gridbag);
        c.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel usernameLabel = new JLabel("Username: ");
        c.gridwidth = 1;
        c.gridx = 0;	//Adding constraints for username label position
        c.gridy = 0;
        inputPanel.add(usernameLabel, c);
        
        JLabel passwordLabel = new JLabel("Password: ");
        c.gridx = 0;
		c.gridy = 1;
		inputPanel.add(passwordLabel, c);
        
		JTextField usernameField = new JTextField(15);
        c.ipadx = 50;	//Adding constraints for username textfield position
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
		inputPanel.add(usernameField, c);
		
		JTextField passwordField = new JTextField(15);
		c.gridx = 1;
		c.gridy = 1;
		inputPanel.add(passwordField, c);
		c.ipadx = 30;
		c.gridy = 2;

		JPanel buttonPanel = new JPanel();
		GridBagLayout btnGridBag = new GridBagLayout();
		buttonPanel.setLayout(btnGridBag);
		JButton authenticate = new JButton("Login");
		JButton register = new JButton("Register");
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
//					JLabel noUserFound = new JLabel("The username entered was not found!");
//					c.gridy = 3;
//					inputPanel.add(noUserFound, c);
//					inputPanel.repaint();
					
				} else {
					System.out.println("Bidder exists in Login");
					
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
		
		myFrame.add(inputPanel, BorderLayout.CENTER);
		myFrame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
}
