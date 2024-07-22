/**
 * FindShortestPath implements the algorithm to compute a shortest path from the initial chamber to the exit.
 * 
 * @author Parth Kathiria
 *
 */


public class FindShortestPath {
	
	public static int findShortestPath(Dungeon dungeon) {
		//creating a priority queue to store hexagons to be checked for the shortest path.
		DLPriorityQueue<Hexagon> queue = new DLPriorityQueue<>();
		//getting the start hexagon from the dungeon.
		Hexagon start = dungeon.getStart();
		//getting the exit hexagon from the dungeon.
		Hexagon exit = dungeon.getExit();
		//priority variables of data type double initially set to 0.
		double priority = 0;
		//adding the starting hexagon to the queue with the priority.
		queue.add(start, priority);
		//marking the starting hexagon as enqueued.
		start.markEnqueued();
		//while the queue is not empty or the exit has not been reached.
		while(!queue.isEmpty() && exit.getPredecessor() == null) {
			//getting the hexagon with the least priority from the queue
			Hexagon current = queue.removeMin();
			current.markDequeued();
			//If the current hexagon is the exit, break out of the loop.
			if(current == exit) {
				break;
			}
			//If the current hexagon contains a dragon or has a neighbouring dragon, skip it and continue with the loop.
			if(current.isDragon() || isDragonNeighbour(current)) {
				continue;
			}
			//Exploring the neighbouring hexagons.
			for(int i=0; i<=5;i++) {
				Hexagon neighbour = current.getNeighbour(i);
				//if the hexagon is null, wall hexagons, and hexagons that have already been dequeued, continue with the loop.
				if(neighbour == null || neighbour.isWall() || neighbour.isMarkedDequeued()) {
					continue;
				}
				//D instance variable of int data type equal to 1 + distance from current to the initial chamber. 
				int D = 1 + current.getDistanceToStart();
				//If distance of neighbour to initial chamber is larger than D then setting the distance of neighbour to the initial chamber to D and setting current as the predecessor of neighbour 
				if(neighbour.getDistanceToStart() > D) {
					neighbour.setDistanceToStart(D);
					neighbour.setPredecessor(current);
					//If neighbour is marked as enqueued, then invoking the updatePriority method from class DLPriorityQueue to update the priority that neighbour has in the priority queue.
					if(neighbour.isMarkedEnqueued()) {
						//new priority of neighbour is the distance from neighbour to the initial chamber plus the distance from neighbour to the exit.
						double newPriority = neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon);
						queue.updatePriority(neighbour, newPriority);
					}
					//else adding neighbour to the queue with priority equal to its distance to the initial chamber plus its distance to the exit. 
					else {
						double newPriority = neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon);
						queue.add(neighbour, newPriority);
						//marking neighbour as enqueued.
						neighbour.markEnqueued();
					}
				}
			}
		}
		//returning the distance from the starting hexagon to the exit, plus 1 to include the exit in the path length.
		return exit.getDistanceToStart()+1;
	}
	
	//private static boolean isDragonNeighbout method that checks if any of the neighbouring hexagons contain a dragon.
	private static boolean isDragonNeighbour(Hexagon curr) {
		for(int i =0 ; i<=5 ; i++) {
			Hexagon side = curr.getNeighbour(i);
			if(side != null && side.isDragon()) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		try {
			if (args.length < 1) throw new Exception("No input file specified");
			String dungeonFileName = args[0];
			Dungeon d = new Dungeon(dungeonFileName);
			//pathLength variable stores  the length of the shortest path through the dungeon using the findShortestPath method, which takes a Dungeon object as input and returns an integer value.
			int pathLength = findShortestPath(d);
			//if statement that prints the pathLength if the length of the path is 0 or greater than 0.
			if(pathLength >= 0) {
				System.out.println("Path of length " + pathLength + " found");		
			}
			//else prints "No Path found".
			else {
				System.out.println("No path found");
			}
		}
		//catching any other exception that might occur and printing its message.
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
