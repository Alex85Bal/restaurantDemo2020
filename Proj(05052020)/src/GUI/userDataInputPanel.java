package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class userDataInputPanel extends JPanel {
	
	private JLabel label_ID;
	private JTextField inputID;
	
	public userDataInputPanel() {
		
		setLayout(new BorderLayout());
		
		label_ID = new JLabel("your personal ID goes here:");
		inputID = new JTextField("123456789",6);
		
		//add(label_ID,BorderLayout.LINE_START);
		add(inputID,BorderLayout.CENTER);
	}
	

}
