import java.util.*;

// 1625 https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/
public class LexicographicSmallestString {

    public static void main(String[] args) {
        LexicographicSmallestString obj = new LexicographicSmallestString();
        System.out.println(obj.findLexSmallestString("43987654", 7, 3));
        String str = "123456789";
        int n = str.length();
        int b = 3;
        System.out.println(str.substring(b) + str.substring(0, b));
    }

    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(s);
        set.add(s);
        String smallest = s;
        int n = s.length();

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (curr.compareTo(smallest) < 0) {
                smallest = curr;
            }

            String rotated = curr.substring(b) + curr.substring(0, b);
            if (set.add(rotated)) {
                queue.offer(rotated);
            }

            char[] chars = curr.toCharArray();
            for (int i = 1; i < n; i += 2) {
                int val = chars[i] - '0';
                val = (val + a) % 10;
                chars[i] = (char) (val + '0');
            }
            String str = new String(chars);

            if (set.add(str)) {
                queue.offer(str);
            }
        }

        return smallest;
    }

    public String findLexSmallestString2(String s, int a, int b) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(s);
        set.add(s);
        String smallest = s;

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (curr.compareTo(smallest) < 0) {
                smallest = curr;
            }

            String rotated = rotateRight(curr, b);
            if (!set.contains(rotated)) {
                queue.offer(rotated);
                set.add(rotated);
            }

            char[] chars = new char[s.length()];
            for (int i = 0; i < curr.length(); i++) {
                if (i % 2 == 0) {
                    chars[i] = curr.charAt(i);
                } else {
                    int val = curr.charAt(i) - '0';
                    val = (val + a) % 10;
                    chars[i] = (char) (val + '0');
                }
            }
            String str = new String(chars);
            if (!set.contains(str)) {
                queue.offer(str);
                set.add(str);
            }
        }

        return smallest;
    }

    private String rotateRight(String s, int rotation) {
        char[] temp = new char[rotation];
        for (int i = 0; i < rotation; i++) {
            temp[i] = s.charAt(i);
        }
        char[] chars = new char[s.length()];

        int i;
        for (i = rotation; i < s.length(); i++) {
            chars[i - rotation] = s.charAt(i);
        }

        i -= rotation;
        int j = 0;
        while (j < rotation) {
            chars[i + j] = temp[j];
            j++;
        }

        return new String(chars);
    }
}
