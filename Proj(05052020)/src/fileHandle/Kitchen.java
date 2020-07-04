package fileHandle;

import java.io.Serializable;
import java.util.Vector;

public class Kitchen extends Order {
	
	private static final long serialVersionUID = 6L;
	private Vector<Order> orderList = new Vector<Order>();
	private Vector<dataBaseItem> fileRebuilder;


	public Kitchen() {}
	
	public Kitchen(String buildFromFile) {
		super();
		this.orderList = new Vector<Order>();
		Vector<dishItem> dishesInOrder= new Vector<dishItem>();
		String[] tokens = buildFromFile.split("#");
		int i=0;
		try {
		super.setDBName(tokens[i++]);
		super.setID(Integer.parseInt(tokens[i++]));
		super.setInBranch(Integer.parseInt(tokens[i++]));
		this.setServicePersonalID(Integer.parseInt(tokens[i++]));
		while(tokens.length>i) {
		dishesInOrder.add(new dishItem(0,tokens[i++])); }
		this.orderList.add(new Order(super.getDBName(),super.getID(),super.getInBranch(),this.getServicePersonalID(),dishesInOrder)
			);
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


@Override
public String asText() {
	String temp=super.getDBname();
	temp += ("#") + super.getID();
	temp += ("#") + super.getInBranch();
	temp += ("#") + super.servicePersonalID;
	temp += ("#");
	for (int i = 0; i < this.getOrderList().size() ; i++) {
		for (int j=0; j < this.getOrderList().get(i).getDishList().size(); j++) {
		temp += this.getOrderList().get(i).getDishList().get(j).getItemName();
		if (j+1<this.getOrderList().get(i).getDishList().size()) temp +=("#");
		}
		}
	return temp;
}

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

