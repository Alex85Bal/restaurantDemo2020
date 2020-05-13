package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class userDataInputPanel extends JPanel implements ActionListener {
	
	private JLabel IDLabel;
	private JLabel passwordLabel;
	private JTextField IDInput;
	private JTextField passwordInput;
	private JButton okButton;
	private GridBagConstraints gc;
	private StringListener event;
	private StringListener PAS;
	
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
		okButton = new JButton("okBoomer");
		
		IDInput.addActionListener(this);
		passwordInput.addActionListener(this);
		okButton.addActionListener(this);
		
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
		
		/* ok button, under second input field ****************************************************/
		gc.gridx = 1;		gc.gridy = 2; // (1,2)
		gc.weightx = 1;		gc.weighty = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // stick as much as possible to the left side
		add(okButton, gc);
	}
	
	public void userEvent (StringListener sl) {
		this.event = sl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(okButton))
		{
			event.textEmitted(IDInput.getText());
			event.textEmitted(passwordInput.getText());
		}
	}
	

}
