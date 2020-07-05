package GUI;

import java.util.ArrayList;
import java.util.Arrays;

public class user_obj {
	
	//private static int globalID;
	private int system_id;
	private int personal_id;
	private String pass;
	ArrayList<Integer> auth;
	int[] autho;
	public int[] getAutho() {
		return autho;
	}

	public void setAutho(int[] autho) {
		this.autho = autho;
	}

	private String type;
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

	public user_obj(int personal_id, String pass, ArrayList<Integer> auth, String type, int branch) {
		try {
			this.personal_id = personal_id;
			this.pass = pass;
			this.auth = auth;
			this.type = type;
			this.branch = branch;
			//this.system_id = globalID++;
		} catch (Exception e) {
			this.personal_id = 0;
			this.pass = "";
			this.auth = new ArrayList<>(Arrays.asList(0));
			this.type = "None";
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

	public ArrayList<Integer> getAuth() {
		return auth;
	}

	public void setAuth(ArrayList<Integer> auth) {
		this.auth = auth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
