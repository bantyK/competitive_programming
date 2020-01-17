import java.util.*;
import java.util.stream.Collectors;
public class LetterTile {
    public static void main(String[] args) {
        System.out.println(numTilePossibilities("ABC"));
    }

    private static int numTilePossibilities(String tiles) {
        Set<String> possibilities = new HashSet<>();
        if(tiles.length == 0) return 0;

        for(int i = 1; i <= tiles.length(); i++) {
            List<Character> letters = new ArrayList<>();
            getLetters(tiles, letters, possibilities, i);
        }

        return possibilities.size();
    }

    private static void getLetters(String tiles, List<Character> letters, Set<String> possibilities, int currentSize) {
        if(letters.size() == currentSize) {
            possibilities.add(letters.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }

        for(int i = 0; i < currentSize; i++) {
            letters.add(tiles.charAt(i));
            getLetters(tiles, letters, possibilities, currentSize);
            letters.remove(letters.size() - 1);
        }
    }
}