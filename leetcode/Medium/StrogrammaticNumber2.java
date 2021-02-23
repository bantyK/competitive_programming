import java.util.*;

// 247 https://leetcode.com/problems/strobogrammatic-number-ii/
public class StrogrammaticNumber2 {
    public static void main(String[] args) {
        StrogrammaticNumber2 obj = new StrogrammaticNumber2();
        System.out.println(obj.findStrobogrammatic(1));
        System.out.println(obj.findStrobogrammatic(2));
        System.out.println(obj.findStrobogrammatic(3));
        System.out.println(obj.findStrobogrammatic(5));
    }

    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    private List<String> helper(int n, int m) {
        // base cases when n == 1 and n == 2
        if (n == 0) return Collections.singletonList("");
        if (n == 1) return Arrays.asList("0", "1", "8");

        List<String> res = new ArrayList<>();

        List<String> temp = helper(n - 2, m);

        for (String s : temp) {
            if (n != m) res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }

}