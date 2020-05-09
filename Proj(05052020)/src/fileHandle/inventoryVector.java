package fileHandle;

import java.util.Scanner;

public class inventoryVector extends dataBaseItemVector {

	@Override
	public boolean getWholeVectorFromFile (String path, String fileName) {
		try {
			
			String fullPath = path+"\\"+fileName;
			if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
			Scanner in = new Scanner (super.getSource());
			super.getReleaseToDB().clear();
			
			while (in.hasNextLine()) {
				String readFromFile = in.nextLine();
				super.getReleaseToDB().add(new inventoryItem(readFromFile).regress());
			}
			
			in.close();
			return true;
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
}
