import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class problem1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int numTowns = in.nextInt();
		for(int curTown = 0; curTown < numTowns; curTown++) {

			int numInters = in.nextInt();
			int numRoads = in.nextInt();
			int startInter = in.nextInt();
			int startSpeed = in.nextInt();

			// initialize speeds to -1 so we can check if a speed is invalid
			int[][] speeds = new int[numInters][numInters];
			for(int x = 0; x < numInters; x++) {
				for(int y = 0; y < numInters; y++) {
					speeds[x][y] = -1;
				}
			}

			// read the graph into an adjacency matrix
			for(int road = 0; road < numRoads; road++) {
				int edge1 = in.nextInt();
				int edge2 = in.nextInt();
				int speed = in.nextInt();

				speeds[edge1][edge2] = speed;
				speeds[edge2][edge1] = speed;
			}

			// stuff necessary for the search to complete
			Queue<Integer> positionQ = new LinkedList<Integer>();
			Queue<Integer> speedQ = new LinkedList<Integer>();
			int minSpeed = startSpeed;

			// we will mark index (i, j) to true as a transition from intersection i to intersection j (DIRECTION MATTERS)
			// since there is only one road between any two intersections, transitioning in the same direction over the same road causes an infinite cycle
			boolean[][] completedTransitions = new boolean[numInters][numInters];

			// start the search
			speedQ.add(startSpeed);
			positionQ.add(startInter);

			while(!positionQ.isEmpty()) {

				// where are we currently?
				int curSpeed = speedQ.remove().intValue();
				int curPos = positionQ.remove().intValue();

				// update the minimum speed
				minSpeed = Math.min(minSpeed, curSpeed);

				// add all the possible next neighbours to the queues
				for(int i = 0; i < numInters; i++) {

					// check if we already did this transition before
					// also check if there is a road between these intersections
					// also check if it's "legal" to take that road (is the difference in speed 10 or 0)
					if(!completedTransitions[curPos][i] && speeds[curPos][i] != -1 && 10 >= curSpeed - speeds[curPos][i] && curSpeed - speeds[curPos][i] >= 0) {
						positionQ.add(i);
						speedQ.add(speeds[curPos][i]);

						// mark down that we've considered this transition
						completedTransitions[curPos][i] = true;
					}
				}
			}

			System.out.printf("Town %d: %d\n", curTown, minSpeed);
		}
	}
}
