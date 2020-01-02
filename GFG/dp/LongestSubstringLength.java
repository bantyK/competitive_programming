import java.util.*;
// https://practice.geeksforgeeks.org/problems/longest-common-substring/0
class LCS {
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		s.nextLine();
		for(int i = 0; i < t; i++) {
		    s.nextLine();
		    String s1 = s.nextLine();
		    String s2 = s.nextLine();
	        System.out.println(lcs(s1, s2, 0, 0, 0, new HashMap<>()));
		}
	}
	
	private static int lcs(String s1, String s2, int i1, int i2, int count, Map<String, Integer> dp) {
        if (i1 == s1.length() || i2 == s2.length()) {
            return count;
        }

        String key = i1 + "|" + i2 + "|" + count;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int c1 = count;
        if (s1.charAt(i1) == s2.charAt(i2)) {
            c1 = lcs(s1, s2, i1 + 1, i2 + 1, count + 1, dp);
        }

        int c2 = lcs(s1, s2, i1 + 1, i2, 0, dp);
        int c3 = lcs(s1, s2, i1, i2 + 1, 0, dp);
        dp.put(key, Math.max(c1, Math.max(c2, c3)));
        return dp.get(key);
    }
}
