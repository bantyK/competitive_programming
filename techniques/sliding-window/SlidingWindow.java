import java.util.*;

// Watch this video first : https://www.youtube.com/watch?v=MK-NZ4hN7rs
// Demonstrates the sliding window technique by examples.

public class SlidingWindow {
	public static void main(String[] args) {
		int[] nums = new int[]{4,2,1,7,8,1,2,8,1,0};
		int k = 3;
		System.out.println(maxSumSubArray(nums, k));
		System.out.println(minimumSubArray(nums, 8));
		System.out.println(longestSubstringWithKDistinctChar("ABBCCCDE", 1));
	}

	// Fixed size window
	private static int maxSumSubArray(int[] nums, int k) {
		if(nums == null || nums.length == 0) return 0;

		int maxSum = Integer.MIN_VALUE;
		int runningSum = 0;
		
		for(int i = 0; i < nums.length; i++) {
			runningSum += nums[i];

			if(i >= k - 1) {
				maxSum = Math.max(maxSum, runningSum);
				runningSum -= nums[i - k + 1];
			}
		}

		return maxSum;
	}

	// Dynamic size window 
	private static int minimumSubArray(int[] nums, int target) {
		if(nums == null || nums.length == 0) {
			return 0;
		}

		int windowStart = 0;
		
		// sum of the current window
		int currentRunningSum = 0;
		
		// minimum size of the window we found so far
		int minWindowSize = nums.length + 1;
		
		for(int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
			currentRunningSum += nums[windowEnd];
			
			if(currentRunningSum >= target) {
				while(currentRunningSum >= target) {
					minWindowSize = Math.min(minWindowSize, windowEnd - windowStart + 1);
					currentRunningSum -= nums[windowStart];
					windowStart++;	
				}
			}
		}
		return minWindowSize;
	}

	// Dynamic size with auxiliary memory
	private static int longestSubstringWithKDistinctChar(String s, int k) {
		Map<Character, Integer> charFrequencyMap = new HashMap<>();	

		int windowStart = 0;
		
		int maxSubstringLength = 0;

		for(int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
			char endChar = s.charAt(windowEnd);
			charFrequencyMap.put(endChar, charFrequencyMap.getOrDefault(endChar, 0) + 1);
			
			while(charFrequencyMap.size() > k) {
				char startChar = s.charAt(windowStart);
				
				charFrequencyMap.put(startChar, charFrequencyMap.get(startChar) - 1);

				if(charFrequencyMap.get(startChar) == 0) {
					charFrequencyMap.remove(startChar);
				}

				windowStart++;
			}

			maxSubstringLength = Math.max(maxSubstringLength, windowEnd - windowStart + 1);	
		}
		return maxSubstringLength;
	}
}



























