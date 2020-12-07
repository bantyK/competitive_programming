import java.util.*;

// 1679 https://leetcode.com/problems/max-number-of-k-sum-pairs/
public class MaxKSumPairs {

    public static void main(String[] args) {
        MaxKSumPairs obj = new MaxKSumPairs();
        System.out.println(obj.maxOperations(new int[]{1, 2, 3, 4}, 5) == 2);
        System.out.println(obj.maxOperations(new int[]{3, 1, 3, 2, 1, 2}, 5) == 2);
        System.out.println(obj.maxOperations(new int[]{3, 1, 3, 2, 1, 2}, 6) == 1);
        System.out.println(obj.maxOperations(new int[]{3, 1, 5, 1, 1, 1, 1, 1, 2, 2, 3, 2, 2}, 1) == 0);
    }

    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>(); // duplicate numbers are possible so we have to use map of num : count
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > k) continue;
            int diff = Math.abs(nums[i] - k); // if the current num - k exists in the freqMap, then we found a pair
            if (freqMap.containsKey(diff)) {
                count++;
                freqMap.put(diff, freqMap.get(diff) - 1);
                if (freqMap.get(diff) == 0) {
                    freqMap.remove(diff);
                }
            } else {
                freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
            }
        }
        return count;
    }

    // Approach 2 using sorting
    public int maxOperations2(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int count = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                count++;
                left++;
                right--;
            } else if (sum > k) {
                right--;
            } else {
                left++;
            }
        }

        return count;
    }
}