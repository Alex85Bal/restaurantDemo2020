package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class loginFrame extends JFrame {
	
	private userDataInputPanel UID_panel;
	
	public loginFrame () {
		
		super("Please Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLayout(new BorderLayout());
		
		UID_panel = new userDataInputPanel();
		
		add(UID_panel, BorderLayout.PAGE_START);
		
		setVisible(true);
		
		
		
		
	}

}
