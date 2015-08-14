package clustering;

import java.util.ArrayList;

public class ClusterGraph {
	ArrayList<Node> nodeList;
	ArrayList<Edge> edgeList;
	public ClusterGraph(EdgeList e , NodeList n) {
		nodeList = n.getAll() ;
		edgeList = e.getAll() ;
	}
}
	