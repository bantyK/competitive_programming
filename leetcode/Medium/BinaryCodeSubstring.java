// 1461 https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
public class BinaryCodeSubstring {

    public static void main(String[] args) {
        BinaryCodeSubstring obj = new BinaryCodeSubstring();
//        System.out.println(obj.hasAllCodes("00110110", 2));
//        System.out.println(obj.hasAllCodes("0110", 1));
        System.out.println(obj.hasAllCodes("01010000010011011100111", 4));
    }

    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) return false;
        int max = (int) Math.pow(2, k);
        // left shift operation moves the MSB one bit before k, this will unset it
        // eg: 11 < 1 and k = 2 will result in 110 (we only need 2 bits)
        int clearMostSignificantBit = max - 1;

        boolean[] seen = new boolean[max];
        int n = Integer.parseInt(s.substring(0, k), 2);
        seen[n] = true;
        int count = max - 1;

        for (int right = k; right < s.length(); right++) {
            n = n << 1;
            n = n & clearMostSignificantBit;
            n = n | (s.charAt(right) - '0');

            if (!seen[n]) {
                count--;
                seen[n] = true;
            }
        }

        return count == 0;
    }
}
