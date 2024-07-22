/**
 * @author Parth Kathiria
 * 
 */
public class BSTDictionary implements BSTDictionaryADT {
	
	//private instance variable of BinarySearchTree class named dictTree
	private BinarySearchTree dictTree;
	
	// Initializing the binary search tree
	public BSTDictionary() {
		this.dictTree = new BinarySearchTree();
	}

	@Override
	public Record get(Key k) {
		// Gets the record associated with the specified key from the dictionary
		BSTNode foundNode = dictTree.get(dictTree.getRoot(), k);
		if(foundNode != null) {
			// Record found, return it
			return foundNode.getRecord();
		}
		//else if not found, return null
		else return null;
	}
	
	@Override
	public void put (Record d) throws DictionaryException {
		try {
			// Inserts the record into the binary search tree
			dictTree.insert(dictTree.getRoot(), d);
		}
		catch (DictionaryException ex){
			// Duplicate key exception, rethrow with a more specific message
			throw new DictionaryException("Key already exists in in.");
		}
	}
	
	
	@Override
	public void remove(Key k) throws DictionaryException {
		try {
			// Removes the record with the specified key from the dictionary
			dictTree.remove(dictTree.getRoot(), k);
		}
		catch(DictionaryException ex) {
			// Key not found exception, throw with a more specific message
			throw new DictionaryException("Key already exists .");
		}
	}
	
	
	@Override
	public Record successor(Key k) {
		 // Gets the successor record of the given key in the dictionary
		BSTNode successorNode = dictTree.successor(dictTree.getRoot(), k);
		if(successorNode != null) {
			// Successor found, return its record
			return successorNode.getRecord();
		}
		//else if not found, return null
		else return null;
	}
	
	@Override
	public Record predecessor(Key k) {
		// Gets the predecessor record of the given key in the dictionary
		BSTNode predecessorNode = dictTree.predecessor(dictTree.getRoot(), k);
		if(predecessorNode != null) {
			// Predecessor found, return its record
			return predecessorNode.getRecord();
		}
		//else if not found, return null
		else return null;
	}
	
	@Override
	public Record smallest() {
		// Gets the record with the smallest key in the dictionary
		BSTNode smallestNode = dictTree.smallest(dictTree.getRoot());
		if(smallestNode != null) {
			// Smallest record found, return it
			return smallestNode.getRecord();
		}
		//else if not found, return null
		else return null;
	}
	
	@Override
	public Record largest() {
		// Gets the record with the largest key in the dictionary
		BSTNode largestNode = dictTree.largest(dictTree.getRoot());
		if(largestNode != null) {
			 // Largest record found, return it
			return largestNode.getRecord();
		}
		//else if not found, return null
		else return null;
	}

}
