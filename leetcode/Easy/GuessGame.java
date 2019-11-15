package solutions.easy;


// 374 : https://leetcode.com/problems/guess-number-higher-or-lower/
public class GuessGame {
    public static void main(String[] args) {
        GuessGame obj = new GuessGame();
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;

        while(left <= right) {

            int mid = left + (right - left) / 2;

            if(guess(mid)==0) {
                return mid;
            } else if(guess(mid) == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int guess(int n) {
        // this is provided by leetcode.
        // remove this function before submitting your code to leetcode
        return true;
    }
}
