//1696 https://leetcode.com/problems/jump-game-vi/
public class JumpGame6 {
	public int maxResult(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int max = deque.isEmpty() ? 0 : nums[deque.peekFirst()];

            nums[i] = nums[i] + max;

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                // remove all the elements from the deque which are less than nums[i] because they are useless after we add
                // nums[i] into the queue and this will also ensure that the maximum element is at the first
                deque.pollLast();
            }

            deque.addLast(i);

            while (!deque.isEmpty() && i - deque.peekFirst() + 1 > k) {
                // should be a valid window, remove all the elements which are outside of window
                deque.pollFirst();
            }
        }

        return nums[n - 1];
    }
}