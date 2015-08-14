package clustering;

import io.testio.Printable;

import java.util.ArrayList;
import java.util.HashMap;

public class GenericGraphRepresentation <T> implements Printable{
	private ArrayList<GenericClusterRepresentation<T> > clusterList ;
	
	public GenericGraphRepresentation(){
		clusterList = new ArrayList<>();
	}
	
	public void addCluster(GenericClusterRepresentation<T> cl){
		clusterList.add(cl);
	}
	
	public <M> void transform (GenericGraphRepresentation<M> other , HashMap<M, T> transformationMap){
		for(GenericClusterRepresentation<M> element : other.getClusters() ){
			GenericClusterRepresentation<T> tmp = new GenericClusterRepresentation<>();
			tmp.transform(element, transformationMap);
			clusterList.add(tmp);
		}
	}
	
	public ArrayList<GenericClusterRepresentation<T> > getClusters(){
		return clusterList;
	}
	@Override
	public String print() {
		StringBuilder sb = new StringBuilder() ;
		for(GenericClusterRepresentation<T> element: getClusters()){
			sb.append(element.print());
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString(); 
	}
}
