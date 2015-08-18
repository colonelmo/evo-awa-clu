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
		
		String inFile = "face.in";
		String outFile = "out.txt";
		TestInput in = new TestInput(inFile);
		TestOutput out = new TestOutput(outFile);
//		System.out.println("done");
		
		Graph<Integer> g = new Graph<>();
		int tm = 1 ;
		while(in.hasNextInt()){
			int numEdges = in.nextInt(); 
			int num = 0 ;
			for(int i = 0 ;i < numEdges ; i++){
				int a = in.nextInt(), b = in.nextInt() ;
				g.addEdge(a ,b) ;
//				System.out.println(a + " " + b);
				num ++ ;
			}
//			System.out.println(num);
			if(tm%1000 == 0)
				System.out.println(tm);
			g.evaluate();
//			System.out.println("evaling");
//			out.print("time : " + tm + "\n") ;
//			out.print(g.getRepresentation());
//			out.print("\n");
			tm ++ ;
		}
		out.print(g.getRepresentation());
		out.close() ;
	}
}
