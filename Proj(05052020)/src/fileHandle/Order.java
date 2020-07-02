package fileHandle;

import java.io.Serializable;
import java.util.Vector;

public class Order extends dataBaseItem implements Serializable {
			
	private static final long serialVersionUID = 4L;
	protected Vector<dishItem> dishList;
	protected Vector<inventoryItem> tradbleItemList;
	protected int servicePersonalID;
	private boolean orderStatus=false;

	
	public Order() {
		super();
		super.setDBName("Order");

	}
	
	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order(String buildFromFile) {
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

	
	public void setTradbleItemList(Vector<inventoryItem> tradbleItemList) {
		this.tradbleItemList = tradbleItemList;
	}

	public Order(String DBName,int ID,int branch,int servicePID,Vector<dishItem> g,Vector<inventoryItem> p) {
		super.setDBName(DBName);
		super.setID(ID);
		super.setInBranch(branch);
		this.servicePersonalID= servicePID;
		this.dishList = g;
		this.tradbleItemList = p;
	}
	
	public Order(String DBName,int ID,int branch,int servicePID,Vector<dishItem> g) {
		super.setDBName(DBName);
		super.setID(ID);
		super.setInBranch(branch);
		this.servicePersonalID= servicePID;
		this.dishList = g;
	}


	public int getServicePersonalID() {
		return servicePersonalID;
	}

	public void setServicePersonalID(int servicePersonalID) {
		this.servicePersonalID = servicePersonalID;
	}

	public void setDishList(Vector<dishItem> dishList) {
		this.dishList = dishList;
	}
	
	/*
	public void addToDishList(dishItem g) {
		this.dishList.add(g);
	}
	*/

	public Vector<dishItem> getDishList() {
		return dishList;
	}

	public Vector<inventoryItem> getTradbleItemList() {
		return tradbleItemList;
	}
	
	public dishItem findDishInOrder(dishItem findthis) {
		int idx=this.dishList.indexOf(findthis);
		return this.dishList.get(idx);
	}

	@Override
	public String asText() {
		String temp=super.getDBname();
		temp += ("#") + super.getID();
		temp += ("#") + super.getInBranch();
		temp += ("#") + this.servicePersonalID;
		temp += ("#");
		for (int i = 0; i < getDishList().size(); i++) {
			temp += (String.valueOf(getDishList().get(i).asText(true)));
			if (i+1<getDishList().size())temp +=("#");
		}
		temp += ("@");
		for (int i=0;i<getTradbleItemList().size();i++) {
			temp += String.valueOf(getTradbleItemList().get(i).asText(true));
			if (i+1<getTradbleItemList().size()) temp +=("@");
		}
		return temp;
	}

	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}

}
