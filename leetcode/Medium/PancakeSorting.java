package solutions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/pancake-sorting/
// https://www.youtube.com/watch?v=RB9hlDDWQY0
public class PancakeSorting {
    public static void main(String[] args) {
        PancakeSorting obj = new PancakeSorting();
        int[] A = {3, 2, 4, 1};
        List<Integer> integers = obj.pancakeSort(A);
        integers.forEach(a -> System.out.print(a + " "));
        System.out.println();
        for (int a : A) {
            System.out.print(a + " ");
        }
    }

    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new ArrayList<>();
        int endIdx = A.length - 1;
        int i = 0;
        while (i < A.length) {
            int maxIdx = maxIndex(A, endIdx);
            flip(A, maxIdx);
            result.add(maxIdx+1);
            flip(A, endIdx);
            result.add(endIdx+1);
            endIdx--;
            i++;
        }

        return result;
    }

    private void flip(int[] arr, int i) {

        int right = i;
        int left = 0;
        int temp;

        while (left < right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    private int maxIndex(int[] arr, int endIdx) {
        int maxIdx = 0;
        for (int i = 0; i <= endIdx; i++) {
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
