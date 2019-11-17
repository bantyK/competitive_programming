package solutions.medium;

import java.util.*;

//540 : https://leetcode.com/problems/single-element-in-a-sorted-array/

public class SingleElementSortedArray {
    public static void main(String[] args) {
        SingleElementSortedArray obj = new SingleElementSortedArray();
        System.out.println(
                obj.singleNonDuplicate(new int[]{1,2,2})
        );
    }

    public int singleNonDuplicate(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (left == right) {
                return arr[left];
            }

            if (mid % 2 == 0) {
                if (mid > 0 && arr[mid] == arr[mid - 1]) {
                    right = mid - 1;
                } else if (arr[mid] == arr[mid + 1]) {
                    left = mid + 1;
                } else {
                    return arr[mid];
                }
            } else {
                if (mid < arr.length-1 && arr[mid] == arr[mid + 1]) {
                    right = mid - 1;
                } else if (arr[mid] == arr[mid - 1]) {
                    left = mid + 1;
                } else {
                    return arr[mid];
                }
            }
        }

        return -1;
    }
}
