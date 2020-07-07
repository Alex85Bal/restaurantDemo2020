package fileHandle;

import java.io.Serializable;
import java.util.Vector;

public class Kitchen extends Order {
	
	private static final long serialVersionUID = 6L;
	private Vector<Order> orderList = new Vector<Order>();
	private Vector<dataBaseItem> fileRebuilder;


	public Kitchen() {}
	

	public Kitchen (String time,boolean fileLock) {
		super.setCurrentTime(time);
		super.setFileLocked(fileLock);
	}
	
	public Kitchen(String buildFromFile) {
		super();
		this.orderList = new Vector<Order>();
		Vector<dishItem> dishesInOrder= new Vector<dishItem>();
		String[] tokens = buildFromFile.split("#");
		int i=0;
		int j=0;
		try {
		super.setDBName(tokens[i++]);
		while(tokens.length>i) {
		dishesInOrder.add(new dishItem(tokens[i++],Integer.parseInt(tokens[i++])));
		while (tokens[i] != " "){
		this.dishList.get(j).getDishIngredientss().add(new inventoryItem(tokens[i++],Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++])));
		}
		j++;
		}
		this.orderList.add(new Order(Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++]),dishesInOrder));
		}catch (Exception String­Index­Out­Of­Bounds­Exception) {}
	}


public Kitchen(Order g) {
	super.setDBName(g.getDBname());
	super.setID(g.getID());
	super.setInBranch(g.getInBranch());
	super.setServicePersonalID(g.getServicePersonalID());
	super.setDishList(g.getDishList());
	this.orderList.add(g);
}

public void changeDishReady(int orderID, dishItem readyDish) {
	dishItem temp;
	for(int i=0;i< this.orderList.size();i++) {
      if (this.orderList.get(i).getID() == orderID) {
    	  temp = this.orderList.get(i).findDishInOrder(readyDish);
    	  temp.setDishReadyStatus(true);
    	  this.orderList.get(i).getDishList().remove(temp);
    	  
    	  //dbOpenOrdersLink.writeToFileUpdates("C:\\projects", "openOrders@1.txt", temp.getItemName());
      }
	}
}


/*
@Override
public String asText() {
	String temp=super.getDBname();
	temp += ("#");
	for (int i = 0; i < this.getOrderList().size() ; i++) {
		for (int j=0; j < this.getOrderList().get(i).getDishList().size(); j++) {
		temp += this.getOrderList().get(i).getDishList().get(j).getItemName();
		temp += ("#") + String.valueOf(getOrderList().get(i).getDishList().get(j).getID());
		for (int k=0;k<this.getOrderList().get(i).getDishList().get(j).getDishIngredientss().size();j++) {
			temp += ("#") + getOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getItemName();
			temp += ("#") + String.valueOf(getOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getID());
			temp += ("#") + String.valueOf(getOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getInBranch());
			temp += ("#") + String.valueOf(getOrderList().get(i).getDishList().get(j).getDishIngredientss().get(k).getUsageInDish());
		}
		temp += ("#") + " ";
		if (j+1<this.getOrderList().get(i).getDishList().size()) temp +=("#");
		}
		temp += ("#") + String.valueOf(getOrderList().get(i).getID());
		temp +=  ("#") +  String.valueOf(getOrderList().get(i).getInBranch());
		temp += ("#") + String.valueOf(getOrderList().get(i).getServicePersonalID());
		if (i+1<this.getOrderList().size()) temp += ("#");
		}
	return temp;
}
*/
public Vector<Order> getOrderList() {
	return orderList;
}


@Override
public dataBaseItem regress() {
	dataBaseItem temp = this;
	return temp;
}

@Override
public Vector<dataBaseItem> rebuild(dataBaseItemTest z) {
	fileRebuilder = new Vector<dataBaseItem>();
	for(int i=0;i<z.getVectorSize();i++) 
		fileRebuilder.add(new Kitchen(z.getReleaseToDB().get(i).asText()));

	return fileRebuilder;
}
}

