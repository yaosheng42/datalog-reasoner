package basics;

import java.io.PrintStream;
import java.util.Arrays;

public abstract class AbstractAtom<P extends Predicate> implements IAtom<P>{
	protected final P predicate;
	protected final ITerm[] args;
	protected Boolean isGround;
	
	protected AbstractAtom(final P predicate, final ITerm[] args){
		this.predicate = predicate;
		this.args = args;
		if(predicate.getArity() != args.length)
			throw new IllegalArgumentException("Arity of the predicate symbol is not equal to the args");
	}

	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public P getPredicate() {
		// TODO Auto-generated method stub
		return this.predicate;
	}

	@Override
	public ITerm[] getArgs() {
		// TODO Auto-generated method stub
		return this.args;
	}

	@Override
	public boolean isGround() {
		// TODO Auto-generated method stub
		if(this.isGround == null){
			boolean  b = true;
			for(ITerm t : args)
				b &= t instanceof Constant;
			this.isGround = Boolean.valueOf(b);
		}
		return isGround;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + ((predicate == null) ? 0 : predicate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAtom other = (AbstractAtom) obj;
		if(isGround != null && other.isGround != null && isGround != other.isGround)
			return false;
		if (!Arrays.equals(args, other.args))
			return false;
		if (predicate == null) {
			if (other.predicate != null)
				return false;
		} else if (!predicate.equals(other.predicate))
			return false;
		return true;
	}
}
