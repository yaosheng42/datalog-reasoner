package facts;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import basics.Atom;
import basics.Constant;
import basics.ITerm;
import basics.Predicate;
import basics.Variable;
import substitution.RestrictedSubstitution;
import util.DatalogUtil;

public class FactIndexer<T extends Iterable<Atom>> implements FactCollection {
	private final Supplier<T> generator;
	private final BiConsumer<T, Atom> addFunc;
	private final Supplier<T> empty;
	
	private final ConcurrentMap<Predicate, AtomicReferenceArray<ConcurrentMap<Constant, T>>> fineIdx = DatalogUtil.createConcurrentMap();
	private final ConcurrentMap<Predicate, T> coarseIdx = DatalogUtil.createConcurrentMap();
	
	public FactIndexer(Supplier<T> generator, BiConsumer<T, Atom> addFunc, Supplier<T> empty){
		this.generator = generator;
		this.addFunc = addFunc;
		this.empty = empty;
	}
	
	public FactIndexer(Supplier<T> generator, BiConsumer<T, Atom> addFunc){
		this(generator, addFunc, generator);
	}
	
	public void add(Atom fact) {
		assert fact.isGround();
		T rough = this.coarseIdx.get(fact.getPredicate());
		if (rough == null) {
			rough = this.generator.get();
			T existing = this.coarseIdx.putIfAbsent(fact.getPredicate(), rough);
			if (existing != null) {
				rough = existing;
			}
		}
		this.addFunc.accept(rough, fact);
		
		AtomicReferenceArray<ConcurrentMap<Constant, T>> byPos = this.fineIdx.get(fact.getPredicate());
		if (byPos == null) {
			byPos = new AtomicReferenceArray<>(fact.getPredicate().getArity());
			AtomicReferenceArray<ConcurrentMap<Constant, T>> existing = this.fineIdx.putIfAbsent(fact.getPredicate(), byPos);
			if (existing != null) {
				byPos = existing;
			}
		}
		assert byPos != null;
		
		ITerm[] args = fact.getArgs();
		for (int i = 0; i < args.length; ++i) {
			ConcurrentMap<Constant, T> byConstant = byPos.get(i);
			if (byConstant == null) {
				byConstant = DatalogUtil.createConcurrentMap();
				if (!byPos.compareAndSet(i, null, byConstant)) {
					byConstant = byPos.get(i);
				}
			}
			Constant key = (Constant) args[i];
			T n = byConstant.get(key);
			if (n == null) {
				n = this.generator.get();
				T existing = byConstant.putIfAbsent(key, n);
				if (existing != null) {
					n = existing;
				}
			}
			this.addFunc.accept(n, fact);
		}
	}
	
	public void addAll(Iterable<Atom> facts) {
		for (Atom a : facts) {
			this.add(a);
		}
	}
	
	public void addAll(FactCollection that) {
		for (Predicate pred : that.getPreds()) {
			this.addAll(that.indexInto(pred));
		}
	}
	
	public void clear() {
		this.fineIdx.clear();
		this.coarseIdx.clear();
	}
	
	@Override
	public T indexInto(Atom atom) {
		// TODO Auto-generated method stub
		return this.indexInto(atom, null);
	}

	@Override
	public T indexInto(Atom atom, RestrictedSubstitution substitution) {
		// TODO Auto-generated method stub
		AtomicReferenceArray<ConcurrentMap<Constant, T>> byPos = this.fineIdx.get(atom.getPredicate());
		if(byPos ==null)
			return this.empty.get();
		
		int bestIdx = -1;
		ITerm bestConst = null;
		int maxKeySetSize = -1;
		ITerm[] args = atom.getArgs();
		for(int i = 0; i < args.length; i++){
			ITerm t = args[i];
			if(t instanceof Constant || (substitution != null && (t = substitution.get((Variable) t)) != null)){
				ConcurrentMap<Constant, T> byConstant = byPos.get(i);
				if(byConstant != null){
					if(byConstant.containsKey(t))
						return this.empty.get();
					int keySetSize = byConstant.size();
					if(keySetSize > maxKeySetSize){
						maxKeySetSize = keySetSize;
						bestIdx = i;
						bestConst = t;
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public T indexInto(Predicate predicate) {
		// TODO Auto-generated method stub
		T t = this.coarseIdx.get(predicate);
		if(t == null)
			t = this.empty.get();
		return t;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.coarseIdx.isEmpty();
	}

	@Override
	public Set<Predicate> getPreds() {
		// TODO Auto-generated method stub
		return this.coarseIdx.keySet();
	}

}
