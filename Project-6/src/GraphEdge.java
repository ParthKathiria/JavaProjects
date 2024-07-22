/**
 * @author Parth Kathiria
 * 
 * This class represents an edge of the graph.
 * 
 */

public class GraphEdge {
	
	private GraphNode u;
	private GraphNode v;
	private int type;
	private String label;
	
	
	//Constructor for the class
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		this.u = u;
		this.v = v;
		this.type = type;
		this.label = label;
	}
	
	public GraphNode firstEndpoint() {
		return u;
	}
	
	public GraphNode secondEndpoint() {
		return v;
	}
	
	//A getter method which returns the type of the edge (how many coins are required to open).
	public int getType() {
		return type;
	}
	
	//A setter method which sets the type of the edge to newType.
	public void setType(int newType) {
		this.type = newType;
	}
	
	//A getter method which returns the label of the edge (corridor or door).
	public String getLabel() {
		return label;
	}
	
	//A setter method which sets the label of the edge to newLabel.
	public void setLabel(String newLabel) {
		this.label = newLabel;
	}

}
