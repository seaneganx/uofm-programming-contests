import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Q1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int numTowns = in.nextInt();
		for(int curTown = 0; curTown < numTowns; curTown++) {

			int numInters = in.nextInt();
			int numRoads = in.nextInt();
			int startInter = in.nextInt();
			int startSpeed = in.nextInt();

			// init speeds to -1 as invalid speed
			int[][] speeds = new int[numInters][numInters];
			for(int x = 0; x < numInters; x++) {
				for(int y = 0; y < numInters; y++) {
					speeds[x][y] = -1;
				}
			}

			// read in graph info
			for(int road = 0; road < numRoads; road++) {
				int edge1 = in.nextInt();
				int edge2 = in.nextInt();
				int speed = in.nextInt();

				speeds[edge1][edge2] = speed;
				speeds[edge2][edge1] = speed;
			}

			Queue<Integer> positionQ = new LinkedList<Integer>();
			Queue<Integer> speedQ = new LinkedList<Integer>();
			int minSpeed = startSpeed;
			boolean[][] completedTransitions = new boolean[numInters][numInters];

			// start the search
			speedQ.add(startSpeed);
			positionQ.add(startInter);

			// keep going until we run out of paths to check
			while(!positionQ.isEmpty()) {

				// where are we currently?
				int curSpeed = speedQ.remove().intValue();
				int curPos = positionQ.remove().intValue();

				// update the minimum speed
				minSpeed = Math.min(minSpeed, curSpeed);

				// add all the possible next neighbours to the queues
				for(int i = 0; i < numInters; i++) {

					// check if we already did this one before
					// also check if there is an existing connection
					// also check if it's "legal" to take that road (is the difference in speed 10 or 0)
					if(!completedTransitions[curPos][i] && speeds[curPos][i] != -1 && 10 >= curSpeed - speeds[curPos][i] && curSpeed - speeds[curPos][i] >= 0) {
						positionQ.add(i);
						speedQ.add(speeds[curPos][i]);

						// mark this transition as "happened"
						completedTransitions[curPos][i] = true;
					}
				}
			}

			System.out.printf("Town %d: %d\n", curTown, minSpeed);
		}
	}
}
