// 443 https://leetcode.com/problems/string-compression/
public class StringCompression {
    public static void main(String[] args) {
        StringCompression obj = new StringCompression();
        final char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int len = obj.compress(chars);

        for (int i = 0; i < len; i++) {
            System.out.print(chars[i]);
        }
    }

    public int compress(char[] chars) {
        int i = 0;
        int next = 0;
        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == currentChar) {
                count++;
                i++;
            }

            chars[next++] = currentChar;
            if (count > 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[next++] = c;
                }
            }
        }

        return next;
    }
}
