package Observatory;

import java.util.Observable;
import java.util.Observer;

public class main {

	public static void main(String[] args) {
		
		SubjectNumbers sn = new SubjectNumbers();
		sn.addObserver(new ObserverMult());
		sn.addObserver(new ObserverSum());
		
		sn.updateNumbers(new int[] {2,3});
	}

}
