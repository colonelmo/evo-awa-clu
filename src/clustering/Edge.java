package clustering;

/**
 * 
 * @author colonelmo
 * Representation of an edge
 */
public class Edge {
	private int from , to ;
	private Double weight ;
	private int arrivalTimestamp ;
	private int edgeId ;
	
	public Edge(int f , int t , int now , int id, Double w){
		this(f , t , now, id);
		weight = w ;
	}
	
	public Edge(int f, int t , int now , int id ){
		from = f ;
		to = t ;
		arrivalTimestamp = now ;		
		edgeId = id; 
	}
	
	public int getArrivalTime(){
		return arrivalTimestamp;
	}
	public int getA(){
		return from ;
	}
	public int getB(){
		return to ;
	}
	public Double getWeight(){
		return weight ;
	}
	public int getId(){
		return edgeId ;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Edge){
			return ((Edge) obj).getId()== getId();
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return edgeId ;
	}
	
	public String toString(){
		return from + " " + to + " t : " + arrivalTimestamp + " id : " + edgeId;
	}
	
}
