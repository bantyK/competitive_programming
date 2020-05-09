import java.util.*;

//367 https://leetcode.com/problems/valid-perfect-square/
public class ValidPerfectSquare {
    public static void main(String[] args) {
        ValidPerfectSquare obj = new ValidPerfectSquare();
        System.out.println(obj.isPerfectSquare(808201));
    }

    public boolean isPerfectSquare(int num) {
        long low = 1;
        long high = num - 1;
        long mid, square;

        while(low <= high) {
            mid = (low + high) / 2;
            square = mid * mid;

            if(square == num) return true;
            else if(square > num) high = mid - 1;
            else low = mid + 1;
        }

        return false;
    }
}
