package solutions.easy;

// 953 https://leetcode.com/problems/verifying-an-alien-dictionary/
public class AlienDictionary {
    public static void main(String[] args) {
        AlienDictionary obj = new AlienDictionary();
        System.out.println(
                obj.isAlienSorted(new String[]{"a", "b", "c", "d","e", "g","f"}, "abcdefghijklmnopqrstuvwxyz")
        );
    }

    public boolean isAlienSorted(String[] words, String order) {
        int i = 0;

        while (i < words.length - 1) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int start1 = 0, start2 = 0;

            while (start1 < word1.length() && start2 < word2.length() && word1.charAt(start1) == word2.charAt(start2)) {
                start1++;
                start2++;
            }

            if (word1.length() == start1) {
                i++;
            } else if (word2.length() == start2) {
                return false;
            } else {
                int word1Index = order.indexOf(word1.charAt(start1));
                int word2Index = order.indexOf(word2.charAt(start2));

                if (word1Index > word2Index) {
                    return false;
                } else {
                    i++;
                }
            }
        }
        return true;
    }
}
