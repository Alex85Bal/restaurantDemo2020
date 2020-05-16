package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testWindow implements Observer {
	
	private main_cardFrame run ;
	public testWindow () {
		
		run = new main_cardFrame();
		run.addObserver(this);
	}
	
	public static void main(String[] args) {
		
		new testWindow();

	}

	@Override
	public void update(Observable o, Object arg) {
		user_obj temp = (user_obj)arg;
		System.out.println("controler says :\n");
		System.out.println(temp.getPersonal_id()+"\n"+temp.getPass()+"\n");
		
		// fake call to module for a search of given ID and Password
		if (temp.getUserRequest() == "loginRequest")
			if (temp.getPersonal_id() == 123)
				if (temp.getPass().matches("abc"))
				{
					run.displayNavScreen(new int[] {99,2,3,4});
				}		
	}
}
