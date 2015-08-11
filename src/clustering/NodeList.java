package clustering;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;

public class NodeList {
	HashSet<Node> list ;
	
	public NodeList() {
		list = new HashSet<>();
	}
	
	public void add(Node a){
		list.add(a);
	}
	
	public int size() {
		return list.size();
	}

	public void reset() {
		list.clear();
	}

	public ArrayList<Node> getAll() {
		ArrayList<Node> ret = new ArrayList<Node>() ;
		for(Object o : list.toArray()){
			ret.add((Node)o);
		}
		return ret; 
	}
}
