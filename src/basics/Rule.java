package basics;

import java.util.List;

public class Rule extends AbstractRule<Atom>{

	protected Rule(Atom head, List<Atom> body) {
		super(head, body);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Rule [head=" + head + ", body=" + body + "]";
	}
	
	
}
