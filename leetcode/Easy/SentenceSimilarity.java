import java.util.*;
//734 https://leetcode.com/problems/sentence-similarity/
public class SentenceSimilarity1 {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        Map<String, Set<String>> similar = new HashMap<>();

        if(sentence1.length != sentence2.length) return false;

        if(similarPairs.size() == 0) {
            for(int i = 0; i < sentence1.length; i++) {
                String w1 = sentence1[i];
                String w2 = sentence2[i];

                if(!w1.equals(w2)) {
                    return false;
                }
            }
            return true;
        }

        for(List<String> pair : similarPairs) {
            String a = pair.get(0);
            String b = pair.get(1);

            similar.putIfAbsent(a, new HashSet<String>());
            similar.putIfAbsent(b, new HashSet<String>());

            similar.get(a).add(b);
            similar.get(b).add(a);
        }

        for(int i = 0; i < sentence1.length; i++) {
            String w1 = sentence1[i];
            String w2 = sentence2[i];

            if(w1.equals(w2)) continue;

            if(!similar.containsKey(w1) || !similar.containsKey(w2)) return false;

            if(!similar.get(w1).contains(w2) && !similar.get(w2).contains(w1)) {
                return false;
            }
        }

        return true;
    }
}