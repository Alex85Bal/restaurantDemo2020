package Observatory;

import java.util.Observable;
import java.util.Observer;

public class ObserverSum implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		int[] temp = (int[]) arg;
		System.out.println(temp[0]+"+"+temp[1]+"="+(temp[0] + temp[1]));
	}
}
