package Factory;

import java.util.Vector;

import fileHandle.dataBaseItem;
import fileHandle.dishItem;
import fileHandle.inventoryItem;

public class dishItemFactory extends dataBaseItemFactory {

	public dishItem createdishItemObject(String DBN, int ID, String item_name, int branchID, Vector<inventoryItem> g,int req) {
		if (req == 5) return new dishItem(DBN,ID, item_name, branchID, g);
		if (req == 2) return new dishItem(DBN,ID);
		if (req == 1) return new dishItem(DBN);
		if (req == 0) return new dishItem();
		return null;
		
	}
	
	public dishItem createEmtpyDCtor() {
		return new dishItem();
	}
	
	@Override
	public dataBaseItem createEmptyCtor() {
		return new dishItem();
	}

	@Override
	public dataBaseItem createObject() {
		
		return new dishItem();
	}

	
}
