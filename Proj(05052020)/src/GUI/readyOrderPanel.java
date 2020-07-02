package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class readyOrderPanel extends JPanel{
	
	private JPanel mainPanel;
	private JPanel scrollable; // ready orders
	private JScrollPane scroller;
	private JButton goBack;
	private JLabel orderAmount;
	private JLabel amountLabel;
	private goBackListener mainListensForGoBack;
	private Vector<readyDish_item> maybeServed; // can be empty in case that everything was served.
	
	public readyOrderPanel (Vector<readyDish_item> displayThese) {
		
		maybeServed = displayThese;
		scrollable = new JPanel();
		scrollable.setLayout(new FlowLayout(FlowLayout.LEFT));
		scrollable.setAutoscrolls(true);
		scrollable.setPreferredSize(new Dimension(500,500));
		scroller = new JScrollPane(scrollable);
		scroller.setViewportView(scrollable);
		this.setLayout(new BorderLayout());
		
		int total = 0;
		for (int i = 0; i < maybeServed.size(); i++) {
			scrollable.add(addReadyDish(
					maybeServed.get(i).getName(), // i will display name
					maybeServed.get(i).getAmount(), // i will display amount of said dish
					maybeServed.get(i).getTable(), // i will display table
					i)); // pass index in dishes vector in order to remove the dish when served
			// lets also add the amount of dishes to the total
			total += Integer.valueOf(maybeServed.get(i).getAmount());
		}
		buildMainPanel (String.valueOf(total));
		
		scroller.setViewportView(scrollable);
		add(mainPanel, BorderLayout.PAGE_START);
		add(scroller,BorderLayout.CENTER);
		scroller.revalidate();
		
	}
	
	private void buildMainPanel (String totalOrders) {
	
		goBack = new JButton("back");
		amountLabel = new JLabel("amount of ready orders:");
		orderAmount = new JLabel(totalOrders);
		ActionListener local_goBackPressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBackEvent temp = new goBackEvent(this);
				if (mainListensForGoBack != null) {
					mainListensForGoBack.requestToGoBack(temp);
					// main_cardFrame should do update of dataBaseObject on "goBack" being pressed.
					JOptionPane.showMessageDialog(readyOrderPanel.this, "orders will be updated upon return to this screen", "leaving screen", JOptionPane.CLOSED_OPTION);
				}
			}
		};
		goBack.addActionListener(local_goBackPressed);
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(amountLabel);
		mainPanel.add(orderAmount);
		mainPanel.add(goBack);
		
	}
	
	public JPanel addReadyDish(String dish, String amount, String tableNumber, int index) {
		JPanel temp = new JPanel();
		temp.setLayout(new FlowLayout());
		
		JLabel temp_tableNumber = new JLabel("{"+tableNumber+"}");
		JLabel temp_dishName = new JLabel(dish);
		JLabel temp_dishAmount = new JLabel(amount);
		JButton temp_servedDish = new JButton("served dish");
		JButton temp_refusedDish = new JButton("client refused");
		// Locally defined action: what will happen when dish is served or refused.
		ActionListener local_dishButtonPressed = new ActionListener() {
			int amount = Integer.valueOf(temp_dishAmount.getText());
			public void actionPerformed(ActionEvent e) {
				if(amount > 1) {
					amount--;
					temp_dishAmount.setText(String.valueOf(amount));
					maybeServed.get(index).setAmount(String.valueOf(amount));
				}
				else {
					maybeServed.get(index).setAmount("0");
					scrollable.remove(temp);
					scrollable.revalidate();
					scrollable.repaint();
				}
				int smaller = Integer.valueOf(orderAmount.getText())-1;
				orderAmount.setText(String.valueOf(smaller));
			}
		}; // end of action listener definition.
		temp_servedDish.addActionListener(local_dishButtonPressed);
		temp_refusedDish.addActionListener(local_dishButtonPressed);
		temp.add(temp_refusedDish);
		temp.add(temp_servedDish);
		temp.add(temp_dishAmount);
		temp.add(temp_dishName);
		temp.add(temp_tableNumber);

		return temp;
	}
	
	public Vector<readyDish_item> getYetToBeServed() {
		if(maybeServed != null) {
			int stop = maybeServed.size();
			for (int i = 0; i < stop; i++) {
				if(maybeServed.get(i).getAmount().equals("0")) {
					maybeServed.remove(i);
					stop--;
					i--;
				}
			}
			return (Vector<readyDish_item>) this.maybeServed.clone();
		}
		else return null;
	}
	
	public void setGoBackListener (goBackListener gb) {
		mainListensForGoBack = gb;
	}
}
