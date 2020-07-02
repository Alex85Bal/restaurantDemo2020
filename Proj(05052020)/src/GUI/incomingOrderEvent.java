package GUI;

import java.util.EventObject;
import java.util.List;

public class incomingOrderEvent extends EventObject {
	
	private int staff_id;
	private int table_id;
	private List<order_item> orders;
	
	public incomingOrderEvent (Object source) {
		super(source);
	}
	
	public incomingOrderEvent(Object source, List<order_item> orders, int table) {
		super(source);
		this.orders = orders;
		table_id = table;
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public List<order_item> getOrders() {
		return orders;
	}
	
}
