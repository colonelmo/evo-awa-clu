package clustering;

import io.testio.Printable;

import java.util.ArrayList;
import java.util.HashMap;

public class GenericClusterRepresentation<T> implements Printable{
	ArrayList <T> nodeList ;
	ArrayList<GenericPair<T> > edgeList;
	T clusterId ;

	public GenericClusterRepresentation(){
		nodeList = new ArrayList<>();
		edgeList = new ArrayList<>() ;	
	}

	public GenericClusterRepresentation(T clusterId) {
		this();
		this.clusterId = clusterId;
	}
	
	public ArrayList<T> getNodes(){
		return nodeList;
	}
	
	public ArrayList<GenericPair<T> > getEdges(){
		return edgeList; 
	}
	
	public <M> void transform (GenericClusterRepresentation<M> other , HashMap<M, T> transformationMap){
		nodeList.clear();
		edgeList.clear();
		clusterId = transformationMap.get(other.clusterId) ;
		for(M element : other.getNodes()){
			nodeList.add(transformationMap.get(element));
		}
		
		for(GenericPair<M> element : other.getEdges()){
			edgeList.add(new GenericPair<T>(transformationMap.get(element.first), transformationMap.get(element.second)));
		}
		
	}

	public void addEdge(T a , T b){
		edgeList.add(new GenericPair<T>(a, b));
	}
	
	public void addNode(T a){
		nodeList.add(a);
	}
	
	@Override
	public String print() {
		StringBuilder sb = new StringBuilder() ;
		sb.append("Nodes :") ;
		for(T element : getNodes()){
			sb.append(" ");
			sb.append(element);
		}
		sb.append("\n");
		sb.append("Edges :");
		boolean isFirst= true;
		for(GenericPair<T> element : getEdges()){
			if(!isFirst){
				sb.append(",");
			}
			sb.append(" ");
			sb.append(element.first);
			sb.append(" ");
			sb.append(element.second);
			isFirst = false; 
		}
		return sb.toString() ;
	}
	
}
