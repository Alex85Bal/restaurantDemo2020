package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormPanel extends JPanel {
	
	private JLabel label1;
	private JLabel label2;
	private JTextField input1;
	private JTextField input2;
	private JButton okButton;
	
	public FormPanel () {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		label1 = new JLabel("prompt1: ");
		label2 = new JLabel("prompt2: ");
		input1 = new JTextField(10);
		input2 = new JTextField(10);
		okButton = new JButton("okBoomer");
		
	
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;
		
		// FIRST ROW ! /////////////////////////////////////////////////////////////
		
		// space it takes relative to other components on GridBagLayout
		gc.weightx = 1; 
		gc.weighty = 0.1;
		// 0,0 is to left, x goes right, y goes down.
		gc.gridx = 0; 
		gc.gridy = 0;
		// anchor our component to the end of line 
		gc.anchor = GridBagConstraints.LINE_END;
		// add to grid
		add(label1, gc);
		
		gc.gridx = 1; // we went right
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(input1, gc);
		
		// SECOND ROW ! ////////////////////////////////////////////////////////////
		gc.weightx = 0.1; 
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 1; // we went down and left
		gc.anchor = GridBagConstraints.LINE_END;
		add(label2, gc);
		
		gc.gridx = 1;
		gc.gridy = 1; // we went right
		gc.anchor = GridBagConstraints.LINE_START;
		add(input2, gc);
		
		// THIRD ROW ! ////////////////////////////////////////////////////////////
		gc.weightx = 1; 
		gc.weighty = 1;
		gc.gridx = 1;
		gc.gridy = 2; // we went down
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // Anchor to the first line !
		add(okButton, gc);
	}
}
