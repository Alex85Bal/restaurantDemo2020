package fileHandle;

import java.util.Vector;

public class test01 {

	public static void main(String[] args) {
		
		inventoryVector dbLink = new inventoryVector();
		dbLink.addItem(new inventoryItem(1, "potato", 1, 200));
		dbLink.addItem(new inventoryItem(1, "apple", 1, 30));
		dbLink.addItem(new inventoryItem(1, "lemon", 1, 6));

		dbLink.setVectorToFile("C:\\projects", "inventory@1.txt", false);
		dbLink.getWholeVectorFromFile("C:\\projects", "inventory@1.txt");
		
		System.out.println(dbLink.getReleaseToDB().get(0).asText());
		System.out.println(dbLink.getReleaseToDB().get(1).asText());
		System.out.println(dbLink.getReleaseToDB().get(2).asText());
	
		System.out.println("SUP");
	}
}