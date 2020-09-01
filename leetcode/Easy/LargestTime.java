//949 https://leetcode.com/problems/largest-time-for-given-digits/
public class LargestTime {
    public static void main(String[] args) {
        LargestTime obj = new LargestTime();
        System.out.println(obj.largestTimeFromDigits(new int[]{1,2,3,4}));
    }

    public String largestTimeFromDigits(int[] A) {
        StringBuilder result = backtrack(A, new StringBuilder(), new StringBuilder(), new boolean[4]);
        if (result.length() == 4) {
            result.insert(2, ":");
        }
        return result.toString();
    }

    private StringBuilder backtrack(int[] nums, StringBuilder current, StringBuilder result, boolean[] used) {
        if (current.length() == 4 &&
                Integer.parseInt(current.toString()) <= 2359 &&
                Integer.parseInt(current.substring(2)) <= 59) {

            int tempRes = Integer.parseInt(current.toString());
            if (result.length() == 0) {
                result = new StringBuilder(current);
                return result;
            } else {
                int resInt = Integer.parseInt(result.toString());
                if (tempRes > resInt) {
                    return new StringBuilder(current);
                } else {
                    return result;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            current.append(nums[i]);
            result = backtrack(nums, current, result, used);
            used[i] = false;
            current.deleteCharAt(current.length() - 1);
        }
        return result;
    }
}
