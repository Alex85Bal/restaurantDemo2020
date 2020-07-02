package GUI;

import java.util.EventObject;

public class goBackEvent extends EventObject {
	
	private boolean goBackRequest = false;

	public goBackEvent(Object source) {
		super(source);
		goBackRequest = true;
	}

	public boolean isGoBackRequest() {
		return goBackRequest;
	}

	public void toggleGoBackRequest() {
		this.goBackRequest = !goBackRequest;
	}
}
