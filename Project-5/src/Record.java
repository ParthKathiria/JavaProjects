/**
 * @author Parth Kathiria
 * 
 */
public class Record {
	
	//Declaring two instance variables - theKey of Key class and data of String datatype
	Key theKey;
	String data;
	
	//Constructor for the class which initializes a new Record object with the specified parameters
	public Record(Key k, String theData) {
		this.theKey = k;
		this.data = theData;
	}
	
	//Getter method which returns the key
	public Key getKey() {
		return theKey;
	}
	
	//Getter method which returns the data
	public String getDataItem() {
		return data;
	}
	
}
