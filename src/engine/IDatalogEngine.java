package engine;

import java.util.Set;

import basics.Rule;

public interface IDatalogEngine {
	void init(Set<Rule> program);
}
