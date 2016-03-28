package engine.bottomup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import basics.Atom;
import basics.Predicate;
import basics.Rule;
import facts.FactCollection;
import facts.FactIndexer;
import facts.FactIndexerFactory;
import substitution.RuleBasedSubstitution;

public abstract class SeminaiveEvalManager implements IEvalManager{
	protected FactIndexer<Set<Atom>> edbFacts = FactIndexerFactory.createConcurrentFactIndexer();
	protected FactIndexer<Set<Atom>> idbPrev = FactIndexerFactory.createConcurrentFactIndexer();
	protected FactIndexer<Set<Atom>> idbCur = FactIndexerFactory.createConcurrentFactIndexer();
	protected FactIndexer<Set<Atom>> deltaOld = FactIndexerFactory.createConcurrentFactIndexer();
	protected FactIndexer<Set<Atom>> deltaNew = FactIndexerFactory.createConcurrentFactIndexer();
	
	private final Set<AnnotatedRule> firstRoundRules = new HashSet<>();
	private final Set<AnnotatedRule> laterRoundRules = new HashSet<>();
	private boolean useFirstRoundRules = true;
	private final RuleAnnotator ra;
	private final Map<AnnotatedRule, RuleBasedSubstitution> ruleSubstitution = new HashMap<>();
	
	private boolean isInitialized = false;
	private boolean isEvaluated = false;
	
	
	
	protected SeminaiveEvalManager(RuleAnnotator ra) {
		this.ra = ra;
	}

	@Override
	public void init(Set<Rule> program) {
		// TODO Auto-generated method stub
		if(this.isEvaluated)
			throw new IllegalStateException("Cannot initialize an evaluation manager more than once.");
		
		Set<Predicate> idbPreds = new HashSet<>();
		Set<Predicate> potentialEdbPreds = new HashSet<>();
		Set<Rule> edbOnlyRules = new HashSet<>();
		Set<Rule> rulesWithIdbPreds = new HashSet<>();
		
		for(Rule r : program){
			if(r.getBody().isEmpty())
				potentialEdbPreds.add(r.getHead().getPredicate());
			else{
				idbPreds.add(r.getHead().getPredicate());
				for(Atom a : r.getBody())
					potentialEdbPreds.add(a.getPredicate());
			}
		}
		potentialEdbPreds.remove(idbPreds);
		
		for(Rule r : program){
			if(potentialEdbPreds.contains(r.getHead().getPredicate()))
				this.edbFacts.add(r.getHead());
			else{
				if(r.getBody().isEmpty()){
					this.deltaNew.add(r.getHead());
					continue;
				}
				
				boolean hasIdb = false;
				for(Atom a : r.getBody()){
					if(!potentialEdbPreds.contains(a.getPredicate()))
						hasIdb = true;
				}
				if(!hasIdb)
					edbOnlyRules.add(r);
				else
					rulesWithIdbPreds.add(r);
			}
		}
		
		this.ra.resetIdbPreds(idbPreds);
		for(Rule r : edbOnlyRules)
			this.firstRoundRules.addAll(this.ra.getAnnotated(r));
		for(Rule r : rulesWithIdbPreds)
			this.laterRoundRules.addAll(this.ra.getAnnotated(r));
	}

	@Override
	public FactCollection evaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
