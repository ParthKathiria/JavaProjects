/**
 * @author Parth Kathiria
 * 
 */

public class BinarySearchTree {
	
	//a private instance variable root for the class which represrnts the root of the BST
	private BSTNode root;
	
	//Initializing the root node to null, indicating an empty tree
	public BinarySearchTree() {
		this.root = null;
	}
	
	//Returning the root node of the binary search tree
	public BSTNode getRoot() {
		return root;
	}
	
	//Recursive method to search for a record with the specified key in the tree
	public BSTNode get(BSTNode r, Key k) {
		if(r == null) {
			// Key not found, return null
			return null;
		}
		
		int comparision = k.compareTo(r.getRecord().getKey());
		
		if(comparision == 0) {
			// Key found, return the corresponding node
			return r;
		}
		
		else if(comparision < 0) {
			//Key is less than the current node's key, search the left subtree
			return get(r.getLeftChild(), k);
		}
		// Key is more than the current node's key, search the right subtre
		else return get(r.getRightChild(), k);
	}
	
	//this method inserts a new record into the binary search tree
	public void insert(BSTNode r, Record d) throws DictionaryException {
        if (r == null) {
            // If the tree is empty, create a new root node
            this.root = new BSTNode(d);
        } else {
            // Call the recursive helper method to insert the record
            recursiveInsert(r, d);
        }
    }
	
	//Recursive helper method for inserting a record into the tree
    private void recursiveInsert(BSTNode node, Record d) throws DictionaryException {
        int compareResult = d.getKey().compareTo(node.getRecord().getKey());
        
        //If the new record's key is less than the current node's key
        if (compareResult < 0) {
        	//checking if the left child is null. If so, inserting the new record as the left child. Otherwise, recursively inserting the record into the left subtree.
            if (node.getLeftChild() == null) {
                BSTNode newNode = new BSTNode(d);
                newNode.setParent(node);
                node.setLeftChild(newNode);
            } else {

                recursiveInsert(node.getLeftChild(), d);
            }
          
         //If the new record's key is greater than the current node's key
        } else if (compareResult > 0) {
        	//checking if the right child is null. If so, insert the new record as the right child. Otherwise, recursively insert the record into the right subtree.
            if (node.getRightChild() == null) {

                BSTNode newNode = new BSTNode(d);
                newNode.setParent(node);
                node.setRightChild(newNode);
            } else {
                recursiveInsert(node.getRightChild(), d);
            }
        } 
        else
        {
        	// Duplicate key found, throw an exception
            throw new DictionaryException("Duplicate key found during insertion");
        }
    }
	
    //Removing a record with the specified key from the tree
	public void remove(BSTNode r, Key k) throws DictionaryException {
	    root = removeNode(root, k);
	}
	
	//Recursive helper method for removing a node from the tree
	private BSTNode removeNode(BSTNode node, Key k) throws DictionaryException {
	    if (node == null) {
	    	// Key not found, throw an exception
	        throw new DictionaryException("Key does not exist in the Binary Search Tree.");
	    }

	    int comparing = k.compareTo(node.getRecord().getKey());
	    
	    //Recursively search the left subtree for the node to remove if key is less than the other key
	    if (comparing < 0) {
	        node.setLeftChild(removeNode(node.getLeftChild(), k));
	     // Recursively search the right subtree for the node to remove if the key is larger than the other key
	    } else if (comparing > 0) {
	        node.setRightChild(removeNode(node.getRightChild(), k));
	    } else {
	    	// Node to remove is found, handle the three cases:
            // Case 1: Leaf node (no children)
	    	// Remove the leaf node from its parent
	        if (node.isLeaf()) {
	            return null;
	        }
	        // Case 2: Node has one child
	        //if theres not node to the leftChild, there must be a node to the right Child and vice versa
	        else if (node.getLeftChild() == null) {
	            return node.getRightChild();
	        } else if (node.getRightChild() == null) {
	            return node.getLeftChild();
	        }
	        // Case 3: Node has two children
	        else {
	        	// Find the successor node (smallest node in the right subtree)
	            BSTNode successor = smallest(node.getRightChild());

	            // Null check for successor and its record
	            if (successor != null && successor.getRecord() != null) {
	            	 // Replace the current node's record with the successor node's record
	                node.setRecord(successor.getRecord());
	             // Recursively remove the successor node from the right subtree
	                node.setRightChild(removeNode(node.getRightChild(), successor.getRecord().getKey()));
	            }
	        }
	    }
	    return node;
	}

	// Finds the successor node (smallest node greater than the given key)
	public BSTNode successor(BSTNode r, Key k) {
	     
        BSTNode node = get(r, k);

        if (node == null) {
        	// Key not found, return null
            return null;
        }

 
        if (node.getRightChild() != null) 
        {	
        	// If the right child exists, the successor is the smallest node in the right subtree
            return smallest(node.getRightChild());
        }
        
        
        BSTNode successor = null;
        BSTNode current = r;

        while (current != null) {
            if (node.getRecord().getKey().compareTo(current.getRecord().getKey()) < 0) 
            {	
            	// The current node is an ancestor that is greater than the current node
                successor = current;
                current = current.getLeftChild();
            } 
            else if (node.getRecord().getKey().compareTo(current.getRecord().getKey()) > 0)
            {	
            	// Need to keep searching the right subtree
                current = current.getRightChild();
            } 
            else
            {	
            	// Node found, breaking out of the loop
                break; 
            }
        }

        return successor;
    }
	
	// Finds the predecessor node (largest node less than the given key)
    public BSTNode predecessor(BSTNode r, Key k) 
    {
    	// Key not found, return null
        BSTNode node = get(r, k);

        if (node == null) {
        	// Key not found, return null
            return null;
        }

        if (node.getLeftChild() != null) {
        	// If the left child exists, the predecessor is the largest node in the left subtree
            return largest(node.getLeftChild());
        }

     // If the left child is null, the predecessor is the first ancestor that is less than the current node
        BSTNode predecessor = null;
        BSTNode current = r;

        while (current != null) {
            if (node.getRecord().getKey().compareTo(current.getRecord().getKey()) > 0) 
            {	
            	// If the left child is null, the predecessor is the first ancestor that is less than the current node
                predecessor = current;
                current = current.getRightChild();
            }
            else if (node.getRecord().getKey().compareTo(current.getRecord().getKey()) < 0) 
            {	
            	 // Need to keep searching the left subtree
                current = current.getLeftChild();
            } 
            else 
            {	
            	// Node found, break the loop
                break; 
            }
        }

        return predecessor;
    }
	
    //This method returns the smallest node by checking the left subtree
	public BSTNode smallest(BSTNode r) {
		if(r == null) {
			return null;
		}
		
		while(r.getLeftChild() != null) {
			r = r.getLeftChild();
		}
		
		return r;
	}
	
	//This method returns the largest node by checking the right subtree
	public BSTNode largest(BSTNode r) {
		if(r == null) {
			return null;
		}
		
		while(r.getRightChild() != null) {
			r = r.getRightChild();
		}
		
		return r;
	}
	
}


