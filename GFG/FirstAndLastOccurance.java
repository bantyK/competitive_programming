package binarysearch;

//https://practice.geeksforgeeks.org/problems/first-and-last-occurrences-of-x/0

import java.util.*;

public class FirstAndLastOccurance {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();
        s.nextLine();

        for (int k = 0; k < t; k++) {
            String[] line = s.nextLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);

            int[] nums = new int[n];

            line = s.nextLine().split(" ");

            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(line[i]);
            }

            System.out.println(firstAndLast(nums, x));
        }
    }

    private static String firstAndLast(int[] nums, int x) {
        int first = findFirst(nums, x);
        if (first == -1) {
            return "-1";
        }
        int last = findLast(nums, x);

        return first + " " + last;
    }

    private static int findFirst(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int minIndex = -1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == x) {
                minIndex = mid;
                high = mid - 1;
            } else if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return minIndex;
    }

    private static int findLast(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int maxIndex = 0;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == x) {
                maxIndex = mid;
                low = mid + 1;
            } else if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return maxIndex;
    }
}

