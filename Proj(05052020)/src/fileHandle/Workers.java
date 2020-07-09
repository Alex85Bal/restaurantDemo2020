/**
 * 
 */
package fileHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import GUI.user_obj;

/**
 * @author Yosi
 *
 */
public class Workers extends dataBaseItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	private ArrayList<Integer> auth;
	private String pass;
	private String Type;
	private Vector<dataBaseItem> fileRebuilder;
	private Vector<dataBaseItem> source = new Vector<dataBaseItem>();
	protected Exception insufficient_Access_To_FIle = new Exception();
	
	
	public Workers() {
	}
	

	public Workers (String time,boolean fileLock) {
		super.setCurrentTime(time);
		super.setFileLocked(fileLock);
	}
	
	public Workers(String buildFromString) {
		super();
		String[] tokens = buildFromString.split("#");
		this.auth= new ArrayList<Integer>();
		int i=0;
			try {
		super.setDBtype(tokens[i++]);
		super.setItemName(tokens[i++]);
		super.setID(Integer.parseInt(tokens[i++]));
		super.setInBranch(Integer.parseInt(tokens[i++]));
		this.pass=(tokens[i++]);
		this.Type=(tokens[i++]);
		while(tokens.length>i) 
			this.auth.add(Integer.parseInt(tokens[i++])); 
		} catch (Exception String­Index­Out­Of­Bounds­Exception) {}
			}
	
	//public setValuesInWorkers(String buildFromString)
	
	public Workers(String DBName,int id,String Name, int branch, ArrayList<Integer> auth,String pass,String Type) {
		super(DBName, id, Name, branch);
		this.auth = auth;
		this.pass=	pass;
		this.Type=Type;
	}


	public ArrayList<Integer> getAuth() {
		return auth;
	}


	public void setAuth(ArrayList<Integer> auth) {
		this.auth = auth;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}

	
	
	public String asText() {
		String temp=this.getDBname();
		temp += ("#") + super.getItemName();
		temp += ("#") + super.getID();
		temp += ("#") + super.getInBranch();
		temp += ("#") + this.getPass();
		temp += ("#") + this.getType();
		temp += ("#");
		for (int i = 0; i < getAuth().size(); i++) {
			temp += this.getAuth().get(i);
			if (i+1<getAuth().size())temp +=("#");
		}
		return temp;
	}

	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}

	
	public Workers compareAccountInfo(user_obj temp, Vector<dataBaseItem> source) throws IOException, Exception {
		for (int i=0;i<source.size();i++) {
			if (source.get(i).getClass() == new Workers().getClass()) {
				Workers workerInfoCheck = (Workers) source.get(i);
			if (workerInfoCheck.getID() == temp.getPersonal_id()) {
				if (workerInfoCheck.getPass().equals(temp.getPass())) return workerInfoCheck;
	}
			}
			}
		return null;
	}
	
	public Vector<dataBaseItem> readAccountInfo() throws IOException, Exception {
		dataBaseItemTest dbWorkers = new dataBaseItemTest();
		String fullPath = "C:\\projects\\Workers@1.txt";
		if (dbWorkers.getFile(fullPath) == null) throw insufficient_Access_To_FIle;
		
		dbWorkers.readFromFile("C:\\projects", "Workers@1.txt");
		source=dbWorkers.whatFileToAccess("Worker", dbWorkers);
		return source;
	}

	
	@Override
	public Vector<dataBaseItem> rebuild(dataBaseItemTest z) {
		fileRebuilder = new Vector<dataBaseItem>();
		for(int i=1;i<z.getVectorSize();i++) 
			fileRebuilder.add(new Workers(z.getReleaseToDB().get(i).asText()));
		return fileRebuilder;
	}
}




