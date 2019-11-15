package solutions.medium;

import solutions.util.ArrayUtils;

import java.util.*;

//912 https://leetcode.com/problems/sort-an-array/
public class SortArray {
    public static void main(String[] args) {
        SortArray obj = new SortArray();

        ArrayUtils.printArrayList(obj.sortArray(new int[]{5, 4, 3, 2, 1}));
    }

    public List<Integer> sortArray(int[] nums) {
        List<Integer> sortedArray = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> a - b);

        for(int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
        }

        while(!minHeap.isEmpty()) {
            sortedArray.add(minHeap.poll());
        }

        return sortedArray;
    }
}
