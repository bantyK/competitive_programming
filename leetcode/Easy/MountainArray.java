package com.company.leet;

public class MountainArray {

    public static void main(String[] args) {
        MountainArray obj = new MountainArray();
        System.out.println(obj.peakIndexInMountainArray(new int[]{0, 1, 2, 3, 4, 5, 1, 0}));
    }

    public int peakIndexInMountainArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] > A[i + 1]) {
                return i;
            }
        }

        return A.length - 1;
    }
}
