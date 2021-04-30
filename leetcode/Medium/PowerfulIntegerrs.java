import java.util.*;

// 970 https://leetcode.com/problems/powerful-integers/
public class PowerfulIntegerrs {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> res = new HashSet<>();
        int a = 1;
        while (a < bound) {
            int b = 1;
            while (b < bound) {
                if (a + b <= bound) {
                    res.add(a + b);
                }
                b *= y;
                if (y == 1) break;
            }
            a *= x;
            if (x == 1) break;
        }

        return new ArrayList<>(res);
    }
}