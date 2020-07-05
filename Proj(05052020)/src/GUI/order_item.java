package GUI;

public class order_item {
	
	private String dish;
	private int amount;
	
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

	public order_item(String dish, int amount) {
		this.dish = dish;
		this.amount = amount;
	}
}
