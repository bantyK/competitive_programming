package com.company.leet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RemoveDuplicates {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("removedups.in"));
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 6};
        int index = removeDuplicates.removeDuplicates(nums);

        for (int i = 0; i < index; i++) {
            System.out.print(nums[i]);
        }
    }

    public int removeDuplicates(int[] nums) {
        int endIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[endIndex]) {
                endIndex++;
                nums[endIndex] = nums[i];
            }
        }
        return endIndex + 1;
    }
}
