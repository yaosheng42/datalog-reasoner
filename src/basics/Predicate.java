package basics;

public class Predicate {
	private final String symbol;
	private final int arity;
	
	Predicate(final String symbol, final int arity){
		this.symbol = symbol;
		this.arity = arity;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getArity() {
		return arity;
	}
	
	
}
