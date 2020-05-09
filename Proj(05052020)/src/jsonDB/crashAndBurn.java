package jsonDB;

import java.io.IOException;

public class crashAndBurn {

	public static void main(String[] args) {

		// lets imagine an common inventory row
		jsnObjArray2DB common = null;
		
		try {
			common = new jsnObjArray2DB("C:\\projects", "inventori.json" ,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    int[] Date = {5,5,2020};
	    
	    common.addRow(new invRowObj("potato", 10, Date, 1, 300, false).getRow());
	    common.addRow(new invRowObj("fucks_to_give", 0, Date, 1, 1, false).getRow());
	    
	    System.out.println(common.getInventory());
	    try {
			common.updateInventoryDB();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}