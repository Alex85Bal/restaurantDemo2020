package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class loginFrame extends JFrame {
	
	private userDataInputPanel UDIP_panel;
	private StringListener event;
	
	public loginFrame () {
		
		super("Please Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 250);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		UDIP_panel = new userDataInputPanel();
		UDIP_panel.userEvent(new StringListener() {
			
			@Override
			public void textEmitted(String text) {
				System.out.println(text);
				
			}
		});
		
		add(UDIP_panel);
		
		setVisible(true);
				
	}
}
