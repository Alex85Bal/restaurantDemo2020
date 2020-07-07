package fileHandle;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.management.RuntimeErrorException;
import javax.swing.JPanel;

import GUI.incomingOrderEvent;
import GUI.newOrderPanel;
import GUI.order_item;

public class OrderModule {
	
	private Vector<dataBaseItem> CurrentInventory;
	private Vector<dataBaseItem> CurrentDishes;
	private HashMap<Integer, Integer> ReverseUpdates;
	private HashMap<String, Integer> Approved;
	private boolean reloadrequired = false;
	private dataBaseItemTest Stream;
	private String errorMsg;
	
	public OrderModule() {
		
		Stream = new dataBaseItemTest();
		CurrentInventory = new Vector<dataBaseItem>();
		ReverseUpdates = new HashMap<Integer, Integer>();
		Approved = new HashMap<String, Integer>();
	}
	
	public boolean loadInventory() {
		
		try {

			Stream.readFromFile("C:\\projects", "inventory@1.txt");
			CurrentInventory = Stream.whatFileToAccess("inventoryItem", Stream);

			return true;
			
		} catch (Exception e) {
			return false;
		}	
	}
	
	public boolean loadDishes() {
		
		try {
			
			Stream.readFromFile("C:\\projects", "Dishes@1.txt");
			CurrentDishes = Stream.whatFileToAccess("dishItem", Stream);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public String incomingOrder (incomingOrderEvent IncomingOrder) {
		
		ReverseUpdates.clear();
		Approved.clear();
		ChangeableString canOrderOnlyThose = new ChangeableString("");
		inventoryItem temp = (inventoryItem) CurrentInventory.get(1);
		dishItem minus = (dishItem) CurrentDishes.get(0);
		System.out.println(temp.getDBName()+" : "+temp.getCurrentStock());
		System.out.println(minus.getDishIngredientss().get(1).getItemName()+" : "+minus.getDishIngredientss().get(1).getUsageInDish());
		if (CurrentInventory.isEmpty()) return "0 inventory";
		if (isOrderAllowed(IncomingOrder, canOrderOnlyThose)) {
			// write to CurrentInventory to inventory file.
			return "";
		}
		else return canOrderOnlyThose.getIt();
	}
	
	private boolean isOrderAllowed (incomingOrderEvent IncomingOrder, ChangeableString output) {
		
		try {
			boolean dishFailed = false;
			boolean greenLight = true;
			Vector<order_item> approvedToOrder = new Vector<order_item>();
			Vector<inventoryItem> ings = new Vector<inventoryItem>();
		
			// incomingOrderEvent represents information from the UI.
			// incomingOrderEvent == k{ n{order_item == {dish == q{ingredients}, amount} } }
			for (order_item dish : IncomingOrder.getOrders()) { // for each dish == order_item
				dishFailed = false;
				ings.clear();
				ings = dish.getDb_link_to_dish().getDishIngredientss();
				// O( 2[all the ingredients inside the order] * [inventory size])
				for (int j = 0; j < dish.getAmount(); j++) { // for each dish of the same type in the order_item
					for (inventoryItem inv : ings) { // for any ingredient in a dish
						dishFailed = cantReduceInventory(inv, inv.getUsageInDish(), true); // any problem with ingredient
						if (dishFailed) {
							greenLight = false;
							break;
						}
					}
					if (!dishFailed) { // no problems with ingredient --> notice that we can execute new dish
						Approved.put(dish.getDb_link_to_dish().getItemName(), j+1);
					}
				}	
			}
			
			if (!greenLight) {
				reloadrequired =  !unfuckInventory(ReverseUpdates);
				output.changeTo(whatCanBeRequested());
			}
			return greenLight;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public Vector<dishItem> getDishesh() {
		 try {
			 
			 Vector typelessVector = CurrentDishes;
			 Vector<dishItem> returnVector = (Vector<dishItem>)typelessVector;
			 return returnVector;
			 
		} catch (Exception e) {
			return null;
		}	
	}
	
	private boolean cantReduceInventory (inventoryItem ing, int amount, boolean reduceInventory) {
		try {
			for (int i = 0; i < CurrentInventory.size(); i++) {
				inventoryItem temp = (inventoryItem) CurrentInventory.get(i);
				if ( ing.getID() == temp.getID() ) {
					if (temp.getCurrentStock() >= amount) {
						if(reduceInventory) {
							ReverseUpdates.putIfAbsent(i, temp.getCurrentStock());
							temp.setCurrentStock(temp.getCurrentStock() - amount);
							return false;
						}
						else;
					}
					else return true;
				}
			}
			return false;		
		} catch (Exception e) {
			return true;
		}
	}
	
	private boolean unfuckInventory (HashMap<Integer, Integer> fixer) {
		
		try {
			Set mapStart = fixer.entrySet(); // binds the map into sets 
			Iterator iterator = mapStart.iterator(); // allows iteration
			while(iterator.hasNext()) {
				Map.Entry mapSet = (Map.Entry)iterator.next();
				inventoryItem temp = (inventoryItem) CurrentInventory.get((int) mapSet.getKey());
				temp.setCurrentStock((int) mapSet.getValue());
				CurrentInventory.set((int) mapSet.getKey(), temp);
			}
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	private void displayInventory () {
		try {
			System.out.println("Dispalying the current Inventory:");
			for (dataBaseItem dbi : CurrentInventory) {
				inventoryItem temp = (inventoryItem) dbi;
				System.out.println(temp.getItemName()+" : "+temp.getCurrentStock());
			}
			System.out.println("End.\n");
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}
	
	private String whatCanBeRequested() {
		
		try {
			if(Approved.isEmpty()) return "Whole Order Refused";
			String Error = "";
			Set mapStart = Approved.entrySet(); // binds the map into sets 
			Iterator iterator = mapStart.iterator(); // allows iteration
			while(iterator.hasNext()) {
				Map.Entry mapSet = (Map.Entry)iterator.next();
				Error += mapSet.getKey()+" "+mapSet.getValue();
				if(iterator.hasNext()) Error += "\n";
			}
			return Error;
		} catch (Exception e) {
			return "Error_OrdMod";
		}
	}
	
public static void main(String[] args) throws IOException, Exception  {
		
		OrderModule tes = new OrderModule();
		tes.loadDishes();
		tes.loadInventory();
		String results = "";
		
		Vector<dishItem> dishes = tes.getDishesh();

		
		List<order_item> myDishes = new Vector<order_item>();
		myDishes.add(new order_item("test1", 5, dishes.get(0)));
		myDishes.add(new order_item("test2", 11, dishes.get(1)));
		incomingOrderEvent ord1 = new incomingOrderEvent(new JPanel(), myDishes, 666);
		
		//tes.displayInventory();
		results = tes.incomingOrder(ord1);
		System.out.println( results == "" ? "Order Accepted" : "Order Refused\n"+results+"\nwill be accepted");
		System.out.println("\n");
		//tes.displayInventory();
	}
}
