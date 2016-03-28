package facts;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import basics.Atom;
import util.DatalogUtil;

public final class FactIndexerFactory {
	private FactIndexerFactory(){
		
	}
	
	public static FactIndexer<Set<Atom>> createConcurrentFactIndexer(){
		return new FactIndexer<>(() -> DatalogUtil.createConcurrentSet(), (set, fact) -> set.add(fact));
	}
	
	public static FactIndexer<Queue<Atom>> createConcurrentQueueFactIndexer(){
		return new FactIndexer<>(() -> new ConcurrentLinkedQueue<>(), (queue,
				fact) -> queue.add(fact));
	}
}
