package basics;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.PrintStream;

public class Predicate implements IPredicate{
	private final String symbol;
	private final int arity;
	
	Predicate(final String symbol, final int arity){
		if(arity < 0)
			throw new IllegalArgumentException("Arity cannot be negative.");
		this.symbol = symbol;
		this.arity = arity;
	}



	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		out.print(this.symbol);
	}



	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int getArity() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String toString() {
		return "Predicate [symbol=" + symbol + ", arity=" + arity + "]";
	}
	
	
	
}
