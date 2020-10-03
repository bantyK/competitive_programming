import java.util.*;

// 523 https://leetcode.com/problems/k-diff-pairs-in-an-array/
public class FindPairs {

    public int findPairsOnePass(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                if (k == 0 && countMap.get(num) == 1) {
                    // handles sum equals 0 and unique number
                    res += 1;
                }
                countMap.put(num, countMap.get(num) + 1);
            } else {
                if (countMap.containsKey(num - k)) {
                    res += 1;
                }
                if (countMap.containsKey(num + k)) {
                    res += 1;
                }
                countMap.put(num, 1);
            }
        }

        return res;
    }

    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int res = 0;

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) {
                    res++;
                }
            } else {
                if (countMap.containsKey(entry.getKey() + k)) {
                    res++;
                }
            }
        }

        return res;
    }


    public int findPairsSlower(int[] nums, int k) {
        Arrays.sort(nums);

        int res = 0;
        if (k == 0) {
            for (int i = 0; i < nums.length - 1; i++) {
                int val = nums[i];
                if (nums[i] == nums[i + 1]) {
                    res++;
                }
                while (i < nums.length && nums[i] == val) {
                    i++;
                }
                i--;
            }
        } else {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) set.add(num);

            for (int num : set) {
                if (set.contains(num + k)) {
                    res += 1;
                }
            }
        }
        return res;
    }
}
