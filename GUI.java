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
		
		loginScreen();
		
		myFrame.setVisible(true);
	}
	
	public void loginScreen() {
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
//		inputPanel.add(authenticate, c);
//		username.setText("Username");
//		password.setText("Password");
//		username.setMinimumSize(username.getPreferredSize());
		
		JPanel buttonPanel = new JPanel();
		GridBagLayout btnGridBag = new GridBagLayout();
		buttonPanel.setLayout(btnGridBag);
		JButton authenticate = new JButton("Login");
		JButton register = new JButton("Register");
		buttonPanel.add(authenticate);
		buttonPanel.add(register);
		
		authenticate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (myUserLogin.getUser(usernameField.getText()) == null) {
					System.out.println("User is null");
				} else {
					
				}
			}
		});
		
		myFrame.add(inputPanel, BorderLayout.CENTER);
		myFrame.add(buttonPanel, BorderLayout.SOUTH);
	}
}
