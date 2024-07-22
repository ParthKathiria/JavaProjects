//A class representing the PowerSet of a given set of elements of type T
public class PowerSet<T> {
	//An array of Sets representing the PowerSet
	private Set<T>[] set;
	
	//A constructor to initialize the PowerSet for a given set of elements
	@SuppressWarnings("unchecked")
	public PowerSet(T[]elements) {
		//An integer variable named n setting it equal to the length of the elements array.
		int n = elements.length;
		//An integer variable named powerSetSize and setting it equal to the size of the size of the power set i.e. 2^n.
		int powerSetSize = (int)Math.pow(2, n);
		//Initializing the set array with the size of the PowerSet.
		set = new Set[powerSetSize];
		//A for loop to loop through all possible binary combinations of the given elements in the set.
		for(int i=0;i<powerSetSize;i++) {
			//A string variable named binary and equating it to the conversion of the decimal number to binary string representation using .toBinaryString in-built method.
			String binary = Integer.toBinaryString(i);
			//An if statement that checks if the binary representation has fewer digits than n and padding it with leading zeros to equal the lengths of every string binary representation.
			if(binary.length() < n) {
				while(binary.length() != n) {
					binary = "0" + binary;
				}	
			}
			//Create a new Set to represent the current subset
			Set<T> subSet = new Set<T>();
			//A for loop which loops through the binary string and adding the corresponding element to the subset if the binary digit is 1
			for (int j=0;j<n;j++) {
				if(binary.charAt(j)=='1') {
					subSet.add(elements[j]);	
				}
			}
			//adding the sets in the powerSet.
			set[i] = subSet;
		}
	}
	
	//A public int getLength method that gets the length of the set
	public int getLength() {
		return set.length;
	}
	
	//A public getSet method that gets the set at index i.
	public Set<T> getSet(int i){
		if(i < set.length) {
			return set[i];
		} else return null;
	}	
}
