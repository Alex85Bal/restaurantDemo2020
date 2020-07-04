package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import GUI.goBackEvent;
import GUI.incomingOrderEvent;
import GUI.main_cardFrame;

public class NewOrderControl extends Observable implements Observer {
	
	private main_cardFrame orders;
	private String[] servableDishes;
	private boolean goBack = false;
	
	public NewOrderControl() {
		orders = new main_cardFrame();
		
		if (orders != null) {
			orders.addObserver(this);
			// Module <-- request for dishes
			//Module --> dishes that can be served at this point of time
			servableDishes = new String[] {"Tofu Soup", "Soy-milk Flan", "Rice Stir-fry with Veggies"};
			System.out.println("in NewOrderControl , requesting servable dishes from the Model");
			orders.displayNewOrderScreen(servableDishes, servableDishes.length);
			System.out.println("in NewOrderControl , launching new orders screen");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg.getClass() == new incomingOrderEvent(new JFrame()).getClass() ) {
			System.out.println("in NewOrderControl , user event: --> new order");
			incomingOrderEvent temp = (incomingOrderEvent) arg;
			// Model <-- validate incoming order;
			// Model --> order is valid / order invalid + amount that can be served
			boolean Model = true;
			
			if (Model) {// Model says yes
				
			}
			else {// Model says no
				orders.displayInvalidAmount(new String[] {"1 tofu","2 soy milk"});
			}

			
			//orders.displayInvalidAmount();
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
