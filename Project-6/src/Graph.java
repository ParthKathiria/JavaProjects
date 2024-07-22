/**
 * @author Parth Kathiria
 * 
 * This class represents an undirected graph.
 * 
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph implements GraphADT {
	
	private int n;
	private GraphNode[] nodes;
	private GraphEdge[][] adjacencyMatrix;
	
	// Constructor to initialize the graph with a given number of nodes
	public Graph(int n) {
		this.n = n;
		this.nodes = new GraphNode[n];
		this.adjacencyMatrix = new GraphEdge[n][n];

		for (int i = 0; i < n; i++) {
            nodes[i] = new GraphNode(i);
        }
	}
	
	// Method to insert an edge between two nodes with a specified edge type and label
	@Override
	public void insertEdge(GraphNode u, GraphNode v, int edgeType, String label) throws GraphException{
		int uNode = u.getName();
		int vNode = v.getName();
		
		//Checking if nodes u and v are valid
		if(uNode < 0 || uNode >= n || vNode < 0 || vNode >= n) {
			throw new GraphException("One or both nodes do not exist.");
		}
		
		//Checking if an edge already exists between u and v
		if(adjacencyMatrix[uNode][vNode] != null || adjacencyMatrix[vNode][uNode] != null) {
			throw new GraphException("Edge already connects the given nodes.");
		}
		
		// Create a new edge and update the adjacency matrix
		GraphEdge edge = new GraphEdge(u,v,edgeType,label);
		adjacencyMatrix[uNode][vNode] = edge;
		adjacencyMatrix[vNode][uNode] = edge;
		
	}
	
	// Method to get a node by its name
	@Override
	public GraphNode getNode(int name) throws GraphException {
		//Checking if node is in the range, if not , theres doesnt exist a node with name
		if(name < 0 || name >= n) {
			throw new GraphException("No node with the name " + name + " exists.");
		}
		return new GraphNode(name);
	}
	
	// Method to get an iterator over the incident edges of a given node
	@Override
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
	    int uIndex = -1;
	    for (int i = 0; i < n; i++) {
	        if (nodes[i].getName() == u.getName()) {
	            uIndex = i;
	            break;
	        }
	    }

	    if (uIndex == -1) {
	        throw new GraphException("Node does not exist in the graph.");
	    }
	    //Creating an ArrayList to store the incident edges in a container.
	    List<GraphEdge> incidentEdgesList = new ArrayList<>();
	    // Iterate through nodes to find the index of the given node
	    for (int vIndex = 0; vIndex < n; vIndex++) {
	        if (adjacencyMatrix[uIndex][vIndex] != null) {
	            incidentEdgesList.add(adjacencyMatrix[uIndex][vIndex]);
	        }
	    }
	    
	    //If the list is empty, then theres no incident edge and we return null
	    if (incidentEdgesList.isEmpty()) {
	        return null;
	    }
	    
	    //otherwise, we iterate over the list using iterator and return it
	    return incidentEdgesList.iterator();
	}

	// Method to get the edge between two nodes
	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		int uNode = u.getName();
		int vNode = v.getName();
		// Throw exception if nodes are invalid
		if(uNode < 0 || uNode >= n || vNode < 0 || vNode >= n) {
			throw new GraphException("One or both nodes do not exist.");
		}
		// Throw exception if there is no edge
		if(adjacencyMatrix[uNode][vNode] == null) {
			throw new GraphException("There is no edge between u and v.");
		}
		// Return the edge between nodes u and v
		return adjacencyMatrix[uNode][vNode];
	}
	
	// Method to check if two nodes are adjacent (connected by an edge)
	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		int uNode = u.getName();
		int vNode = v.getName();
		// Throw exception if nodes are invalid
		if(uNode < 0 || uNode >= n || vNode < 0 || vNode >= n) {
			throw new GraphException("u or v are not nodes of the graph.");
		}
		// Return true if there is an edge between nodes u and v
		return adjacencyMatrix[uNode][vNode] != null;
	}

}

