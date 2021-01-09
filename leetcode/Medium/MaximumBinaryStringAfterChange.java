// 1702 https://leetcode.com/problems/maximum-binary-string-after-change/
public class MaximumBinaryStringAfterChange {
    public static void main(String[] args) {
        MaximumBinaryStringAfterChange obj = new MaximumBinaryStringAfterChange();
        System.out.println(obj.maximumBinaryString("100101"));
    }

    public String maximumBinaryString(String binary) {
        if (binary == null || binary.length() < 2) {
            return binary;
        }

        char[] result = binary.toCharArray();

        int left = 0, right = 1;

        while (right < result.length) {
            if (result[left] == '0' && result[right] == '0') {
                // 00 -> 10
                result[left] = '1';
                result[right] = '1';
                result[left + 1] = '0';
                left++;
                right++;
            } else if (result[left] == '1') {
                // we are kind of resetting the looping here
                left++;
                right = left + 1;
            } else {
                right++;
            }
        }
        return new String(result);
    }
}
