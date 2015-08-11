package clustering;

import java.util.ArrayList;
/**
 * 
 * @author colonelmo
 * Accessor
 */
public class Graph {
	private AccumulatingGraph updates ;
	private WindowManager wm ;
	private GraphManager gm ;
	
	public Graph(){
		updates = new AccumulatingGraph() ;
		wm = new WindowManager(this) ;
		gm = new GraphManager(this) ;
	}
	
	public void addEdge(int from , int to){
		updates.addEdge(from , to);
	}
	
	public void evaluate(){
		wm.update(updates);
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
}
