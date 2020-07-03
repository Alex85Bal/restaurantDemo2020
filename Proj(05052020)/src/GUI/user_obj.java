package GUI;

public class user_obj {
	
	//private static int globalID;
	private int system_id;
	private int personal_id;
	private String pass;
	private int[] auth;
	private int type;
	//private String userRequest;
	private int branch;
	
	public user_obj() {
	}
	
	public user_obj(String ID, String Password) {
		try {
			this.personal_id = Integer.valueOf(ID);
			this.pass = Password;
			//this.userRequest = "loginRequest";
		} catch (Exception e) {
			this.personal_id = 0;
			this.pass = "";
		}
	}

	public user_obj(int personal_id, String pass, int[] auth, int type, int branch) {
		try {
			this.personal_id = personal_id;
			this.pass = pass;
			this.auth = auth.clone();
			this.type = type;
			this.branch = branch;
			//this.system_id = globalID++;
		} catch (Exception e) {
			this.personal_id = 0;
			this.pass = "";
			this.auth = new int[0];
			this.type = 0;
			this.branch = 0;
			//this.system_id = globalID;
		}
	}
	
	public int getSystem_id() {
		return system_id;
	}

	public void setSystem_id(int system_id) {
		this.system_id = system_id;
	}

	public int getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(int personal_id) {
		this.personal_id = personal_id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int[] getAuth() {
		return auth;
	}

	public void setAuth(int[] auth) {
		this.auth = auth.clone();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
/*
	public String getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(String userRequest) {
		this.userRequest = userRequest;
	}
*/
	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}
}
