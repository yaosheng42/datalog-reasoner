package substitution;

import basics.ITerm;
import basics.Variable;
import util.IPrintable;

public interface Substitution extends IPrintable{
	ITerm[] apply(ITerm[] originalArgs);
	ITerm get(Variable x);
}
