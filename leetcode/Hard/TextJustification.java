import java.util.*;

//68 https://leetcode.com/problems/text-justification/
public class TextJustification {

    public static void main(String[] args) {
        TextJustification obj = new TextJustification();
        System.out.println(obj.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(obj.fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));
        System.out.println(obj.fullJustify(new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"}, 20));
        System.out.println(obj.fullJustify(new String[]{"ask", "not", "what", "your", "country", "can", "do", "for", "you", "ask", "what", "you", "can", "do", "for", "your", "country"}, 16));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            List<String> currentWords = new ArrayList<>();
            int currentLen = words[i].length();
            currentWords.add(words[i]);
            int j = ++i;
            while (j < words.length && currentLen + words[j].length() + 1 <= maxWidth) {
                currentWords.add(words[j]);
                currentLen += words[j].length() + 1; // +1 because of space between each word
                j++;
            }

            i = --j;

            if (j < words.length - 1) {
                String justifiedString = justify(currentWords, currentLen, maxWidth);
                res.add(justifiedString);
            } else {
                String leftJustified = leftJustify(currentWords, maxWidth);
                res.add(leftJustified);
            }


        }

        return res;
    }

    private String leftJustify(List<String> words, int maxWidth) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1); // delete the extra space after the last word
        int remaining = maxWidth - builder.length();
        addSpace(builder, remaining);
        return builder.toString();
    }

    private String justify(List<String> words, int wordsLength, int maxWidth) {
        int spaceNeeded = maxWidth - wordsLength;
        int size = words.size();
        StringBuilder builder = new StringBuilder();
        if (spaceNeeded == 0) {
            for (String word : words) {
                builder.append(word).append(" ");
            }
            builder.deleteCharAt(builder.length() - 1); // delete the extra (last) space
            return builder.toString();
        }

        if (size == 1) {
            // single word, so it needs to be left justified
            return leftJustify(words, maxWidth);
        }

        int spaceBetweenEachWord = spaceNeeded / (size - 1);
        int remainingSpace = spaceNeeded % (size - 1);
        for (int i = 0, wordsSize = size - 1; i < wordsSize; i++) {
            String word = words.get(i);
            builder.append(word).append(" ");
            if (spaceBetweenEachWord > 0) addSpace(builder, spaceBetweenEachWord);
            // adjust the remaining space between words from the left side
            if (remainingSpace > 0) {
                builder.append(" ");
                remainingSpace--;
            }
        }
        builder.append(words.get(size - 1));
        System.out.println(builder.toString());
        return builder.toString();
    }

    private void addSpace(StringBuilder builder, int spaces) {
        while (spaces-- > 0) builder.append(" ");
    }
}