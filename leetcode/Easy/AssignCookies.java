package solutions.easy;

import java.util.Arrays;

// https://leetcode.com/problems/assign-cookies/
public class AssignCookies {
    public static void main(String[] args) {
        AssignCookies obj = new AssignCookies();
        int[] g = new int[]{10, 4, 8, 3, 9, 13};
        int[] s = new int[]{1, 5, 8, 2, 9, 13, 16};

        System.out.println(obj.findContentChildren(g, s));
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int greedIndex = 0;
        int sizeIndex = 0;

        int result = 0;
        while (greedIndex < g.length && sizeIndex < s.length) {
            if (s[sizeIndex] >= g[greedIndex]) {
                ++result;
                sizeIndex += 1;
                greedIndex += 1;
            } else {
                sizeIndex += 1;
            }
        }

        return result;
    }
}
