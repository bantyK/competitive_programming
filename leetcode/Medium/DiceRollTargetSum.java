import java.util.*;

// 1155 https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
public class DiceRollTargetSum {
    public static void main(String[] args) {
        DiceRollTargetSum obj = new DiceRollTargetSum();

        System.out.println(obj.numRollsToTarget(1, 6, 3));
        System.out.println(obj.numRollsToTarget(2, 6, 7));
        System.out.println(obj.numRollsToTarget(2, 5, 9));
        System.out.println(obj.numRollsToTarget(2, 2, 1));
        System.out.println(obj.numRollsToTarget(10, 6, 45));
        System.out.println(obj.numRollsToTarget(30, 30, 500));
    }

    public int numRollsToTarget(int d, int f, int target) {
        Map<String, Integer> cache = new HashMap<>();
        return helper(d, f, target, cache);
    }

    private int helper(int d, int f, int remaining, Map<String, Integer> cache) {
        if(remaining == 0 && d == 0) {
            return 1;
        }

        if(d == 0 || remaining == 0) {
            return 0;
        }

        String key = d + " " + remaining;

        if(cache.containsKey(key)) {
            return cache.get(key);
        }

        int totalWays = 0;

        for(int i = 1; i <= f; i++) {
            if(remaining >= i) {
                totalWays =  (totalWays + helper(d - 1, f, remaining - i, cache)) % 1000000007;
            } else {
                break;
            }

        }

        cache.put(key, totalWays);
        return totalWays;
    }
}
