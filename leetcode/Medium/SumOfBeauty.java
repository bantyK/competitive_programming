// 1781 https://leetcode.com/problems/sum-of-beauty-of-all-substrings/
public class SumOfBeauty {
    public int beautySum(String s) {
        int ans = 0;
        int n = s.length();
        for(int i = 0; i < n; i++) {
            int[] charCount = new int[26];
            for(int j = i; j < n; j++) {
                charCount[s.charAt(j) - 'a']++;

                int most = 0;
                int least = Integer.MAX_VALUE;

                for(int k = 0; k < 26; k++) {
                    if(charCount[k] == 0) continue;
                    most = Math.max(most, charCount[k]);
                    least = Math.min(least, charCount[k]);
                }

                ans += (most - least);
            }
        }
        return ans;
    }
}
