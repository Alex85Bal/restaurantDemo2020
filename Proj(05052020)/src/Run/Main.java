package Run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import Controller.LoginControl;
import GUI.main_cardFrame;
import fileHandle.Workers;
import fileHandle.dataBaseItem;
import fileHandle.dataBaseItemTest;

public class Main {


	public static void main(String[] args) throws IOException, Exception  {
		dataMine p = new dataMine();
		p.workerMineData();
		// new controller 1;
		LoginControl start = new LoginControl();
		
		// run controler 1 --> {controller 2 --> {controller 3} }
		
		
		
	}
	
	
	

}
