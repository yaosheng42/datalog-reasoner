package engine.bottomup;

import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import basics.Atom;
import basics.IAtom;
import basics.IPredicate;
import basics.ITerm;
import basics.Predicate;
import substitution.Substitution;

public class AnnotatedAtom implements IAtom<Predicate>{
	public final Atom atom;
	public final AtomAnnotation annotation;
	private static final ConcurrentMap<Atom, ConcurrentMap<AtomAnnotation, AnnotatedAtom>> memo = new ConcurrentHashMap<>();
	
	AnnotatedAtom(final Atom atom, final AtomAnnotation annotation){
		this.atom = atom;
		this.annotation = annotation;
	}
	
	public static AnnotatedAtom create(Atom unannotated, AtomAnnotation annotation){
		ConcurrentMap<AtomAnnotation, AnnotatedAtom> byAnno = memo.get(unannotated);
		if(byAnno == null){
			byAnno = new ConcurrentHashMap<>();
			ConcurrentMap<AtomAnnotation, AnnotatedAtom> existing = memo.putIfAbsent(unannotated, byAnno);
			if(existing != null)
				byAnno = existing; // may be redundant
		}
		
		AnnotatedAtom annotated = byAnno.get(annotation);
		if(annotated == null){
			annotated = new AnnotatedAtom(unannotated, annotation);
			AnnotatedAtom existing = byAnno.putIfAbsent(annotation, annotated);
			if(existing != null)
				annotated = existing;
		}
		return null;
	}
	
	public Atom getUnannotated(){
		return this.atom;
	}
	
	public Substitution unify(Atom fact){
		return this.atom.unify(fact);
	}
	
	public AnnotatedAtom applySubstitution(Substitution substitution){
		return AnnotatedAtom.create(this.atom.applySubstitution(substitution), annotation);
	}
	
	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		this.atom.print(out);
		switch(this.annotation){
		case EDB_FACTS:
			out.print("<EDB_FACTS>");
			break;
		case IDBS_PREV:
			out.print("<IDBS_PREV>");
			break;
		case IDBS_CUR:
			out.print("<IDBS_CUR>");
			break;
		case DELTA:
			out.print("<DELTA>");
			break;
		default:
			assert false;
		}
	}

	@Override
	public Predicate getPredicate() {
		// TODO Auto-generated method stub
		return this.atom.getPredicate();
	}

	@Override
	public ITerm[] getArgs() {
		// TODO Auto-generated method stub
		return this.atom.getArgs();
	}

	@Override
	public boolean isGround() {
		// TODO Auto-generated method stub
		return this.atom.isGround();
	}

}
