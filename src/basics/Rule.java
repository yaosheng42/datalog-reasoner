package basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rule {
	private final List<Atom> head;
	private final List<Atom> body;
	
	Rule(final List<Atom> head, final List<Atom> body){
		if(head == null)
			throw new IllegalArgumentException("The head must not be null");
		if(head.contains(null))
			throw new IllegalArgumentException("The head must not contain null");
		if(body == null)
			throw new IllegalArgumentException("The body must not be null");
		if(body.contains(null))
			throw new IllegalArgumentException("The body must not contain null");
		
		this.head = Collections.unmodifiableList(new ArrayList<Atom>(head));
		this.body = Collections.unmodifiableList(new ArrayList<Atom>(body));
	}

	public List<Atom> getHead() {
		return head;
	}

	public List<Atom> getBody() {
		return body;
	}
	
	
}
