package fileHandle;

public abstract class dataBaseItem {

	private String DBtype = "base";
	private int ID = -1;
	private String itemName = "nope"; 
	private int inBrench = -1;
	
	public dataBaseItem(int item_ID, String item_name, int branchID) {
		this.ID = item_ID;
		this.itemName = item_name;
		this.inBrench = branchID;
	}
	
	public dataBaseItem() {
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getDBtype() {
		return DBtype;
	}
	
	public void setDBtype(String DBtype) {
		this.DBtype = DBtype;
	}
	
	public int getInBrench() {
		return inBrench;
	}
	
	public void setInBrench(int inBrench) {
		this.inBrench = inBrench;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public abstract String asText ();
}
