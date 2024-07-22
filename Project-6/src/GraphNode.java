/**
 * @author Parth Kathiria
 * 
 * This class represent a node of the graph.
 * 
 */

public class GraphNode {
	
	private int name;
	private boolean marked;
	
	//Constructor for the class
	public GraphNode(int name){
		this.name = name;
		this.marked = false;
	}
	
	//mark method marks the node with the specified value.
	public void mark(boolean mark) {
		this.marked = mark;
	}
	
	//isMarked method returns the value with which the node has been marked.
	public boolean isMarked() {
		return marked;
	}
	
	//getName method returns the name of the node.
	public int getName() {
		return name;
	}
	
}
