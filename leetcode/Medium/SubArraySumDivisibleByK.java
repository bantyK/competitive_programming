import java.util.*;

// 974 https://leetcode.com/problems/subarray-sums-divisible-by-k/
public class SubArraySumDivisibleByK {
    public static void main(String[] args) {
        SubArraySumDivisibleByK obj = new SubArraySumDivisibleByK();

        System.out.println(obj.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
        System.out.println(obj.subarraysDivByK_2(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 1);
        int count = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int rem = sum % k;
            if (map.containsKey(rem)) {
                count += map.get(rem);
            }

            int prevCount = map.getOrDefault(rem, 0);
            map.put(rem, prevCount + 1);
        }

        return count;
    }

    public int subarraysDivByK_2(int[] nums, int k) {
        int n = nums.length;
        int[] modGroups = new int[k];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int group = (sum % k);
            if(group < 0) group += k;
            modGroups[group]++;
        }

        int total = 0;
        for (int x : modGroups) {
            if (x > 0) {
                total += (x * (x - 1)) / 2;
            }
        }
        return total + modGroups[0];
    }

}
