// 1365 https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/

public class NumsSmallerThanSelf {
	public int[] smallerNumbersThanCurrent(int[] nums) {
	        int n = nums.length;
	        if (n == 0) return new int[0];
	        Map<Integer, Integer> indexMap = new HashMap<>();
	        int[] copy = new int[n];

	        for (int i = 0; i < n; i++) {
	            copy[i] = nums[i];
	        }
	        Arrays.sort(copy);

	        int[] dp = new int[n];
	        dp[0] = 0;
	        for (int i = 1; i < n; i++) {
	            if (copy[i] > copy[i - 1]) {
	                dp[i] = i;
	            } else {
	                dp[i] = dp[i - 1];
	            }
	        }

	        for (int i = 0; i < n; i++) {
	            indexMap.put(copy[i], dp[i]);
	        }

	        int[] res = new int[n];
	        for (int i = 0; i < n; i++) {
	            int num = nums[i];
	            res[i] = indexMap.get(num);
	        }
	        return res;
	    }
}