import java.util.*;

public class SubarraysWithExactlyKDistinctNumbers {
    public static void main(String[] args) {
        SubarraysWithExactlyKDistinctNumbers obj = new SubarraysWithExactlyKDistinctNumbers();
        System.out.println(obj.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        int left = 0;

        // this map will store the count of each number inside the window at any given time
        Map<Integer, Integer> countMap = new HashMap<>();

        int prefix = 0;
        int count = 0;
        int distinctCount = 0;

        for (int right = 0; right < A.length; right++) {
            int rightNum = A[right];

            if (!countMap.containsKey(rightNum) || countMap.get(rightNum) == 0) {
                // either we have not seen this number, or it is not present in the current window that we are looking at
                ++distinctCount;
            }

            // take a note of the number that we have encountered, increase its count in map
            countMap.put(rightNum, countMap.getOrDefault(rightNum, 0) + 1);

            // As soon as the number of distinct numbers in our map becomes 1 more that K
            // we will remove the element at left from the window, reset the prefix because this window cannot form a subarray
            // from the left hand side elements

            //our window will never contain the duplicates, the duplicates are actually taken care by prefix counter.
            // Hence we will only have to decrease 1 and reset the prefix
            if (distinctCount > K) {
                int leftNum = A[left++];
                prefix = 0;
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                --distinctCount;
            }

            // check if the number which is at the left pointer has any duplicate inside the window,
            // if there is a duplicate, then we increase our prefix value and move the left pointer to the right
            while (countMap.get(A[left]) > 1) {
                int leftNum = A[left++];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                ++prefix;
            }

            // we have found K distinct numbers, update the count variable
            if (distinctCount == K) {
                count += 1 + prefix;
            }
        }

        // right pointer has exceeded the length of array, return the count value.
        return count;
    }
}
