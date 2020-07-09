package GUI; 

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import fileHandle.dishItem;

public class main_cardFrame extends Observable {
	
	private boolean vis = false;
	private JFrame mainFrame;
	private JPanel cards;
	private userDataInputPanel UDIP_panel;
	private navigationPanel navigation_panel;
	private readyOrderPanel ready_orders;
	private CardLayout screens;
	private user_obj searchThis;
	private String NavigationChoice = "nope";
	private newOrderPanel nOrder;
	private incomingOrderEvent incomingOrd;
	private boolean goBackRequested = false;
	private Vector<readyDish_item> serveTheseDishes;
	
	public main_cardFrame () {
		
		mainFrame = new JFrame("my app");
		cards = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(400, 400);
		mainFrame.setLocationRelativeTo(null);
		
		screens = new CardLayout();
		
		mainFrame.add(cards, BorderLayout.CENTER);
		cards.setLayout(screens);
	}
	
	public main_cardFrame (user_obj user) {
		
		mainFrame = new JFrame("my app");
		cards = new JPanel();
		searchThis = user;
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(400, 400);
		mainFrame.setLocationRelativeTo(null);
		
		screens = new CardLayout();
		
		mainFrame.add(cards, BorderLayout.CENTER);
		cards.setLayout(screens);
	}
	
	public void displayLoginScreen() {
		UDIP_panel = new userDataInputPanel();
		UDIP_panel.setListener(new loginListener() {
			@Override
			public void userEvent(loginEvent sharedEvent) {
				searchThis = new user_obj(sharedEvent.getPersonalID(),sharedEvent.getPassword());
				updateSerchable();
			}
		});

		cards.add(UDIP_panel, "start");
		mainFrame.setVisible(true);
	}
	
	public boolean displayNoSuchUser (String UserName, String Password) {
		
		String display = "no such username and password\n";
		display += "\n" + UserName + "\n" + Password;
		JOptionPane.showMessageDialog(this.UDIP_panel, display, "can't login", JOptionPane.CLOSED_OPTION);
		return true;
	}
	
	public boolean displayErrorOnLogin () {
		
		String display = "{Personal ID} should be from the type of int\n";
		display += "{Password} should be from the type of integer";
		JOptionPane.showMessageDialog(this.UDIP_panel, display, "can't login", JOptionPane.CLOSED_OPTION);
		return true;
	}
	
	public user_obj getSearchThis() {
		return searchThis;
	}

	public void updateSerchable() {
		setChanged();
		notifyObservers(searchThis);
	}
	
	public void updateNavRequest() {
		setChanged();
		notifyObservers(NavigationChoice);
	}
	
	public void displayNavScreen(ArrayList<Integer> arrayList, int perm_amount, String[] buttons, int button_amount) {
		navigation_panel = new navigationPanel(arrayList, perm_amount, buttons, button_amount);
		navigation_panel.setListener(new navigatioListener() {
			
			@Override
			public void userEvent(navigationEvent sharedEvent) {
				NavigationChoice = sharedEvent.getScreenRequested();
				setChanged();
				notifyObservers(NavigationChoice);
			}
		});
		cards.add(navigation_panel,"nav");
		mainFrame.setSize(new Dimension(100,400));
		mainFrame.setLocationRelativeTo(null);
		screens.show(cards, "nav");
		mainFrame.setVisible(true);
	}
	
	public void displayNewOrderScreen(Vector<dishItem> dishes, int dishes_amount, user_obj user) {
		nOrder = new newOrderPanel(dishes, dishes_amount, user);
		nOrder.setOrderListener(new incomingOrderListener() {
			
			@Override
			public void incomingOrderEvent(incomingOrderEvent ie) {
				if(ie != null)
					incomingOrd = ie;
				incomingOrd.setStaff_id(searchThis.getSystem_id());
				setChanged();
				notifyObservers(incomingOrd);	
			}
		});
		nOrder.setGoBackListener(new goBackListener() {
			
			@Override
			public void requestToGoBack(goBackEvent ge) {
				goBackRequested = ge.isGoBackRequest();
				if(goBackRequested) {
					setChanged();
					notifyObservers(goBackRequested);
				}
					
			}
		});
		cards.add(nOrder,"nOrder");
		mainFrame.setSize(new Dimension(430,400));
		mainFrame.setLocationRelativeTo(null);
		screens.show(cards, "nOrder");
		mainFrame.setVisible(true);
	}
	
	public void displayReadyOrderScreen (Vector<readyDish_item> displayThese) {
		serveTheseDishes = (Vector<readyDish_item>) displayThese.clone();
		ready_orders = new readyOrderPanel(displayThese);
		ready_orders.setGoBackListener(new goBackListener() {
			
			@Override
			public void requestToGoBack(goBackEvent ge) {
				goBackRequested = ge.isGoBackRequest();
				if(goBackRequested) {
					serveTheseDishes = ready_orders.getYetToBeServed();
					setChanged();
					notifyObservers(serveTheseDishes);
				}			
			}
		});
		cards.add(ready_orders,"readyOrd");
		mainFrame.setSize(new Dimension(430,400));
		mainFrame.setLocationRelativeTo(null);
		screens.show(cards, "readyOrd");
		mainFrame.setVisible(true);
	}

	public incomingOrderEvent getIncomingOrd() {
		return incomingOrd;
	}
	
	public void displayOrderSucess () {	
		nOrder.displayOrderSucess();
	}
	
	public void displayInvalidAmount (String problems) {
		nOrder.displayInvalidAmount(problems);
	}
	
	public void rip () {
		mainFrame.dispose();
	}
	
	public void flipVisibility() {
		mainFrame.setVisible(vis);
		vis = ! vis;
	}
}
