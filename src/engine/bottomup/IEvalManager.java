package engine.bottomup;

import java.util.Set;

import basics.Rule;
import facts.FactCollection;

public interface IEvalManager {
	void init(Set<Rule> program);
	FactCollection evaluate();
}
