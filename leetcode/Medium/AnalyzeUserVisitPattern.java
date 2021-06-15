import java.util.*;
import java.util.stream.Collectors;

// 1152 https://leetcode.com/problems/analyze-user-website-visit-pattern/
public class AnalyzeUserVisitPattern {

    public static void main(String[] args) {
        String[] usernames = new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] websites = new String[]{"home", "home", "home", "home", "cart", "maps", "home", "home", "about", "career"};

        System.out.println(new AnalyzeUserVisitPattern().mostVisitedPattern(usernames, timestamp, websites));
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap<>();

        // {username: [timestamp, website]}
        for (int i = 0; i < username.length; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }

        // map of 3-sequence websites
        Map<String, Integer> countSequence = new HashMap<>();
        String best = "";

        // find the 3-sequence for each user
        for (String key : map.keySet()) {
            Set<String> seen = new HashSet<>();
            List<Pair> pair = map.get(key);
            pair.sort((p1, p2) -> p1.time - p2.time); // sort by time

            // creating the sequence, brute force way
            for (int i = 0; i < pair.size(); i++) {
                for (int j = i + 1; j < pair.size(); j++) {
                    for (int k = j + 1; k < pair.size(); k++) {
                        String sequence = new StringBuilder()
                                .append(pair.get(i).website).append(" ")
                                .append(pair.get(j).website).append(" ")
                                .append(pair.get(k).website)
                                .toString();

                        if (seen.add(sequence)) {
                            countSequence.put(sequence, countSequence.getOrDefault(sequence, 0) + 1);
                        }

                        if (best.equals("") // if this is the first res
                                // we want the sequence with most count
                                || countSequence.get(sequence) > countSequence.get(best)
                                // if the count is same, then the new sequence will be considered only if new sequence is lexicographically smaller
                                || (countSequence.get(sequence).equals(countSequence.get(best)) && best.compareTo(sequence) > 0)) {
                            best = sequence;
                        }
                    }
                }
            }
        }
        return Arrays.stream(best.split(" ")).collect(Collectors.toList());
    }

    static class Pair {
        int time;
        String website;

        public Pair(int time, String website) {
            this.time = time;
            this.website = website;
        }
    }
}