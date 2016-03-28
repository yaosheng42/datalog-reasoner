package basics;

import util.IPrintable;

public interface IPredicate extends IPrintable{
	public String getSymbol();
	public int getArity();
}
