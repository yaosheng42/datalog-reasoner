package engine.bottomup;

import java.util.Set;

import basics.Rule;
import engine.IDatalogEngine;
import facts.FactCollection;

public class BottomUpEngineFrame implements IDatalogEngine {
	private final IEvalManager manager;
	private FactCollection facts;
	private boolean isInitialized;
	
	public BottomUpEngineFrame(IEvalManager manager){
		this.manager = manager;
	}
	
	@Override
	public void init(Set<Rule> program){
		if(this.isInitialized)
			throw new IllegalStateException("Cannot initialize an engine more than once");
		
		this.manager.init(program);
		this.facts = this.manager.evaluate();
		this.isInitialized = true;
	}
	

}
