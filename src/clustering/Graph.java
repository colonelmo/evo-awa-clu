package clustering;

import io.testio.Printable;

import java.util.ArrayList;
import java.util.HashMap;

import javax.print.attribute.standard.MediaSize.Other;
/**
 * 
 * @author colonelmo
 * Accessor
 */
public class Graph <T>{
	private AccumulatingGraph updates ;
	private WindowManager wm ;
	private GraphManager gm ;
	int nodeNum = 1 ;
	
	HashMap<T , Integer> nodeMap  ;
	HashMap<Integer, T> inverseMap ;
	
	
	public Graph(){
		nodeNum = 1 ;
		updates = new AccumulatingGraph() ;
		wm = new WindowManager(this) ;
		gm = new GraphManager(this) ;
		
		nodeMap = new HashMap<>();
		inverseMap  = new HashMap<>() ;
	
	}
	
	public void addEdge(T from , T to){
		if(!nodeMap.containsKey(from)){
			nodeMap.put(from, nodeNum);
			inverseMap.put(nodeNum ++ , from);
//			nodeMap.put(from, (Integer)from);
//			inverseMap.put((Integer)from , from);
		}
		if(!nodeMap.containsKey(to)){
			nodeMap.put(to , nodeNum);
			inverseMap.put(nodeNum++ , to);
//			nodeMap.put(to, (Integer)to);
//			inverseMap.put((Integer)to , to);
		}
		updates.addEdge(nodeMap.get(from), nodeMap.get(to));
	
	}
	
	public void evaluate(){
		wm.update(updates);
		gm.entries() ;
		clear() ;
	}
	public void clear(){
		updates.clear() ;
	}
	
	public GraphManager getGraphManager(){
		return gm ;
	}
	
	public WindowManager getWindowManager(){
		return wm ;
	}
//	public ArrayList<ClusterGraph> getClusters(){
//		ArrayList<ClusterGraph> ret = getWindowManager().getClusters() ;
		
//		return ret ;
//	}
	
	public GenericGraphRepresentation<T> getRepresentation(){
		GenericGraphRepresentation<T> ret =  new GenericGraphRepresentation<T>();
		ret.transform(getGraphManager().getRepresentation(), inverseMap );//transform(getGraphManager().getRepresentation(), inverseMap);
		return ret ;
	}
	
}
