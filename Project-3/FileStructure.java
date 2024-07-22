/**
 * Class FileStructure represents the linked structure that will store the information of the file objects in your file system.
 * 
 * @author Parth Kathiria
 */

//importing the ArrayList and Iterator classes from the Java library.
import java.util.Iterator;
import java.util.ArrayList;

//a public class named FileStructure that uses the NLNode class to represent the nodes of the file structure as a tree.
public class FileStructure {
	
	//declaring private instance variable named root that is the root node of the tree representing the file structure.
	private NLNode<FileObject> root;
	
	//a constructor that throws the FileObjectException is the file object is not found
	public FileStructure(String fileObjectName) throws FileObjectException {
		
		try {
			//creating a new FileObject with the given fileObjectName.
			FileObject fileObj = new FileObject(fileObjectName);
			//creating a new NLNode with the FileObject as its data and null as its parent
			root = new NLNode<FileObject>(fileObj,null);
			//if the FileObject represents a directory, create its children.
			if(fileObj.isDirectory()) {
				createChildren(root);
			}
		}
		catch(Exception e) {
			//if there is an exception while creating the FileObject or the NLNode, throw a new FileObjectException with the message of the caught exception.
			throw new FileObjectException(e.getMessage());
		}
	}
	
	//a private helper method that recursively creates the children of a given node.
	private void createChildren(NLNode<FileObject> node){
		//if the node represents a file, there are no children to create and return.
		if(node.getData().isFile()) {
			return;
		}
		//getting the FileObject of the current node and store it in variable named file.
		FileObject file = node.getData();
		//if the FileObject represents a directory, get an iterator over its files and create a new NLNode for each one.
		if(file.isDirectory()) {
			Iterator<FileObject> itr = file.directoryFiles();
			while(itr.hasNext()) {
				FileObject childFile = itr.next();
				NLNode<FileObject> childNode = new NLNode<FileObject>(childFile,node);
				//setting node as the parent of childNode.
				childNode.setParent(node);
				//adding childNode to node using the .addChild method from NLNode class.
				node.addChild(childNode);
				createChildren(childNode);
			}
		}	
	}
	
	//a getter method that returns the root node of the tree representing the file structure.
	public NLNode<FileObject> getRoot(){
		return this.root;
	}
	
	//a public method that returns an iterator over the names of all files in the tree with the given file extension or type.
	public Iterator<String> filesOfType(String type){
		//creating an empty ArrayList to hold the names of the matching files.
		ArrayList<String> fileNames = new ArrayList<>();
		//calling a private helper method to populate the ArrayList with the matching file names.
		getFilesOfType(root,type,fileNames);
		//returning an iterator over the ArrayList.
		return fileNames.iterator();
	}
	
	//a private helper method that recursively adds the names of all files with the given extension or type to an ArrayList.
	private void getFilesOfType(NLNode<FileObject> node, String type, ArrayList<String> fileNames) {
		//if the node represents a file, check if its name contains the given extension or type.
		if(node.getData().isFile()) {
			//getting the LongName of the data of node and storing it inside String variable named nameOfFile.
			String nameOfFile = node.getData().getLongName();
			//if the nameOfFile contains the type or extension specified in the method parameter, adding it to fileNames ArrayList using .add method from ArrayList.
			if(nameOfFile.contains(type)) {
				fileNames.add(nameOfFile);
			}
			//returning
			return;
		}
		
		//if the node represents a directory, recursively call this method on all its children
		Iterator<NLNode<FileObject>> children = node.getChildren();
		while(children.hasNext()) {
			NLNode<FileObject> child = children.next();
			getFilesOfType(child,type,fileNames);
		}			
	}
	
	//a public method named findFile with a return type of String that takes one argument called name of type String.
	public String findFile(String name) {
		//calling the private fileFinder method with the same name being searched for and the root node
		return fileFinder(name,root);
	}
	
	//a private method named fileFinder with a return type String that takes two arguments called name of String type and node of NLNode<FileObject> type.
	private String fileFinder(String name, NLNode<FileObject> node) {
		//checking if the data of the node is a file.
		if(node.getData().isFile()) {
			//checking if the long name of the data of the node contains the name being searched for.
			if(node.getData().getLongName().contains(name)) {
				//returning the long name of the data of the node
				return node.getData().getLongName();
			}
		}
		//if the data of the node is not a file but a folder.
		else {
			//creating an iterator that iterates over the children of the node.
			Iterator<NLNode<FileObject>> iterator = node.getChildren();
			//while there are still children to iterate over.
			while(iterator.hasNext()) {
				//getting the next child node.
				NLNode<FileObject> childNode = iterator.next();
				//recursively calling the fileFinder method with the same name being searched for and the child node.
				String fileName = fileFinder(name,childNode);
				//if a file name is found, returning it.
				if(!fileName.equals("")) {
					return fileName;
				}
			}
		}
		//else if no file name was found, return an empty string.
		return "";
	}
}
