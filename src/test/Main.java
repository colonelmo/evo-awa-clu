package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import clustering.Graph;
import io.testio.TestInput;
import io.testio.TestOutput;

public class Main {
	public static void main(String[] args) throws IOException {		
		
//		PrintWriter pw = new PrintWriter("out.txt" , "UTF-8");
//		pw.print("Hello !");
		
		String inFile = "input.txt";
		String outFile = "out.txt";
		TestInput in = new TestInput(inFile);
		TestOutput out = new TestOutput(outFile);
//		System.out.println("done");
		
		Graph<String> g = new Graph<>();
		int tm = 1 ;
		while(in.hasNextInt()){
			int numEdges = in.nextInt(); 
			for(int i = 0 ;i < numEdges ; i++){
				String a = in.next(), b = in.next() ;
				g.addEdge(a ,b) ;
				System.out.println(a + " " + b);
			}
			g.evaluate();
			
//			System.out.println("evaling");
			out.print("time : " + tm + "\n") ;
			out.print(g.getRepresentation());
			out.print("\n");
			tm ++ ;
		}
		out.close() ;
	}
}
