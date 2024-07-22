/**
 * @author Parth Kathiria
 * 
 */

public class Key {
	
	//Declaring private instance variables for the Key class
	private String label;
	private int type;
	
	//The constructor for the Key class which initializes a new Key object with the specified parameters
	public Key(String theLabel, int theType) {
		this.label = theLabel.toLowerCase();
		this.type = theType;
	}
	
	//Getter method which returns the label
	public String getLabel() {
		return label;
	}
	
	//Getter method which returns the type
	public int getType() {
		return type;
	}
	
	//compareTo method which compares 2 key objects, returns 0 is they are equal, 1 if first Key object is greater than the second key object and -1 if its not. 
	public int compareTo(Key k) {
		int labelComparision = this.label.compareTo(k.getLabel());
		
		if(labelComparision == 0) {
			if(this.type < k.getType()) {
				return -1;
			}
			else if(this.type == k.getType()) {
				return 0;
			}
			else return 1;
		}
		return labelComparision;
	}

}
