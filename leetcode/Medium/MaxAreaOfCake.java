import java.util.Arrays;

//1465 https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
public class MaxAreaOfCake {

    public static final int MOD = (int) (Math.pow(10, 9) + 7);

    public static void main(String[] args) {
        MaxAreaOfCake obj = new MaxAreaOfCake();
        int[] arr = new int[]{2};
        System.out.println(obj.maxArea(1000000000, 1000000000, arr, arr));
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int MOD = (int) (Math.pow(10, 9) + 7);

        long maximumLength = horizontalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            // consecutive cuts
            maximumLength = Math.max(maximumLength, horizontalCuts[i] - horizontalCuts[i - 1]);
        }

        maximumLength = Math.max(maximumLength, h - horizontalCuts[horizontalCuts.length - 1]);
        maximumLength %= MOD;

        long maximumWidth = verticalCuts[0];
        for (int i = 1; i < verticalCuts.length; i++) {
            maximumWidth = Math.max(maximumWidth, verticalCuts[i] - verticalCuts[i - 1]);
        }
        maximumWidth = Math.max(maximumWidth, w - verticalCuts[verticalCuts.length - 1]);
        maximumWidth %= MOD;

        return (int) ((maximumLength * maximumWidth) % MOD);
    }
}