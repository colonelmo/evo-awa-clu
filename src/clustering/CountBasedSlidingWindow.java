package clustering;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author colonelmo
 * Simulating sliding window behavior
 */
public class CountBasedSlidingWindow {
	private int maxSize;
	private Queue<Edge> edgeQ ;
	WindowManager manager; 
	public CountBasedSlidingWindow(WindowManager wm , int size){
		maxSize = size ;
		manager = wm ;
		edgeQ = new LinkedList<>();
	}
	
	public void handleNewEdge(Edge e){
		addToWindow(e);
		checkSlidingWindowCondition();
	}

	private void checkSlidingWindowCondition() {
		while(edgeQ.size() > maxSize){
			popFromWindow();
		}
	}

	private void popFromWindow() {
		getGraphManager().deleteEdge(edgeQ.remove());		
	}

	private void addToWindow(Edge e) {
		edgeQ.add(e);
		getGraphManager().addEdge(e);	
	}
	
	private GraphManager getGraphManager(){
		return manager.getGraphManager();
	}
	
}
