package util;

import java.io.PrintStream;

public class Printer {
	private final PrintStream out;
	
	Printer(PrintStream out){
		this.out = out;
	}
	
	public void print(IPrintable obj){
		obj.print(out);
	}
	
	public void print(String s){
		this.out.print(s);
	}
	
	public void println(IPrintable obj){
		this.println(obj);
		this.out.println();
	}
	
	public void println(String s){
		this.out.println();
	}
	
	
}
