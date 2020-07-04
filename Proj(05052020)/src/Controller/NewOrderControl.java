package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import GUI.goBackEvent;
import GUI.incomingOrderEvent;
import GUI.main_cardFrame;
import GUI.user_obj;

public class NewOrderControl extends Observable implements Observer {
	
	private main_cardFrame orders;
	private String[] servableDishes;
	private boolean goBack = false;
	
	public NewOrderControl(user_obj user) {
		orders = new main_cardFrame(user);
		
		if (orders != null) {
			orders.addObserver(this);
			// Module <-- request for dishes
			//Module --> dishes that can be served at this point of time
			servableDishes = new String[] {"Tofu Soup", "Soy-milk Flan", "Rice Stir-fry with Veggies"};
			System.out.println("in NewOrderControl , requesting servable dishes from the Model");
			orders.displayNewOrderScreen(servableDishes, servableDishes.length, user);
			System.out.println("in NewOrderControl , launching new orders screen");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg.getClass() == new incomingOrderEvent(new JFrame()).getClass() ) {
			System.out.println("in NewOrderControl , user event: --> new order");
			incomingOrderEvent temp = (incomingOrderEvent) arg;
			// Yosi, an example of how i built the incoming order event class: 
			//temp.getStaff_id();
			//temp.getTable_id();
			//temp.getOrders(); // <-- a List<order_item>
			//temp.getOrders().get(0).getAmount();
			//temp.getOrders().get(0).getDish();
			
			// Model <-- validate incoming order , check if dish ingredients exist in inventory 
			// Model --> order is valid / order invalid + amount that can be served or any error msg
			boolean Model = false;
			
			if (Model) {// Model says yes
				System.out.println("in NewOrderControl , Model approves order");
				orders.displayOrderSucess();
			}
			else {// Model says no
				System.out.println("in NewOrderControl , Model dis-approves order");
				orders.displayInvalidAmount(new String[] {"1 tofu","2 soy milk"});
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