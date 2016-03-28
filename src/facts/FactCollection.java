package facts;

import java.util.Set;

import basics.Atom;
import basics.Predicate;
import substitution.RestrictedSubstitution;

public interface FactCollection {
	public Iterable<Atom> indexInto(Atom atom);
	public Iterable<Atom> indexInto(Atom atom, RestrictedSubstitution substitution);
	public Iterable<Atom> indexInto(Predicate predicate);
	public boolean isEmpty();
	Set<Predicate> getPreds();
}
