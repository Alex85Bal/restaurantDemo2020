package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class navigationPanel extends JPanel {
	
	navigatioListener sharedWithMainFrame;
	
	public navigationPanel(int[] permissions, int perm_amount, String[] buttons, int button_amount) {

		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		int min;
		if (permissions.length >= buttons.length)
			min = buttons.length;
		else
			min = permissions.length;
		
		for(int i = 0; i < min ; i++) {
			if (permissions[i] != 0)
			addButton(buttons[i]);
		}
		
		JButton exit = new JButton("exit");
		ActionListener getout = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		exit.addActionListener(getout);
		exit.setAlignmentY(CENTER_ALIGNMENT);
		exit.setAlignmentX(CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		add(exit);
		add(Box.createVerticalGlue());
	}
	
	private void addButton(String string) {
		
		JButton temp = new JButton(string);
		temp.setAlignmentX(CENTER_ALIGNMENT);
		temp.setAlignmentY(CENTER_ALIGNMENT);
		
		temp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				navigationEvent navButtonPressed = new navigationEvent(this,temp.getLabel());
				if (sharedWithMainFrame != null)
					sharedWithMainFrame.userEvent(navButtonPressed);
				}
				
			});
		add(Box.createVerticalGlue());
		add(temp);
		add(Box.createVerticalGlue());

	}
	
	public void setListener (navigatioListener NL) {
		sharedWithMainFrame = NL;
	}
	
}
