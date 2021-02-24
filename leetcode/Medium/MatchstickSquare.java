import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 473 https://leetcode.com/problems/matchsticks-to-square/
public class MatchstickSquare {

    int maxSideLen;
    int[] nums;

    public static void main(String[] args) {
        MatchstickSquare obj = new MatchstickSquare();
        // System.out.println(obj.makesquare(new int[]{1, 1, 1, 1, 3, 1, 2, 2, 2, 2}));
        // System.out.println(obj.makesquare(new int[]{5, 6, 3, 2, 9, 20, 15, 11, 17,
        // 17, 3, 1, 2, 11, 16}));
        // System.out.println(obj.makesquare(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5,
        // 4, 3, 2, 1}));
        System.out.println(obj.makesquare(new int[] { 3, 3, 3, 3, 4 }));
        // System.out.println(obj.makesquare(new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3,
        // 3}));
    }

    public boolean makesquare(int[] nums) {
        int sum = 0;
        this.nums = nums;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }

        if (nums.length < 4 || sum % 4 != 0 || max > sum / 4)
            return false;

        maxSideLen = sum / 4;

        return helper(new int[4], 0);
    }

    private boolean helper(int[] edges, int numsIndex) {
        if (numsIndex == nums.length) {
            return true;
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            if (seen.contains(edges[i])) {
                // We have seen this pattern(values of sides) before, we haven't got any answer
                // before
                // otherwise we would have returned earlier.
                // Hence we wont go into this path again

                // Ex: Consider the values of sides: [1,2,0,3] and we added 1 to index = 2
                // Now consider the values of sides: [1,2,3,0] and we are adding 1 to index = 3
                // example2 is same as example1, so we wont consider example 2
                continue;
            }

            if (edges[i] + nums[numsIndex] > maxSideLen) {
                continue;
            }

            seen.add(edges[i]);
            edges[i] += nums[numsIndex];
            if (helper(edges, numsIndex + 1)) {
                return true;
            }
            edges[i] -= nums[numsIndex];
        }

        return false;
    }

    // Simple DFS
    public boolean makesquare2(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }

        Arrays.sort(nums);

        if (nums.length < 4 || sum % 4 != 0 || max > sum / 4)
            return false;

        return dfs(nums, new int[4], nums.length - 1, sum / 4); // checking the array from reverse, this avoid the need
                                                                // to reverse the array
    }

    private boolean dfs(int[] nums, int[] sides, int index, int maxLen) {
        if (index < 0) {
            if (sides[0] == maxLen && sides[1] == maxLen && sides[2] == maxLen && sides[3] == maxLen) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (sides[i] + nums[index] > maxLen)
                continue;
            sides[i] += nums[index];
            if (dfs(nums, sides, index - 1, maxLen)) {
                return true;
            }
            sides[i] -= nums[index];
        }

        return false;
    }

}