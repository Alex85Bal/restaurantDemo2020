package Controller;

import java.util.Observable;
import java.util.Observer;

import GUI.main_cardFrame;
import GUI.user_obj;

public class NavigationControl implements Observer{
	
	private int[] Permissions;
	private main_cardFrame navigation;
	private NewOrderControl nOrders;
	private OrderServedControl rOrders;
	private String Buttons[] = {"create order","ready orders","under construction"};
	
	public NavigationControl(int[] Permissions) {
		
		navigation = new main_cardFrame();
		if(navigation != null) {
			navigation.addObserver(this);
			this.Permissions = Permissions;
			System.out.println("in NavigationControl , launching navigation screen");
			navigation.displayNavScreen(Permissions, Permissions.length, Buttons, Buttons.length);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof String)
		{
			String button = (String) arg;
			int doesExist = 0;
			for (String i : Buttons) {
				if (i.equals(button))
					doesExist++;
			}
			if (doesExist > 0) {
				switch (button) {
				case "create order":
					System.out.println("in NavigationControl , user event: navigation to create order screen");
					navigation.flipVisibility();
					nOrders = new NewOrderControl();
					nOrders.addObserver(this);
					break;
				case "ready orders":
					System.out.println("in NavigationControl , user event: navigation to ready orders screen");
					break;
				default:
					System.out.println("in NavigationControl , user event: navigation error --> can't display screen");
					break;
				}
			}
		}
		if (arg instanceof Boolean) {
			boolean temp = (boolean) arg;
			if(temp) {
				navigation.flipVisibility(); //displayNavScreen(Permissions, Permissions.length, Buttons, Buttons.length);
			}
				
		}
	}
}