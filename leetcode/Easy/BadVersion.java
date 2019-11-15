package solutions.easy;

import java.util.*;

// 278: https://leetcode.com/problems/first-bad-version/
public class BadVersion {
    public static void main(String[] args) {
        BadVersion obj = new BadVersion();
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int bad = 0;
        while(left <= right) {
            int mid = left + (right-left) / 2;
            if(isBadVersion(mid)) {
                bad = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right+1;
    }

    public boolean isBadVersion(int n) {
        // this is provided by leetcode.
        // remove this function before submitting your code to leetcode

        return true;
    }
}
