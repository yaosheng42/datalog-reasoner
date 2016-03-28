package basics;

import util.IPrintable;

public interface IAtom<P extends Predicate> extends IPrintable{
	public P getPredicate();
	public ITerm[] getArgs();
	public boolean isGround();
}
