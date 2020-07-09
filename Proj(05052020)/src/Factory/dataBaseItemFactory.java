package Factory;

import fileHandle.dataBaseItem;

public abstract class dataBaseItemFactory {
	
	public dataBaseItemFactory() {}
	public abstract dataBaseItem createEmptyCtor();
	public abstract dataBaseItem createObject();
	
}