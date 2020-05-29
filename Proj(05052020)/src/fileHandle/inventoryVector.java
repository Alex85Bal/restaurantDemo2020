package fileHandle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class inventoryVector extends dataBaseItemVector {

	@Override
	public boolean readFromFile (String path, String fileName) {
		try {
			
			String fullPath = path+"\\"+fileName;
			String currentLine;
			if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
			BufferedReader br = new BufferedReader(new FileReader(getFile(fullPath)));
			super.getReleaseToDB().clear();
			
			while ((currentLine = br.readLine()) != null) {
				super.getReleaseToDB().add(new inventoryItem(currentLine).regress());
			}
			
			br.close();
			return true;
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}	
	
	@Override
	public void showCase() {
		System.out.println("1.DBName 2.#ID 3. #itemName 4.#Branch 5.#Stock 6.#MinStock 7.#type\n");
	}

}
