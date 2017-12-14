package application.model;

public class Pair {

	public String left;
	public String right;
	public String center;
	
	public Pair(String left, String center, String right) {
		this.left = left;
		this.right = right;
		this.center = center;
	}
	
	@Override
	public String toString() {
		return left+" ("+center+") "+right;
	}
}
