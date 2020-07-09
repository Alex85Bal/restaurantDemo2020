package UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.LoginControl;
import GUI.user_obj;
import Run.dataMine;
import fileHandle.Workers;
import fileHandle.dataBaseItemTest;
import junit.framework.Assert;

import static org.junit.Assert.assertTrue;

class LoginTest {
	
	private static dataMine p;
	private static LoginControl start;
	private static user_obj infoFromUser;
	private static Workers Worker_Object;
	private static Workers pop;
	private static dataBaseItemTest Stream;
	private static String Errors = "";


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		try {
			p = new dataMine();
			p.workerMineData();
			p.inventoryItemsMineData();
			p.dishItemMineData();
			p.timeStampFileCreaton();
			Stream = new dataBaseItemTest();
			Worker_Object = new Workers();
			
		} catch (Exception e) {	
			Errors += " {Failed @BeforeAll}";
			System.err.println(e.toString());
		}		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		System.out.println(Errors);
	}

	@BeforeEach
	void setUp() throws Exception {
		try {
			Stream.setReleaseToDB(Worker_Object.readAccountInfo());
		} catch (Exception e) {
			System.err.println(e.toString());
			Errors += " {Failed @BeforeEach}";
			fail(Errors);
		}
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test // EXPECTING A NULL
	void empty_user_canot_happen() {
		try {
			infoFromUser = new user_obj();
			Worker_Object = Worker_Object.compareAccountInfo(infoFromUser, Stream.getReleaseToDB());
			Assert.assertNull(Worker_Object);
		} catch (Exception e) {
			Errors += " {Failed empty_user_canot_happen}";
			System.err.println(e.toString());
			fail(Errors);
		}
	}
	
	@Test // EXPECTING A NULL
	void user_string_string_of_chars() {

		try {
			String pass = "pass";
			String id = "id"; 
			infoFromUser = new user_obj(id,pass);
			Worker_Object = Worker_Object.compareAccountInfo(infoFromUser, Stream.getReleaseToDB());
			Assert.assertNull(Worker_Object);
		} catch (Exception e) {
			Errors += " {Failed user_string_string_of_chars}";
			System.err.println(e.toString());
			fail(Errors);
		}
	}
	
	@Test
	void user_big_constructor() {
		String pass = "pass";
		int personal_id = 123;
		ArrayList<Integer> auth = new ArrayList<Integer>();
		auth.add(1); auth.add(1); auth.add(1); auth.add(1);
		String type = "type";
		int branch = 1;
		
		infoFromUser = new user_obj(personal_id, pass, auth, type, branch);
		// function that returns user obj to display
	}
}
