package sliding;

// 1052 https://leetcode.com/problems/grumpy-bookstore-owner/
public class GrumpyBookstore {
    public static void main(String[] args) {
        GrumpyBookstore obj = new GrumpyBookstore();
        int max = 0;
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int x = 3;
        max = obj.maxSatisfied(customers, grumpy, x);
        System.out.println(max);

        customers = new int[]{4, 10, 10};
        grumpy = new int[]{1, 1, 0};
        x = 3;
        max = obj.maxSatisfied(customers, grumpy, x);
        System.out.println(max);


        customers = new int[]{6, 10, 2, 1, 7, 9};
        grumpy = new int[]{1, 0, 0, 0, 0, 1};
        x = 3;
        max = obj.maxSatisfied(customers, grumpy, x);
        System.out.println(max);
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int x) {
        int n = customers.length;

        int initialSatisfactionSum = 0;

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                initialSatisfactionSum += customers[i];
            }
        }

        int currentSatisfactionSum = initialSatisfactionSum;

        int start = 0;
        int end = 0;

        // window creation
        while (end < x) {
            if (grumpy[end] == 1) {
                currentSatisfactionSum += customers[end];
            }

            end++;
        }
        end--;
        int maxSatisfaction = currentSatisfactionSum; // 26

        while (end < n - 1) {
            if (grumpy[start] == 1) {
                currentSatisfactionSum -= customers[start];
            }
            start++;
            end++;
            if (grumpy[end] == 1) {
                currentSatisfactionSum += customers[end];
            }

            maxSatisfaction = Math.max(maxSatisfaction, currentSatisfactionSum);

        }

        return maxSatisfaction;
    }
}
