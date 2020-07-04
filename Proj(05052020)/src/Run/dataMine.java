package Run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import fileHandle.Workers;
import fileHandle.dataBaseItem;
import fileHandle.dataBaseItemTest;

public class dataMine {
	private dataBaseItemTest dbMine = new dataBaseItemTest();
	
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
	// ***** Worker file creaton *****

	public void inventoryItemsMineData() throws IOException, Exception {
		
		
	}
	
	public void dishItemMineData1() throws IOException, Exception {
		
		
	}
	
    public void dishItemMineData() throws IOException, Exception {
		
		
	}


		public dataBaseItemTest getDbMine() {
			return dbMine;
		}

		public void setDbMine(dataBaseItemTest dbMine) {
			this.dbMine = dbMine;
		}

	
	
}
