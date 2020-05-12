package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class textPanel extends JPanel {
	
	private JTextArea txt1; 
	
	public textPanel() {
		
		txt1 = new JTextArea();
		setLayout(new BorderLayout());
		
		add(new JScrollPane(txt1), BorderLayout.CENTER);
	}
	
	public void appendText(String text) {
		txt1.append(text);
	}
}