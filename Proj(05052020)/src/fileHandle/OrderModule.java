package fileHandle;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private Vector<dataBaseItem> UpdatedInventory;
	
	private Vector<dataBaseItem> CurrentDishes;
	private HashMap<Integer, Integer> reverseInvChanges;
	private HashMap<Integer, Integer> reverseUpdate;
	
	private HashMap<String, Integer> Approved;
	private boolean reloadrequired = false;
	private dataBaseItemTest Stream;
	private String errorMsg;
	private DateTimeFormatter dtf;
	private LocalDateTime now;
	
	public OrderModule() {
		
		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		now = LocalDateTime.now();  
		System.out.println(dtf.format(now));  
		
		Stream = new dataBaseItemTest();
		CurrentInventory = new Vector<dataBaseItem>();
		UpdatedInventory = new Vector<dataBaseItem>();
		reverseInvChanges = new HashMap<Integer, Integer>();
		reverseUpdate = new HashMap<Integer, Integer>();
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
	
	private boolean loadUpdatedInventory () {
		try {
			
			Stream.readFromFile("C:\\projects", "inventory@1.txt");
			UpdatedInventory = Stream.whatFileToAccess("inventoryItem", Stream);
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public String incomingOrder (incomingOrderEvent IncomingOrder) {
		
		ChangeableString canOrderOnlyThose = new ChangeableString("");
		//displayInventory();
		reverseInvChanges.clear();
		Approved.clear();
		if (CurrentInventory.isEmpty()) {
			return "0 inventory";
		}
		if (isOrderAllowed(IncomingOrder, canOrderOnlyThose)) {
			// write to CurrentInventory to inventory file.
			loadUpdatedInventory();
			fileVsInventoryCheck();
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
				ings = (Vector<inventoryItem>) dish.getDb_link_to_dish().getDishIngredientss().clone();
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
				reloadrequired =  !unfuckInventory(reverseInvChanges, CurrentInventory);
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
							reverseInvChanges.putIfAbsent(i, temp.getCurrentStock());
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
	
	private boolean unfuckInventory (HashMap<Integer, Integer> fixer, Vector<dataBaseItem> unfuck) {
		
		try {
			Set mapStart = fixer.entrySet(); // binds the map into sets 
			Iterator iterator = mapStart.iterator(); // allows iteration
			while(iterator.hasNext()) {
				Map.Entry mapSet = (Map.Entry)iterator.next();
				inventoryItem temp = (inventoryItem) unfuck.get((int) mapSet.getKey());
				temp.setCurrentStock((int) mapSet.getValue());
				unfuck.set((int) mapSet.getKey(), temp);
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
	
	public void hasMyInventoryExpired () {
		// date <-- first line of inventoryfile
		// if date > myDate --> update(UpdatedInventory) + return true + writeToFile(lockFile)
		// only a partial solution:
		// else return false
	}
	
	public boolean fileVsInventoryCheck() {
		try {
			//if(UpdatedInventory.isEmpty()) return false;
			Set mapStart = reverseInvChanges.entrySet(); // binds the map into sets 
			Iterator iterator = mapStart.iterator(); // allows iteration
			while(iterator.hasNext()) {
				Map.Entry mapSet = (Map.Entry)iterator.next();
				inventoryItem myItem = (inventoryItem) CurrentInventory.get((int) mapSet.getKey());
				inventoryItem updatedItem = (inventoryItem) UpdatedInventory.get((int) mapSet.getKey());
				//CurrentInventory.set((int) mapSet.getKey(), temp);
				//System.out.println(myItem.getItemName()+" : INDEX --> "+(int) mapSet.getKey());
				//System.out.println(updatedItem.getItemName()+" : INDES --> "+(int) mapSet.getKey());
				int delta = Math.abs( (int)mapSet.getValue() - myItem.getCurrentStock());
				System.out.println("value Before local order "+(int)mapSet.getValue());
				System.out.println("currently in stock "+updatedItem.getCurrentStock());
				if( delta <= updatedItem.getCurrentStock() ) {

					reverseUpdate.put((int) mapSet.getKey(), updatedItem.getCurrentStock());
					int newStock = updatedItem.getCurrentStock() - delta;
					updatedItem.setCurrentStock(newStock);
					UpdatedInventory.set((int) mapSet.getKey(), updatedItem);
				}
				else {
					
					unfuckInventory(reverseUpdate, UpdatedInventory);
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
public static void main(String[] args) throws IOException, Exception  {
		
		OrderModule tes = new OrderModule();
		tes.loadDishes();
		tes.loadInventory();
		tes.loadUpdatedInventory();
		
		inventoryItem now;
		inventoryItem upd;
		
		// lets diminish the updated inventory in 2 items !
		upd = (inventoryItem) tes.UpdatedInventory.get(0);
		upd.setCurrentStock(10); // we have 10 potato left! in the "file"
		tes.UpdatedInventory.set(0, upd);
		
		upd = (inventoryItem) tes.UpdatedInventory.get(1);
		upd.setCurrentStock(0);
		tes.UpdatedInventory.set(1, upd);
			
		// lets fake an dish order that used potato and chicken
		tes.reverseInvChanges.put(0, 210); // index 0 is the potato 10 is the amount that was before the order
		tes.reverseInvChanges.put(1,18);
			
		tes.fileVsInventoryCheck();
}

}




