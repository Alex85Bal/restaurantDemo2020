package GUI;

import java.util.List;

public class readyDish_item {
	
	private String table;
	private String staffId;
	private String name;
	private String amount;
	private String note;
	
	public readyDish_item(String table, String staffId, String name, String amount, String note) {
		this.table = table;
		this.staffId = staffId;
		this.name = name;
		this.amount = amount;
		this.note = note;
	}

	public readyDish_item() {
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
