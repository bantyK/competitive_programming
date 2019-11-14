package solutions.easy;

import java.util.*;
import solutions.util.*;

// 88. https://leetcode.com/problems/merge-sorted-array/
public class MergeArray {
    public static void main(String[] args) {
        MergeArray obj = new MergeArray();
        obj.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < m && j < n) {
            if(nums1[i] < nums2[j]) {
                temp[k++] = nums1[i++];
            } else {
                temp[k++] = nums2[j++];
            }
        }

        while(i < m) {
            temp[k++] = nums1[i++];
        }

        while(j < n) {
            temp[k++] = nums2[j++];
        }


        for(int x = 0; x < m + n; x++) {
            nums1[x] = temp[x];
        }

        ArrayUtils.printArray(nums1);
    }
}
