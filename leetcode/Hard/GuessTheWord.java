import java.util.*;
//843 https://leetcode.com/problems/guess-the-word/
interface Master {
    public int guess(String word);
}

public class GuessTheWord {
    public static void main(String[] args) {
        GuessTheWord obj = new GuessTheWord();
    }

    public void findSecretWord(String[] wordlist, Master master) {
        Random random = new Random();

        for (int i = 0, matches = 0; i < 10 && matches != 6; i++) {
            String guess = wordlist[random.nextInt(wordlist.length)];
            matches = master.guess(guess);
            List<String> candidates = new ArrayList<>();

            for (String word : wordlist) {
                if (matches == match(guess, word)) {
                    candidates.add(word);
                }
            }
            wordlist = candidates.toArray(new String[0]);
        }
    }

    private int match(String guess, String word) {
        int match = 0;
        for (int i = 0; i < 6; i++) {
            if (guess.charAt(i) == word.charAt(i)) {
                match++;
            }
        }
        return match;
    }


}
