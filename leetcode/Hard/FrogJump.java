import java.util.*;

// 403 https://leetcode.com/problems/frog-jump/
public class FrogJump {
    public static void main(String[] args) {
        FrogJump obj = new FrogJump();
        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(
                obj.canCross(stones)
        );
    }

    public boolean canCross(int[] stones) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
        }

        // cache for storing subproblem result
        Map<String, Boolean> dp = new HashMap<>();
        return canCross(stones, map, dp, 1, 1);
    }

    private boolean canCross(int[] stones, Map<Integer, Integer> map, Map<String, Boolean> dp, int position, int jump) {
        if (!map.containsKey(position)) return false;

        if (map.get(position) == stones.length - 1) {
            return true;
        }

        // If the subproblem is already solved, return the result from the cache. No further calculations required.
        final String key = position + "" + jump;
        if (dp.containsKey(key)) return dp.get(key);

        // option 1
        int newJump = jump + 1;
        int newPosition = position + newJump;
        if (canCross(stones, map, dp, newPosition, newJump)) {
            dp.put(key, true); // store the intermediate results.
            return true;
        }

        // we are taking the same number of jumps as before, so no need to update the jumps variable, just change the position
        newPosition = position + jump;
        if (canCross(stones, map, dp, newPosition, jump)) {
            dp.put(key, true); // store the intermediate results.
            return true;
        }

        newJump = jump - 1;
        if (newJump > 0) {
            newPosition = position + newJump;
            if (canCross(stones, map, dp, newPosition, newJump)) {
                dp.put(key, true); // store the intermediate results.
                return true;
            }
        }

        dp.put(key, false); // store the intermediate results.
        return false;
    }

}