import java.util.*;

// 1354 https://leetcode.com/problems/construct-target-array-with-multiple-sums/
public class TargetArrayFromMultipleSums {
    public static void main(String[] args) {
        TargetArrayFromMultipleSums obj = new TargetArrayFromMultipleSums();
        System.out.println(obj.isPossible(new int[]{8, 5}));
        System.out.println(obj.isPossible(new int[]{1, 1, 1, 2}));
        System.out.println(obj.isPossible(new int[]{2, 2}));
        System.out.println(obj.isPossible(new int[]{3, 2}));
        System.out.println(obj.isPossible(new int[]{9, 3, 5}));
        System.out.println(obj.isPossible(new int[]{1, 2, 3, 4}));
    }

    public boolean isPossible(int[] target) {
        long sum = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int num : target) {
            sum += num;
            maxHeap.offer(num);
        }

        while (maxHeap.peek() != 1) {
            int maxVal = maxHeap.poll();
            if (maxVal == 1) {
                // all the values are 1 so we have reached the initial state. possible to make the target array
                return true;
            }

            // difference between total sum and largest element in the array
            long diff = sum - maxVal; // sum of all the remaining elements of the array (except the max element)


            if (diff == 1) return true;
            /*
             * 1. If diff > maxValue -> This means that if we add all the numbers in the array than the sum will become
             *                          greater than the value required in the array. Consider target = [1, 2, 2].
             *                          It is not possible to make this target starting from [1,1,1] because the sum will
             *                          become greater than max value
             *
             * 2. If Diff == 0       -> If this is the case, then we will keep forming the same array again and again
             *                          Consider that target = [2]. We will start with [1]. The sum of all elements is 1 and
             *                          the highest element is also 1. We will keep updating 1 by 1 and never reach to 2. Hence
             *                          this case is also not possible
             *
             * 3. maxVal % diff == 0 -> This case will occur if there are 2 max element. When we subtract one from another, the
             *                          result will become 0, not 1. Ex: [20, 5] -> [15,5] -> [10,5] -> [5, 5]-> [0, 1]
             *
             */
            if (diff > maxVal || diff == 0 || maxVal % diff == 0) return false;

            maxVal %= diff; // avoid repetitive subtraction to speed up the algo

            /*
              sum = sum (except the max val) + new value of max val
             */
            sum = diff + maxVal;

            maxHeap.offer(maxVal);
        }

        return true;
    }

    public boolean isPossible2(int[] target) {
        while (true) {
            int largestIdx = largestNum(target);
            if (target[largestIdx] == 1) return true;
            int sum = sum(target) - target[largestIdx];
            target[largestIdx] -= sum;
            if (target[largestIdx] < 1) return false;
        }
    }

    private int sum(int[] nums) {
        return Arrays.stream(nums).sum();
    }

    private int largestNum(int[] nums) {
        int max = 0;
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                idx = i;
                max = nums[i];
            }
        }
        return idx;
    }
}