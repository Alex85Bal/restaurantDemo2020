package Run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import fileHandle.Workers;
import fileHandle.dataBaseItemTest;
import fileHandle.dishItem;
import fileHandle.inventoryItem;

public class dataMine {
	
	// ***** Worker file creaton *****
	public void workerMineData() throws IOException, Exception {
		dataBaseItemTest dbWorker = new dataBaseItemTest();
			ArrayList<Integer> g1 = new ArrayList<>(Arrays.asList(1,2));
			dbWorker.addItem(new Workers("Worker",1, "Bob", 1 ,g1 ,"123", "Temporary"));
			
			ArrayList<Integer> g2 = new ArrayList<>(Arrays.asList(1));
			dbWorker.addItem(new Workers("Worker",2, "Moshe", 1 ,g2 ,"1234", "Permanent"));
			
			ArrayList<Integer> g3 = new ArrayList<>(Arrays.asList(1,3));
			dbWorker.addItem(new Workers("Worker",3, "Yosi", 1 ,g3 ,"321", "Permanent"));
			
			ArrayList<Integer> g4 = new ArrayList<>(Arrays.asList(2,5));
			dbWorker.addItem(new Workers("Worker",4, "Alex", 1 ,g4 ,"213", "Permanent"));
			
			ArrayList<Integer> g5 = new ArrayList<>(Arrays.asList(0));
			dbWorker.addItem(new Workers("Worker",5, "Sharon", 1 ,g5 ,"659", "Temporary"));
			
			ArrayList<Integer> g6 = new ArrayList<>(Arrays.asList(1,2));
			dbWorker.addItem(new Workers("Worker",6, "Obama", 1 ,g6 ,"867", "Permanent"));
			
			ArrayList<Integer> g7 = new ArrayList<>(Arrays.asList(1,4));
			dbWorker.addItem(new Workers("Worker",7, "Yulia", 1 ,g7 ,"911", "Permanent"));
			
			ArrayList<Integer> g8 = new ArrayList<>(Arrays.asList(5));
			dbWorker.addItem(new Workers("Worker",8, "Yuri", 1 ,g8 ,"100", "Temporary"));
			
			dbWorker.writeToFile("C:\\projects", "Workers@1.txt", false);
			
}
	// ***** inventoryItem file creaton *****

	public void inventoryItemsMineData() throws IOException, Exception {
		dataBaseItemTest dbInventoryLink = new dataBaseItemTest();
		dbInventoryLink.addItem(new inventoryItem("Inventory", 1, "Potato", 1, 200, 20, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 2, "Chicken", 1, 16, 5, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 3, "Watermelon", 1, 600, 1, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 4, "CokeZero", 1, 999, 40, "Tradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 5, "Pasta", 1, 23, 11, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 6, "Wine", 1, 60, 12, "Tradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 7, "Avocado", 1, 35, 4, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 8, "Cheese", 1, 800,600, "Tradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 9, "Bread", 1, 500, 234, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 10, "Vanilla", 1, 69, 1, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 11, "Beef", 1, 420, 160, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 12, "Humus", 1, 880, 440, "Tradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 13, "Tomato", 1, 210, 33, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 14, "Vodka", 1, 555, 444, "Tradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 15, "Honey", 1, 120, 25, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 16, "Rice", 1, 666, 420, "Tradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 17, "Onion", 1, 1111, 555, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 18, "Salomon", 1, 332, 111, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 19, "Tuna", 1, 521, 390, "nonTradble"));
		dbInventoryLink.addItem(new inventoryItem("Inventory", 20, "Tea", 1, 700, 249, "Tradble"));
		
		dbInventoryLink.writeToFile("C:\\projects", "inventory@1.txt", false);
	}
	
	// ***** dishItem file creaton *****
	
