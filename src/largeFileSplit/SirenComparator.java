package largeFileSplit;

import java.util.Comparator;

public class SirenComparator implements Comparator<Data> {

	@Override
	public int compare(Data d1, Data d2) {
		int siren1 = Integer.parseInt(d1.getSiren());  
		int siren2 = Integer.parseInt(d2.getSiren());  
		return siren1 - siren2;  
	}

}
