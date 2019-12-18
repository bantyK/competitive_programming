import java.util.*;

// 721 https://leetcode.com/problems/accounts-merge/
public class AccountMerge {
    public static void main(String[] args) {
        AccountMerge obj = new AccountMerge();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));

        obj.accountsMerge(accounts);
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) {
            return result;
        }

        Map<String, String> names = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        Set<String> emails = new HashSet<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emails.add(email);
                names.put(email, name);
                graph.putIfAbsent(email, new HashSet<>());

                if (i == 1) continue;

                graph.get(account.get(i - 1)).add(email);
                graph.get(email).add(account.get(i - 1));
            }
        }


        Set<String> visited = new HashSet<>();
        for (String s : emails) {
            if (!visited.contains(s)) {
                visited.add(s);
                List<String> buffer = new ArrayList<>();
                buffer.add(s);
                helper(s, graph, visited, buffer);
                Collections.sort(buffer);
                buffer.add(0, names.get(s));
                result.add(buffer);
            }
        }

        return result;
    }

    private void helper(String s, Map<String, Set<String>> graph, Set<String> visited, List<String> buffer) {
        for(String node : graph.get(s)) {
            if(!visited.contains(node)) {
                visited.add(node);
                buffer.add(node);
                helper(node, graph, visited, buffer);
            }
        }
    }
}
