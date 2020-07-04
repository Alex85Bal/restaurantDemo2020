package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import GUI.goBackEvent;
import GUI.incomingOrderEvent;
import GUI.main_cardFrame;

public class NewOrderControl implements Observer {
	
	private main_cardFrame orders;
	private String[] servableDishes;
	
	public NewOrderControl(main_cardFrame runing) {
		if (runing != null)
			orders = runing;
		else
			throw new RuntimeException();
		orders.addObserver(this);
		
		// Module <-- request for dishes
		// Module --> dishes that can be served at this point of time
		servableDishes = new String[] {"Tofu Soup", "Soy-milk Flan", "Rice Stir-fry with Veggies"};
		orders.displayNewOrderScreen(servableDishes, servableDishes.length);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg.getClass() == new incomingOrderEvent(new JFrame()).getClass() )
			System.out.println("gg");
		if (arg instanceof Boolean)
			System.out.println("hh");
	}
	
}
