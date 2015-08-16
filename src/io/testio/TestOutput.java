package io.testio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TestOutput {
	private PrintWriter out ;
	public TestOutput(String outFileName) throws IOException{
		out = new PrintWriter(outFileName , "UTF-8");
	}
	
	public void print(Printable p){
		out.println(p.print());
//		System.out.println(p.print());
	}
	
	public void close(){
		out.close();
	}
	public void print(String str){
		out.print(str);
	}
}
