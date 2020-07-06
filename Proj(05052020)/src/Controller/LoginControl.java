package Controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import GUI.main_cardFrame;
import GUI.user_obj;
import fileHandle.Workers;
import fileHandle.dataBaseItem;
import fileHandle.dataBaseItemTest;

public class LoginControl implements Observer {

	dataBaseItemTest dbWorkers = new dataBaseItemTest();
	Vector<dataBaseItem> source = new Vector<dataBaseItem>();
	Vector<Workers> heyo = new Vector<Workers>();
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
		Workers workerInfoCheck = new Workers();
		boolean Module = false;
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
				try {
					dbWorkers.readFromFile("C:\\projects", "Workers@1.txt");
					source=dbWorkers.whatFileToAccess("Worker", dbWorkers);
					for (int i=0;i<source.size();i++) {
						if (source.get(i).getClass() == new Workers().getClass()) {
							workerInfoCheck = (Workers) source.get(i);
						if (workerInfoCheck.getID() == temp.getPersonal_id()) {
							Module=workerInfoCheck.getPass().equals(temp.getPass());
							if (Module == true) break;
						}
					}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// Module says yes
				if(Module) {
					System.out.println("inLoginControl , module response is positive");
					temp.setAuth(workerInfoCheck.getAuth());
					temp.setBranch(workerInfoCheck.getInBranch());
					temp.setPersonal_id(workerInfoCheck.getID());
					temp.setPass(workerInfoCheck.getPass());
					temp.setSystem_id(1);
					temp.setType(workerInfoCheck.getType());
					System.out.println("switching to NavigationControl");
					login.rip();
					nav = new NavigationControl(temp.getAuth(),temp);
				}
				else {// Module says no
					System.out.println("in LoginControl , module response is negative");
					login.displayNoSuchUser(String.valueOf(temp.getPersonal_id()),temp.getPass());		
				}
			}
		}
	}
}