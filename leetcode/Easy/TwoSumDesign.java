import java.util.*;

// 170 https://leetcode.com/problems/two-sum-iii-data-structure-design/
// This has linear time complexity for find and constant time complexity for add
// Another submission is made with the opposite of this complexity here: https://leetcode.com/submissions/detail/432158056/ (its TLE though)
public class TwoSumDesign {

    public static void main(String[] args) {
        TwoSum obj = new TwoSum();
        obj.add(0);

        System.out.println(obj.find(0));

    }

    static class TwoSum {

        Map<Integer, Integer> map;
        int index;

        public TwoSum() {
            index = 0;
            map = new HashMap<>();
        }

        public void add(int number) {
            if (map.containsKey(number)) {
                map.put(number, 2);
            } else {
                map.put(number, 1);
            }
        }

        public boolean find(int value) {
            for (int key : map.keySet()) {
                int diff = value - key;
                if (map.containsKey(diff)) {
                    if (key != diff) return true;
                    if (map.get(diff) == 2) return true;
                }
            }
            return false;
        }
    }

}