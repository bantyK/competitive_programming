package solutions.easy;

import java.util.*;
import solutions.util.ArrayUtils;

// 806:  https://leetcode.com/problems/number-of-lines-to-write-string/
public class NumberOfLines {
    public static void main(String[] args) {
        NumberOfLines obj = new NumberOfLines();

        ArrayUtils.printArray(
                obj.numberOfLines(new int[]{4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10},
                        "bbbcccdddaaa"
                )
        );
    }

    public int[] numberOfLines(int[] widths, String S) {
        if (widths == null || widths.length == 0 || S == null || S.length() == 0) {
            return new int[2];
        }

        int line = 1;
        int remainingWidth = 100;

        for (int i = 0; i < S.length(); ) {
            while (remainingWidth >= widths[S.charAt(i) - 'a']) {
                remainingWidth -= widths[S.charAt(i) - 'a'];
                i++;
                if(i > S.length()-1) break;
            }

            if (i < S.length()) {
                line++;
                remainingWidth = 100;
            }
        }

        return new int[]{line, 100 - remainingWidth};
    }
}
