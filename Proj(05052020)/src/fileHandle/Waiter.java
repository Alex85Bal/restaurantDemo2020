package fileHandle;

import java.util.Vector;

public class Waiter extends Order {
	
	private Vector<Order> readyOrderList;
	KitchenOrders dbOpenOrdersLink = new KitchenOrders();
	WaiterOrders dbReadyOrdersLink = new WaiterOrders();

	
	public Waiter(String buildFromFile) {
		super();
		this.dishList = new Vector<dishItem>();
		this.tradbleItemList = new Vector<inventoryItem>();
		int idx = buildFromFile.indexOf("@");
		String tokeno = buildFromFile.substring(0, idx);
		String lokeno = buildFromFile.substring(idx+1);
		String[] tokens = tokeno.split("#");
		String[] lokens = lokeno.split("@");
		int i=0;
		try {
		super.setDBName(tokens[i++]);
		super.setID(Integer.parseInt(tokens[i++]));
		super.setInBranch(Integer.parseInt(tokens[i++]));
		this.setServicePersonalID(Integer.parseInt(tokens[i++]));
		while(tokens.length>i) {
		this.dishList.add(new dishItem(0,(tokens[i++]))); }
		i=0;
		while (lokens.length >i) {
		this.tradbleItemList.add(new inventoryItem(0,(lokens[i++]))); }
		}catch (Exception String­Index­Out­Of­Bounds­Exception) {}
	}


public Waiter(Order g) {
	super.setDBName(g.getDBname());
	super.setID(g.getID());
	super.setInBranch(g.getInBranch());
	super.setServicePersonalID(g.getServicePersonalID());
	super.setDishList(g.getDishList());
	super.setTradbleItemList(g.getTradbleItemList());
	this.readyOrderList.add(g);
}

public void sendOrderToKitchen() {
	
}
public void changeDishReady(int orderID, dishItem readyDish) {
	dishItem temp;
	for(int i=0;i< this.readyOrderList.size();i++) {
      if (this.readyOrderList.get(i).getID() == orderID) {
    	  temp = this.readyOrderList.get(i).findDishInOrder(readyDish);
    	  temp.setDishReadyStatus(true);
    	  this.readyOrderList.get(i).getDishList().remove(temp);
    	  dbOpenOrdersLink.addItem(this.regress());
    	  dbOpenOrdersLink.writeToFile("C:\\projects", "openOrders@1.txt", false);
      }
	}
}


@Override
public String asText() {
	String temp=super.getDBname();
	temp += ("#") + super.getID();
	temp += ("#") + super.getInBranch();
	temp += ("#") + super.servicePersonalID;
	temp += ("#");
	for (int i = 0; i < getDishList().size(); i++) {
		temp += (String.valueOf(getDishList().get(i).asText(true)));
		if (i+1<getDishList().size())temp +=("#");
	}
	return temp;
}

public dataBaseItem regress() {
	dataBaseItem temp = this;
	return temp;
}

}

