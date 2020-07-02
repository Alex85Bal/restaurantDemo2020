package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testWindow implements Observer {
	
	private main_cardFrame run ;
	private Vector<readyDish_item> canBeServed;
	
	public testWindow () {
		
		canBeServed = new Vector<readyDish_item>();
		//canBeServed.add(new readyDish_item("55","007","שקשוקה חריפה","1","בגרסה מתאימה לאשכנזים"));
		//canBeServed.add(new readyDish_item("56","007","עוגת גזר","1","ללא גלוטן"));
		//canBeServed.add(new readyDish_item("55","007","פאד-טאי","2",""));
		
		run = new main_cardFrame();
		run.addObserver(this);
	}
	
	public static void main(String[] args) {
		
		new testWindow();

	}

	@Override
	public void update(Observable o, Object arg) {
		
		user_obj UO = new user_obj();
		incomingOrderEvent I_ORD = new incomingOrderEvent(new Object());
		Vector<readyDish_item> RDI = new Vector<readyDish_item>(); 
		boolean GOB = false;

		String str = "";
		String[] dishes = {"שקשוקה חריפה","עוגת גזר","פאד-טאי"};
		
		if (arg.getClass() == UO.getClass() )
		{
			UO = (user_obj)arg;
			
			System.out.println("controler goes to model with :\n");
			System.out.println(UO.getPersonal_id()+"\n"+UO.getPass()+"\n");
			run.displayNavScreen(new int[] {1,1,0,0}, 3,
					new String[] {"new order","ready orders","underConstruction1","exit"},4);
		}
		else
			if (arg.getClass() == str.getClass())
			{
				str = (String)arg;
				System.out.println(arg);
				
				if (str.equals("new order"))
					run.displayNewOrderScreen(dishes, dishes.length);
				else
					if(str.equals("ready orders")) {

						run.displayReadyOrderScreen(canBeServed);
					}
			}
			else
				if (arg.getClass() == I_ORD.getClass()) {
					I_ORD = (incomingOrderEvent)arg;
					
					for (order_item item : I_ORD.getOrders()) {
						System.out.println(item.getDish());
						System.out.println(item.getAmount());
						canBeServed.add(new readyDish_item(
								String.valueOf(I_ORD.getTable_id()),
								String.valueOf(I_ORD.getStaff_id()),
								item.getDish(),
								String.valueOf(item.getAmount()),
								""
								));
					}
					
					run.displayOrderSucess();
					run.displayInvalidAmount(new String[] {"only 1 x","only 2 y","only 3 z"});
				}
				else
					if(arg instanceof Boolean) {
						GOB = (boolean)arg;
						if (GOB)
							run.displayNavScreen(new int[] {1,1,0,1}, 4,
									new String[] {"new order","ready orders","underConstruction1"},3);
					}
					else
						if(arg.getClass() == RDI.getClass()) {
							RDI = (Vector<readyDish_item>)arg;
							canBeServed = (Vector<readyDish_item>)arg;
							for (readyDish_item readyDish_item : RDI) {
								System.out.println(readyDish_item.getTable());
								System.out.println(readyDish_item.getName());
								System.out.println(readyDish_item.getAmount());
							}
							run.displayNavScreen(new int[] {1,1,0,0}, 3,
									new String[] {"new order","ready orders","underConstruction1","exit"},4);
						}
	}
}
