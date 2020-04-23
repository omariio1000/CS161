/* class with getters and setters for an edge, stores which nodes it is connected to as well as
 the label/value for the edge */
public class Edge {
	
	Node first;
	Node second;
	String label;
	
	public Edge(Node newfirst, Node newsecond, String newlabel) {
		first = newfirst;
		second = newsecond;
		label = newlabel;
	}
	
	public Node getOtherEnd(Node n) {
		if (first.equals(n)) {
			return second;
		}
		if (second.equals(n)) {
			return first;
		}
		else {
			return null;
		}
	}
	
	public Node getFirst() {
		return first;
	}
	public void setFirst(Node first) {
		this.first = first;
	}
	public Node getSecond() {
		return second;
	}
	public void setSecond(Node second) {
		this.second = second;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
