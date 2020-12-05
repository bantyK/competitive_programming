//605 https://leetcode.com/problems/can-place-flowers/
public class CanPlaceFlowers {

    public static void main(String[] args) {

        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0}, 2));
        System.out.println(canPlaceFlowers(new int[]{0}, 1));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0}, 3));
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 1, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0}, 3));
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0}, 2));
    }

    public static boolean canPlaceFlowers(int[] nums, int n) {
        int len = nums.length;
        if (n == 0) return true;

        if (len == 1) {
            return nums[0] == 0 && n == 1;
        }
        int i = 0;
        // one flower can be planted in the start if the second bed is not planted
        if (len >= 2 && nums[1] == 0 && nums[0] == 0) {
            n--;
            nums[0] = 1;
        }

        while (i < len) {
            while (i < len && nums[i] == 1) {
                i++;
            }
            int j = i;
            while (j < len && nums[j] == 0) {
                if (j - i == 2) {
                    n--;
                    nums[j - 1] = 1;
                    i = j;
                }
                if (n == 0) return true;
                j++;
            }
            i = j;
        }

        // one flower can be planted at the end if the second last bed is not planted
        if (len > 2 && nums[len - 1] == 0 && nums[len - 2] == 0) n--;

        return n <= 0;
    }
}