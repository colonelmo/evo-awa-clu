package clustering;

/**
 * 
 * @author colonelmo
 * Representation of a Node in the graph
 */
public class Node {
	private int id ;
	private Cluster own;
	private Cluster assoc ;
	public Node(int id){
		this.id = id; 
		own = new Cluster(id);
		setAssociatedCluster(own);
	}
	
	public Cluster getOwnCluster(){
		return own ;
	}
	
	public Cluster getAssociatedCluster(){
		return assoc; 
	}
	
	public void setAssociatedCluster(Cluster cl){
		assoc = cl.recordAddition(this) ;
	}
	
	public int getId(){
		return id ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Node){
			return ((Node) obj).getId() == getId() ;
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return getId() ;
	}

	public void reset() {
		setAssociatedCluster(own);
	}
	
}
