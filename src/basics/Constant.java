package basics;

import java.io.PrintStream;

public class Constant implements ITerm{
	private String name;
	
	Constant(final String name){
		this.name = name;
	}
	
	public boolean isGround(){
		return true;
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
		Constant other = (Constant) obj;
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

	@Override
	public String toString() {
		return "Constant [name=" + name + "]";
	}

	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		out.print(this.name);
	}
	
	
}
