package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

// main platform of display
public class MainFrame extends JFrame {
	
	private textPanel text1;
	private JButton btn1;
	private Toolbar tbr1;
	private FormPanel leftBuff;
	
	public MainFrame () {
		
		// 1) new frame <-- frame == platform to display what we want.
		super("derp frame");
		
		// 2) define a condition of termination for said frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 3) set a size to said frame
		setSize(600, 600);
		
		// 4) for this frame define a layout (for arranging components).
		// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		// we will now use a "border layout" type of layout for this frame.
		setLayout(new BorderLayout());
		
		// 5) initialize new elements for display on the layout
		text1 = new textPanel();
		btn1 = new JButton("click meh!");
		tbr1 = new Toolbar();
		tbr1.setStringListner(new StringListener() {
			public void textEmitted(String text) {
				text1.appendText(text);
			
			}
		});
		leftBuff = new FormPanel();
	
		// 6) set up user_event_listener on the button
		// so basically :
		// btn.addEvent -> new button_click event -> {on click write text}
		btn1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				text1.appendText("i'm so triggered!\n");
			}
		});
		
		// 7) add elements to layout
		add(leftBuff, BorderLayout.WEST);
		add(tbr1, BorderLayout.NORTH);
		//tbr1.setTextPanel(text1);
		add(text1, BorderLayout.CENTER);
		add(btn1, BorderLayout.SOUTH);
		
		
		setVisible(true);

	}
	
}
