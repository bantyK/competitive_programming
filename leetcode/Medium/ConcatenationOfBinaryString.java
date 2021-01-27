// 1680 https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/

public class ConcatenationOfBinaryString {
    public static void main(String[] args) {
        ConcatenationOfBinaryString obj = new ConcatenationOfBinaryString();
        System.out.println(obj.concatenatedBinary(100000));
    }

    public int concatenatedBinary(int n) {
        int MOD = 1000000007;
        if (n == 1)
            return 1;
        long num = 1;
        for (int i = 2; i <= n; i++) {
            // when we append the binary representation of a number into the result,
            // we are basically moving all the existing bits to left by the number of bits
            // which is
            // equal to the size of the binary string of new number.

            // so just left shift the existing number and then add the existing number
            String binaryString = Integer.toBinaryString(i);
            num = num << binaryString.length();
            num += i;
            num %= MOD;
        }

        return (int) (num % MOD);
    }
}