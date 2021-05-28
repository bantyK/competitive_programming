import java.util.*;
import java.util.stream.Collectors;

// 89 https://leetcode.com/problems/gray-code/
public class GrayCode {

    public static void main(String[] args) {
        GrayCode obj = new GrayCode();
        System.out.println(obj.grayCode(2));
        System.out.println(obj.grayCode(3));
    }

    public List<Integer> grayCode(int n) {
        List<String> s = helper(n);

        return s.stream().map(ss -> Integer.parseInt(ss, 2)).collect(Collectors.toList());
    }

    private List<String> helper(int n) {
        if (n == 1) {
            return Arrays.asList("0", "1");
        }

        // Get the gray code of the remaining bits (n - 1)
        List<String> remaining = helper(n - 1);

        List<String> res = new ArrayList<>();

        // the nth bit can either be 0 or 1
        // so add 0, 1 against all the bits
        // reverse the array before adding 1
        for (String s : remaining) {
            res.add("0" + s);
        }

        // add the array in reversed order
        for (int i = remaining.size() - 1; i >= 0; i--) {
            res.add("1" + remaining.get(i)); // reverse of second part
        }

        return res;
    }
}