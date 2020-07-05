package fileHandle;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;



public class dishItem extends dataBaseItem {

	private static final long serialVersionUID = 3L;
	private Vector<inventoryItem> dishIngredients;
	private boolean ingredientsEnough4Dish = false;
	private boolean dishReadyStatus=false;
	private Vector<dataBaseItem> fileRebuilder;

	public boolean isDishReadyStatus() {
		return dishReadyStatus;
	}


	public void setDishReadyStatus(boolean dishReadyStatus) {
		this.dishReadyStatus = dishReadyStatus;
	}

	public dishItem() {
		super();
		super.setDBName("Dish");

	}

	public Vector<inventoryItem> getDishIngredientss() {
		return dishIngredients;
	}


	public void setDishIngredients(Vector<inventoryItem> dishIngredients) {
		this.dishIngredients = dishIngredients;
	}

	public dishItem(String buildFromString) {
		super();
		this.dishIngredients = new Vector<inventoryItem>();
		String[] tokens = buildFromString.split("#");
		int i=0;
		try {
		super.setDBName(tokens[i++]);
		super.setID(Integer.parseInt(tokens[i++]));
		super.setItemName(tokens[i++]);
		super.setInBranch(Integer.parseInt(tokens[i++]));
		while(tokens.length>i) {
		this.dishIngredients.add(new inventoryItem(tokens[i++],Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++]),Integer.parseInt(tokens[i++])));
		}
		}catch (Exception String­Index­Out­Of­Bounds­Exception) {}
	}

	public dishItem(String DBName, int item_ID, String item_name, int branchID, Vector<inventoryItem> g) {
		super(DBName, item_ID, item_name, branchID);
		this.dishIngredients = g;

	}
	
	public dishItem(String item_name,int ID) {
		super.setItemName(item_name);
		super.setID(ID);
		this.dishIngredients= new Vector<inventoryItem>();
	}

	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}

	public void setIngredientsUsageInDish(dishItem foody) {
		for (int i = 0; i < foody.dishIngredients.size(); i++) 
				foody.dishIngredients.get(i).setUsageInDish(foody.dishIngredients.get(i));
	}

	@Override
	public String asText() {
		String temp=super.getDBname();
		temp += ("#") + super.getID();
		temp += ("#") + super.getItemName();
		temp += ("#") + super.getInBranch();
		temp += ("#");
		for (int i = 0; i < getDishIngredientss().size(); i++) {
			temp += getDishIngredientss().get(i).getItemName();
			temp += ("#") + String.valueOf(getDishIngredientss().get(i).getID());
			temp += ("#") + String.valueOf(getDishIngredientss().get(i).getInBranch());
			temp += ("#") + String.valueOf(getDishIngredientss().get(i).getUsageInDish());
			if (i+1<getDishIngredientss().size())temp +=("#");
		}
		return temp;
}


	@Override
	public Vector<dataBaseItem> rebuild(dataBaseItemTest z) {
		fileRebuilder = new Vector<dataBaseItem>();
		for(int i=0;i<z.getVectorSize();i++) 
			fileRebuilder.add(new dishItem(z.getReleaseToDB().get(i).asText()));
	
		return fileRebuilder;
	}


}