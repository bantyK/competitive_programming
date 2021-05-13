import java.util.ArrayList;
import java.util.List;

// 816 https://leetcode.com/problems/ambiguous-coordinates/
public class AmbiguousCoordinates {
    public static void main(String[] args) {
        AmbiguousCoordinates obj = new AmbiguousCoordinates();
//        System.out.println(obj.ambiguousCoordinates("(1000)"));
//        System.out.println(obj.ambiguousCoordinates("(1120)"));
        System.out.println(obj.ambiguousCoordinates("(0010)"));
    }

    public List<String> ambiguousCoordinates(String s) {
        s = s.substring(1, s.length() - 1);
        List<String> res = new ArrayList<>();
        // first create substrings by dividing s into 2 parts
        for (int i = 1; i < s.length(); i++) {
            String first = s.substring(0, i);
            String second = s.substring(i);

            List<String> decimalAddedToFirst = helper(first);
            List<String> decimalAddedToSecond = helper(second);

            for (String s1 : decimalAddedToFirst) {
                for (String s2 : decimalAddedToSecond) {
                    res.add(format(s1, s2));
                }
            }

        }
        return res;
    }

    private String format(String a, String b) {
        return "(" + a + ", " + b + ")";
    }

    // return a list of string by adding '.' between the digits
    private List<String> helper(String str) {
        int len = str.length();
        List<String> res = new ArrayList<>();


        char[] chr = str.toCharArray();

        if (chr[0] == '0' && chr[len - 1] == '0') {
            if (len == 1) res.add("0");
            return res;
        }

        if (chr[0] == '0') {
            res.add("0." + str.substring(1));
            return res;
        }

        if (chr[len - 1] == '0') {
            res.add(str);
            return res;
        }

        res.add(str);
        for (int i = 1; i < str.length(); i++) {
            res.add(str.substring(0, i) + "." + str.substring(i));
        }
        return res;

    }
}