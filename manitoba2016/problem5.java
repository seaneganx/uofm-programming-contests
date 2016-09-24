import java.util.Scanner;

public class problem5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int numCases = in.nextInt();
		for(int curCase = 1; curCase <= numCases; curCase++) {
			int numRacers = in.nextInt();
			int[] laps = new int[numRacers];
			int[] raceTimes = new int[numRacers];

			// get the lap list
			in.nextLine(); // burn the newline character
			String lapStrings = in.nextLine();

			// create a lapcount array with the racer number as the index
			int[] lapCounts = new int[numRacers];
			for(int i = 0; i < numRacers; i++) {
				lapCounts[i] = 0;
			}

			// count how many laps each person did
			Scanner lapScanner = new Scanner(lapStrings);
			while(lapScanner.hasNextInt()) {
				lapCounts[lapScanner.nextInt()]++;
			}

			// get the race finish times
			for(int i = 0; i < numRacers; i++) {
				int racer = in.nextInt();
				String[] time = in.next().split(":");
				int seconds = 60 * Integer.parseInt(time[0]) + Integer.parseInt(time[1]);

				raceTimes[racer] = seconds;
			}

			// loop through the arrays and find the best racer
			int bestRacer = 0;
			int bestLapCount = lapCounts[0];
			int bestTime = raceTimes[0];

			for(int i = 0; i < numRacers; i++) {
				if(lapCounts[i] > bestLapCount || (lapCounts[i] == bestLapCount && raceTimes[i] < bestTime)) {
					bestRacer = i;
					bestLapCount = lapCounts[i];
					bestTime = raceTimes[i];
				}
			}

			System.out.printf("Race %d: %d\n", curCase, bestRacer);
		}
	}
}
