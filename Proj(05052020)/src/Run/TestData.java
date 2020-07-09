package Run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import fileHandle.Workers;
import fileHandle.dataBaseItem;
import fileHandle.dataBaseItemTest;
import fileHandle.dishItem;
import fileHandle.formatMyDateDaddy;
import fileHandle.inventoryItem;

public class TestData {
	
	private formatMyDateDaddy popo = new formatMyDateDaddy();
	
	
	public void timeStampFileCreaton() throws IOException {
		dataBaseItemTest db = new dataBaseItemTest();
		
		db.addItem(new Workers(popo.getCurrentTime(),popo.getFileLockage()));
		db.writeToFile("C:\\projects", "filesTimeStamp@1.txt", false);
	}
	// ***** Worker file creaton *****
	public void workerMineData() throws IOException, Exception {
		dataBaseItemTest dbWorker = new dataBaseItemTest();
		
			dbWorker.addItem(new Workers(popo.getCurrentTime(),popo.getFileLockage()));
			
			ArrayList<Integer> g1 = new ArrayList<>(Arrays.asList(1,2));
			dbWorker.addItem(new Workers("Worker",1, "Bob", 1 ,g1 ,"123", "Temporary"));
			
			ArrayList<Integer> g2 = new ArrayList<>(Arrays.asList(1));
			dbWorker.addItem(new Workers("Worker",2, "Moshe", 1 ,g2 ,"1234", "Permanent"));
			
			dbWorker.writeToFile("C:\\projects", "Workers@1.txt", false);
			
}
	// ***** inventoryItem file creaton *****

	public void inventoryItemsMineData() throws IOException, Exception {
		dataBaseItemTest dbInventoryLink = new dataBaseItemTest();
		dbInventoryLink.addItem(new inventoryItem(popo.getCurrentTime(),popo.getFileLockage()));
		
		dbInventoryLink.addItem(new inventoryItem("Inventory", 1, "Potato", 1, 18, 20, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 2, "Chicken", 1, 14, 5, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 9, "Bread", 1, 18, 234, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 10, "Vanilla", 1, 18, 1, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 15, "Honey", 1, 18, 25, "nonTradble"));
		
		dbInventoryLink.writeToFile("C:\\projects", "inventory@1.txt", false);
	}
	
	// ***** dishItem file creaton *****
	
	public void dishItemMineData() throws IOException, Exception {
		dataBaseItemTest dbDishLink = new dataBaseItemTest();
		dbDishLink.addItem(new dishItem(popo.getCurrentTime(),popo.getFileLockage()));
		
		Vector<inventoryItem> AA = new Vector<inventoryItem>();
		AA.add(new inventoryItem("Inventory", 1, "Potato", 1, 200, 20, "nonTradble"));
		AA.add(new inventoryItem("Inventory", 2, "Chicken", 1, 40, 5, "nonTradble"));
		dishItem one = new dishItem("Dish", 1, "Chicken N Fries", 1, AA);
		one.setIngredientsUsageInDish(one);
		dbDishLink.addItem(one.regress());
		
		Vector<inventoryItem> BB = new Vector<inventoryItem>();
		BB.add(new inventoryItem("Inventory", 10, "Vanilla", 1, 555, 444, "Tradble"));
		BB.add(new inventoryItem("Inventory", 15, "Honey", 1, 120, 25, "nonTradble"));
		BB.add(new inventoryItem("Inventory", 9, "Bread", 1, 500, 234, "nonTradble"));
		dishItem two= new dishItem("Dish", 2, "Special Bread", 1, BB);
		two.setIngredientsUsageInDish(two);
		dbDishLink.addItem(two.regress());
		
		dbDishLink.writeToFile("C:\\projects", "Dishes@1.txt", false);
	}
	

	
	
}
