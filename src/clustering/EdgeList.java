package clustering;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 
 * @author colonelmo
 * edge list of a cluster, handling insertions/deletions/etc.
 */
public class EdgeList extends AbstractContainer {
	
	PriorityQueue<Edge> list;
	
	public EdgeList(){
		list = new PriorityQueue<>(1, new EdgeTimeStampComparator());
	}
	
	public void add(Edge e){
		list.add(e);
	}
	
	public int num(){
		return list.size() ;
	}

	public Edge getOldestEdge() {
		return list.peek();
	}

	public void reset() {
		list.clear();
	}

	public ArrayList<Edge> getAll() {
		ArrayList<Edge> ret = new ArrayList<>();
		for(Object o : list.toArray()){
			ret.add((Edge)o);
		}
		return ret; 
	}
	
}
