import java.util.*;

// 666 https://leetcode.com/problems/path-sum-iv/
public class PathSum4 {
    int sum = 0;
    Map<Integer, Map<Integer, Integer>> tree;
    int maxDepth = 1;

    public static void main(String[] args) {
        PathSum4 obj = new PathSum4();
        System.out.println(obj.pathSum(new int[]{113, 221}));
        System.out.println(obj.pathSum(new int[]{111, 212, 223}));
        System.out.println(obj.pathSum(new int[]{111, 212, 223, 314, 325, 341, 475}));
        System.out.println(obj.pathSum(new int[]{111, 212, 223, 314, 345, 426, 437}));
    }

    public int pathSum(int[] nums) {
        sum = 0;
        tree = new HashMap<>();
        maxDepth = 1;
        for (int num : nums) {
            int depth = num / 100;
            num = num % 100;
            int level = num / 10;
            int value = num % 10;

            tree.putIfAbsent(depth, new HashMap<>());
            tree.get(depth).put(level, value);

            maxDepth = Math.max(maxDepth, depth);
        }


        helper(1, 1, 0);
        return sum;
    }

    private void helper(int depth, int level, int currentSum) {
        if (!tree.containsKey(depth)) {
            return;
        }
        int leftLevelIndex = 2 * level - 1;
        int rightLevelIndex = 2 * level;
        if (depth == maxDepth || (!tree.get(depth + 1).containsKey(leftLevelIndex) && !tree.get(depth + 1).containsKey(rightLevelIndex))) {
            // leaf node
            sum += currentSum + tree.get(depth).get(level);
            return;
        }

        if (tree.get(depth + 1).containsKey(leftLevelIndex)) {
            helper(depth + 1, leftLevelIndex, currentSum + tree.get(depth).get(level));
        }

        if (tree.get(depth + 1).containsKey(rightLevelIndex)) {
            helper(depth + 1, rightLevelIndex, currentSum + tree.get(depth).get(level));
        }
    }

    private void helper2(int depth, int level, int currentSum) {
        if (!tree.containsKey(depth)) {
            sum += currentSum;
            return;
        }
        if (depth == maxDepth) {
            sum += currentSum * 10 + tree.get(depth).get(level);
            return;
        }
        if (tree.get(depth).containsKey(level)) {
            currentSum = (currentSum * 10) + tree.get(depth).get(level);
            helper(depth + 1, 2 * level - 1, currentSum);
            helper(depth + 1, 2 * level, currentSum);
        }
    }
}
