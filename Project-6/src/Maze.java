/**
 * 
 * @author Parth Kathiria
 * 
 * This class represents the maze.
 * 
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Maze {
	private Graph graph;
	private int numOfCoins;
	private int scale;
	private int width;
	private int length;
	private GraphNode exitNode;
	private GraphNode startNode;
	private Stack<GraphNode> stack;

	//Constructor of the maze class
	public Maze(String inputFile) throws MazeException, GraphException, NumberFormatException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))){
	        // Read the first 4 lines of the input file (scale, width, length, numOfCoins)
	        scale = Integer.parseInt(br.readLine());
	        width = Integer.parseInt(br.readLine());
	        length = Integer.parseInt(br.readLine());
	        numOfCoins = Integer.parseInt(br.readLine());
		
	        

	        // Creating an empty graph of proper size with nodes representing rooms in the maze.
	        graph = new Graph(width * length);
	        
	        // Iterate through lines in the input file and read.
	        for(int i = 0; i < (length*2)-1; i++) {
	        	String line = br.readLine().trim();
	        	
	        	// Iterate through characters in the line
	        	for(int j = 0; j < line.length() ; j++) {
	        		
	        		//Read every character in the row using .charAt method
	        		char roomType = line.charAt(j);
	        		// Process based on the position in the maze (even or odd lines)
	        		if(i % 2 == 0) {
	        			// Process even lines
	        			if(j % 2 == 0) {
	        				int nodeIndex = (i/2) * width + (j/2);
	    					GraphNode node = graph.getNode(nodeIndex);
	    					// Check roomType and take appropriate actions
	        				if(roomType == 's') {
	        					setStartNode(node);
	        				}
	        				else if(roomType == 'x') {
	        					setExitNode(node);	
	        				}
	        				else if(roomType == 'o') {
	        					//doing nothing for 'o' (room)
	        				}
	        			}
	        			// Process odd  characters in even lines
	        			else {
	        				int nodeIndex = (i/2) * width + (j/2);
	    					GraphNode node = graph.getNode(nodeIndex);
	    					// Check roomType and take appropriate actions
	        				if(roomType == 'w') {
	        					//doing nothing for 'w' (wall)
	        				}
	        				else if(roomType == 'c') {
	        					//Inserting the corridor to the right Node
	        					GraphNode rightNode = graph.getNode(nodeIndex + 1); 
	        					if (j < width - 1) {
		                	        graph.insertEdge(node, rightNode, 0, "corridor");
		                	    }
	        				}
	        				//checking if the character is a door (a digit) using Character.isDigit()
	        				else if(Character.isDigit(roomType)) {
	        					//Getting the numberic value of the type.
	        					int requiredCoins = Character.getNumericValue(roomType);
	        					//Inserting edge with its type to the right node.
	        					GraphNode rightNode = graph.getNode(nodeIndex + 1);
	        					graph.insertEdge(node, rightNode, requiredCoins, "door");
	        				}
	        			}
	        		}
	        		// Process odd lines
	        		else {
	        			//Process even characters
	        			if(j % 2 == 0) {
	        				if(roomType == 'w') {
	        					//doing nothing for 'w' (wall)
	        				}
	        				//else if its a corridor adding the edges vertically to the top and the bottom node.
	        				else if(roomType == 'c') {
	        					int nodeIndex = (i/2) * width + (j/2);
	        					GraphNode topNode = graph.getNode(nodeIndex);
	        					GraphNode bottomNode = graph.getNode(nodeIndex + width);
	        					graph.insertEdge(topNode, bottomNode, 0, "corridor");
	        				}
	        				//else if the character is a room, getting its type and adding the door vertically to the top and the bottom node with its type.
	        				else if(Character.isDigit(roomType)) {
	        					int requiredCoins = Character.getNumericValue(roomType);
	        					int nodeIndex = (i/2) * width + (j/2);
	        					GraphNode topNode = graph.getNode(nodeIndex);
	        					GraphNode bottomNode = graph.getNode(nodeIndex + width);
	                	        graph.insertEdge(topNode, bottomNode, requiredCoins, "door");
	        				}
	        			}
	        			//Proces odd characters (is always 'w' in case of an odd line + odd character
	        			else {
	        				if(roomType == 'w') {
	        					//doing nothing for 'w' (wall)
	        				}
	        			}
	        		}
	        	}
	        }
	        
		}
	}
	
	// Getter method for retrieving the graph
	public Graph getGraph() throws MazeException{
		//checking if the graph is null, if it is , throw MazeException
		if(graph == null) {
			throw new MazeException("The graph is null.");
		}
		//else return graph
		return graph;
	}
	
	
  public Iterator<GraphNode> solve() throws GraphException { 
	  
	// Create a stack for DFS traversal
     stack= new Stack<>();
     
     // Check if a valid path is found using DFS
     if (dfs(startNode, exitNode, numOfCoins)) {
         return stack.iterator();
       //else returning null
     } else {
         return null;
     }
  }

  private boolean dfs(GraphNode current, GraphNode destination, int coins) throws GraphException {
	  // Mark the current node and push it to the stack
      current.mark(true);
      stack.push(current);

      // Check if the current node is the destination
      if (current.equals(destination)) {
          return true;
      }
      
      // Get incident edges of the current node using the .incidentEdges method from Graph class.
      Iterator<GraphEdge> edgeIterator;
      edgeIterator= graph.incidentEdges(current);

      // Explore neighboring nodes
      while (edgeIterator.hasNext()) {
          GraphEdge edge = edgeIterator.next();
          //Declaring the nextNode variable and giving it appropriate value.
          GraphNode nextNode = edge.secondEndpoint();
          // Check if the next node is not marked
          if (!nextNode.isMarked()) {
              int coinsNeeded = edge.getType();
              //if loop to check if the number of coins that we have is larger than the number of coins needed for the door
              if (coins >= coinsNeeded) {
            	  // Recursively explore the next node
                  if (dfs(nextNode, destination, coins-coinsNeeded)) {
                      return true;
                  }
                  // Backtrack: restore coins when returning from recursion
                  coins += coinsNeeded;
                  current.mark(false);
              }
          }
      }
      
      // Backtrack: remove the current node from the stack
      stack.pop();
      return false;
  	}
  	
	
	//setter method to set the node to the exitNode
	private void setExitNode(GraphNode node) {
	    this.exitNode = node;
	}
	
	//setter method to set the node to the private node
	private void setStartNode(GraphNode node) {
		this.startNode = node;
	}
	
}







