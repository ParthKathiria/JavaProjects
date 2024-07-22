public class Counter {
	// PowerSet of Card objects
	private PowerSet<Card> cardps;
	// starter Card
	private Card starter;
	// Set of Card objects
	private Set<Card> set;
	
	//create a new PowerSet instance with the given hand of Card objects
	public Counter(Card[]hand, Card starter) {
		this.starter = starter;
		cardps = new PowerSet<>(hand);
	}
	
	public int countPoints() {
		
		int totalPoints = 0;
		// add run points to the total points
		totalPoints += runPoints();
		// add pair points to the total points
		totalPoints += pairPoints();
		// add His Knob points to the total points
		totalPoints += hisKnobPoints();
		// add flush points to the total points
		totalPoints += flushPoints();
		// add fifteen points to the total points
		//totalPoints += fifteenPoints();
		
		//return the totalPoints
		return totalPoints;
		
	}
	
	//private hisKnobPoints that calculates hisKnob points.
	private int hisKnobPoints() {
		int totalHisKnobPoints = 0;
		
		Set<Card> current = new Set<Card>();
		int largestSetSize = 0;
		// iterate over all subsets of the hand of cards to find the subset with the largest size.
		for (int i = 0; i < cardps.getLength(); i++) {
		  if (cardps.getSet(i).getLength() > largestSetSize) {
		    current = cardps.getSet(i);
		    largestSetSize = cardps.getSet(i).getLength();
		  }
		}
		// check if the subset with the largest size contains a Jack of the same suit as the starter Card
		for(int j = 0;j<current.getLength();j++) {
			if(current.getElement(j).getLabel().equals("J") && current.getElement(j).getSuit().equals(starter.getSuit())){
				totalHisKnobPoints = 1;
			}
		}
		return totalHisKnobPoints;	
	}
	
	
	private int flushPoints() {
	    int totalFlushPoints = 0;
	    Set<Card> largestSetOfCards = new Set<Card>();
	    int largestSetSize = 0;
	 // iterate over all subsets of the hand of cards to find the subset with the largest size
	    for (int i = 0; i < cardps.getLength(); i++) {
	        if (cardps.getSet(i).getLength() > largestSetSize) {
	            largestSetOfCards = cardps.getSet(i);
	            largestSetSize = cardps.getSet(i).getLength();
	        }
	    }
	 // check if the subset with the largest size is a flush or a run, and add points accordingly
	    if (largestSetOfCards.getLength() == 5) {
	        String handSuit = largestSetOfCards.getElement(0).getSuit();
	        boolean isFlush = true;
	        for (int j = 1; j < largestSetOfCards.getLength(); j++) {
	            if (!largestSetOfCards.getElement(j).getSuit().equals(handSuit)) {
	                isFlush = false;
	                break;
	            }
	        }
	        if (isFlush) {
	            totalFlushPoints += 4;
	            if (starter.getSuit().equals(handSuit)) {
	                totalFlushPoints += 1;
	            }
	        }
	    } else if (isRun(largestSetOfCards)) {
	        totalFlushPoints += 5;
	    }
	    return totalFlushPoints;
	}
	
	//private int fifteenPoints() {
	    //int totalFifteenPoints = 4;
	    //int n = cardps.getLength();
	    //if(n > 1) {
	        //for(int i = 0; i < n; i++) {
	            //totalFifteenPoints = cardps.getSet(i).getElement(i).getFifteenRank();    
	        //}
	    //}
	    //return totalFifteenPoints;
	//}
	
	// This method calculates the number of pair points in the given cardps (card play set).
	private int pairPoints() {
		// It initializes totalPairPoints to 0, and iterates through each set in cardps.
		int totalPairPoints = 0;
		int n = cardps.getLength();
		for(int i = 0; i < n;i++) {
			// If the length of a set is 2, and both elements in the set have the same label, it adds 2 to totalPairPoints.
			if(cardps.getSet(i).getLength() == 2) {
				if(cardps.getSet(i).getElement(0).getLabel().equals(cardps.getSet(i).getElement(1).getLabel())) {
					totalPairPoints += 2;
				}
			}
		}
		// The method returns the total number of pair points found in cardps.
		return totalPairPoints;
	}
	
	//provided boolean method
	private boolean isRun (Set<Card> set) {
		
		int n = set.getLength();
		
		if (n <= 2) return false; 
		
		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; 
		
		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { 
			return true;
		} else {
			return false;
		}

	}
	
	// This method calculates the number of run points in the given cardps.
	private int runPoints(){
		// It first initializes totalRunPoints and maxStreak to 0, and iterates through each set in cardps.
		int totalRunPoints = 0;
		int maxStreak = 0;
		for(int i =0; i < cardps.getLength();i++) {
			if(isRun(cardps.getSet(i))) {
				if(cardps.getSet(i).getLength() > maxStreak) {
					maxStreak = cardps.getSet(i).getLength();
				}
			}
		}
		// The method then iterates through each set in cardps again, and adds maxStreak to totalRunPoints.
		for(int i = 0; i < cardps.getLength(); i++) {
			// If a set is a run, and its length is greater than maxStreak, maxStreak is updated to its length.
			if(cardps.getSet(i).getLength() == maxStreak) {
				if(isRun(cardps.getSet(i))) {
					// set that is a run and has length equal to maxStreak.
					totalRunPoints += maxStreak;
				}
			}
		}
		// The method returns the total number of run points found in cardps.
		return totalRunPoints;
	}
	
}