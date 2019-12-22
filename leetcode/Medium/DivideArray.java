import java.util.*;

// 1296 https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
public class DivideArray {
    public static void main(String[] args) {
        DivideArray obj = new DivideArray();
        int[] nums = new int[]{1, 2, 3, 4};
        int k = 3;

        System.out.println(obj.isPossibleDivide(nums, k));
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        minHeap.addAll(countMap.keySet());
        List<Integer> temp;
        while (!minHeap.isEmpty()) {
            temp = new ArrayList<>();
            int num1 = minHeap.remove();
            temp.add(num1);
            for (int i = 1; i < k; i++) {
                if (minHeap.isEmpty()) return false;
                int num2 = minHeap.remove();
                if (num2 - num1 != 1) return false;
                else {
                    temp.add(num2);
                    num1 = num2;
                }
            }

            for (int i : temp) {
                if (countMap.get(i) - 1 > 0) {
                    minHeap.add(i);
                    countMap.put(i, countMap.get(i) - 1);
                }
            }
        }
        return true;
    }
}
