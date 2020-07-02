package GUI;

import java.util.EventObject;

public class navigationEvent extends EventObject {
	
	private String screenRequested;

	public navigationEvent(Object source, String string) {
		super(source);
		screenRequested = string;
	}

	public String getScreenRequested() {
		return screenRequested;
	}

	public void setScreenRequested(String screenRequested) {
		this.screenRequested = screenRequested;
	}
}
