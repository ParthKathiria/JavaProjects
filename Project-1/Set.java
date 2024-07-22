//This is a generic class named Set
public class Set<T>{
	//A generic instance variable named setStart
	private LinearNode<T> setStart;
	
	//Constructor named Set() that initializes the setStart variable to null.
	public Set() {
		setStart = null;
	}

	//A public void method with generic type element as a parameter to add an element to the set.
	public void add(T element) {
		//creating a new LinearNode object with the element to be added
		LinearNode<T> newNode = new LinearNode<T>(element);
		//an if statement that checks if the setStart is null and if it is, it initializes the setStart to the newNode.
		if(setStart == null) {
			setStart = newNode;
			//else statement that adds the newNode at the beginning if the set is not empty.
		} else {
			newNode.setNext(setStart);
			setStart = newNode;
		}
	}
		
	//A public int getLength() method that gets the length of the set.	
	public int getLength() {
		//A variable named numItems whose value is set to 0 initially.
		int numItems = 0;
		//setting the current pointer to setStart
		LinearNode<T> current = setStart;
		//while the current is not null, visiting each node of the list once in order to get the next
		while(current != null) {
			current = current.getNext();
			numItems++;		
		}
		//returning numItems
		return numItems;	
	}
	
	//A public getElement method to get an element at the index i in the set.
	public T getElement(int i) {
		LinearNode<T> current = setStart;
		//A variable named count initially set to 0.
		int count = 0;
		//a while loop which travels to the particular index in the list if current is not null.
		while(current != null && count < i) {
			current = current.getNext();
			count++;
		}
		//if statement that returns an element using .getElement() method that returns the element at the specified index.
		if(current != null) {
			return current.getElement();
			//else it returns null
		} else {
			return null;
		}
	}
	
	//A public boolean contains method that checks if an element is present in the set.
	public boolean contains(T element) {
		LinearNode<T> current = setStart;
		//a while loop that travels throughout the list if current in not null in order to find the element and returns true if it does find it.
		while(current != null) {
			if(current.getElement() == element) {
				return true;
			}
			//incrementing the current position in the list.
			current = current.getNext();
		}
		//if the element is not found, it returns false.
		return false;
	}
	
	//A public String toString() method that converts the set to string.
	public String toString(){
		LinearNode<T> current = setStart;
		//Initializing an empty string variable named emptyString.
		String emptyString = "";
		//while the current is not null, traverse through the loop.
		while(current != null) {
			//appending the current element to the string.
			emptyString += current.getElement();
			//If the current element is not the last element, append a space to the string
			if(current.getNext() != null) {
				emptyString += " ";
			}
			//incrementing the current position in the list.
			current = current.getNext();
		}
		//returning the emptyString thats not empty anymore.
		return emptyString;
	}	
}
