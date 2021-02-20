// 1446 https://leetcode.com/problems/consecutive-characters/
public class ConsecutiveCharacters {
    public static void main(String[] args) {
        ConsecutiveCharacters obj = new ConsecutiveCharacters();
        System.out.println(obj.maxPower("leetcode"));
        System.out.println(obj.maxPower("hooraaaaaaaaaaay"));
    }

    public int maxPower(String s) {
        int maxPower = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            int j = i;
            int len = 0;
            while (j < s.length() && s.charAt(j) == currentChar) {
                j++;
                len++;
            }
            maxPower = Math.max(maxPower, len);
            i = --j;
        }
        return maxPower;
    }
}
