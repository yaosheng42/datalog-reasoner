package substitution;

import java.io.PrintStream;

import basics.Constant;
import basics.ITerm;
import basics.Variable;

public interface RestrictedSubstitution extends Substitution{

	@Override
	Constant get(Variable x);

}
