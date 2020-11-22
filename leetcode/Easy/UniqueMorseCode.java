// 804 https://leetcode.com/problems/unique-morse-code-words/
public class UniqueMorseCode {
	
		public int uniqueMorseRepresentations(String[] words) {
    	Set<String> uniqueCodes = new HashSet<>();
        String[] codes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        
        for(String word : words) {
            StringBuilder builder = new StringBuilder();
            for(char c : word.toCharArray()) {
                int index = c - 'a';
                builder.append(codes[index]);
            }
            uniqueCodes.add(builder.toString());
        }
        
        return uniqueCodes.size();    
    }

}
