package fileHandle;

import java.io.Serializable;
import java.util.Vector;

public class inventoryItem extends dataBaseItem {
	
	private static final long serialVersionUID = 1L;
	private int currentStock=0;
	private int minStockWarning=0; // min stock that would cause a warning
	private String itemType="noneTradble"; // tradble or not
	private int inWhatOrder; // if invItem is tradble immideatley ready for delivery
	private Vector<dataBaseItem> fileRebuilder;
	private int usageInDish;
	
	
	public inventoryItem() {
		super();
		super.setDBName("inventory");
		
	}	
	
	public inventoryItem(String name,int id,int branch,int usage) {
		super.setItemName(name);
		super.setID(id);
		super.setInBranch(branch);
		this.usageInDish=usage;
	}
	
	public inventoryItem(int id,String name) {
		super.setID(id);
		super.setItemName(name);
	}
	
	public inventoryItem(String buildFromString) {
		super();
		String[] tokens = buildFromString.split("#");
		int i=0;
			try {
		super.setDBName(tokens[i++]);
		super.setID(Integer.parseInt(tokens[i++]));
		super.setItemName(tokens[i++]);
		super.setInBranch(Integer.parseInt(tokens[i++]));
		this.currentStock=Integer.parseInt(tokens[i++]);
		this.minStockWarning=Integer.parseInt(tokens[i++]);
		this.itemType=tokens[i++];
		} catch (Exception String­Index­Out­Of­Bounds­Exception) {}
			}
	
	public inventoryItem(String DBName,int item_ID, String item_name, int branchID, int stock,int minStock,String type) {
		super(DBName, item_ID, item_name,branchID);
		this.currentStock = stock;
		this.minStockWarning=minStock;
		this.itemType=type;
	}
	
	public boolean isItemTradble(String type) {
		if (type == "Tradble") {
			return true;
		}
		return false;
	}
	
	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public int getMinStockWarning() {
		return minStockWarning;
	}

	public void setMinStockWarning(int minStockWarning) {
		this.minStockWarning = minStockWarning;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}
	
	
	public int getUsageInDish() {
		return usageInDish;
	}

	public void setUsageInDish(int usageInDish) {
		this.usageInDish = usageInDish;
	}


	@Override
	public String asText () {
		String temp = super.getDBName();
		temp += "#"+super.getID();
		temp += "#"+super.getItemName();
		temp += "#"+super.getInBranch();
		temp += "#"+String.valueOf(this.currentStock);
		temp += "#"+String.valueOf(this.minStockWarning);
		temp += "#"+this.itemType;
		return temp;
	}

	@Override
	public Vector<dataBaseItem> rebuild(dataBaseItemTest z) {
		fileRebuilder = new Vector<dataBaseItem>();
		for(int i=0;i<z.getVectorSize();i++) 
			fileRebuilder.add(new inventoryItem(z.getReleaseToDB().get(i).asText()));
	
		return fileRebuilder;
	}
}