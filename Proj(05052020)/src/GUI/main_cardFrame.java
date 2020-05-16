package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

public class main_cardFrame extends Observable {
	
	private JFrame mainFrame;
	private JPanel cards;
	private userDataInputPanel UDIP_panel;
	private navigationPanel navigation_panel;
	private BorderLayout center;
	private CardLayout screens;
	private user_obj searchThis;
	
	public main_cardFrame () {
		
		mainFrame = new JFrame("my app");
		cards = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400, 400);
		mainFrame.setLocationRelativeTo(null);
		
		center = new BorderLayout();
		screens = new CardLayout();
		screens.setVgap(100);
		
		mainFrame.setLayout(center);
		mainFrame.add(cards, BorderLayout.CENTER);
		cards.setLayout(screens);
		
		
		UDIP_panel = new userDataInputPanel();		
		UDIP_panel.setListner(new loginListener() {
			
			@Override
			public void userEvent(loginEvent sharedEvent) {
				
				searchThis = new user_obj(sharedEvent.getPersonalID(),sharedEvent.getPassword());
				System.out.println("GUI says:\n");
				System.out.println(searchThis.getPersonal_id()+"\n"+searchThis.getPass()+"\n");
				updateSerchable();
			}
		});
		
		cards.add(UDIP_panel, "start");
		mainFrame.setVisible(true);	
	}
	
	public user_obj getSearchThis() {
		return searchThis;
	}

	public void updateSerchable() {
		setChanged();
		notifyObservers(searchThis);
	}
	
	public void displayNavScreen(int[] permissions) {
		navigation_panel = new navigationPanel(permissions);
		cards.add(navigation_panel,"nav");
		screens.show(cards, "nav");
	}
}
