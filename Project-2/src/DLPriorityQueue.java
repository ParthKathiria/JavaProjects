/**
 * DLPriorityQueue implements the PriorityQueueADT in java and it will store the data items of the priority queue in a doubly linked list.
 * 
 * @author Parth Kathiria
 *
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T>{
	/* private instance variables are declared:
	 * front pointer which points at the first node in the doubly linked list.
	 * rear pointer which points are the last node in the doubly linked list.
	 * count of data type int which keeps a count of the number of nodes in the doubly linked list.
	 */
	private DLinkedNode<T> front;
	private DLinkedNode<T> rear;
	private int count;
	
	//constructor which sets the count to 0, front and rear pointers to null initially.
	public DLPriorityQueue() {
		front = null;
		rear = null;
		count = 0;
	}
	
	//declaring an add method which adds a node with a specific dataItem and priority to its correct place inside the doubly linked linked according to its priority.
	public void add(T dataItem, double priority) {
		//creating a newNode with a certain dataItem and priority.
	    DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);
	    
	    /*CASE-1: If the doubly linked list is empty.*/
	    if (isEmpty()) {
	    	//setting the front and rear pointer towards the newNode, which adds newNode as the first node in the linked list.
	        front = newNode;
	        rear = newNode;
	      
	        /*CASE-2: If the newNode's priority is the least.(Adding newNode to the front)*/
	    } else if (priority <= front.getPriority()) {
	    	//setting the newNode's next pointer to front
	        newNode.setNext(front);
	        //setting front's previous pointer to newNode.
	        front.setPrev(newNode);
	        //newNode is now the new front of the data structure.
	        front = newNode;
	      
	        /*CASE-3: If the newNode's priority is the highest.(Adding newNode to the rear)*/
	    } else if (priority >= rear.getPriority()) {
	    	//setting rear node's next pointer from null to newNode.
	        rear.setNext(newNode);
	        //setting newNode's previous pointer to rear
	        newNode.setPrev(rear);
	        //newNode is now the new rear of the data structure.
	        rear = newNode;
	        
	      /*CASE-4: Adding the newNode in between the nodes of double linked list according to its priority.*/
	    } else {
	    	//creating a current object which is front at first.
	        DLinkedNode<T> current = front;
	        //while the current's next is not null and current's next's priority is less than the newNode's priority, incrementing the current's pointer to the next Node until the appropriate position is found to add the node.
	        while (current.getNext() != null && current.getNext().getPriority() <= priority) {
	            current = current.getNext();
	        }
	        
	        //setting the next pointer of the newNode to current's next.
	        newNode.setNext(current.getNext());
	        //setting the newNode's previous pointer to current.
	        newNode.setPrev(current);
	        //setting the current's next's pointer previous pointer to newNode.
	        current.getNext().setPrev(newNode);
	        //setting current's next pointer to newNode.
	        current.setNext(newNode);
	    }
	    //incrementing the count by 1.
	    count++;
	}
	
	//a public updatePriority method which changes the priority of a node with the given dataItem, and throws an InvalidElementException of the node with the given element is not found in the linked list.
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException{
		//creating a current object which is front at first.
		DLinkedNode<T> current = front;
		//while current is not null and current's data item is not equal to the given data item, increment current's pointer to the nextNode until the node with the given dataItem is found.
		while(current != null && !current.getDataItem().equals(dataItem)){
			current = current.getNext();
		}
		//if current reaches the end of the doubly linked list and no node is found with the given data item, throw the exception.
		if(current == null) {
			throw new InvalidElementException("Element not found!");
		}
		
		//else if the priority of the node is same as the new priority, do nothing and return.
		else if(current.getPriority() == newPriority){
			return;
		}
		
		//else removing the node from the linked list but storing it.
		else{
			/*CASE-1 : If the node to have updated priority is the only node in the linked list. OR the node is the front of the linked list.*/
			if(current.getPrev() == null){
				if(current.getNext() != null){
					current.getNext().setPrev(null);
				}
				front = current.getNext();
				current.setNext(null);
			}
			
			/*CASE-2: If the node to have updated priority is the last node in the linked list. */
			else if(current.getNext() == null){
				current.getPrev().setNext(null);
				rear = current.getPrev();
				current.setPrev(null);
			}
			/*CASE-3: If the node to have updated priority is somewhere in between in the linked list.*/
			else{
				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());
				current.setNext(null);
				current.setPrev(null);
			}
			//setting the priority of the current node(the node with the given dataItem) to the newPriority.
			current.setPriority(newPriority);
			
			//adding the node back to the linked list with the new Priority. (similar algorithm as the add method above)
			DLinkedNode<T> temp = front;
			//initialize a temporary node to the front of the list. Loop through the list until the end is reached or a node with a lower priority than the new node is found.
			while(temp != null && temp.getPriority() <= newPriority){
				temp = temp.getNext();
			}
			//if the current node is not the only node in the list, set the current node as the next node of the current rear node and set the rear node as the previous node of the current node.
			if(temp == null){
				if(current != rear){
					rear.setNext(current);
					current.setPrev(rear);
					rear = current;
					
				  //otherwise, the current node is both the front and rear node of the list.
				} else {
					front=current;
					rear=current;
				}
			}
			
			//if a node with a higher priority than the new node is found at the beginning of the list, add the new node to the beginning of the list.
			else if(temp.getPrev() == null){
				current.setNext(front);
				front.setPrev(current);
				front = current;
			}
			
			//if a node with a higher priority than the new node is found in the middle of the list, insert the new node before that node.
			else{
				current.setNext(temp);
				current.setPrev(temp.getPrev());
				temp.getPrev().setNext(current);
				temp.setPrev(current);
			}
		}
	}
	
	public T removeMin() throws EmptyPriorityQueueException{
		//if there are no nodes, throw the EmptyPriorityQueueException.
		if(front == null){
			throw new EmptyPriorityQueueException("Queue is empty");
		}
		//dataItem variables of parameter T that stores the dataItem of the front node.
		T dataItem = front.getDataItem();
		//if there is only one node in the linked linked.
		if(front == rear){
			front = null;
			rear = null;
		}
		//else setting the new front of the linked list to the initial front's next node.
		else{
			DLinkedNode<T> temp = front;
			front = front.getNext();
			front.setPrev(null);
			temp.setNext(null);
			temp.setPrev(null);
			temp.setNext(null);
		}
		//decrementing the count by 1.
		count--;
		//returning the dataItem.
		return dataItem;	
	}
	
	//a public isEmpty() method that return true if the count is 0, i.e., no nodes in the doubly linked list.
	public boolean isEmpty() {
		return count == 0;
	}
	
	//a public size() method that returns the count, i.e., the number of nodes in the doubly linked list.
	public int size() {
		return count;
	}
	
	//a public toString method that is coded according to the requirements.
	public String toString() {
		String str = "";
		DLinkedNode<T> current = front;
		while(current!=null) {
			str += current.getDataItem().toString();
			current = current.getNext();
		}
	    return str;
	}
	
	//a public getRear method that return the rear node of the double linked list.
	public DLinkedNode<T> getRear() {
		return rear;
	}
}