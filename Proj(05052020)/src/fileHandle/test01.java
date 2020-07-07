package fileHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class test01 {

	public static void main(String[] args) throws Exception {
		
		formatMyDateDaddy popo = new formatMyDateDaddy();
		dataBaseItemTest dbInventoryLink = new dataBaseItemTest();
		dataBaseItemTest dbDishLink = new dataBaseItemTest();
		dataBaseItemTest dbOpenOrdersLink = new dataBaseItemTest();
		dataBaseItemTest dbOrders = new dataBaseItemTest();
		dataBaseItemTest dbWorkers = new dataBaseItemTest();
		dataBaseItemTest dbWorkersq = new dataBaseItemTest();
		Vector<dataBaseItem> pls = new Vector<dataBaseItem>();
		Vector<dataBaseItem> pls1 = new Vector<dataBaseItem>();
		Vector<dataBaseItem> pls2 = new Vector<dataBaseItem>();
		Vector<dataBaseItem> pls3 = new Vector<dataBaseItem>();
		Vector<inventoryItem> PP = new Vector<inventoryItem>();
		Vector<inventoryItem> YY = new Vector<inventoryItem>();
		Vector<inventoryItem> QQ = new Vector<inventoryItem>();
		inventoryItem roki = new inventoryItem();
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
		Order ZZ = new Order("Order",1,1,420,MM,QQ);
		//Kitchen BG = new Kitchen(ZZ);
		ArrayList<Integer> g = new ArrayList<Integer>();
		g.add(1); g.add(2);
		Workers w1 = new Workers("Worker",0, "Bob",1,g , "1234", "Temporary");
		//dbOpenOrdersLink.addItem(BG.regress());
		
		dbWorkers.addItem(new Workers(popo.getCurrentTime(),popo.getFileLockage()));
		dbWorkers.addItem(w1.regress());
		dbWorkers.writeToFile("C:\\projects", "Workers@1.txt", false);
		dbWorkers.readFromFile("C:\\projects", "Workers@1.txt");
		dbWorkersq.readOneObjectFromFile("C:\\projects", "Workers@1.txt");
		

		inventoryItem w3 = new inventoryItem();
		w3.setCurrentTime(popo.getCurrentTime());
		w3.setFileLocked(false);
		dbInventoryLink.addItem(w3.regress());
		dbInventoryLink.addItem(new inventoryItem("Inventory", 1, "Potato", 1, 200, 20, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 2, "Chicken", 1, 40, 5, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 3, "Watermelon", 1, 600, 1, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 4, "CokeZero", 1, 999, 40, "Tradble"));
		dbInventoryLink.writeToFile("C:\\projects", "inventory@1.txt", false);
		dbInventoryLink.readFromFile("C:\\projects", "inventory@1.txt");

		dishItem w4 = new dishItem();
		w4.setCurrentTime(popo.getCurrentTime());
		w4.setFileLocked(false);
		dbDishLink.addItem(w4.regress());
		dbDishLink.addItem(GG.regress());
		dbDishLink.addItem(LL.regress());
		dbDishLink.writeToFile("C:\\projects", "Dishes@1.txt", false);
		dbDishLink.readFromFile("C:\\projects", "Dishes@1.txt");
		
		Order w5 = new Order();
		w5.setCurrentTime(popo.getCurrentTime());
		w5.setFileLocked(false);
		dbOrders.addItem(w5.regress());
		dbOrders.addItem(ZZ.regress());
		dbOrders.writeToFile("C:\\projects", "Orders@1.txt", false);
		dbOrders.readFromFile("C:\\projects", "Orders@1.txt");	

		for (int i = 1; i < dbWorkersq.getVectorSize(); i++) 
			System.out.println(dbWorkersq.getReleaseToDB().get(i).asText());
		
		pls=dbWorkersq.whatFileToAccess("Worker", dbWorkersq);
		
		for (int i = 1; i < dbInventoryLink.getVectorSize(); i++)
			System.out.println(dbInventoryLink.getReleaseToDB().get(i).asText());
		
		pls1=dbInventoryLink.whatFileToAccess("inventoryItem", dbInventoryLink);
				
		for (int i = 1; i < dbDishLink.getVectorSize(); i++)
		System.out.println(dbDishLink.getReleaseToDB().get(i).asText());
		
		
		pls2=dbDishLink.whatFileToAccess("dishItem", dbDishLink);
		
//
		for (int i = 1; i < dbOrders.getVectorSize(); i++)
		System.out.println(dbOrders.getReleaseToDB().get(i).asText());
		

		pls3=dbOrders.whatFileToAccess("Order", dbOrders);
		
	
	}
	
}