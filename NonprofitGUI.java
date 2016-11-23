import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The GUI for users that are represented as Nonprofit
 * organizations in the system.
 * 
 * @author Andrew Dinh
 * @version 11/22/2016
 */

public class NonprofitGUI {
	
	private Nonprofit myNPO;
	
	private JFrame myFrame;

	public NonprofitGUI(User theUser) {
		myNPO = (Nonprofit) theUser;
		myFrame = new JFrame("Hello" + myNPO.getName());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() {
		BorderLayout bLayout = new BorderLayout();
		myFrame.setSize(400, 400);
		myFrame.setLayout(bLayout);
		
		NonprofitScreen();
		
		myFrame.setVisible(true);
	}

	private void NonprofitScreen() {
		// TODO Auto-generated method stub
		
	}

}
