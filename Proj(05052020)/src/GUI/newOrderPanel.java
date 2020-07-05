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
import java.util.List;
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

public class newOrderPanel extends JPanel {
	
	private JButton goBack;
	private JButton sendOrder;
	private JTextField tableNumInput;
	private incomingOrderListener mainListensForOrder;
	private goBackListener mainListensForGoBack;
	private List<JLabel> noticed; // i = dish, i+1 = amount.
	private List<order_item> finalOrdersList;
	
	public newOrderPanel(String[] dishes, int dishes_amount, user_obj user ) {
		
		noticed = new Vector<JLabel>();
		finalOrdersList = new Vector<order_item>();
		JPanel scrollable = new JPanel();
		JScrollPane scroller = new JScrollPane(scrollable);
		
		scrollable.setLayout(new FlowLayout(FlowLayout.LEFT));   
		scrollable.setPreferredSize(new Dimension(500,500));

		
		for (String string : dishes) {
			scrollable.add(addOrderOption(string));
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
						
						int i = 0;
						while (i < noticed.size() ) {
							if(Integer.valueOf(noticed.get(i+1).getText()) > 0) {
								finalOrdersList.add(new order_item(noticed.get(i).getText(), Integer.valueOf(noticed.get(i+1).getText())));
							}
							i += 2;
						}
						if (finalOrdersList.size() > 0) {
							if (mainListensForOrder != null) {
								incomingOrderEvent temp_event = new incomingOrderEvent(newOrderPanel.this, finalOrdersList, Integer.valueOf(temp));
								mainListensForOrder.incomingOrderEvent(temp_event); 
							}
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
	
	private JPanel addOrderOption (String dish) {
		
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
		
		noticed.add(intoNoticed);
		noticed.add(dishAM);
		
		return temp;
	}
	
	public void displayInvalidAmount (String[] problems) {
		
		String display = "not enough ingridiants!\n";
		for (String string : problems) {
			display += string + "\n"; 
			 
		}
		JOptionPane.showMessageDialog(newOrderPanel.this, display, "can't execute the order", JOptionPane.CLOSED_OPTION);
	}
	
	public void displayOrderSucess () {	
		JOptionPane.showMessageDialog(newOrderPanel.this, "Order has been made :)", "Order Sucess", JOptionPane.CLOSED_OPTION);
		SwingUtilities.updateComponentTreeUI(this);
		for (int i = 1; i < noticed.size(); i+=2) {
			noticed.get(i).setText("0");
		}
		tableNumInput.setText("");
		finalOrdersList.clear();
	}
	
	public void setOrderListener (incomingOrderListener io) {
		mainListensForOrder = io;
	}
	
	public void setGoBackListener (goBackListener gb) {
		mainListensForGoBack = gb;
	}
	
}
