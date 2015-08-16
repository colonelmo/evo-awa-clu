package io.testio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestInput {
	private Scanner in;
	public TestInput(String inFile) throws FileNotFoundException {
//		in = new Scanner(System.in);
				in = new Scanner(new File(inFile));
	}
	
	public boolean hasNextInt(){
		return in.hasNextInt() ;
	}
	
	public int nextInt(){
		return in.nextInt();
	}
	
	public String next(){
		return in.next();
	}
	
}
