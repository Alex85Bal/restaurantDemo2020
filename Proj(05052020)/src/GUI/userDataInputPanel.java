package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class userDataInputPanel extends JPanel {
	
	private JLabel IDLabel;
	private JLabel passwordLabel;
	private JTextField IDInput;
	private JTextField passwordInput;
	private JButton okButton;
	private GridBagConstraints gc;
	private loginListener sharedLoginListner ;
	
	public userDataInputPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width = 200;
		dim.height = 200;
		setPreferredSize(dim);
		
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		IDLabel = new JLabel("Personal ID: ");
		passwordLabel = new JLabel("Password: ");
		IDInput = new JTextField(10);
		passwordInput = new JTextField(10);
		okButton = new JButton("Submit");
		
		// Motivation:
		// we need main-frame to know when OK button is pressed and provide ID and Password to main-frame
		// Explanation:
		// we need an object from main-frame that can catch action upon a button => EventListner object => "loginListner" 
		// we need an object to contain the info i need: ID and Password => EventObject => "loginEvent"
		// we create a custom "loginListner" object, it can be constructed with "loginEvent"
		// Actions :
		// we add to our "OK" button anonymous ActionListener
		// upon some action on the "OK" button our ActionListener will create a custom event: "loginEvent"
		// here "loginEvent" object named "okPressed"
		// if our mainFrame, gave us a "loginListner" object
		// we will add caught event, named okPressed to given listener, that main-frame holds.
		// Result:
		// if OK button is pressed main will have "loginListner" object with ID and Password inside an loginEvent object
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				// i don't care about "e" as i know only 1 action can happen to okButton
				
				loginEvent okPressed = new loginEvent(this, IDInput.getText(), passwordInput.getText());
				if (sharedLoginListner != null)
					sharedLoginListner.userEvent(okPressed);	
			}
		});
					
		gc.fill = GridBagConstraints.NONE;
		
		/* first label, most left *****************************************************************/
		gc.gridx = 0;		gc.gridy = 0; // (0,0)
		gc.weightx = 1;		gc.weighty = 0.3;
		gc.anchor = GridBagConstraints.LINE_END; // stick as much as possible to the right side
		add(IDLabel, gc);
		
		/* first input field, from the right side of first label *********************************/
		gc.gridx = 1; //(1,0)
		gc.anchor = GridBagConstraints.LINE_START; // stick as much as possible to the left side
		add(IDInput, gc);
		
		/* second label, under first label ********************************************************/		
		gc.gridx = 0;		gc.gridy = 1; // (0,1)
		gc.weightx = 1;		gc.weighty = 0.3;
		gc.anchor = GridBagConstraints.LINE_END; // stick as much as possible to the right side
		add(passwordLabel, gc);
		
		/* second input field, under first input field ********************************************/
		gc.gridx = 1; // (1,1)
		gc.anchor = GridBagConstraints.LINE_START; // stick as much as possible to the left side
		add(passwordInput, gc);
		
		/* OK button, under second input field ****************************************************/
		gc.gridx = 1;		gc.gridy = 2; // (1,2)
		gc.weightx = 1;		gc.weighty = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // stick as much as possible to the left side
		add(okButton, gc);
	}
	
	// this gets from main-frame a listener object that we will use to catch an event
	// main-frame has that object
	public void setListner (loginListener LL) {
		this.sharedLoginListner = LL;
	}
}
