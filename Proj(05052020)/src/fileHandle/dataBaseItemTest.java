package fileHandle;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

 public class dataBaseItemTest implements Serializable {

	protected Vector<dataBaseItem> releaseToDB = new Vector<dataBaseItem>();
	protected Exception insufficient_Access_To_FIle = new Exception();
	private File source = null;
	protected FileWriter out = null;
	protected BufferedWriter bro = null;
	protected ObjectOutputStream oos = null;
	
	public boolean addItem (dataBaseItem addThis) {
		try {
			return releaseToDB.add(addThis);	
		} catch (Exception e) {
			return false;
		}
	}
	
	public int getVectorSize() {
		return this.releaseToDB.size();
	}
	
	public void showCase() {
		System.out.println("DBName,ID,itemName,Branch");
	}
		
	public void writeToFile(String path, String fileName, boolean keepOldData) throws IOException {
		FileOutputStream fos = null;
		try {
			  String fullPath = path+"\\"+fileName;
			  if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
			  fos= new FileOutputStream(fullPath);
			  
				for (dataBaseItem dataBaseItem : releaseToDB) {
					 try {
						 oos = new ObjectOutputStream(fos);
						 oos.writeObject(dataBaseItem);
	                    } catch (NotSerializableException e) {
	                        e.printStackTrace();
	                    }
				}
				
		}  catch (IOException e1) {
		e1.printStackTrace();
				} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
					if (fos!=null) { fos.close(); }
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
	
	public Vector<dataBaseItem> whatFileToAccess(String DBName,dataBaseItemTest dbVec) {
		Vector<dataBaseItem> fatty = new Vector<dataBaseItem>();
		switch(DBName) {
		
		case "Worker" :
		Workers tempWO = new Workers();
		fatty=tempWO.rebuild(dbVec);
		break;
		
		case "inventoryItem" :
		inventoryItem tempI = new inventoryItem();
		fatty=tempI.rebuild(dbVec);
		break;	
		
		case "dishItem" :
		dishItem tempD = new dishItem();
		fatty=tempD.rebuild(dbVec);
		break;
		
		case "Order" :
		Order tempO = new Order();
		fatty=tempO.rebuild(dbVec);
		break;
		
		case "Kitchen" :
		Kitchen tempK = new Kitchen();
		fatty=tempK.rebuild(dbVec);
		break;
		
		case "Waiter" :
		Waiter tempW = new Waiter();
		fatty=tempW.rebuild(dbVec);		
		break;
		
		default:
		System.out.println("Mistakes have been made");
		break;
		}
		return fatty;
	}
	
	
	public boolean readFromFile (String path, String fileName) throws Exception, IOException {
		ObjectInputStream ois = null;
	    FileInputStream fis = null;
	    this.getReleaseToDB().clear();
	    String fullPath = path+"\\"+fileName;
		 if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
	    try {
	        fis = new FileInputStream(fullPath);
	        while (true) {
	            ois = new ObjectInputStream(fis);
	            this.getReleaseToDB().add((((dataBaseItem)ois.readObject())));
	        }
	    } catch (EOFException ignored) {
	        // as expected
	    } finally {
	        if (fis != null)   { fis.close(); } }
		return false;
	}	
	
}