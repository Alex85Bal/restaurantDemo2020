package Controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JFrame;

import GUI.goBackEvent;
import GUI.incomingOrderEvent;
import GUI.main_cardFrame;
import GUI.user_obj;
import fileHandle.OrderModule;
import fileHandle.dishItem;

public class NewOrderControl extends Observable implements Observer {
	
	private main_cardFrame orders;
	private Vector<dishItem> servableDishes;
	private boolean goBack = false;
	private OrderModule data;
	
	public NewOrderControl(user_obj user) {
		orders = new main_cardFrame(user);
		data = new OrderModule();
		data.loadDishes();
		data.loadInventory();
		servableDishes = (Vector<dishItem>) data.getDishesh().clone();
		if (servableDishes == null) servableDishes = new Vector<dishItem>(); // anti crash
		if (orders != null) {
			orders.addObserver(this);
			System.out.println("in NewOrderControl , requesting servable dishes from the Model");
			orders.displayNewOrderScreen(servableDishes, servableDishes.size(), user);
			System.out.println("in NewOrderControl , launching new orders screen");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg.getClass() == new incomingOrderEvent(new JFrame()).getClass() ) {
			System.out.println("in NewOrderControl , user event: --> new order");
			incomingOrderEvent temp = (incomingOrderEvent) arg;
			String result = new String();
			result = data.incomingOrder(temp);
			
			if (result.equals("")) {// Model says yes
				System.out.println("in NewOrderControl , Model approves order");
				orders.displayOrderSucess();
			}
			else {// Model says no
				System.out.println("in NewOrderControl , Model dis-approves order");
				orders.displayInvalidAmount(result);
			}
		}
			
		if (arg instanceof Boolean) {
			System.out.println("in NewOrderControl , user event: --> screen exit, back");
			goBack = true;
			orders.rip();
			setChanged();
			notifyObservers(goBack);
		}
	}
}