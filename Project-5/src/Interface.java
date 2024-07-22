/**
 * @author Parth Kathiria
 * 
 */

// Importing necessary libraries for the Interface class
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.*;


public class Interface {
	
	// Creating an instance of the BSTDictionary class
	private static BSTDictionary dictionary = new BSTDictionary();
	
	// main method which is mainly responsible for reading and segmenting the user input
	public static void main(String[] args) throws Exception {
		// Checking if the correct number of command-line arguments are being provided by the user.
		if(args.length == 0) {
			// If the user does not provide the correct output, we display the syntax of the input and return.
			System.out.println("Usage: java Interface <inputFile>");
			return;
		}
	
		// Reading input file using BufferedReader library and store records in the dictionary
	    String inputFile = args[0];
	    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	    
	    // Declaring label variable of type String and typeAndData variable of type String as well.
	    String label;
	    String typeAndData;
	    
	    // Iterating through each line in the input file
	    while ((label = reader.readLine()) != null) {
	    	// Reading the next line, which contains type and data information
	        typeAndData = reader.readLine();
	        
	        // Processing the type of the record based on the typeAndData information and storing it in type variable of datatype integer.
	        int type = processType(typeAndData);
	        String data = typeAndData;
	        
	        // Adjusting data for specific types
	        if (type == 2 || type == 3 || type == 4 || type == 5) {
	            data = typeAndData.substring(1);
	        }
	        
	        // Creating a new Record object with the inferred type and data
	        Record record = new Record(new Key(label, type), data);
	        // Checking if the key already exists in the dictionary using the get function of BSTDictionary class.
	        Record existingRecord = dictionary.get(new Key(label, type));

	        if (existingRecord == null) {
	            // if the Key does not exist, add the record
	            dictionary.put(record);
	        } 
	    }
	    
	    // Closing the BufferedReader
	    reader.close();

	    // Creating a StringReader object to read user commands
	    StringReader keyboard = new StringReader();
	    
	    // Processing the user commands until the user enters "exit" and asking for a command until then.
	    String command;
	    while (!(command = keyboard.read("Enter next command: ")).equals("exit")) {
	        processCommand(command);
	    }
	}
	
	// Processing the type based on the typeAndData information.
	private static int processType(String typeAndData) {
		if (typeAndData.startsWith("-")) {
	        return 3; // Sound file
		}
    	else if(typeAndData.startsWith("/")){
    		return 2; //Translation file
	    } else if (typeAndData.startsWith("+")) {
	        return 4; // Music file
	    } else if (typeAndData.startsWith("*")) {
	        return 5; // Voice file
	    } else if (typeAndData.endsWith(".gif")) {
	        return 7; // Animated image file
	    } else if (typeAndData.endsWith(".jpg")) {
	        return 6; // Image file
	    } else if (typeAndData.endsWith(".html")) {
	        return 8; // Web-page
	    } else {
	        return 1; // Default type
	    }
    }
	
	// Getting the next token from the StringTokenizer, converting it to lower case
	private static String getNextToken(StringTokenizer tokenizer) {
		
	    if (tokenizer.hasMoreTokens()) {
	        return tokenizer.nextToken().toLowerCase();
	    } else {
	        return null;
	    }
	}
	
