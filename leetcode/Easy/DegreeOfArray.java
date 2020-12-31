import java.util.*;

//697 https://leetcode.com/problems/degree-of-an-array/
public class DegreeOfArray {
    public static void main(String[] args) {
        DegreeOfArray obj = new DegreeOfArray();
        System.out.println(obj.findShortestSubArray(new int[] { 1 }));
    }

    public int findShortestSubArray(int[] nums) {
        if (nums.length == 0)
            return 0;

        Map<Integer, int[]> indexMap = new HashMap<>();
        int maxDegree = 1;

        for (int i = 0; i < nums.length; i++) {
            if (!indexMap.containsKey(nums[i])) {
                indexMap.put(nums[i], new int[] { 1, i, i }); // {degree, first occurance index, last occurance index}
            } else {
                int[] temp = indexMap.get(nums[i]);
                temp[0] += 1; // increase the degree
                temp[2] = i; // change the last index, the first index will not change

                maxDegree = Math.max(maxDegree, temp[0]);
            }
        }

        int smallestLength = Integer.MAX_VALUE;

        for (int index : indexMap.keySet()) {
            int[] temp = indexMap.get(index);
            if (temp[0] == maxDegree) {
                smallestLength = Math.min(smallestLength, temp[2] - temp[1] + 1);
            }
        }
        return smallestLength;
    }
}