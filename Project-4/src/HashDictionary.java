/**
 * HashDictionary class implements the dictionary ADT using a hash table with seperate chaining using LinkedLists.
 * 
 * @author Parth Hasmukh Kathiria
 * 
 */

import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
	
	//Declaring the instance variables tableSize of data type integer and hashTable of LinkedList data structure holding Data objects.
	private LinkedList<Data>[] hashTable;
	private int tableSize;
	
	//This constructor method returns an empty dictionary of the specified size.
	public HashDictionary(int size) {
		this.tableSize = size;
		hashTable = new LinkedList[size];
		for(int i=0;i<size;i++) {
			hashTable[i] = new LinkedList<>();	
		}
	}
	
	//private hashFunction method which is responsible for Hashing which lets us store the data in the form of key-value pair while simultaneously reducing the number of collisions.
	private int hashFunction(String key, int tableSize) {
		int hash = 0;
		for(int i=0; i<key.length();i++) {
			hash = ((11*hash) + (int)key.charAt(i))%tableSize;
		}
		return hash;
	}
	
	//put method adds a Data object called record in the dictionary.
	public int put(Data record) throws DictionaryException{
		
		//Finding out the hashIndex to determine where the the record will be put inside the hash table/
		int hashIndex = hashFunction(record.getConfiguration(),tableSize);
		//store the linked list inside the hashTable at hashIndex in order to store the record.
		LinkedList<Data> list = hashTable[hashIndex];
		
		//checking if the record is already present in the linked list using a for loop, if it is present a DictionaryException is thrown to indicate its presence.
		for(Data data : list) {
			if(data.getConfiguration().equals(record.getConfiguration())){
				throw new DictionaryException();
			}
		}
		//adds the record to the linked list.
		list.add(record);
		
		//if there is a collision produced by the hashFunction, 1 is returned , if not 0 is returned.
		if(list.size() > 1) {
			return 1;
		} else return 0;

	}
	
	//remove method removes the record with the given config from the dictionary.
	public void remove(String config) throws DictionaryException{
		
		//hashIndex stores the index where the record could be stored in the hashTable.
		int hashIndex = hashFunction(config,tableSize);
		//Get the linked list at the calculated hashIndex.
		LinkedList<Data> list = hashTable[hashIndex];
		//Iterating through the linked list using for loop to check if the record with the given configuration is present inside the linked list.
		for(Data data : list) {
			//If the record is found remove it from the linked list and return.
			if(data.getConfiguration().equals(config)) {
				list.remove(data);
				return;
			}
		}
		//If the record is not found with the given configuration a DictionaryException is thrown.
		throw new DictionaryException();
	}
	
	//get method returns the score stored in the record of the dictionary with key config, or -1 if config is not in the dictionary.
	public int get(String config) {
		//calculating hashIndex using hashFunction which stores the index where the record could be stored in the hashTable.
		int hashIndex = hashFunction(config,tableSize);
		//Get the linked list at the calculated hashIndex.
		LinkedList<Data> list = hashTable[hashIndex];
		//Iterating through the linked list using for loop to check if the record with the given configuration is present inside the linked list.
		for(Data data : list){
			// If the configuration is found, returning its associated score.
			if(data.getConfiguration().equals(config)) {
				return data.getScore();
			}
		}
		//if the configuration is not found, returning -1.
		return -1;	
	}
	
	//numRecords method returns the number of Data objects stored in the dictionary.
	public int numRecords() {
		//initializing the count variable of integer to 0 to keep track of the number of records.
		int count = 0;
		//using a for loop to iterate through the hashTable to check for the number of records.
		for(int i = 0; i<tableSize; i++) {
			//Adding the size of the linked list at each index to the count.
			count += hashTable[i].size();
		}
		//returning the count.
		return count;
	}
	
}
