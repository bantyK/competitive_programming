import java.util.Arrays;

// 869 https://leetcode.com/problems/reordered-power-of-2/
public class ReorderedPowerOf2 {

    public static void main(String[] args) {
        ReorderedPowerOf2 obj = new ReorderedPowerOf2();
        System.out.println(obj.reorderedPowerOf2(16));
        System.out.println(obj.reorderedPowerOf2(32));
        System.out.println(obj.reorderedPowerOf2(128));
        System.out.println(obj.reorderedPowerOf2(46));
    }

    public boolean reorderedPowerOf2(int n) {
        int x = n & (n - 1);
        if (x == 0) {
            // n is a power of 2, no need to check anything
            return true;
        }
        char[] str = String.valueOf(n).toCharArray();
        Arrays.sort(str);
        String s1 = new String(str);

        for (int i = 0; i < 31; i++) {
            int powerOf2 = 1 << i;
            char[] str2 = String.valueOf(powerOf2).toCharArray();
            Arrays.sort(str2);

            String s2 = new String(str2);
            if (s1.equals(s2)) {
                return true;
            }
        }

        return false;

    }
}