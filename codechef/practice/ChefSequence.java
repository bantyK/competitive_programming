//https://www.codechef.com/problems/CHFAR
import java.util.Scanner;

class ChefSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int N, K, count;

        for (int i = 0; i < t; i++) {
            String[] values = scanner.nextLine().split(" ");
            N = Integer.parseInt(values[0]);
            K = Integer.parseInt(values[1]);
            String[] input = scanner.nextLine().split(" ");
            count = 0;
            for(String s : input) {
                if(Integer.parseInt(s) > 1) {
                    count++;
                    if(count > K) {
                        System.out.println("NO");
                        break;
                    }
                }
            }
            if(count <= K)
                System.out.println("YES");

        }

    }
}