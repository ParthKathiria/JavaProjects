/**
 * DLinkedNode class implements a structure of a doubly linked list where each node stores a reference to the next and previous nodes, and contains a data item as well as a priority.
 * 
 * @author Parth Kathiria
 * 
 */
public class DLinkedNode<T> {
	
	/* private instance variables are declared:
	 * dataItem with T parameter.
	 * priority of data type double.
	 * next referencing to the next node of the class type.
	 * prev referencing to the previous node of the class type.
	 */
	
	private T dataItem;
	private double priority;
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;
	
	//constructor which sets the dataItem and priority to data and prio respectively and next and prev pointers to null initially.
	public DLinkedNode(T data, double prio) {
		this.dataItem = data;
		this.priority = prio;
		this.next = null;
		this.prev = null;
	}
	
	//another constructor which sets the dataItem to null and priority to 0 initially.
	public DLinkedNode() {
		this.dataItem = null;
		this.priority = 0;
	}
	
	//getter method named getPriority which is responsible for returning the priority. 
	public double getPriority(){
		return this.priority;
	}
	
	//getter method named getDataItem which is responsible for returning the dataItem.
	public T getDataItem(){
		return this.dataItem;
	}
	
	//getter method named getNext which is responsible for returning the next pointer.
	public DLinkedNode<T> getNext() {
		return this.next;
	}
	
	//getter method named getPrev which is responsible for returning the previous pointer.
	public DLinkedNode<T> getPrev(){
		return this.prev;
	}
	
	//setter method which sets the value of dataItem to data for a node.
	public void setDataItem(T data){
		this.dataItem = data;	
	}
	
	//setter method which sets the next pointer to next for a node.
	public void setNext(DLinkedNode<T> next) {
		this.next = next;
	}
	
	//setter method which sets the previous pointer to prev for a node. 
	public void setPrev(DLinkedNode<T> prev) {
		this.prev = prev;
	}
	
	//setter method which sets the priority to priority for a node.
	public void setPriority(double priority) {
		this.priority = priority;
	}
	
}
