import java.util.*;

//187 https://leetcode.com/problems/repeated-dna-sequences/
public class RepeatedDNASeq {
    public static void main(String[] args) {
        RepeatedDNASeq obj = new RepeatedDNASeq();
        System.out.println(obj.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(obj.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> countMap = new HashMap<>();
        StringBuilder builder = new StringBuilder(s.substring(0, 9));
        for (int right = 9; right < s.length(); right++) {
            builder.append(s.charAt(right));
            String sub = builder.toString();
            countMap.put(sub, countMap.getOrDefault(sub, 0) + 1);
            builder.deleteCharAt(0);
        }

        List<String> res = new ArrayList<>();
        for (String key : countMap.keySet()) {
            if (countMap.get(key) > 1) res.add(key);
        }
        return res;
    }
}
