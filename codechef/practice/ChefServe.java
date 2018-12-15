
// https://www.codechef.com/problems/CHSERVE
import java.util.Scanner;

class ChefServe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int p1, p2, k;
        for (int i = 0; i < t; i++) {
            String input = scanner.nextLine();
            p1 = Integer.parseInt(input.split(" ")[0]);
            p2 = Integer.parseInt(input.split(" ")[1]);
            k = Integer.parseInt(input.split(" ")[2]);

            printServe(p1, p2, k);
        }
    }

    static void printServe(int p1, int p2, int k) {
        int total = p1 + p2;
        int quotient = total / k;

        if (quotient % 2 == 0) {
            System.out.println("CHEF");
        } else {
            System.out.println("COOK");
        }
    }
}