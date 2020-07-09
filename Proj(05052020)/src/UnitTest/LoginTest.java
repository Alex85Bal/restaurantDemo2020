package UnitTest;

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

class LoginTest {
	
	private static dataMine p;
	private static LoginControl start;
	private static user_obj infoFromUser;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		p = new dataMine();
		p.workerMineData();
		p.inventoryItemsMineData();
		p.dishItemMineData();
		p.timeStampFileCreaton();
		start = new LoginControl();
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//infoFromUser = new user_obj();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void empty_user_canot_happen() {
		infoFromUser = new user_obj();
		// function that returns user obj to display
	}
	
	@Test
	void user_string_string() {
		String pass = "pass";
		String id = "id"; 
		infoFromUser = new user_obj(id,pass);
		// function that returns user obj to display
	}
	
	@Test
	void user_string_int() {
		String pass = "pass";
		int id = 123;
		System.out.println("constructort only takes String ! test impossiable");
		//infoFromUser = new user_obj(pass,id);
		// function that returns user obj to display
	}
	
	@Test
	void user_int_string() {
		int pass = 123;
		String id = "id";
		System.out.println("constructort only takes String ! test impossiable");
		//infoFromUser = new user_obj(pass,id);
		// function that returns user obj to display
	}
	
	@Test
	void user_int_int() {
		int pass = 123;
		int id = 456;
		System.out.println("constructort only takes String ! test impossiable");
		//infoFromUser = new user_obj(pass,id);
		// function that returns user obj to display
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
