import java.util.*;

// 1347 https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
public class MinimumStepsAnagram {
	public static void main(String[] args) {
		MinimumStepsAnagram obj = new MinimumStepsAnagram();
		System.out.println(obj.minSteps2("bab", "aba"));
		System.out.println(obj.minSteps2("leetcode", "practice"));		
		System.out.println(obj.minSteps2("friend", "family"));
		System.out.println(obj.minSteps2("xxyyzz", "zzyxyx"));
		System.out.println(obj.minSteps2("abcfghs","oplavgd"));
	}
	
	//Optimised solution
	public int minSteps2(String s, String t) {
		int[] arr = new int[26];
		for(int i = 0; i < s.length(); i++) {
			arr[s.charAt(i) - 'a']++;
			arr[t.charAt(i) - 'a']--;
		}
		
		int steps = 0;
		for(int i = 0; i < 26; i++) {
			if(arr[i] > 0) {
				steps += arr[i];
			}
		}
		
		return steps;
	}
	
	
	public int minSteps(String s, String t) {
		int minSteps = 0;
		if(s == null || s.length() == 0) return minSteps;
		
		Map<Character, Integer> sMap = new HashMap<>();
		Map<Character, Integer> tMap = new HashMap<>();
		
		for(char c : s.toCharArray()) {
			sMap.put(c, sMap.getOrDefault(c, 0) + 1);
		}
		

		for(char c : t.toCharArray()) {
			tMap.put(c, tMap.getOrDefault(c, 0) + 1);
		}

		Set<Character> seen = new HashSet<>();
		
		for(char c : s.toCharArray()) {
			if(!seen.contains(c)) {
				seen.add(c);
				int requiredCount = sMap.get(c);
				int availableCount = tMap.getOrDefault(c, 0);

				// System.out.println(requiredCount);
				// System.out.println(availableCount);
			
				if(requiredCount > availableCount)
					minSteps += requiredCount - availableCount;
			}
		}
		
		return minSteps;
	}
}