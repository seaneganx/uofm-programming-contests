import java.util.Scanner;

public class Q3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int testCount = in.nextInt();
		for(int caseNumber = 0; caseNumber < testCount; caseNumber++) {

			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();

			double dogDist = getDist(0, 0, x1, y1) + getDist(x1, y1, x2, y2);
			double youDist = getDist(0, 0, x2, y2);

			double decResult = Math.abs(dogDist - youDist) * 100 / youDist;

			long wholeResult = Math.round(decResult);
			System.out.printf("Case %d: ", caseNumber);
			System.out.println(wholeResult + "%");
		}

	}

	public static double getDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
	}
}
