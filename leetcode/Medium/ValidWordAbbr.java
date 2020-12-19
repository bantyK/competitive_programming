import java.util.HashMap;
import java.util.Map;

// #288 https://leetcode.com/problems/unique-word-abbreviation/
class ValidWordAbbr {

    private Map<String, String> map;

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String word : dictionary) {
            String abbr = getAbbr(word);
            if (map.containsKey(abbr)) {
                // if this abbr is already present in the map, this means that it is not unique
                // hence mark it as null so that we can recognize it later in the isUnique method 
                if (map.get(abbr) != null && !map.get(abbr).equals(word)) {
                    map.put(abbr, null);
                }
            } else {
                map.put(abbr, word);
            }
        }
    }

    private String getAbbr(String word) {
        if (word.length() <= 2) return word;
        return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (map.containsKey(abbr)) {
            if (map.get(abbr) == null) {
                // it means that it is not unique
                return false;
            }
            return map.get(abbr).equals(word);
        }
        return true;
    }
}