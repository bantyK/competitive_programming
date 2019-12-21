import java.util.*;

//332 https://leetcode.com/problems/reconstruct-itinerary/
public class PrintItinerary {

    public static void main(String[] args) {
        PrintItinerary obj = new PrintItinerary();
        // [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "KUL"),
                Arrays.asList("JFK", "NRT"),
                Arrays.asList("NRT", "JFK"));
        List<String> itinerary = obj.findItinerary(tickets);
        System.out.println(itinerary);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, List<String>> map = new HashMap<>();
        if (tickets == null || tickets.size() == 0) return Collections.emptyList();

        for(List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(to);
        }

        for(String airport : map.keySet()) {
            Collections.sort(map.get(airport));
        }

        List<String> res = new ArrayList<>();
        dfs(map, res, "JFK");
        return res;

    }

    private boolean dfs(Map<String, List<String>> map, List<String> result, String curr) {
        if (map.keySet().size() == 0) {
            result.add(curr);
            return true;
        } else if (!map.containsKey(curr)) {
            return false;
        }

        for (int i = 0; i < map.get(curr).size(); i++) {
            String to = map.get(curr).get(i);
            result.add(curr);

            map.get(curr).remove(i);
            if (map.get(curr).size() == 0) map.remove(curr);

            if (dfs(map, result, to)) return true;

            map.putIfAbsent(curr, new ArrayList<String>());
            map.get(curr).add(i, to);
            result.remove(result.size() - 1);
        }
        return false;
    }


}
