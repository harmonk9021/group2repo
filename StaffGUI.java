import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The GUI for users that are represented as staff in
 * the system.
 * 
 * @author Andrew Dinh
 * @version 11/22/2016
 */

public class StaffGUI {
	
	private Staff myStaff;
	
	private JFrame myFrame;

	public StaffGUI(User theUser) {
		myStaff = (Staff) theUser;
		myFrame = new JFrame("Hello" + myStaff.getName());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() {
		BorderLayout bLayout = new BorderLayout();
		myFrame.setSize(400, 400);
		myFrame.setLayout(bLayout);
		
		staffScreen();
		
		myFrame.setVisible(true);
	}

	private void staffScreen() {
		// TODO Auto-generated method stub
		
	}

}
