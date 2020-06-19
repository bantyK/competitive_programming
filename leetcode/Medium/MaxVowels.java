package sw;

//1456 https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
public class MaxVowels {
    public static void main(String[] args) {
        MaxVowels obj = new MaxVowels();
        System.out.println(obj.maxVowels("abciiidef", 3));
        System.out.println(obj.maxVowels("aeiou", 5));
        System.out.println(obj.maxVowels("leetcode", 3));
    }

    public int maxVowels(String s, int k) {
        int left = 0;
        int maxCount = 0;

        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                ++maxCount;
            }
        }
        int currentCount = maxCount;

        for (int right = k; right < s.length(); ++right) {
            char rightChar = s.charAt(right);
            if (isVowel(rightChar)) {
                ++currentCount;
            }

            char leftChar = s.charAt(left++);
            if (isVowel(leftChar)) {
                --currentCount;
            }

            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
