package Controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import GUI.main_cardFrame;
import GUI.user_obj;

public class NavigationControl implements Observer{
	
	private ArrayList<Integer> Permissions;
	private main_cardFrame navigation;
	private NewOrderControl nOrders;
	private OrderServedControl rOrders;
	private String Buttons[] = {"create order","ready orders","under construction"};
	private user_obj user;
	
<<<<<<< HEAD
	public NavigationControl(ArrayList<Integer> arrayList) {
		
=======
	public NavigationControl(int[] Permissions, user_obj user) {
		this.user = user;
>>>>>>> a6084e758f72da287a62f95e72d7857615e32771
		navigation = new main_cardFrame();
		if(navigation != null) {
			navigation.addObserver(this);
			this.Permissions = arrayList;
			System.out.println("in NavigationControl , launching navigation screen");
			navigation.displayNavScreen(arrayList, arrayList.size(), Buttons, Buttons.length);
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
					nOrders = new NewOrderControl(user);
					nOrders.addObserver(this);
					break;
				case "ready orders":
					System.out.println("in NavigationControl , user event: navigation to ready orders screen");
					navigation.flipVisibility();
					if(rOrders == null) {
						rOrders = new OrderServedControl(user);
						rOrders.addObserver(this);
					}
					else {
						rOrders.backToReadyOrders();
					}
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
				navigation.flipVisibility();
			}
		}
	}
}