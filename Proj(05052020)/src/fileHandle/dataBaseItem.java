package fileHandle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public abstract class dataBaseItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String DBName = "base";
	private int ID = -1;
	private String Name = "nope"; 
	private int inBranch = -1;
	private String currentTime;
	private boolean isFileLocked  = false;
	
	
	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	
	
	public dataBaseItem(String DBname,int item_ID, String item_name, int branchID) {
		this.DBName = DBname;
		this.ID = item_ID;
		this.Name = item_name;
		this.inBranch = branchID;
	}
	
	public boolean getFileLockageStatus() {
		return isFileLocked;
	}

	public void setFileLocked(boolean isFileLocked) {
		this.isFileLocked = isFileLocked;
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
		return Name;
	}

	public void setItemName(String itemName) {
		this.Name = itemName;
	}

	public abstract String asText ();
	
	public abstract Vector<dataBaseItem> rebuild (dataBaseItemTest z);

	
}
