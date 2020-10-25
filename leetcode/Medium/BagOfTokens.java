import java.util.*;

//948 https://leetcode.com/problems/bag-of-tokens/
public class BagOfTokens {
    public static void main(String[] args) {
        BagOfTokens obj = new BagOfTokens();

        System.out.println(obj.bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
    }

    public int bagOfTokensScore(int[] tokens, int P) {
        // sorting the tokens values because we want to know where the smallest and largest tokens lies
        // so that we can exchange them accordingly
        Arrays.sort(tokens);

        int smallestTokenIndex = 0;
        int largestTokenIndex = tokens.length - 1;

        int points = 0;
        int maxPoints = 0;

        while (smallestTokenIndex <= largestTokenIndex) {
            if (P >= tokens[smallestTokenIndex]) {
                // have minimum power to trade for a point
                // we would want to lose as little power as we can, hence we are exchaning with the smallest power
                points++;
                maxPoints = Math.max(maxPoints, points);
                P -= tokens[smallestTokenIndex++];
            } else if (points > 0) {
                // we have points to exchange for power
                // we would want to gain as much power as we can hence we are exchanging it with maximum power possible
                points--;
                P += tokens[largestTokenIndex--];
            } else {
                // we cannot exchange points for tokens and we cannot exchange tokens for points, so simply return
                return maxPoints;
            }
        }

        return maxPoints;
    }
}
