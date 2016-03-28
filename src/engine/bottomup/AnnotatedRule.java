package engine.bottomup;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

import basics.Atom;
import substitution.Substitution;

public class AnnotatedRule implements Printable {
	private final Atom head;
	private final List<AnnotatedAtom> body;
	
	public AnnotatedRule(Atom head, List<AnnotatedAtom> body){
		this.head = head;
		this.body = body;
	}
	
	public AnnotatedRule applySubstitution(Substitution substitution){
		List<AnnotatedAtom> newBody = new ArrayList<>();
		for(AnnotatedAtom a : this.getBody())
			newBody.add(a.applySubstitution(substitution));
		return new AnnotatedRule(this.head.applySubstitution(substitution), newBody);
	}
	
	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2) throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((head == null) ? 0 : head.hashCode());
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
		AnnotatedRule other = (AnnotatedRule) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}

	public Atom getHead() {
		return head;
	}

	public List<AnnotatedAtom> getBody() {
		return body;
	}

}