	public void dishItemMineData() throws IOException, Exception {
		dataBaseItemTest dbDishLink = new dataBaseItemTest();
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
		
		Vector<inventoryItem> CC = new Vector<inventoryItem>();
		CC.add(new inventoryItem("Inventory", 19, "Tuna", 1, 521, 390, "nonTradble"));
		CC.add(new inventoryItem("Inventory", 16, "Rice", 1, 666, 420, "Tradble"));
		dishItem three = new dishItem("Dish", 3, "Karpacho", 1, CC);
		three.setIngredientsUsageInDish(three);
		dbDishLink.addItem(three.regress());
		
		Vector<inventoryItem> DD = new Vector<inventoryItem>();
		DD.add(new inventoryItem("Inventory", 5, "Pasta", 1, 350, 40, "nonTradble"));
		DD.add(new inventoryItem("Inventory", 13, "Tomato", 1, 210, 33, "nonTradble"));
		dishItem four = new dishItem("Dish", 4, "Spaghetti Pomodoro", 1, DD);
		four.setIngredientsUsageInDish(four);
		dbDishLink.addItem(four.regress());
		
		Vector<inventoryItem> EE = new Vector<inventoryItem>();
		EE.add(new inventoryItem("Inventory", 9, "Bread", 1, 500, 234, "nonTradble"));
		EE.add(new inventoryItem("Inventory", 12, "Humus", 1, 880, 440, "Tradble"));
		dishItem five = new dishItem("Dish", 5, "Bread N Humus", 1, EE);
		five.setIngredientsUsageInDish(five);
		dbDishLink.addItem(five.regress());
		
		Vector<inventoryItem> FF = new Vector<inventoryItem>();
		FF.add(new inventoryItem("Inventory", 20, "Chicken", 1, 700, 249, "Tradble"));
		FF.add(new inventoryItem("Inventory", 15, "Cheese", 1, 120, 25, "nonTradble"));
		dishItem six = new dishItem("Dish", 6, "Chicken Parmesan", 1, FF);
		six.setIngredientsUsageInDish(six);
		dbDishLink.addItem(six.regress());
		
		Vector<inventoryItem> GG = new Vector<inventoryItem>();
		GG.add(new inventoryItem("Inventory", 11, "Beef", 1, 420, 160, "nonTradble"));
		GG.add(new inventoryItem("Inventory", 8, "Cheese", 1, 800,600, "Tradble"));
		dishItem seven = new dishItem("Dish", 7, "Hamburger N Cheese", 1, GG);
		seven.setIngredientsUsageInDish(seven);
		dbDishLink.addItem(seven.regress());
		
		Vector<inventoryItem> HH = new Vector<inventoryItem>();
		HH.add(new inventoryItem("Inventory", 3, "Watermelon", 1, 600, 1, "nonTradble"));
		HH.add(new inventoryItem("Inventory", 10, "Vanilla", 1, 69, 1, "nonTradble"));
		dishItem eight = new dishItem("Dish", 8, "Watermelon Fresho", 1, HH);
		eight.setIngredientsUsageInDish(eight);
		dbDishLink.addItem(eight.regress());
		
		Vector<inventoryItem> II = new Vector<inventoryItem>();
		II.add(new inventoryItem("Inventory", 18, "Salomon", 1, 332, 111, "nonTradble"));
		II.add(new inventoryItem("Inventory", 7, "Avocado", 1, 35, 4, "nonTradble"));
		dishItem nine = new dishItem("Dish", 9, "Salomonela", 1, II);
		nine.setIngredientsUsageInDish(nine);
		dbDishLink.addItem(nine.regress());
		
		Vector<inventoryItem> JJ = new Vector<inventoryItem>();
		JJ.add(new inventoryItem("Inventory", 16, "Rice", 1, 666, 420, "Tradble"));
		JJ.add(new inventoryItem("Inventory", 17, "Onion", 1, 1111, 555, "nonTradble"));
		dishItem ten = new dishItem("Dish", 10, "Fried Rice", 1, JJ);
		ten.setIngredientsUsageInDish(ten);
		dbDishLink.addItem(ten.regress());
		
		Vector<inventoryItem> KK = new Vector<inventoryItem>();
		KK.add(new inventoryItem("Inventory", 6, "Tomato", 1, 60, 12, "Tradble"));
		KK.add(new inventoryItem("Inventory", 11, "Beef", 1, 420, 160, "nonTradble"));
		dishItem eleven = new dishItem("Dish", 11, "Gulash", 1, KK);
		eleven.setIngredientsUsageInDish(eleven);
		dbDishLink.addItem(eleven.regress());
		
		Vector<inventoryItem> LL = new Vector<inventoryItem>();
		LL.add(new inventoryItem("Inventory", 5, "Pasta", 1, 350, 40, "nonTradble"));
		LL.add(new inventoryItem("Inventory", 8, "Cheese", 1, 800,600, "Tradble"));
		dishItem twelve = new dishItem("Dish", 12, "Mac N Cheese", 1, LL);
		twelve.setIngredientsUsageInDish(twelve);
		dbDishLink.addItem(twelve.regress());
		
		dbDishLink.writeToFile("C:\\projects", "Dishes@1.txt", false);
	}
	

	
	
}
