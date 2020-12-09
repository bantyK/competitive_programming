import java.util.Arrays;

//683 https://leetcode.com/problems/k-empty-slots/
public class KEmptySlots {

    public static void main(String[] args) {
        KEmptySlots obj = new KEmptySlots();

        System.out.println(obj.kEmptySlots(new int[]{3, 9, 2, 8, 1, 6, 10, 5, 4, 7}, 1));
    }

    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;

        // index in the position array represents the bulb number and the value represent the day when that bulb
        // was turned on

        int[] position = new int[n + 1]; // +1 because the indexing is 1 based
        for (int i = 0; i < n; i++) {
            position[bulbs[i]] = i + 1;
        }

        // we need to find a window of length K + 2, such that all the elements between the boundary of the window
        // has values greater than the boundary values
        // what this represent is that all the bulbs between the 2 boundary bulbs are turned off
        // and since we are already looking at window of length K + 2, we are looking at the valid window

        // create the position array by hand and the above logic will make sense

        // now to find the minimum time where this condition happens, we will start from 0 and keep
        // checking the windows of size i + K + 1

        // if we find a valid window, then return the end value (or i because they will be same),
        // because that's going to be minimum time. remember the indices represent the time when these
        // bulbs were turned on. Hence, the first i where we get a valid window is going to be the minimum time

        int start = 1;
        int end = start + k + 1;
        int res = Integer.MAX_VALUE;

        for (int i = 1; end <= n; i++) {
            if (position[i] > position[start] && position[i] > position[end]) continue;

            if (i == end) {
                // a valid window. there are no bulb which got turned on in between before the bulbs
                // at the boundary
                // both the bulbs need to be turned on. The one which is turned on later should be considered
                res = Math.min(res, Math.max(position[start], position[end]));
            }

            start = i;
            end = start + k + 1;
        }


        return res == Integer.MAX_VALUE ? -1 : res;
    }

}