package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	
	private JButton left;
	private JButton left1;
	private textPanel tp1;
	private StringListener trigger1;
	
	public Toolbar() {
		left = new JButton("left");
		left1 = new JButton("right");
		
		setLayout(new FlowLayout());
		add(left);
		add(left1);
		
		left.addActionListener(this);
		left1.addActionListener(this);
	}
	
	public void setStringListner (StringListener sl) {
		this.trigger1 = sl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if( clicked == left)
			if( trigger1 != null)
				trigger1.textEmitted("left\n");	
			else;
		else
			if( trigger1 != null)
				 trigger1.textEmitted("right\n");
	}
}
