package Controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;

import GUI.goBackEvent;
import GUI.main_cardFrame;
import GUI.readyDish_item;
import GUI.user_obj;

public class OrderServedControl extends Observable implements Observer{
	
	private main_cardFrame ready;
	private boolean goBack = false;
	private user_obj user;
	private Vector<readyDish_item> shouldBeServed;
	
	public OrderServedControl(user_obj user) {
		
		this.user = user;
		shouldBeServed = new Vector<readyDish_item>();
		ready = new main_cardFrame(user);
		ready.addObserver(this);
		// Module <-- request for Ready dishes for user.
		// Module --> partial  OR whole Order.
		// i'm faking it ^ :
		shouldBeServed.add(new readyDish_item("1",String.valueOf(user.getSystem_id()), "carrot cake", "2", ""));
		shouldBeServed.add(new readyDish_item("1",String.valueOf(user.getSystem_id()), "died coke", "1", ""));
		// end of faking.
		ready.displayReadyOrderScreen(shouldBeServed);
	}
	
	public void backToReadyOrders () {
		ready.flipVisibility();
		// Module <-- request for Ready dishes for user.
		// Module --> partial  OR whole Order.
		// i'm faking it:
		shouldBeServed.add(new readyDish_item("6",String.valueOf(user.getSystem_id()), "Strong Green-tea", "1", ""));
		ready.displayReadyOrderScreen(shouldBeServed);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass() == new Vector<readyDish_item>().getClass() ) {
			Vector<readyDish_item> temp = (Vector<readyDish_item>) arg;
			for (readyDish_item readyDish_item : temp) {
				System.out.println(readyDish_item.getName()+" "+readyDish_item.getAmount()+" Left to serve");
			}
			goBack = true;
			ready.flipVisibility();
			setChanged();
			notifyObservers(goBack);
		}
	}
}
