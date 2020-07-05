package fileHandle;

import java.io.Serializable;
import java.util.Vector;

public class Order extends dataBaseItem implements Serializable {
			
	private static final long serialVersionUID = 4L;
	protected Vector<dishItem> dishList;
	protected Vector<inventoryItem> tradbleItemList;
	protected int servicePersonalID;
	private boolean orderStatus=false;
	private Vector<dataBaseItem> fileRebuilder;

	
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
			int j=0;
			try {
			super.setDBName(tokens[i++]);
			super.setID(Integer.parseInt(tokens[i++]));
			super.setInBranch(Integer.parseInt(tokens[i++]));
			this.setServicePersonalID(Integer.parseInt(tokens[i++]));
			while(tokens.length>i) {
			this.dishList.add(new dishItem(tokens[i++],Integer.parseInt(tokens[i++])));
			while(!(tokens[i].contentEquals(" "))) {
			this.dishList.get(j).getDishIngredientss().add(new inventoryItem(tokens[i++],Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++])));
			}
			i++; j++;
			}
			i=0;
			while (lokens.length >i) 
			this.tradbleItemList.add(new inventoryItem((lokens[i++]),Integer.parseInt(lokens[i++]),Integer.parseInt(lokens[i++])));
			}catch (Exception ex ) {
            if (ex instanceof NumberFormatException || ex instanceof StringIndexOutOfBoundsException) {}};	
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
	
	public Order(int ID,int branch,int servicePID,Vector<dishItem> g) {
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
			temp += getDishList().get(i).getItemName();
			temp += ("#") + getDishList().get(i).getID();
			for (int j=0;j<getDishList().get(i).getDishIngredientss().size();j++) {
			temp += ("#") + getDishList().get(i).getDishIngredientss().get(j).getItemName();
			temp += ("#") + String.valueOf(getDishList().get(i).getDishIngredientss().get(j).getID());
			temp += ("#") + String.valueOf(getDishList().get(i).getDishIngredientss().get(j).getInBranch());
			temp += ("#") + String.valueOf(getDishList().get(i).getDishIngredientss().get(j).getUsageInDish());
			}
			temp += ("#") + (" ");
			if (i+1<getDishList().size())temp +=("#");
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

	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}

	@Override
	public Vector<dataBaseItem> rebuild(dataBaseItemTest z) {
		fileRebuilder = new Vector<dataBaseItem>();
		for(int i=0;i<z.getVectorSize();i++) 
			fileRebuilder.add(new Order(z.getReleaseToDB().get(i).asText()));
	
		return fileRebuilder;
	}

}
