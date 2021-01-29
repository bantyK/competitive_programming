// 1221 https://leetcode.com/problems/split-a-string-in-balanced-strings/
class BalancedStringSplit {
    public int balancedStringSplit(String s) {
        int maxSplit = 0;
        
        int countR = 0, countL = 0;
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if(ch == 'R') {
                countR++;
            } else {
                countL++;
            }
            
            if(countL == countR) {
                maxSplit++;
                countL = 0;
                countR = 0;
            }
        }
        
        return maxSplit;
    }
}