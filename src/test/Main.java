package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import clustering.Graph;
import io.testio.TestInput;
import io.testio.TestOutput;

public class Main {
	public static void main(String[] args) throws IOException {		
		String inFile = "in.txt";
		String outFile = "out.txt";
		TestInput in = new TestInput(inFile);
		TestOutput out = new TestOutput(outFile);
		System.out.println("done");
		
		Graph<String> g = new Graph<>();
		while(in.hasNextInt()){
			int numEdges = in.nextInt(); 
			for(int i = 0 ;i < numEdges ; i++){
				String a = in.next(), b = in.next() ;
				g.addEdge(a ,b) ;
			}
			g.evaluate();
			System.out.println("evaling");
			out.print(g.getRepresentation());
		}
		
	}
}
