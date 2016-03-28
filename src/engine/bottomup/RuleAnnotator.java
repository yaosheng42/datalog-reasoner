package engine.bottomup;

import java.util.Set;

import basics.Predicate;
import basics.Rule;

public interface RuleAnnotator {
	void resetIdbPreds(Set<Predicate> idbPreds);
	Set<AnnotatedRule> getAnnotated(Rule rule);
}
