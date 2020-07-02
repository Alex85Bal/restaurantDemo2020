package fileHandle;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Vector;

public class WaiterOrders extends dataBaseItemVector {
	private static final long serialVersionUID = 69L;

	@Override
	public boolean readFromFile (String path, String fileName)  throws Exception, IOException {
		ObjectInputStream ois = null;
	    FileInputStream fis = null;
	    super.getReleaseToDB().clear();
	    String fullPath = path+"\\"+fileName;
		if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
	    try {
	        fis = new FileInputStream(fullPath);
	        while (true) {
	            ois = new ObjectInputStream(fis);
	            super.getReleaseToDB().add((((dataBaseItem)ois.readObject())));
	        }
	    } catch (EOFException ignored) {
	        // as expected
	    } finally {
	        if (fis != null)   { fis.close(); } } 
	    
	return false;
}
		/*
		try {
			
			String fullPath = path+"\\"+fileName;
			if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
			Scanner in = new Scanner (super.getSource());
			super.getReleaseToDB().clear();
			
			while (in.hasNextLine()) {
				String readFromFile = in.nextLine();
				super.getReleaseToDB().add(new Waiter(readFromFile).regress());
			}
			
			in.close();
			return true;
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		*/
	
	public boolean writeUpdateFile(String path,String fileName) {
		return true;
	}
	
	@Override
	public void showCase() {
		System.out.println("1.DBName 2.#ID 3. #itemName 4.#Branch 5.#Stock 6.#MinStock 7.#type\n");
	}
}
