package fileHandle;

import java.io.IOException;
import java.util.Vector;

public class test01 {

	public static void main(String[] args) throws Exception {

		inventoryVector dbInventoryLink = new inventoryVector();
		dishVector dbDishLink = new dishVector();
		KitchenOrders dbOpenOrdersLink = new KitchenOrders();
		WaiterOrders dbWaiterOrders = new WaiterOrders();
		Vector<inventoryItem> PP = new Vector<inventoryItem>();
		Vector<inventoryItem> YY = new Vector<inventoryItem>();
		Vector<inventoryItem> QQ = new Vector<inventoryItem>();
		PP.add(new inventoryItem("Inventory", 1, "Potato", 1, 200, 20, "nonTradble"));
		PP.add(new inventoryItem("Inventory", 2, "Chicken", 1, 40, 5, "nonTradble"));
		YY.add(new inventoryItem("Inventory",3,"Chocolate",1,300,30,"NonTradble"));
		YY.add(new inventoryItem("Inventory",4,"Milk",1,200,69,"NonTradble"));
		QQ.add(new inventoryItem("Inventory", 4, "CokeZero", 1, 999, 40, "Tradble"));
		QQ.add(new inventoryItem("Inventory", 4, "Vodka", 1, 999, 40, "Tradble"));
		dishItem GG = new dishItem("Dish", 1, "Lazania", 1, PP);
		dishItem LL = new dishItem("Dish",2,"Cake",1,YY);
		Vector<dishItem> MM = new Vector<dishItem>();
		MM.add(GG); MM.add(LL);
		GG.setIngredientsUsageInDish();
		Order ZZ = new Order("Order",1,1,420,MM,QQ);
		Kitchen BG = new Kitchen(ZZ);

		dbInventoryLink.addItem(new inventoryItem("Inventory", 1, "Potato", 1, 200, 20, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 2, "Chicken", 1, 40, 5, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 3, "Watermelon", 1, 600, 1, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 4, "CokeZero", 1, 999, 40, "Tradble"));
		dbDishLink.addItem(GG.regress());
		dbDishLink.addItem(LL.regress());
		dbOpenOrdersLink.addItem(BG.regress());
		

		dbInventoryLink.writeToFile("C:\\projects", "inventory@1.txt", false);
		dbInventoryLink.readFromFile("C:\\projects", "inventory@1.txt");

		dbDishLink.writeToFile("C:\\projects", "Dishes@1.txt", false);
		dbDishLink.readFromFile("C:\\projects", "Dishes@1.txt");
		
		dbOpenOrdersLink.writeToFile("C:\\projects", "openOrders@1.txt", false);
		dbOpenOrdersLink.readFromFile("C:\\projects", "openOrders@1.txt");
		

		for (int i = 0; i < dbInventoryLink.getVectorSize(); i++)
			System.out.println(dbInventoryLink.getReleaseToDB().get(i).asText());

		for (int i = 0; i < dbDishLink.getVectorSize(); i++)
		System.out.println(dbDishLink.getReleaseToDB().get(i).asText());
		
//
		for (int i = 0; i < dbOpenOrdersLink.getVectorSize(); i++)
		System.out.println(dbOpenOrdersLink.getReleaseToDB().get(i).asText());


	}
	
}