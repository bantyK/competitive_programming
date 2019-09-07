package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonCharacters {
    public static void main(String[] args) {
        String[] input = new String[]{"cool","lock","cook"};
        List<String> strings = new CommonCharacters().commonChars(input);
        strings.forEach(System.out::println);
    }

    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        int n = A.length;
        boolean first = true;
        HashMap<Character, Integer> perStringMap = new HashMap<>();
        HashMap<Character, Integer> finalOutputMap = new HashMap<>();

        for (String s : A) {
            perStringMap.clear();
            for (char c : s.toCharArray()) {
                if (perStringMap.containsKey(c)) {
                    perStringMap.put(c, perStringMap.get(c) + 1);
                } else {
                    perStringMap.put(c, 1);
                }
            }
            if (first) {
                finalOutputMap = (HashMap<Character, Integer>) perStringMap.clone();
                first = false;
            } else {
                finalOutputMap = intersectionWithFinalMap(finalOutputMap, perStringMap);
            }
        }

        for (char key : finalOutputMap.keySet()) {
            int times = finalOutputMap.get(key);

            for (int i = 0; i < times; i++) {
                result.add(String.valueOf(key));
            }
        }

        return result;
    }

    private HashMap<Character, Integer> intersectionWithFinalMap(HashMap<Character, Integer> finalOutputMap, HashMap<Character, Integer> perStringMap) {
        HashMap<Character, Integer> intersection = new HashMap<>();

        for (Character key : finalOutputMap.keySet()) {
            if (perStringMap.containsKey(key)) {
                int valueToPut = finalOutputMap.get(key) > perStringMap.get(key) ? perStringMap.get(key) : finalOutputMap.get(key);
                intersection.put(key, valueToPut);
            }
        }

        return intersection;
    }
}
