package solutions.medium;

import java.util.*;

// https://leetcode.com/problems/kth-largest-element-in-an-array/submissions/

public class KthLargestElement {
    public static void main(String[] args) {
        KthLargestElement obj = new KthLargestElement();
        int kLargestElement = obj.kthLargest(new int[]{3, 2, 1, 4, 5}, 1);
        System.out.println(kLargestElement);
    }

    //Quick select algorithm
    public int kthLargest(int[] nums, int k) {
        int n = nums.length - 1;
        return kthLargestHelper(nums, 0, n - 1, k);

    }

    public int kthLargestHelper(int[] nums, int start, int end, int k) {
        int n = nums.length;
        if (start <= end) {
            final int partitionIndex = partition(nums, start, end);

            if (partitionIndex == n - k) {
                return nums[partitionIndex];
            } else if (partitionIndex > n - k) {
                return kthLargestHelper(nums, start, partitionIndex - 1, k);
            } else {
                return kthLargestHelper(nums, partitionIndex + 1, end, k);
            }
        }
        return -1;
    }

    public int partition(int[] nums, int left, int right) {
        int partitionIndex = left;
        int pivot = nums[right];

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(nums, partitionIndex, right);
        return partitionIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Using priority queue
    public int findKLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2 - n1);
        for (int i : nums) {
            pq.add(i);
        }

        for (int i = 0; i < k - 1; i++) {
            pq.remove();
        }

        return pq.remove();
    }

    // Naive approach (Complexity : 0(nlogn)
    public int findKthLargest(int[] nums, int k) {
        List<Integer> integerList = new ArrayList<>();
        for (int i : nums) {
            integerList.add(i);
        }

        integerList.sort(Collections.reverseOrder());
        return integerList.get(k - 1);
    }


}
