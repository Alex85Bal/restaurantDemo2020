package fileHandle;

import java.io.Serializable;
import java.util.Vector;

public class Waiter extends Order {
	
	private static final long serialVersionUID = 5L;
	private Vector<Order> readyOrderList;
	private Vector<dataBaseItem> fileRebuilder;


	
	public Waiter() {}
	
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
		this.dishList.add(new dishItem(lokeno, 0,(tokens[i++]), i, tradbleItemList)); }
		i=0;
		while (lokens.length >i) {
		this.tradbleItemList.add(new inventoryItem(lokeno, 0,(lokens[i++]), i, i, i, lokeno)); 
			}
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
    	 // dbOpenOrdersLink.addItem(this.regress());
    	 // dbOpenOrdersLink.writeToFile("C:\\projects", "openOrders@1.txt", false);
      }
	}
}

/*
@Override
public String asText() {
	String temp=super.getDBname();
	temp += ("#");
	for (int i = 0; i < this.getReadyOrderList().size() ; i++) {
		for (int j=0; j < this.getReadyOrderList().get(i).getDishList().size(); j++) {
		temp += this.getReadyOrderList().get(i).getDishList().get(j).getItemName();
		temp += ("#") + String.valueOf(getReadyOrderList().get(i).getDishList().get(j).getID());
		for (int k=0;k<this.getReadyOrderList().get(i).getDishList().get(j).getDishIngredientss().size();j++) {
			temp += ("#") + getReadyOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getItemName();
			temp += ("#") + String.valueOf(getReadyOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getID());
			temp += ("#") + String.valueOf(getReadyOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getInBranch());
			temp += ("#") + String.valueOf(getReadyOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getUsageInDish());
		}
		temp += ("#") + " ";
		if (j+1<this.getReadyOrderList().get(i).getDishList().size()) temp +=("#");
		}
		temp += ("#") + String.valueOf(getReadyOrderList().get(i).getID());
		temp +=  ("#") +  String.valueOf(getReadyOrderList().get(i).getInBranch());
		temp += ("#") + String.valueOf(getReadyOrderList().get(i).getServicePersonalID());
		if (i+1<this.getReadyOrderList().size()) temp += ("#");
		}
	temp += ("@");
	for (int i=0;i<getTradbleItemList().size();i++) {
		temp += getTradbleItemList().get(i).getItemName();
		temp += ("@") + String.valueOf(getTradbleItemList().get(i).getID());
		temp += ("@") + String.valueOf(getTradbleItemList().get(i).getInBranch());
		if (i+1<getTradbleItemList().size()) temp +=("@");
	}
	return temp;
}
*/
public Vector<Order> getReadyOrderList() {
	return readyOrderList;
}

public void setReadyOrderList(Vector<Order> readyOrderList) {
	this.readyOrderList = readyOrderList;
}

public dataBaseItem regress() {
	dataBaseItem temp = this;
	return temp;
}

@Override
public Vector<dataBaseItem> rebuild(dataBaseItemTest z) {
	fileRebuilder = new Vector<dataBaseItem>();
	for(int i=0;i<z.getVectorSize();i++) 
		fileRebuilder.add(new Waiter(z.getReleaseToDB().get(i).asText()));

	return fileRebuilder;
}
}

