
//https://www.codechef.com/problems/QUALPREL
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class PreElimination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        String input;
        int n, k;
        for (int i = 0; i < t; i++) {
            input = scanner.nextLine();
            n = Integer.parseInt(input.split(" ")[0]);
            k = Integer.parseInt(input.split(" ")[1]);
            System.out.println(getWinningTeams(n, k, scanner.nextLine()));
        }
    }

    static int getWinningTeams(int n, int k, String scoreInput) {
        int winningTeams = 0;
        Integer[] scores = new Integer[n];
        String[] scoreString = scoreInput.split(" ");
        for(int i = 0; i < scoreString.length; i++) {
            scores[i] = Integer.parseInt(scoreString[i]);
        }
        Arrays.sort(scores, Collections.reverseOrder());
        int compareElem = scores[k - 1];
        int i = 0;
        while(i < scores.length && scores[i] >= compareElem) {
            i++;
        }

        return i;
    }
}