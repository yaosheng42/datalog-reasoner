package basics;

import java.io.PrintStream;

/**
 * A Datalog variable.
 * @author JuneShi
 *
 */
public class Variable implements ITerm{
	private String name = "";
	
	Variable(final String name){
		this.name = name;
	}
	
	public void print(PrintStream out){
		out.print(this.name);
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
