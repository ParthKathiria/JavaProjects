/**
 * Class NLNode represents the nodes of the non-linear data structure that will store the information about the file system.
 * 
 * @author Parth Kathiria 
 */

//importing the Comparator and Iterator classes from the Java library.
import java.util.Comparator;
import java.util.Iterator;

//a public class named NLNode of generic type T
public class NLNode<T> {
	
	//declaring private instance variables
	//A reference to the parent of this node
	private NLNode<T> parent;
	//A reference to a list storing the children of this node
	private ListNodes<NLNode<T>> children;
	//A reference to the data object stored in this node
	private T data;
	
	//a constructor that sets the instance variables parent and data to null, while children is initialized to an empty ListNodes<NLNode<T>> object.
	public NLNode() {
		this.parent = null;
		this.data = null;
		this.children = new ListNodes<NLNode<T>>();
	}
	
	//another constructor that Initializes instance variable children to an empty ListNodes<NLNode<T>>, while data is set to d and parent to p.
	public NLNode(T d, NLNode<T> p) {
		this.parent = p;
		this.data = d;
		this.children = new ListNodes<NLNode<T>>();
	}
	
	//a setter method that sets the parent of this node to p.
	public void setParent(NLNode<T> p) {
		this.parent = p;
	}
	
	//a getter method that returns the parent of this node.
	public NLNode<T> getParent(){
		return this.parent;
	}
	
	//a public method that adds the given node newChild to the list of children of this node. Node newChild must have its parent set to this node.
	public void addChild(NLNode<T> newChild) {
		newChild.setParent(this);
		children.add(newChild);
	}
	
	//a getter method that returns an iterator containing the children of this node.
	public Iterator<NLNode<T>> getChildren(){
		return children.getList();	
	}
	
	//another getter method that Returns an iterator containing the children of this node sorted in the order specified by the parameter sorter.
	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter){
		return children.sortedList(sorter);
	}
	
	//a getter method that return the data.
	public T getData() {
		return this.data;
	}
	
	//a setter method that sets the data to d.
	public void setData(T d) {
		this.data = d;
	}
}
