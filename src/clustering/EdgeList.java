package clustering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 
 * @author colonelmo
 * edge list of a cluster, handling insertions/deletions/etc.
 */
public class EdgeList extends AbstractContainer {
	
	PriorityQueue<Edge> list;
	HashSet<Edge> edges ;
	public EdgeList(){
		list = new PriorityQueue<>(1, new EdgeTimeStampComparator());
		edges = new HashSet<>();
	}
	
	public void add(Edge e){
		list.add(e);
		edges.add(e);
	}
	
	public int num(){
		return list.size() ;
	}

	public Edge getOldestEdge() {
		return list.peek();
	}

	public void reset() {
		list.clear();
		edges.clear();
	}

	public ArrayList<Edge> getAll() {
		ArrayList<Edge> ret = new ArrayList<>();
		for(Object o : list.toArray()){
			ret.add((Edge)o);
		}
		return ret; 
	}
	
	public boolean hasEdge(Edge e){
		return edges.contains(e);
	}
	
}