	// Processing the user command
	private static void processCommand(String command) {
		StringTokenizer tokenizer = new StringTokenizer(command);
		
		// Checking if theres more to the command.
        if (!tokenizer.hasMoreTokens()) {
            System.out.println("Invalid command. Please try again!");
            return;
        }
        
        // Splitting the command into an array of strings
        String arr[] = command.split(" ");
        String action = tokenizer.nextToken();
        String word = getNextToken(tokenizer);
        
        // Switch statement to handle different commands
        switch (action.toLowerCase()) {
            case "define":
                defineCommand(word);
                break;
            
            case "translate":
                translateCommand(word);
                break;

            case "sound":
                soundCommand(word);
                break;
            
            case "play":
                playCommand(word);
                break;

            case "say":
                sayCommand(word);
                break;

            case "show":
                showCommand(word);
                break;
            
            case "animate":
                animateCommand(word);
                break;

            case "browse":
                browseCommand(word);
                break;
            
            //case for the delete command
            case "delete":
            	//Initializing an array for the key information from the command.
            	String shabd = arr[1];
            	int type = Integer.parseInt(arr[2]);
            	try {
            		//Attempting to remove the record with the specified key using the remove method from BSTDictionary class.
    		        dictionary.remove(new Key(shabd, type));
    		        System.out.println("Record with key (" + word + "," + type + ") deleted successfully.");
    		      //Handling the exception when the record does not exist.
    		       
    		    }
            	catch (DictionaryException e) {
    		       System.out.println("No record in the ordered dictionary has key(" + word + "," + type + ").");
    		    }
            	catch (Exception e) {
    		    	   System.out.println(e.getMessage());
    		       }
            	
            	//breaking out of the case after the above operations have been done successfully.
                break;
            
            //case for the add command.
            case "add":
            	//Initializing an array for the key information from the command.
            	String words = arr[1];
            	int types = Integer.parseInt(arr[2]);
            	String lab = arr[3];
            	
            	// Creating a key and record based on the extracted information.
	            Key key = new Key(words, types);
	            Record rec = new Record(key, lab);
	       		try {
	       			// Checking if the input format is correct.
		       	    if (arr.length != 4) {
		       	    	System.out.println("Incorrect input usage is add w t c");
		       	    }

		       	    else {
		       	    	 // Attempting to add the record to the dictionary using the put method from the BSTDictionary class.
		       	        dictionary.put(rec);
		       	        System.out.println("Record with key (" + words + "," + types + ") added successfully.");
		       	     }
		       	    
		       	    // Handling the case where the record already exists by throwing a DictionaryException
		       	    } catch (DictionaryException e){
		       	    	System.out.println("A record with the given key (" + words + "," + types + ") is already in the ordered dictionary.");
		       	    }
	      
                break;
                
            case "list":
            	
            	String pref = arr[1];
                listCommand(dictionary, pref);
                break;
            

            case "first":
                firstCommand(dictionary);
                break;

            case "last":
                lastCommand(dictionary);
                break;
			
            case "exit":
                System.exit(0);
                System.out.println("Exiting Program!");
                break;

            default:
                System.out.println("Invalid command. Please try again!");
        }
	}
	
	
	// Method to find a record with the specified key in the dictionary, done using get method of the BSTDictionary class.
	private static Record findRecord(Key k) {
	       return dictionary.get(k);
	}
	
	// Method to handle the "define w" command
	private static void defineCommand(String word) {
		// Search for records with type 1 (definition)
	    Record record = findRecord(new Key(word, 1)); // Search for records with type 1 (definition)
	    if (record != null) {
	    	// Print the data attribute if the record is found
	        System.out.println(record.getDataItem()); // Print the data attribute
	    } else {
	        System.out.println("The word " + word + " is not in the ordered dictionary");
	    }
	 }
	 
	// Method to handle the "translate w" command
	private static void translateCommand(String word) {
		// Search for records with type 2 (translation)
	    Record record = findRecord(new Key(word, 2));
	    if (record != null) {
	    	// Print the data attribute if the record is found
	        System.out.println(record.getDataItem()); // Print the data attribute
	    } else {
	        System.out.println("There is no definition for the word "+ word);
	    }
	 }
	 
	// Method to handle the "sound w" command
	private static void soundCommand(String word) {
		// Search for records with type 3 (sound file)
		Record record = findRecord(new Key(word, 3)); 
		if (record != null) {
			// Extract the filename from the record
		    String fileName = record.getDataItem(); 
		    try {
		    	// Making a SoundPlayer object using the provided SoundPlayer.java file
		        SoundPlayer player = new SoundPlayer();
		        // Playing the sound file using SoundPlayer
		        player.play(fileName);
		       // Catching the MultimediaException as stated in the instructions.
		     } catch (MultimediaException e) {
		         System.out.println("Error playing sound file: " + e.getMessage());
		     }
		    } else {
		        System.out.println("There is no sound file for " + word);
		    }
		}
	
	// Method to handle the "play w" command
	private static void playCommand(String word) {
		   // Search for records with type 3 (sound file)
		   Record record = findRecord(new Key(word, 4)); 
		   if (record != null) {
			    // Extracting the filename from the record
		       String fileName = record.getDataItem();
		       try {
		           SoundPlayer player = new SoundPlayer();
		           // Playing the sound file using SoundPlayer
		           player.play(fileName); 
		         // Catching the MultimediaException as stated in the instructions.
		       } catch (MultimediaException e) {
		           System.out.println("Error playing sound file: " + e.getMessage());
		       }
		   	} else {
		        System.out.println("There is no music file for " + word);
		    }
		}
	
