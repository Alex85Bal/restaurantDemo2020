package Factory;

import fileHandle.dataBaseItem;
import fileHandle.inventoryItem;

public class inventoryItemFactory extends dataBaseItemFactory {
	
	public inventoryItem createInventoryItemObject(String DBN,int item_ID, String item_name, int branchID, int stock,int minStock,String type,int req)	{
		if (req == 7)return new inventoryItem(DBN, item_ID, item_name, branchID, stock, minStock, type);
		if (req == 1)return new inventoryItem(DBN);
		if (req == 0) return new inventoryItem();
		else return null;
	}

	
	public inventoryItem createEmtpyICtor() {
		return new inventoryItem();
	}
	
	@Override
	public dataBaseItem createEmptyCtor() {
		return new inventoryItem();
	}


	@Override
	public dataBaseItem createObject() {
		return new inventoryItem();
	}
}
