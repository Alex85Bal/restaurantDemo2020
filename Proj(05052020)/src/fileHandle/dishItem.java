package fileHandle;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;



public class dishItem extends dataBaseItem {

	private static final long serialVersionUID = 3L;
	private Vector<inventoryItem> dishIngredients;
	private boolean ingredientsEnough4Dish = false;
	private boolean dishReadyStatus=false;

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
		this.dishIngredients.add(new inventoryItem(tokens[i++],Integer.parseInt(tokens[i++])));
		}
		}catch (Exception String­Index­Out­Of­Bounds­Exception) {}
	}

	public dishItem(String DBName, int item_ID, String item_name, int branchID, Vector<inventoryItem> g) {
		super(DBName, item_ID, item_name, branchID);
		this.dishIngredients = g;

	}
	
	public dishItem(int item_ID,String item_name) {
		super.setItemName(item_name);
	}

	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}

	public void setIngredientsUsageInDish() {
		Scanner x = new Scanner(System.in);
		System.out.println(super.getItemName() + "\nIngredients in grams:\n");
		for (int i = 0; i < dishIngredients.size(); i++) {
			try {
				System.out.println(dishIngredients.get(i).asText() +":");
				dishIngredients.get(i).setUsageInDish(x.nextInt());
			} catch (InputMismatchException e) {
				x.nextLine();
				throw new InputMismatchException("Error,please input a number");
			}
		}
		x.close();
	}

	public String asText(boolean p) {
		String temp= this.getItemName();
		return temp;
	}
	
	@Override
	public String asText() {
		String temp=super.getDBname();
		temp += ("#") + super.getID();
		temp += ("#") + super.getItemName();
		temp += ("#") + super.getInBranch();
		temp += ("#");
		for (int i = 0; i < getDishIngredientss().size(); i++) {
			temp += (String.valueOf(getDishIngredientss().get(i).asText(true)));
			temp += ("#") + String.valueOf(getDishIngredientss().get(i).getUsageInDish());
			if (i+1<getDishIngredientss().size())temp +=("#");
		}
		return temp;
}
}