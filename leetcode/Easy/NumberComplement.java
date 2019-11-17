package solutions.easy;

import java.util.*;

// 476 https://leetcode.com/problems/number-complement/
public class NumberComplement {
    public static void main(String[] args) {
        NumberComplement obj = new NumberComplement();
        System.out.println(obj.findComplement(1));
    }

    public int findComplement(int num) {
        String s = Integer.toBinaryString(num);
        char[] complement = new char[s.length()];
        int i = 0;
        for(char c : s.toCharArray()) {
            if(c == '1') {
                complement[i++] = '0';
            } else {
                complement[i++] = '1';
            }
        }

        return Integer.parseInt(new String(complement), 2);
    }
}
