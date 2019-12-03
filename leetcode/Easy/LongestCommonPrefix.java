// 14 https://leetcode.com/problems/longest-common-prefix/

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        int start = 0;
        StringBuilder builder = new StringBuilder();
        String first = strs[0];
        while(true) {
            if(start >= first.length()) return builder.toString();
            for(int i = 1; i < strs.length; i++) {
                if(start >= strs[i].length()) return builder.toString();
                else if(strs[i].charAt(start) != first.charAt(start)) return builder.toString();
            }
            
            builder.append(first.charAt(start));
            start++;
            
            if(start >= first.length()) break;
        }
        
        return builder.toString();
    }

}
