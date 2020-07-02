package fileHandle;

import java.io.Serializable;
import java.util.Vector;

public abstract class dataBaseItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String DBName = "base";
	private int ID = -1;
	private String itemName = "nope"; 
	private int inBranch = -1;
	
	public dataBaseItem(String DBname,int item_ID, String item_name, int branchID) {
		this.DBName = DBname;
		this.ID = item_ID;
		this.itemName = item_name;
		this.inBranch = branchID;
	}
	
	public dataBaseItem() {
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		this.ID = iD;
	}
	
	public String getDBname() {
		return DBName;
	}
	
	public void setDBtype(String DBname) {
		this.DBName = DBname;
	}
	
	public int getInBranch() {
		return inBranch;
	}
	
	public void setInBranch(int inBranch) {
		this.inBranch = inBranch;
	}
	
	public String getDBName() {
		return DBName;
	}
	
	public void setDBName(String itemName) {
		this.DBName = itemName;
	}
	
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public abstract String asText ();
	
}
