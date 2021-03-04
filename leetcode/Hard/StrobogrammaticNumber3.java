// 248 https://leetcode.com/problems/strobogrammatic-number-iii/
public class StrobogrammaticNumber3 {

    private static final char[][] PAIRS = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumber3().strobogrammaticInRange("50", "100"));
    }

    public int strobogrammaticInRange(String low, String high) {
        if (low == null
                || high == null
                || low.length() > high.length()
                || (low.length() == high.length() && low.compareTo(high) > 0)
        ) return 0;

        int count = 0;
        for (int len = low.length(); len <= high.length(); len++) {
            char[] ch = new char[len];
            count += dfs(0, len - 1, low, high, ch);
        }
        return count;
    }

    private int dfs(int left, int right, String low, String high, char[] ch) {
        if (left > right) {
            // the number in ch is always going to be strobogrammatic
            // we just need to verify that it is under the limits
            String str = new String(ch);
            if ((ch.length == low.length() && str.compareTo(low) < 0)
                    || (ch.length == high.length() && str.compareTo(high) > 0)) {
                // number is lower than low, or higher than high
                return 0;
            }
            return 1; // number is between the valid range
        }

        int count = 0;
        for (char[] p : PAIRS) {
            ch[left] = p[0];
            ch[right] = p[1];

            if (ch.length != 1 && ch[0] == '0') {
                // if the number has more than 1 digit then that number cannot start with a zero
                // ignore these changes
                continue;
            }

            if (left == right && (p[0] == '6' || p[0] == '9')) {
                // dont put '6' and '9' at the midde of the string
                // because if we are only putting 6 or 9 at the middle than the number
                // will not read same from bottom and up. Hence wont be strobogrammatic
                continue;
            }

            count += dfs(left + 1, right - 1, low, high, ch);
        }

        return count;
    }

}