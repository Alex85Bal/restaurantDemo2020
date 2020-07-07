package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fileHandle.dishItem;
import fileHandle.inventoryItem;

public class newOrderPanel extends JPanel {
	
	private JButton goBack;
	private JButton sendOrder;
	private JTextField tableNumInput;
	private incomingOrderListener mainListensForOrder;
	private goBackListener mainListensForGoBack;
	private HashMap<Integer, JLabel> DB_Labeled;
	private Vector<order_item> finalOrders; 
	private Vector<dishItem> dishes;
	
	public newOrderPanel(Vector<dishItem> dishes, int dishes_amount, user_obj user ) {

		DB_Labeled = new HashMap<Integer, JLabel>();
		finalOrders = new Vector<order_item>();
		this.dishes = dishes;
		if(this.dishes == null) this.dishes = new Vector<dishItem>();
		JPanel scrollable = new JPanel();
		JScrollPane scroller = new JScrollPane(scrollable);
		
		scrollable.setLayout(new FlowLayout(FlowLayout.LEFT));   
		scrollable.setPreferredSize(new Dimension(500,500));

		
		for (int i = 0; i < dishes.size(); i++) {
			scrollable.add( addOrderOption(dishes.get(i).getItemName(), i) );
		}
			
		this.setLayout(new BorderLayout());
		scrollable.setAutoscrolls(true);
		
		scroller.setViewportView(scrollable);
		add(addMainFunctions(), BorderLayout.PAGE_START);
		add(scroller,BorderLayout.CENTER);
		
		scroller.revalidate();
	}
	
	private JPanel addMainFunctions() {
		
		JPanel temp = new JPanel();
		temp.setLayout(new FlowLayout());

		goBack = new JButton("Back");
		goBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goBackEvent temp = new goBackEvent(this);
				if (mainListensForGoBack != null) {
					mainListensForGoBack.requestToGoBack(temp);
				}
			}
		});
		
		sendOrder = new JButton("Make an order");
		sendOrder.addActionListener(new ActionListener() { // what happens when we press sendOrder
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((tableNumInput.getText().compareTo("") != 0)) {
					String temp = tableNumInput.getText();
					if(temp.matches("[0-9]+")) {
						composeFinalOrder();
						if (mainListensForOrder != null) {
							incomingOrderEvent temp_event = new incomingOrderEvent(newOrderPanel.this, finalOrders, Integer.valueOf(temp));
							mainListensForOrder.incomingOrderEvent(temp_event); 
						}
					}
					else {
						JOptionPane.showMessageDialog(newOrderPanel.this, "table id should be only numbers", "Order failure", JOptionPane.CLOSED_OPTION);
					}
				}
				else {
					JOptionPane.showMessageDialog(newOrderPanel.this, "forgot to input table number ?", "Order failure", JOptionPane.CLOSED_OPTION);
				}
			}
		}); // end of the sendOrder button pressed event
		
		tableNumInput = new JTextField(5);
		JLabel tableNumLabel = new JLabel("Input table number: ");
		
		temp.add(tableNumLabel);
		temp.add(tableNumInput);
		temp.add(sendOrder);
		temp.add(goBack);
		
		return temp;
	} 
	
	private JPanel addOrderOption (String dish, int indexOfDish) {
		
		JPanel temp = new JPanel();
		temp.setLayout(new FlowLayout());
		JLabel dishAM = new JLabel("0");
		JButton dishP = new JButton(dish+"+");
		dishP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = Integer.valueOf(dishAM.getText());
				temp++;
				dishAM.setText(String.valueOf(temp));
			}
		});
		
		JButton dishM = new JButton(dish+"-");
		dishM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = Integer.valueOf(dishAM.getText());
				if(temp > 0) temp--;
				dishAM.setText(String.valueOf(temp));
			}
		});
		
		JLabel dishN = new JLabel(dish+": ");
		JLabel intoNoticed = new JLabel(dish);
		temp.add(dishM);
		temp.add(dishP);
		temp.add(dishAM);
		temp.add(dishN);
		DB_Labeled.put(indexOfDish, dishAM);

		return temp;
	}
	
	public void displayInvalidAmount (String canBeOrdered) {
		
		String display = "Order Refused\n"+canBeOrdered+"\nWill be accepted\n";
		if(canBeOrdered.equalsIgnoreCase("Whole Order Refused")) display = canBeOrdered;
		//finalOrders.clear();
		//composeFinalOrder();
		JOptionPane.showMessageDialog(newOrderPanel.this, display, "can't execute the order", JOptionPane.CLOSED_OPTION);
		System.out.println("displayInvalidAmount "+dishes.get(0).getDishIngredientss().isEmpty()+"**********************************************************");
	}
	
	public void displayOrderSucess () {	
		JOptionPane.showMessageDialog(newOrderPanel.this, "Order has been made :)", "Order Sucess", JOptionPane.CLOSED_OPTION);
		SwingUtilities.updateComponentTreeUI(this);
		Set mapStart = DB_Labeled.entrySet(); // binds the map into sets 
		Iterator iterator = mapStart.iterator(); // allows iteration
		while(iterator.hasNext()) {
			Map.Entry mapSet = (Map.Entry)iterator.next();
			JLabel dummy = (JLabel) mapSet.getValue();
			dummy.setText("0");
		}
		tableNumInput.setText("");
		finalOrders.clear();
		System.out.println("displayOrderSucess "+dishes.get(0).getDishIngredientss().isEmpty()+"**********************************************************");

	}
	
	public void setOrderListener (incomingOrderListener io) {
		mainListensForOrder = io;
	}
	
	public void setGoBackListener (goBackListener gb) {
		mainListensForGoBack = gb;
	}
	
	private void composeFinalOrder() {
		finalOrders.clear();
		Set mapStart = DB_Labeled.entrySet(); // binds the map into sets 
		Iterator iterator = mapStart.iterator(); // allows iteration
		while(iterator.hasNext()) {
			Map.Entry mapSet = (Map.Entry)iterator.next();
			JLabel dummy = (JLabel) mapSet.getValue();
			if(Integer.valueOf(dummy.getText()) > 0) {
				finalOrders.add(new order_item(dishes.get((int) mapSet.getKey()).getItemName(),// dish name
						Integer.valueOf(dummy.getText()), // amount
						dishes.get((int) mapSet.getKey())) // dishItem itself
						);
				
			}
		}
	}
	
}
