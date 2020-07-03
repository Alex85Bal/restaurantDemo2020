package fileHandle;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WorkersVector extends dataBaseItemVector {

	@Override
	public boolean readFromFile (String path, String fileName) throws Exception, IOException {
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
	
	@Override
	public void showCase() {
		System.out.println("1.DBName 2.#ID 3. #itemName 4.#Branch 5.#Stock 6.#MinStock 7.#type\n");
	}

}

