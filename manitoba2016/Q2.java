import java.util.Scanner;

public class Q2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int testCount = in.nextInt();
		for(int caseNumber = 0; caseNumber < testCount; caseNumber++) {
			int x = in.nextInt();
			int y = in.nextInt();

			boolean[] xbits = new boolean[16];
			boolean[] ybits = new boolean[16];

			// convert x and y to bit representations
			int xtemp = x;
			for(int i = 15; i >= 0; i--) {
				if(xtemp % 2 == 0) {
					xbits[i] = false;
				}
				else {
					xbits[i] = true;
				}
				xtemp /= 2;
			}

			int ytemp = y;
			for(int i = 15; i >= 0; i--) {
				if(ytemp % 2 == 0) {
					ybits[i] = false;
				}
				else {
					ybits[i] = true;
				}
				ytemp /= 2;
			}

			// interleave their bit representations
			boolean[] bits = new boolean[32];
			int curBit = 0;
			for(int i = 0; i < 16; i++) {
				bits[curBit] = ybits[i];
				bits[curBit + 1] = xbits[i];
				curBit += 2;
			}

			// convert the interleave to decimal
			long result = 0;
			for(int i = 0; i < 32; i++) {
				result *= 2;
				if(bits[i]) {
					result++;
				}
			}

			System.out.println(result);
		}
	}
}
