import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 120 https://leetcode.com/problems/triangle/
public class Triangle {
    public static void main(String[] args) throws IOException {
        Triangle obj = new Triangle();
        List<List<Integer>> dList = new TwoDArrayReader().get2DList();
        System.out.println(obj.minimumTotal(dList));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        else if (triangle.size() == 1) return triangle.get(0).get(0);
        else {
            List<List<Integer>> dp = new ArrayList<>();

            for (int i = 0; i < triangle.size(); i++) {
                dp.add(new ArrayList<>());
            }
            dp.get(0).add(triangle.get(0).get(0));
            for (int i = 1; i < triangle.size(); i++) {
                List<Integer> row = dp.get(i);
                for (int j = 0; j < i + 1; j++) {
                    int left = j - 1 >= 0 ? dp.get(i - 1).get(j - 1) : Integer.MAX_VALUE;
                    int right = j < dp.get(i - 1).size() ? dp.get(i - 1).get(j) : Integer.MAX_VALUE;

                    row.add(triangle.get(i).get(j) + Math.min(left, right));
                }
            }

            int min = Integer.MAX_VALUE;
            for (int num : dp.get(dp.size() - 1)) {
                min = Math.min(min, num);
            }
            return min;
        }
    }
}

