package clustering;

import java.util.Queue;

/**
 * 
 * @author colonelmo
 * Handling graph updates
 */
public class WindowManager {
	private int currentTimestamp ; 
	private CountBasedSlidingWindow window ;
	private Graph gr ;
	static int nextEdgeId ;
	
	static{
		nextEdgeId = 1 ;
	}
	
	public WindowManager(Graph gr){
		this.gr = gr ;
		currentTimestamp = 0 ;
		window = new CountBasedSlidingWindow(this , help.Constants.Parameters.WINDOW_SIZE);
	}
	
	public int getNow(){
		return currentTimestamp ;
	}
	
	private Edge createEdge(Pair p){
		return new Edge(p.a , p.b , currentTimestamp, nextEdgeId) ;
	}
	
	private void advanceTimestamp(){
		currentTimestamp++ ;
	}
	
	public void update(AccumulatingGraph g){
		for(Pair e : g.getInsertions()){
			window.handleNewEdge(createEdge(e));
		}
		// No deletion support yet !
		advanceTimestamp();
	}
	
	public GraphManager getGraphManager(){
		return gr.getGraphManager() ;
	}
	
}
