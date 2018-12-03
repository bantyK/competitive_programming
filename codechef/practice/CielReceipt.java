// https://www.codechef.com/problems/CIELRCPT
import java.util.Scanner;
class CielReceipt {
	public static void main(String[] args) {
		int[] prices = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		scanner.nextLine();

		for(int i = 0; i < t; i++) {
			int p = scanner.nextInt();
			int minimum = minimumOrder(prices, p);
			System.out.println(minimum);
		}

		System.exit(0);
	}

	private static int minimumOrder(int[] prices, int p) {
		int min = 0;
		while(p > 0) {
			p = p - findSmallest(prices, p);
			min += 1;
		}

		return min;
	}

	private static int findSmallest(int[] prices, int p) {
		int i = 0;
		while(i < prices.length && prices[i] <= p) {
			i++;
		}

		return prices[i - 1];
	}
}
