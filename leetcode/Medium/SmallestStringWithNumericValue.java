//1663 https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
public class SmallestStringWithNumericValue {
	
	public String getSmallestString(int n, int k) {
        char[] chars = new char[n];

        for (int i = n - 1; i >= 0; i--) {
            int remainingValue = k - i;
            if (remainingValue >= 26) {
                chars[i] = 'z';
                k -= 26;
            } else {
                chars[i] = (char) (remainingValue + 'a' - 1);
                k -= remainingValue;
            }
        }

        return new String(chars);
    }
}