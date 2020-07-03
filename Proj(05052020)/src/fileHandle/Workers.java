/**
 * 
 */
package fileHandle;


/**
 * @author Yosi
 *
 */
public class Workers extends dataBaseItem {
	
	private int[] auth;
	private String pass;
	private String Type;
	
	

	public Workers() {
		super();
		super.setDBName("Workers");
		
	}	
	
	
	public Workers(String buildFromString) {
		super();
		String[] tokens = buildFromString.split("#");
		int j=0;
		int i=0;
			try {
		super.setDBName(tokens[i++]);
		super.setItemName(tokens[i++]);
		super.setID(Integer.parseInt(tokens[i++]));
		super.setInBranch(Integer.parseInt(tokens[i++]));
		this.pass=(tokens[i++]);
		this.Type=(tokens[i++]);
		while(tokens.length>i) {
			this.auth[j] = Integer.parseInt(tokens[i++]); j++; }
		} catch (Exception String­Index­Out­Of­Bounds­Exception) {}
			}
	
	
	public Workers(String DBName,int ID,String Name, int branchID, int[] auth,String pass,String Type) {
		super(DBName, ID, Name,branchID);
		this.auth = auth;
		this.pass=	pass;
		this.Type=Type;
	}


	public int[] getAuth() {
		return auth;
	}


	public void setAuth(int[] auth) {
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


	
	@Override
	public String asText() {
		int tempAuth[] = getAuth().clone();
		String temp=super.getDBname();
		temp += ("#") + super.getItemName();
		temp += ("#") + super.getID();
		temp += ("#") + super.getInBranch();
		temp += ("#") + this.getPass();
		temp += ("#") + this.getType();
		temp += ("#");
		for (int i = 0; i < getAuth().length; i++) {
			temp += ((tempAuth[i]));
			if (i+1<getAuth().length)temp +=("#");
		}
		return temp;
	}

	public dataBaseItem regress() {
		dataBaseItem temp = this;
		return temp;
	}

}

