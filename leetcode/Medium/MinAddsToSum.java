// 1785 https://leetcode.com/problems/minimum-elements-to-add-to-form-a-given-sum/
public class MinAddsToSum {
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }

        long diff = goal - sum;
        if (diff == 0)
            return 0;

        diff = Math.abs(diff);

        long ans = diff / limit;
        if ((diff % limit) > 0) {
            ans += 1;
        }
        return (int) ans;
    }

}
