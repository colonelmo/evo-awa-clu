package clustering;

import java.util.ArrayList;

/**
 * 
 * @author colonelmo
 * cluster representation
 */
public class Cluster {
	private int size;
	private EdgeList edgeContainer ;
	private NodeList nodeContainer ;
	
	int clusterId;
	
	public Cluster(int id){
		size = 0 ;
		edgeContainer = new EdgeList() ;
		nodeContainer = new NodeList() ;
		clusterId = id; 
	}
	
	public Cluster recordAddition(Node node){
		nodeContainer.add(node);
		return this ;
	}
	
	public Cluster addEdge(Node a , Node b , Edge e){
		edgeContainer.add(e);
		nodeContainer.add(a);
		nodeContainer.add(b);
		return this ;
	}
	
	public int getClusterNodeSize(){
		return nodeContainer.size() ;
	}

	public boolean overSized() {
		return getClusterNodeSize() > help.Constants.Parameters.CLUSTER_SIZE ;
	}

	public Edge getOldestEdge() {
		return edgeContainer.getOldestEdge() ;
	}

	public void reset() {
		edgeContainer.reset() ;
		nodeContainer.reset() ;	
	}

	public ArrayList<Edge> allEdges() {
		return edgeContainer.getAll();
	}

	public ArrayList<Node> allNodes() {
		return nodeContainer.getAll() ;
	}

}