	 // Method to handle the "say w" command
	 private static void sayCommand(String word) {
		 // Search for records with type 5 (voice file)
		 Record record = findRecord(new Key(word, 5)); 
		 if (record != null) {
			 // Extract the filename from the record
		     String fileName = record.getDataItem(); 
		     try {
		    	 // Play the voice file using SoundPlayer
		         SoundPlayer player = new SoundPlayer();
		         player.play(fileName); 
		     } catch (MultimediaException e) {
		         System.out.println("Error playing voice file: " + e.getMessage());
		     }
		 } else {
		     System.out.println("There is no voice file for " + word);
		 }
	 }
	 
	// Method to handle the "show w" command
	private static void showCommand(String word) {
		// Search for records with type 6 (image file)
	    Record record = findRecord(new Key(word, 6)); 
	    if (record != null) {
	    	// Extract the filename from the record
	        String fileName = record.getDataItem(); 
	        try {
	        	// Making a PictureViewer object called viewer from class PictureViewer provded to us.
	            PictureViewer viewer = new PictureViewer();
	            // Display the image using PictureViewer
	            viewer.show(fileName); 
	          // Catching the MultimediaException as stated in the instructions. 
	        } catch (MultimediaException e) {
	            System.out.println("Error displaying image file: " + e.getMessage());
	        }
	    } else {
	        System.out.println("There is no image file for " + word);
	    }
	}

	// Method to handle the "animate w" command
	private static void animateCommand(String word) {
		// Search for records with type 7 (animated image file (.gif))
		Record record = findRecord(new Key(word, 7)); 
		if (record != null) {
			// Extract the filename from the record
		    String fileName = record.getDataItem(); 
		    try {
		        PictureViewer viewer = new PictureViewer();
		        // Display the animated image using PictureViewer
		        viewer.show(fileName); 
		    } catch (MultimediaException e) {
		         System.out.println("Error displaying animated image file: " + e.getMessage());
		    }
		 } else {
		     System.out.println("There is no animated image file for " + word);
		 }
	}
	
	// Method to handle the "browse w" command
	private static void browseCommand(String word) {
		// Search for records with type 8 (web page)
		Record record = findRecord(new Key(word, 8)); 
		if (record != null) {
			// Extract the URL from the record
		    String url = record.getDataItem(); 
		    try {
		    	// Making a ShowHTML object named browser given by us
		        ShowHTML browser = new ShowHTML();
		        // Display the web page using ShowHTML
		        browser.show(url); 
		      // Handling any exception that might occur and printing the message.
		    } catch (Exception e) {
		        System.out.println("Error browsing webpage: " + e.getMessage());
		    }
		 } else {
		     System.out.println("There is no webpage called " + word);
		 }
	}
	
	// Method to handle the "list prefix" command
	private static void listCommand(BSTDictionary dictionary, String prefix) {
		// Start from the smallest record, getting the smallest record using .smallest method from BSTDictionary class.
		Record currentRecord = dictionary.smallest(); 
		boolean check = false;
		// A while loop that will run which the condition is true.
		while (currentRecord != null && currentRecord.getKey().getLabel().compareToIgnoreCase(prefix) < 0) {
		     // Move to the next record using successor until a record with label >= prefix is found
		     currentRecord = dictionary.successor(currentRecord.getKey());
		 }
		 
		// Another while loop that will run while the current node is not null and if it starts with the prefix
		 while (currentRecord != null && currentRecord.getKey().getLabel().startsWith(prefix)) {
		     System.out.println(currentRecord.getKey().getLabel());
		     check = true;

		     // Move to the next record using successor
		     currentRecord = dictionary.successor(currentRecord.getKey());
		  }
		  
		  // Changing the condition of the check boolean if there is no label found starting with the prefix.
		  if (!check) {
		      System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
		  }
	}
	
	// Method to handle the "first" command
	private static void firstCommand(BSTDictionary dictionary) {
		// Get the smallest record using the smallest method from BSTDictionary class.
		Record smallestRecord = dictionary.smallest();
		// Print the label, type and data of the smallestRecord if it is found
		if(smallestRecord != null) {
			System.out.println(smallestRecord.getKey().getLabel() + "," + smallestRecord.getKey().getType() + "," + smallestRecord.getDataItem());
		}
		else {
			System.out.println("The ordered dictionary is empty");
		}
	}
	
	// Method to handle the "last" command
	private static void lastCommand(BSTDictionary dictionary) {
		// Get the largest record using the largest method from BSTDictionary class.
		Record largestRecord = dictionary.largest();
		//print the label, type and the data if the largest Record is found.
		if(largestRecord != null) {
			
			System.out.println(largestRecord.getKey().getLabel() + "," + largestRecord.getKey().getType() + "," + largestRecord.getDataItem());
		}
		else {
			System.out.println("The ordered dictionary is empty.");
		}
	}	 
}

