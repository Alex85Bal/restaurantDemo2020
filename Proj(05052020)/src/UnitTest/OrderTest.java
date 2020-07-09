package UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import Controller.LoginControl;
import GUI.incomingOrderEvent;
import GUI.order_item;
import GUI.user_obj;
import Run.TestData;
import Run.dataMine;
import fileHandle.OrderModule;
import fileHandle.Workers;
import fileHandle.dataBaseItem;
import fileHandle.dataBaseItemTest;
import fileHandle.dishItem;
import fileHandle.inventoryItem;
import junit.framework.Assert;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
class OrderTest {
	
	private static TestData p;
	private static Vector<dataBaseItem> TestInventoryDataBase; 
	private static Vector<dishItem> TestDishesFromModule;
	private static Vector<order_item> orderedDishes;
	private static inventoryItem singleInvItem;
	private static dishItem singleDishItem;
	private static order_item orderedDish1;
	private static order_item orderedDish2;
	private static incomingOrderEvent incomingOrder1;
	private static incomingOrderEvent incomingOrder2;
	private static incomingOrderEvent EmptyIncomingOrder;
	private static user_obj infoFromUser;
	private static OrderModule om;
	private static dataBaseItemTest Stream;
	private static String Errors = "";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		try {
			p = new TestData();
			p.workerMineData();
			p.inventoryItemsMineData();
			p.dishItemMineData();
			p.timeStampFileCreaton();
			Stream = new dataBaseItemTest();
			om = new OrderModule();
			om.loadDishes();
			om.loadInventory();
			TestDishesFromModule = (Vector<dishItem>) om.getDishesh().clone();
			Stream.readFromFile("C:\\projects", "inventory@1.txt");
			TestInventoryDataBase = Stream.whatFileToAccess("inventoryItem", Stream);
			EmptyIncomingOrder = new incomingOrderEvent(new JPanel());
			orderedDishes = new Vector<order_item>();
			
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
			
			singleDishItem = TestDishesFromModule.get(0);
			orderedDish1 = new order_item("potato+chicken", 1, singleDishItem);
			singleDishItem = TestDishesFromModule.get(1);
			orderedDish2 = new order_item("honey+bread+vanila", 1, singleDishItem); 
			
			orderedDishes.add(orderedDish1);
			incomingOrder1 = new incomingOrderEvent(new JPanel(), (List<order_item>) orderedDishes.clone(), 666);
			orderedDishes.add(orderedDish2);
			incomingOrder2 = new incomingOrderEvent(new JPanel(), (List<order_item>) orderedDishes.clone(), 666);
			
		} catch (Exception e) {
			System.err.println(e.toString());
			Errors += " {Failed to execute @BeforeEach}";
			fail(Errors);
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			orderedDishes.clear();
		} catch (Exception e) {
			System.err.println(e.toString());
			Errors += " {Failed to test @AfterEach}";
			fail(Errors);
		}
	}

	@Test
	@Order(1)
	void empty_order() {
		try {
			System.out.println("test1, empty == same amount of chicken");
			Assert.assertEquals("", om.incomingOrder(EmptyIncomingOrder));
		} catch (Exception e) {
			Errors += " {Failed to test empty_order}";
			System.err.println(e.toString());
			fail(Errors);
		}
	}
	
	@Test
	@Order(2)
	void single_dish_order() {
		try {
			System.out.println("test2");
			Assert.assertEquals("", om.incomingOrder(incomingOrder1));
		} catch (Exception e) {
			Errors += " {Failed to test single_dish_order}";
			System.err.println(e.toString());
			fail(Errors);
		}
	}
	
	@Test
	@Order(3)
	void milti_dish_order() {
		try {
			System.out.println("test3");
			Assert.assertEquals("", om.incomingOrder(incomingOrder2));
		} catch (Exception e) {
			Errors += " {Failed to test milti_dish_order}";
			System.err.println(e.toString());
			fail(Errors);
		}
	}
	
	@Test
	@Order(4)
	void no_more_inventory_for_dish_order() {
		try {
			System.out.println("test4");
			Assert.assertEquals("Whole Order Refused", om.incomingOrder(incomingOrder1));
		} catch (Exception e) {
			Errors += " {Failed to test milti_dish_order}";
			System.err.println(e.toString());
			fail(Errors);
		}
	}

}
