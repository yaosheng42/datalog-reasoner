package substitution;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import basics.Constant;
import basics.ITerm;
import basics.Variable;

public class VarToConstSubstitution implements RestrictedSubstitution {
	private final Map<Variable, Constant> substitution;
	
	VarToConstSubstitution(){
		this.substitution = new LinkedHashMap<>();
	}
	
	VarToConstSubstitution(VarToConstSubstitution other){
		this.substitution = new LinkedHashMap<>(other.substitution);
	}
	
	public void put(Variable v, Constant c){
		Constant cons = this.substitution.get(v);
		if(cons != null && !cons.equals(c))
			throw new IllegalArgumentException("Cannot remap a variable to another constant.");
		this.substitution.put(v, c);
	}
	
	public static VarToConstSubstitution unify(ITerm[] xs, ITerm[] ys){
		if(xs.length != ys.length)
			return null;
		
		VarToConstSubstitution s = new VarToConstSubstitution();
		
		for(int i = 0; i < xs.length; i++){
			ITerm x = xs[i];
			ITerm y = ys[i];
			
			if(!(y instanceof Constant))
				throw new IllegalArgumentException("The second argument must be ground");
			
			if(x instanceof Constant){
				if(! x.equals(y))
					return null;
				continue;
			}
			
			Constant c = s.substitution.get(x);
			if(c != null && !c.equals(y))
				return null;
			s.substitution.put((Variable)x, (Constant)y);
		}
		return s;
	}
	
	@Override
	public ITerm[] apply(ITerm[] originalArgs) {
		// TODO Auto-generated method stub
		ITerm[] newArgs = new ITerm[originalArgs.length];
		for(int i = 0; i < originalArgs.length; i++){
			ITerm t = originalArgs[i];
			if (t instanceof Variable){
				Constant c = this.substitution.get(t);
				if(c != null)
					t = c;
				
			}
			newArgs[i] = t;
		}
		return newArgs;
	}

	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		out.println("[");
		for(Iterator<Variable> it = this.substitution.keySet().iterator(); it.hasNext();){
			Variable v = it.next();
			v.print(out);
			out.print("->");
			this.substitution.get(v).print(out);
			if(it.hasNext())
				out.print(", ");
		}
		out.print("]");
	}

	@Override
	public Constant get(Variable x) {
		// TODO Auto-generated method stub
		return this.substitution.get(x);
	}

}
