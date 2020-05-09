package jsonDB;

import org.json.*;

public class invRowObj {
	
	private JSONObject invRow = new JSONObject();
	
	public invRowObj(String itemName, int currentAmount, int[] orderDate, int requesterID, int requestedAmount, boolean isApproved) {
		
		invRow.put("item_name", itemName);
        invRow.put("units_in_stock", currentAmount);
        invRow.put("order_date", orderDate);
        invRow.put("requester_ID",requesterID);
        invRow.put("units_requested",requestedAmount);
        invRow.put("approved?",isApproved);
	}
	
	public JSONObject getRow() {	
		return invRow;
	}
}
