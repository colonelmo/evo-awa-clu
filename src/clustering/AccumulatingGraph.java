package clustering;

import java.util.ArrayList;

/**
 * 
 * @author colonelmo
 * Which edges are added/removed ?
 */
public class AccumulatingGraph {
	private ArrayList<Pair> toInsert ;
//	private ArrayList<Pair> toDelete ; How to define delete ? WHICH connection should be deleted ?
	
	public AccumulatingGraph(){
		toInsert = new ArrayList<>();
	}
	
	public void addEdge(int a, int b){
		toInsert.add(new Pair(a , b));
	}

	public ArrayList<Pair> getInsertions(){ 
		return toInsert ;
	}
	
	public void clear(){
		toInsert.clear();
	}
	
}
