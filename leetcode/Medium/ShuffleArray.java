import java.util.*;

// 384 https://leetcode.com/problems/shuffle-an-array/
public class ShuffleArray {

    class Solution {
        final int n;
        int[] original;
        Random random = new Random();

        public Solution(int[] nums) {
            this.original = nums.clone();
            this.n = nums.length;
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return original;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            List<Integer> nums_list = getOriginalArray();
            int[] res = new int[n];

            for (int i = 0; i < n; i++) {
                res[i] = nums_list.remove(random.nextInt(nums_list.size()));
            }

            return res;
        }

        private List<Integer> getOriginalArray() {
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                l.add(original[i]);
            }

            return l;
        }
    }

}