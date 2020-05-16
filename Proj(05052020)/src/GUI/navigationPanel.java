package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class navigationPanel extends JPanel {
	
	public navigationPanel(int userPerm[]) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (int i : userPerm) {
			addButton(String.valueOf(i));
		}
	}
	
	private void addButton(String string) {
		JButton test = new JButton(string);
		add(Box.createVerticalGlue());
		test.setAlignmentX(CENTER_ALIGNMENT);
		add(test);
	}
}
