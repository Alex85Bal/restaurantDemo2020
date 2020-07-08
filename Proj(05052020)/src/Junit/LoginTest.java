package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Observable;
import java.util.Vector;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import Controller.LoginControl;
import GUI.main_cardFrame;
import GUI.user_obj;
import Run.dataMine;
import fileHandle.Workers;
import fileHandle.dataBaseItem;
import fileHandle.dataBaseItemTest;



class LoginTest {
	
	  @BeforeClass		
	    public static void m1() {							
	        System.out.println("Using @BeforeClass , executed before all test cases ");					
	    }		
	  
	@Before
	public void d() {
		new dataBaseItemTest();
		dataMine p = new dataMine();
		try {
			//p.workerMineData();
			//p.inventoryItemsMineData();
			//p.dishItemMineData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate(Observable o, Object arg) throws IOException, Exception { // login screen
		System.out.println("HELLO");

}
}
