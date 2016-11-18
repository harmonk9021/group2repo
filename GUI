import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	
	public GUI() {
		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		myFrame.setSize(400, 400);
		JTextField username = new JTextField();
		JTextField password = new JTextField();
		JButton authenticate = new JButton("Login");
		JPanel panel = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(gridbag);
		panel.add(username);
		panel.add(password);
		panel.add(authenticate);
		username.setText("Username");
		password.setText("Password");
		username.setMinimumSize(username.getPreferredSize());
		myFrame.add(panel);
		myFrame.setVisible(true);
	}
}
