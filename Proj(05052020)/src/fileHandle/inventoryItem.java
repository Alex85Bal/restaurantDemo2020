package fileHandle;

public class inventoryItem extends dataBaseItem {
	
	private int amount = -1;
	
	public inventoryItem() {
		super();
		super.setItemName("inventory");
	}
	
	public inventoryItem(String buildFromString) {
		super();
		super.setDBtype("inventory");
		String[] tokens = buildFromString.split("#", 5);	
		super.setID(Integer.parseInt(tokens[1]));
		super.setItemName(tokens[2]);
		super.setInBrench(Integer.parseInt(tokens[3]));
		this.amount = Integer.valueOf(tokens[4]);
	}
	
	public inventoryItem(int item_ID, String item_name, int branchID, int amount) {
		super(item_ID, item_name, branchID);
		super.setDBtype("inventory");
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}
	
	@Override
	public String asText () {
		String temp = super.getDBtype();
		temp += "#"+super.getID();
		temp += "#"+super.getItemName();
		temp += "#"+super.getInBrench();
		temp += "#"+String.valueOf(amount);
		return temp;
	}
}