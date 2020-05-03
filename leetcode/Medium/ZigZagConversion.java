import java.util.*;

// 6 https://leetcode.com/problems/zigzag-conversion/
public class ZigZagConversion {
    public static void main(String[] args) {
        ZigZagConversion obj = new ZigZagConversion();
        System.out.println(obj.convert("PAYPALISHIRING", 5).equals("PHASIYIRPLIGAN"));
        System.out.println(obj.convert("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI"));
        System.out.println(obj.convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
        System.out.println(obj.convert("PAYPALISHIRING", 2).equals("PYAIHRNAPLSIIG"));
        System.out.println(obj.convert("PAYPALISHIRING", 1).equals("PAYPALISHIRING"));
    }

    public String convert(String s, int numRows) {
        List<char[]> rows = new ArrayList<>();
        int sIndex = 0;

        while (sIndex < s.length()) {
            if (sIndex + numRows < s.length()) {
                rows.add(s.substring(sIndex, sIndex + numRows).toCharArray());
            } else {
                char[] cArr = new char[numRows];
                int i = 0;
                while (sIndex < s.length()) {
                    cArr[i++] = s.charAt(sIndex++);
                }
                rows.add(cArr);
                break;
            }

            sIndex += numRows;

            int x = numRows - 2;
            while (x > 0 && sIndex < s.length()) {
                char[] cArr = new char[numRows];
                cArr[x--] = s.charAt(sIndex++);
                rows.add(cArr);
            }
        }


        StringBuilder builder = new StringBuilder();
        for (int col = 0; col < numRows; col++) {
            for (char[] chars : rows) {
                final char ch = chars[col];
                if (ch != '\0') {
                    builder.append(ch);
                }
            }
        }

        return builder.toString();
    }
}
