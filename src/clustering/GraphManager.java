package clustering;

import io.testio.Printable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author colonelmo
 * Handling low level queries
 */
public class GraphManager{
	private int numDel = 0;
	private Graph gr ;
//	private HashMap<Edge , Cluster> edgeClusterMap ;
//	private HashMap<Node , Cluster> nodeClusterMap ;
	private HashMap<Integer , Node> idNodeMap ;
	public GraphManager(Graph gr){
		this.gr = gr; 
//		edgeClusterMap = new HashMap<>();
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
//		System.out.println("edge cl map : " + edgeClusterMap.size());
//		System.out.println("edge : " + e + " hash : " + e.hashCode());
		insert(e);
//		System.out.println(edgeClusterMap.size());
	}
	
	public void deleteEdge(Edge e){
		if(edgeExists(e)){
//			System.out.println("deleting "  + e);
			delete(e);
		}
	}
	
	public WindowManager getWindowManager(){
		return gr.getWindowManager();
	}
	
	private boolean edgeExists(Edge e){
		if(idNodeMap.containsKey(e.getA())){
			return idNodeMap.get(e.getA()).getAssociatedCluster().hasEdge(e);
		}
		return false ;
	}
	
	private void insert(Edge e){
//		System.out.println("insing : " + e);
		int nodeIdA = e.getA() ;
		int nodeIdB = e.getB() ;
		Node a = null , b = null ;
		if(isNew(nodeIdA) && isNew(nodeIdB)){ // These 4 conditions could have been handled identically
											  // But they  are handled separately by the author, and the same
											  // Will be done here.
//			System.out.println("2 new");
			a = createNode(nodeIdA); 		  
			b = createNode(nodeIdB);
			mergeNewNew(a , b , e);
		}
		else if(isNew(nodeIdA)){
//			System.out.println("1 new");
			a = createNode(nodeIdA);
			b = getNode(nodeIdB);
			mergeNewOld(a , b , e);
		}
		else if(isNew(nodeIdB)){
//			System.out.println("1 new");
			a = getNode(nodeIdA);
			b = createNode(nodeIdB);
			mergeNewOld(b , a , e);
		}
		else{
//			System.out.println("old");
			a = getNode(nodeIdA);
			b = getNode(nodeIdB);
			mergeOldOld(a , b , e);
		}
	}
	
	private void mergeNewNew(Node a, Node b , Edge e) {
		Cluster c = a.getAssociatedCluster();
		b.setAssociatedCluster(c);
//		edgeClusterMap.put(e,c.addEdge(a , b , e)) ;
		c.addEdge(a , b , e);
	}

	private void mergeOldOld(Node a, Node b , Edge e) {
		Cluster ca = a.getAssociatedCluster();
		Cluster cb = b.getAssociatedCluster();
		
		if(ca == cb){
//			edgeClusterMap.put(e, ca.addEdge(a , b , e));
			ca.addEdge(a , b , e);
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
//			edgeClusterMap.put(e , big.addEdge(getNode(ed.getA()), getNode(ed.getB()), ed));
			big.addEdge(getNode(ed.getA()), getNode(ed.getB()), ed);
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
//		edgeClusterMap.put(e, c.addEdge(newNode, oldNode , e));
		c.addEdge(newNode, oldNode , e);
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
	
	private Cluster findClusterOfEdge(Edge e){
		return idNodeMap.get(e.getA()).getAssociatedCluster() ;
	}
	
	private void delete(Edge e){
		numDel ++;
//		System.out.println("deling : " + e);
//		System.out.println("#################");
//		entries() ;
//		System.out.println("*****************");
		
//		if(!edgeClusterMap.containsKey(e))
//			return ;
		Cluster cl = findClusterOfEdge(e);
//		System.out.println("cl id of del : " + cl.getClusterId());
//		System.out.println(cl.getClusterId());
//		System.out.println(cl.getClusterEdgeSize());
		ArrayList<Edge> toAdd = new ArrayList<>() ;
//		System.out.println("to add : ");
		for(Edge ed : cl.allEdges()){
			if(ed != e){
				toAdd.add(ed);
//				System.out.println(ed);
			}
		}
		
//		edgeClusterMap.remove(e);
		ArrayList < Node> tempNodes = cl.allNodes() ;
		cl.reset();
//		System.out.println("reseting : ");
		for(Node n: tempNodes){
			n.reset() ;
//			System.out.println(n);
		}
		for(Edge ed : toAdd){
			insert(ed);
		}
	}
	
	public GenericGraphRepresentation<Integer> getRepresentation(){
		GenericGraphRepresentation<Integer> ret = new GenericGraphRepresentation<>(getNumDel());
		for(Node n : idNodeMap.values()){
			n.getAssociatedCluster().setPrintUtilityFlag(false);
		}
		for(Node n : idNodeMap.values()){
//			System.out.println(n.getId() + " " + n.getAssociatedCluster().getClusterId());
			if(!n.getAssociatedCluster().getPrintUtilityFlag()){
				ret.addCluster(n.getAssociatedCluster().getRepresentation());
				n.getAssociatedCluster().setPrintUtilityFlag(true);
			}
		}
		return ret; 
	}
	
	public int getNumDel(){
		return numDel;
	}
	
	public void entries(){
//		for(Integer i : idNodeMap.keySet()){
//			Node n = idNodeMap.get(i);
//			Cluster cl = n.getAssociatedCluster() ;
//			System.out.println(i + " : " + n.getId());
//			System.out.println("cluster : " + cl.getClusterId()+ " " + cl.getClusterEdgeSize() + " " + cl.getClusterNodeSize() );
//			System.out.println("node : ");
//			for(Node nd : cl.allNodes()){
//				System.out.println(nd.getId());
//			}
//			System.out.println("edges :");
//			for(Edge e : cl.allEdges()){
//				System.out.println(e.getA() +  " " + e.getB());
//			}
//			
//			System.out.println("\n");
//		}
		
//		for(Edge e : edgeClusterMap.keySet()){
//			System.out.println(e + " maps to : " + edgeClusterMap.get(e).getClusterId());
//		}
		
	}
	
}
