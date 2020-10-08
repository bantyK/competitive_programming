import java.util.*;

//1124 https://leetcode.com/problems/longest-well-performing-interval/
public class LongestWellPerformingInterval {
    public static void main(String[] args) {
        LongestWellPerformingInterval obj = new LongestWellPerformingInterval();
//        System.out.println(obj.longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9}));
//        System.out.println(obj.longestWPI(new int[]{9, 9, 6, 9, 6, 6, 9}));
        System.out.println(obj.longestWPIMostOptimised(new int[]{8, 10, 6, 16, 5}));
    }

    // Brute Force: O(n^3)
    public int longestWPIBrute(int[] hours) {
        int maxLen = 0;
        int len = hours.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int tiring = 0;
                int nontiring = 0;
                for (int k = i; k <= j; k++) {
                    if (hours[k] > 8) {
                        tiring++;
                    } else {
                        nontiring++;
                    }
                }
                if (tiring > nontiring) {
                    int length = j - i + 1;
                    maxLen = Math.max(maxLen, length);
                }
            }
        }
        return maxLen;
    }

    // Prefix Array
    // Using prefix sum to compute the sum.
    public int longestWPI(int[] hours) {
        int len = hours.length;
        int[] prefixTiringDay = new int[len + 1];
        int[] prefixNonTiringDay = new int[len + 1];
        int maxLen = 0;

        for (int i = 1; i <= len; i++) {
            if (hours[i - 1] > 8) {
                prefixTiringDay[i] = prefixTiringDay[i - 1] + 1;
                prefixNonTiringDay[i] = prefixNonTiringDay[i - 1];
            } else {
                prefixTiringDay[i] = prefixTiringDay[i - 1];
                prefixNonTiringDay[i] = prefixNonTiringDay[i - 1] + 1;
            }
        }

        System.out.println(Arrays.toString(hours));
        System.out.println(Arrays.toString(prefixTiringDay));
        System.out.println(Arrays.toString(prefixNonTiringDay));

        for (int i = 1; i <= len; i++) {
            for (int j = i; j <= len; j++) {
                //Current subarray hours[i:j]
                // calculating the number of tiring days and non tiring days for all subarrays between i and j.
                int numberOfTiringDaysForCurrentSubarray = prefixTiringDay[j] - prefixTiringDay[i - 1];
                int numberOfNonTiringDaysForCurrentSubarray = prefixNonTiringDay[j] - prefixNonTiringDay[i - 1];

                if (numberOfTiringDaysForCurrentSubarray - numberOfNonTiringDaysForCurrentSubarray > 0) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    // Optimised solution space optimised. Because we only want the difference, we will only store that
    public int longestWPISpaceOptimised(int[] hours) {
        int len = hours.length;
        int[] difference = new int[len + 1];
        int maxLen = 0;
        int tiring = 0;
        int nonTiring = 0;

        for (int i = 1; i <= len; i++) {
            if (hours[i - 1] > 8) {
                tiring += 1;
            } else {
                nonTiring += 1;
            }
            difference[i] = tiring - nonTiring;
        }

        System.out.println(Arrays.toString(hours));

        for (int i = 1; i <= len; i++) {
            for (int j = i; j <= len; j++) {
                //Current subarray hours[i:j]
                // calculating the number of tiring days and non tiring days for all subarrays between i and j.
                int differenceBetweenTiringAndNonTiring = difference[j] - difference[i - 1];
                if (differenceBetweenTiringAndNonTiring > 0) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    public int longestWPIMostOptimised(int[] hours) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int maxLen = 0;
        int prefixSum = 0;

        for (int i = 0; i < hours.length; i++) {
            prefixSum += (hours[i] > 8) ? 1 : -1;

            if (prefixSum > 0) {
                maxLen = i + 1;
            } else if (indexMap.containsKey(prefixSum - 1)) {
                maxLen = Math.max(maxLen, i - indexMap.get(prefixSum - 1));
            }

            if (!indexMap.containsKey(prefixSum)) {
                indexMap.put(prefixSum, i);
            }
        }
        return maxLen;
    }
}
