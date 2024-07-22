/** 
 * Data class represents the records that will be stored in the HashDictionary.
 * 
 * @author Parth Hasmukh Kathiria
 * 
 */
public class Data {
	
	//Declaring instance variables configuration of data type String and score of data type integer.
	private String configuration;
	private int score;
	
	//A constructor which initializes a new Data object with the specified configuration and score.
	public Data(String config, int score) {
		this.configuration = config;
		this.score = score;	
	}
	
	//A getter method which gets the configuration stored in a Data object.
	public String getConfiguration(){
		return configuration;
	}
	
	//A getter method which gets the score stored in a Data object.
	public int getScore() {
		return score;
	}

}
