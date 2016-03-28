package basics;

import java.io.PrintStream;
import java.util.List;

import util.IPrintable;

public abstract class AbstractRule<A extends IAtom> implements IPrintable{
	protected final A head;
	protected final List<A> body;
	
	protected AbstractRule(A head, List<A> body){
		this.head = head;
		this.body = body;
	}

	public A getHead() {
		return head;
	}

	public List<A> getBody() {
		return body;
	}

	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		this.head.print(out);
		if(!this.getBody().isEmpty()){
			out.print(" :- ");
			for(int i = 0; i < this.getBody().size(); i++){
				this.getBody().get(i).print(out);
				if(i < this.getBody().size() - 1)
					out.print(", ");
			}
		}
		out.print(".");
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
		AbstractRule other = (AbstractRule) obj;
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
	
	
}
