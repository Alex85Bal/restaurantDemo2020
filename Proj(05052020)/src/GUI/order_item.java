package GUI;

import fileHandle.dishItem;

public class order_item {
	
	private String dish;
	private dishItem db_link_to_dish;
	private int amount;
	
	public dishItem getDb_link_to_dish() {
		return db_link_to_dish;
	}

	public void setDb_link_to_dish(dishItem db_link_to_dish) {
		this.db_link_to_dish = db_link_to_dish;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public order_item(String dishName, int amount) {
		this.dish = dish;
		this.amount = amount;
	}
	
	public order_item(String dishName, int amount, dishItem DBdish) {
		this.dish = dishName;
		this.amount = amount;
		this.db_link_to_dish = DBdish;
	}
}
