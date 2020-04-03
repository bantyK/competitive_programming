import java.util.*;

//202 https://leetcode.com/problems/happy-number/
public class HappyNumber {
    public boolean isHappy(int num) {
        Set<Integer> seen = new HashSet<>();
        while (true) {
            num = getSquaresSum(num);
            if (num == 1) return true;
            if (seen.contains(num)) return false;
            seen.add(num);
        }
    }

    private int getSquaresSum(int num) {
        int sum = 0;
        while (num >= 1) {
            int digit = num % 10;
            sum += digit * digit;
            num = num / 10;
        }
        return sum;
    }
}
