package fileHandle;

public class dataBaseItem {

	private String itemName = "base";
	private int ID = -1;
	private String name = "nope"; 
	private int inBrench = -1;
	
	public dataBaseItem(int item_ID, String item_name, int branchID) {
		this.ID = item_ID;
		this.name = item_name;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInBrench() {
		return inBrench;
	}
	public void setInBrench(int inBrench) {
		this.inBrench = inBrench;
	}
	public String asText () {
		return String.valueOf(itemName+"#"+ID+"#"+name+"#"+inBrench);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
