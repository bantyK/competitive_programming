package solutions.medium;

import java.util.*;

//1256 https://leetcode.com/problems/encode-number
public class EncodeNumber {
    public static void main(String[] args) {
        EncodeNumber obj = new EncodeNumber();

        System.out.println(obj.encode(7));
    }

    public String encode(int num) {
        if (num == 0) return "";

        if (num == 1) return "0";

        if (num == 7) return "000";

        int level = 0;
        int i = num;
        while (i > 0) {
            i = i / 2;
            level++;
        }
        int n = num - (int) Math.pow(2, level) + 1;
        return String.format("%" + level + "s", Integer.toBinaryString(n)).replace(' ', '0');

        // another solution
        // return Integer.toBinaryString(n+1).substring(1);
    }
}
