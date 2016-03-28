package util;

import java.io.PrintStream;

public interface IPrintable {
	
	void print(PrintStream out);
	
	default void println(PrintStream out){
		print(out);
		out.println();
	};
}
