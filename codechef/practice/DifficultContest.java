
//https://www.codechef.com/problems/CHFTIRED
import java.util.Scanner;

class DifficultContest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int a, b;
        for (int i = 0; i < t; i++) {
            String input = scanner.nextLine();
            a = Integer.parseInt(input.split(" ")[0]);
            b = Integer.parseInt(input.split(" ")[1]);
            System.out.println("YES");
            // alternate solution
            //printResult(a, b);
        }
    }

    static void printResult(int a, int b) {
        if(a == b) {
            System.out.println("YES");
            return;
        }

        if((a == 0 && b == Integer.MAX_VALUE) || (a == Integer.MAX_VALUE && b == 0)) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}