package clustering;

import java.util.Comparator;

public class EdgeTimeStampComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge a, Edge b) {
		if(a.getArrivalTime()< b.getArrivalTime()){
			return -1 ;
		}
		if(a.getArrivalTime() > b.getArrivalTime()){
			return 1 ;
		}
		return 0;
	}

}
