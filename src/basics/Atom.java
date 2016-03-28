package basics;

import java.io.PrintStream;
import java.util.Arrays;

import substitution.Substitution;
import substitution.VarToConstSubstitution;

public final class Atom extends AbstractAtom<Predicate>{
	private Atom(final Predicate predicate, final ITerm[] args){
		super(predicate, args);
	}

	public static Atom create(final Predicate predicate, final ITerm[] args){
		return new Atom(predicate, args);
	}
	
	public Substitution unify(Atom fact){
		assert fact.isGround();
		if(!this.getPredicate().equals(fact.getPredicate()))
			return null;
		
		return VarToConstSubstitution.unify(this.args, fact.args);
	}
	
	public Atom applySubstitution(Substitution substitution){
		return create(this.predicate, substitution.apply(args));
	}
	
	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		this.predicate.print(out);
		if(this.args.length != 0){
			out.print("(");
			for(int i = 0; i < this.args.length; i++){
				this.args[i].print(out);
				if(i < this.args.length - 1)
					out.print(", ");
			}
		}
	}

	@Override
	public String toString() {
		return "Atom [predicate=" + predicate + ", args=" + Arrays.toString(args) + "]";
	}
	
}
