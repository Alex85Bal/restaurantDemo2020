package Factory;

import fileHandle.dataBaseItem;

public abstract class dataBaseItemFactory {
	
	public dataBaseItemFactory() {}
	public abstract dataBaseItem createEmptyCtor();
	
	public void createdataBaseItem(String type) {
		if (type == "Worker") this.createW(); 
			if (type == "inventoryItem") this.createI(); 
				if (type == "dishItem")   this.createD(); 
					if (type == "Order")    this.createO(); 
	}
	
	public dishItemFactory createD() {return new dishItemFactory(); }
	public inventoryItemFactory createI() {return new inventoryItemFactory(); }
	public orderFactory createO() {return new orderFactory(); }
	public workerFactory createW() {return new workerFactory(); }
}