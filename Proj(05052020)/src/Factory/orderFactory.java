package Factory;

import java.util.Vector;

import fileHandle.Order;
import fileHandle.dataBaseItem;
import fileHandle.dishItem;
import fileHandle.inventoryItem;

public class orderFactory extends dataBaseItemFactory {

	
	public Order createOrderItemObject(String DBN,int ID,int branch,int servicePID,Vector<dishItem> g,Vector<inventoryItem> p,int req) {
		if (req == 1) return new Order(DBN,ID, branch, servicePID, g,p);
		if (req == 2) return new Order(ID, branch, servicePID, g);
		if (req == 3) return new Order(DBN);
		else return null;
	}
	
	@Override
	public dataBaseItem createEmptyCtor() {
		return new Order();
	}

}
