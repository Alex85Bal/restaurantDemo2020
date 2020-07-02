package GUI; 

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.EventListener;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

public class main_cardFrame extends Observable {
	
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
	public Vector<readyDish_item> serveTheseDishes;
	
	public main_cardFrame () {
		
		mainFrame = new JFrame("my app");
		cards = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(200, 200);
		mainFrame.setLocationRelativeTo(null);
		
		screens = new CardLayout();
		
		mainFrame.add(cards, BorderLayout.CENTER);
		cards.setLayout(screens);
		
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
	
	public void displayNavScreen(int[] permissions, int perm_amount, String[] buttons, int button_amount) {
		navigation_panel = new navigationPanel(permissions, perm_amount, buttons, button_amount);
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
		screens.show(cards, "nav");
	}
	
	public void displayNewOrderScreen(String[] dishes, int dishes_amount) {
		nOrder = new newOrderPanel(dishes, dishes_amount);
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
		screens.show(cards, "nOrder");
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
		screens.show(cards, "readyOrd");
	}

	public incomingOrderEvent getIncomingOrd() {
		return incomingOrd;
	}
	
	public void displayOrderSucess () {	
		nOrder.displayOrderSucess();
	}
	
	public void displayInvalidAmount (String[] problems) {
		nOrder.displayInvalidAmount(problems);
	}
}
