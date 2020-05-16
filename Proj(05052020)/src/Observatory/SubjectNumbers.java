package Observatory;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

// observable class/object
public class SubjectNumbers extends Observable {
	
	public void updateNumbers(int[] arr) {
		setChanged();
		notifyObservers(arr);
	}
}
