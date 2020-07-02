package GUI;

import java.util.EventObject;

public class loginEvent extends EventObject {

	private String personalID;
	private String password;
	
	public loginEvent(Object source) {
		super(source);
	}
	
	public loginEvent(Object source, String personalID, String password) {
		super(source);
		this.password = password;
		this.personalID = personalID;
		
	}

	public String getPersonalID() {
		return personalID;
	}

	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
