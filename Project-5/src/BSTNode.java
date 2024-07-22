/**
 *@author Parth Kathiria
 *
 */
public class BSTNode {
	
	//Declaring private instance variables for the BSTNode class
	private Record item;
	private BSTNode leftChild;
	private BSTNode rightChild;
	private BSTNode parent;
	
	//Constructor for this class which initializes the item variable to item and sets the other variables to null.
	public BSTNode(Record item) {
		this.item = item;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}
	
	//Getter method which return the item variable.
	public Record getRecord() {
		return this.item;
	}
	
	//Setter method which sets the item Record to a Record d.
	public void setRecord(Record d) {
		this.item = d;
	}
	
	//Getter method which returns the leftChild
	public BSTNode getLeftChild() {
		return leftChild;
	}
	
	//Getter method which returns the rightChild
	public BSTNode getRightChild() {
		return rightChild;
	}
	
	//Getter method which returns the parent
	public BSTNode getParent() {
		return parent;
	}
	
	//Setter method which sets the leftChild node to a node u
	public void setLeftChild(BSTNode u) {
		this.leftChild = u;
	}
	
	//Setter method which sets the rightChild node to u
	public void setRightChild(BSTNode u) {
		this.rightChild = u;
	}
	
	//Setter method which sets the parent node to u
	public void setParent(BSTNode u) {
		this.parent = u;
	}
	
	//isLeaf method checks if a node is a leaf or not by checking its left child node and the right child node.
	public boolean isLeaf() {
		if(leftChild == null && rightChild == null) {
			return true;
		}
		else return false;
	}

}
