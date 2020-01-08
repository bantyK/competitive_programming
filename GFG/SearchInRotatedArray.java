
// https://practice.geeksforgeeks.org/problems/search-in-a-rotated-array/0
import java.util.*;
import java.lang.*;
import java.io.*;

class SearchInRotatedArray {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        s.nextLine();

        for(int k = 0; k < t; k++) {
            int n = s.nextInt();
            s.nextLine();

            int[] nums = new int[n];
            String[] line = s.nextLine().split(" ");
            for(int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(line[i]);
            }

            int target = s.nextInt();

            System.out.println(search(nums, target));

        }
    }

    private static int search(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(arr[mid] == target) {
                return mid;
            } else if(arr[low] <= arr[mid]) {
                if(target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if(target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}