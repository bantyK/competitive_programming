import java.util.Arrays;

//902 https://leetcode.com/problems/numbers-at-most-n-given-digit-set/submissions/
public class NumbersAtMostNGivenDigitSet {

    public static void main(String[] args) {
        NumbersAtMostNGivenDigitSet obj = new NumbersAtMostNGivenDigitSet();
//        System.out.println(obj.atMostNGivenDigitSet(new String[]{"1", "5", "3", "7"}, 100));
//        System.out.println(obj.atMostNGivenDigitSet(new String[]{"1", "4", "9"}, 1000000000));
//        System.out.println(obj.atMostNGivenDigitSet(new String[]{"3", "5"}, 4));
//        System.out.println(obj.atMostNGivenDigitSet(new String[]{"1", "3", "5", "7"}, 5337));
        System.out.println(obj.atMostNGivenDigitSet(new String[]{"3", "4", "8"}, 4));
    }

    public int atMostNGivenDigitSet(String[] digits, int n) {
        Arrays.sort(digits);

        int count = 0;
        String numString = Integer.toString(n);
        int numLength = numString.length();
        int len = digits.length;

        for (int i = 1; i <= numLength - 1; i++) {
            count += Math.pow(len, i);
        }

        int numIndex = 0; // index at numString
        while (numIndex < numLength) {
            int j = 0;
            while (j < len && ((digits[j].charAt(0) - '0') < numString.charAt(numIndex) - '0')) {
                count += Math.pow(len, numLength - 1 - numIndex);
                j++;
            }

            if (j == len || ((numString.charAt(numIndex) - '0') < (digits[j].charAt(0) - '0'))) {
                // no more nums are possible
                return count;
            }

            numIndex++;

        }

        return count + 1;// +1 for the last number
    }
}