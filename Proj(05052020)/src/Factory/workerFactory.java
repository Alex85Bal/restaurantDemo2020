package Factory;

import java.util.ArrayList;
import java.util.Vector;

import fileHandle.Order;
import fileHandle.Workers;
import fileHandle.dataBaseItem;
import fileHandle.dishItem;
import fileHandle.inventoryItem;

public class workerFactory extends dataBaseItemFactory {

	public Workers createWorkerItemObject(String DBN,int id,String Name, int branch, ArrayList<Integer> auth,String pass,String Type,int req) {
		if (req == 1) return new Workers(DBN,id, Name,branch, auth,pass,Type);
		if (req == 2) return new Workers(DBN);
		else return null;
	}
	@Override
	public dataBaseItem createEmptyCtor() {
		return new Workers();
	}

}
