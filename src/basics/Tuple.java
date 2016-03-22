package basics;

import java.util.Collection;

public class Tuple {
	private final Term[] terms;
	
	Tuple(final Collection<Term> t){
		if(t == null)
			throw new NullPointerException("Input argument must not be null");
		terms = t.toArray(new Term[t.size()]);
	}
	
	public int getSize(){
		return terms.length;
	}
	
	public Term get(final int i){
		if(i < 0)
			throw new IllegalArgumentException("The index must be positive");
		if(i >= terms.length)
			throw new IllegalArgumentException("The index must not be greater or equal to the size of Terms array");
		return terms[i];
	}
	
	
}
