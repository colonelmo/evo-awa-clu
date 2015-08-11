package clustering;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author colonelmo
 * Handling low level queries
 */
public class GraphManager {
	private Graph gr ;
	private HashMap<Edge , Cluster> edgeClusterMap ;
//	private HashMap<Node , Cluster> nodeClusterMap ;
	private HashMap<Integer , Node> idNodeMap ;
	public GraphManager(Graph gr){
		this.gr = gr; 
		edgeClusterMap = new HashMap<>();
//		nodeClusterMap = new HashMap<>() ;
		idNodeMap = new HashMap<>() ;
	}
	
	private Node getNode(int id){
		if(isNew(id)){
			return createNode(id);
		}
		return idNodeMap.get(id);
	}
	
	public void addEdge(Edge e){
		insert(e);
	}
	
	public void deleteEdge(Edge e){
		if(edgeExists(e)){
			delete(e);
		}
	}
	
	public WindowManager getWindowManager(){
		return gr.getWindowManager();
	}
	
	private boolean edgeExists(Edge e){
		return true ;
	}
	
	private void insert(Edge e){
		int nodeIdA = e.getA() ;
		int nodeIdB = e.getB() ;
		Node a = null , b = null ;
		if(isNew(nodeIdA) && isNew(nodeIdB)){ // These 4 conditions could have been handled identically
											  // But they  are handled separately by the author, and the same
											  // Will be done here.
			a = createNode(nodeIdA); 		  
			b = createNode(nodeIdB);
			mergeNewNew(a , b , e);
		}
		else if(isNew(nodeIdA)){
			a = createNode(nodeIdA);
			b = getNode(nodeIdB);
			mergeNewOld(a , b , e);
		}
		else if(isNew(nodeIdB)){
			a = getNode(nodeIdA);
			b = createNode(nodeIdB);
			mergeNewOld(b , a , e);
		}
		else{
			a = getNode(nodeIdA);
			b = getNode(nodeIdB);
			mergeOldOld(a , b , e);
		}
	}
	
	private void mergeNewNew(Node a, Node b , Edge e) {
		Cluster c = a.getAssociatedCluster();
		b.setAssociatedCluster(c);
		edgeClusterMap.put(e,c.addEdge(a , b , e)) ;
	}

	private void mergeOldOld(Node a, Node b , Edge e) {
		Cluster ca = a.getAssociatedCluster();
		Cluster cb = b.getAssociatedCluster();
		
		if(ca == cb){
			edgeClusterMap.put(e, ca.addEdge(a , b , e));
		}
		else{
			if(ca.getClusterNodeSize() > cb.getClusterNodeSize()){
				mergeClusters(ca , cb , e);
			}
			else{
				mergeClusters(ca , cb, e);
			}
		}
	}

	private void mergeClusters(Cluster big, Cluster small, Edge e) {
		for(Edge ed : small.allEdges()){
			edgeClusterMap.put(e , big.addEdge(getNode(ed.getA()), getNode(ed.getB()), ed));
		}
		for(Node node : small.allNodes()){
			node.setAssociatedCluster(big);
		}
		small.reset() ;
		big.addEdge(getNode(e.getA()), getNode(e.getB()), e);
		if(big.overSized()){
			delete(big.getOldestEdge());
		}
	}

	private void mergeNewOld(Node newNode , Node oldNode , Edge e) {
		Cluster c = oldNode.getAssociatedCluster();
		newNode.setAssociatedCluster(c);
		edgeClusterMap.put(e, c.addEdge(newNode, oldNode , e));
		if(c.overSized()){
			delete(c.getOldestEdge());
		}
	}

	private boolean isNew(int nodeId){
		return !idNodeMap.containsKey(nodeId);
	}
	
	public Node createNode(int id){
		Node created = new Node(id) ;
		idNodeMap.put(id,created );
//		nodeClusterMap.put(created , created.getAssociatedCluster());
		return created ;
	}
	
	private void delete(Edge e){
		Cluster cl = edgeClusterMap.get(e);
		ArrayList<Edge> toAdd = new ArrayList<>() ;
		for(Edge ed : cl.allEdges()){
			if(ed != e){
				toAdd.add(ed);
			}
		}
		edgeClusterMap.remove(e);
		for(Node n: cl.allNodes()){
			n.reset() ;
		}
		cl.reset();
		for(Edge ed : toAdd){
			insert(ed);
		}
	}
	
}
