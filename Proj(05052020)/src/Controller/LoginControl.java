package Controller;

import java.util.Observable;
import java.util.Observer;

import GUI.main_cardFrame;
import GUI.user_obj;
import fileHandle.Workers;

public class LoginControl implements Observer {
	
	private boolean canDisplayNav = false; 
	private String PID;
	private String Pass;
	private String UID;
	private main_cardFrame login;
	private NavigationControl nav;
	
	public LoginControl()
	{
		login = new main_cardFrame();
		if (login != null) {
			login.addObserver(this);
			login.displayLoginScreen();	
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// arg, AKA update is a "user_obj" == request for a user-name and password check.
		if (arg.getClass() == new user_obj().getClass()) {
			System.out.println("in LoginControl , user event: user input --> controller call to module");
			user_obj temp = (user_obj) arg;			
			if(temp.getPass() == "") {
				login.displayErrorOnLogin();
			}
			else {
				// module request
				// Module <-- temp
				// Module --> found + temp / not_found + null
				boolean Module = true;
				
				// Module says yes
				if(Module) {
					System.out.println("inLoginControl , module response is positive");
					temp.setAuth(new int[] {1,1,1,1});
					temp.setBranch(1);
					temp.setPersonal_id(007);
					temp.setPass("111");
					temp.setSystem_id(1);
					temp.setType(0);
					System.out.println("switching to NavigationControl");
					login.rip();
					nav = new NavigationControl(temp.getAuth());
					
					
				}
				else {// Module says no
					System.out.println("in LoginControl , module response is negative");
					login.displayNoSuchUser(String.valueOf(temp.getPersonal_id()),temp.getPass());		
				}
			}
		}
	}
}