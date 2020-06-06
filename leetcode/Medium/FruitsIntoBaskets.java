import java.util.*;

// 904 https://leetcode.com/problems/fruit-into-baskets/

public class FruitsIntoBaskets {
    public static void main(String[] args) {
        FruitsIntoBaskets obj = new FruitsIntoBaskets();
        System.out.println(obj.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
    }



    // Need to find max length subarray atmost 2 distinct elements
    public int totalFruit(int[] tree) {
        int left = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        int res = 0;

        for (int right = 0; right < tree.length; right++) {
            int rightNum = tree[right];
            countMap.put(rightNum, countMap.getOrDefault(rightNum, 0) + 1);


            while (countMap.size() > 2) {
                int leftNum = tree[left++];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                if (countMap.get(leftNum) == 0) {
                    countMap.remove(leftNum);
                }
            }

            if (countMap.size() == 2) {
                res = Math.max(res, right - left + 1);
            }
        }

        return res;
    }
}
