package fileHandle;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

abstract class dataBaseItemVector {
	
	private Vector<dataBaseItem> releaseToDB = new Vector<dataBaseItem>();
	protected Exception insufficient_Access_To_FIle = new Exception();
	private File source = null;
	private FileWriter out = null;
	
	public boolean addItem (dataBaseItem addThis) {
		try {
			return releaseToDB.add(addThis);	
		} catch (Exception e) {
			return false;
		}
	}
		
	public boolean setVectorToFile(String path, String fileName, boolean keepOldData) {
		try {
			
			String fullPath = path+"\\"+fileName;
			if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
			out = new FileWriter(fullPath, keepOldData);
	
			for (dataBaseItem dataBaseItem : releaseToDB) {
				out.append(dataBaseItem.asText());
				out.append("\n");
			}
			
			out.flush();
			out.close();
			releaseToDB.clear();
			return true;
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
		
	protected Vector<dataBaseItem> getReleaseToDB() {
		return releaseToDB;
	}
	
	public boolean setReleaseToDB (Vector<dataBaseItem> addThis) {
		try {
			releaseToDB = addThis;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected File getFile(String fullPath) {
		try {
			
			source = new File (fullPath);
			if (!(source.exists())) {
				source.createNewFile();
			}
			if (! (source.canRead()))
					if(! (source.setReadable(true)))
						return null;
						
			if (! (source.canWrite()))
					if(! (source.setWritable(true)))
						return null;

			return source;
			
		} catch (Exception e) {
			return null;
		}
	}

	protected File getSource() {
		return source;
	}

	protected void setSource(File source) {
		this.source = source;
	}

	protected FileWriter getOut() {
		return out;
	}

	protected void setOut(FileWriter out) {
		this.out = out;
	}
	
	public abstract boolean getWholeVectorFromFile (String path, String fileName);
}