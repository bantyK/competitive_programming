// 1780 https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/
public class SumOfPower3 {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 0) {
                n = n / 3;
            } else if ((n - 1) % 3 == 0) {
                n = n - 1;
            } else {
                return false;
            }
        }

        return true;
    }

}
