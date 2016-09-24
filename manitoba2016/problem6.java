import java.util.Scanner;

public class problem6 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int numPokemon = in.nextInt();
		int numCandies = in.nextInt();
		int evoCost = in.nextInt();
		int caseNumber = 0;
		while(numPokemon != 0 || numCandies != 0 || evoCost != 0) {

			int evoCount = 0;

			// while there are more pokemon, and it is possible to evolve more
			while(numPokemon > 0 && numCandies >= evoCost) {

				// until you run out of pokemon or can't afford to evolve, do evolves
				while(numPokemon > 0 && numCandies >= evoCost) {
					numCandies -= evoCost;
					numCandies++;
					numPokemon--;
					evoCount++;
				}

				// if selling pidgeys will allow us to afford another evolve (with a remaining pokemon), sell those pidgeys!
				int neededCandy = evoCost - numCandies;
				if(neededCandy < numPokemon) {
					numPokemon -= neededCandy;
					numCandies += neededCandy;
				}
			}

			System.out.printf("Pokemon %d: %d evolutions, %d pokemon and %d candies.\n", caseNumber, evoCount, numPokemon, numCandies);

			// read next batch of input
			numPokemon = in.nextInt();
			numCandies = in.nextInt();
			evoCost = in.nextInt();
			caseNumber++;
		}
	}
}
