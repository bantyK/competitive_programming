package com.company.leet;

import java.util.Arrays;

public class DIStringMatch {

    public static void main(String[] args) {
        DIStringMatch obj = new DIStringMatch();
        System.out.println(Arrays.toString(obj.diStringMatch("DDIDID")));
    }

    public int[] diStringMatch(String S) {
        int[] result = new int[S.length() + 1];
        int lowest = 0;
        int highest = S.length();
        int index = 0;
        char[] chars = S.toCharArray();
        for (char c : chars) {
            switch (c) {
                case 'I':
                    result[index++] = lowest++;
                    break;
                case 'D':
                    result[index++] = highest--;
            }
        }

        if (chars[chars.length-1] == 'I') {
            result[index] = lowest;
        } else {
            result[index] = highest;
        }
        return result;
    }
}
