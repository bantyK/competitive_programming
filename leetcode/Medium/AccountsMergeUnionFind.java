import java.util.*;

public class AccountsMergeUnionFind {
    public static void main(String[] args) {
        AccountsMergeUnionFind obj = new AccountsMergeUnionFind();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));

        for (List<String> s : accounts) {
            System.out.println(s);
        }


        List<List<String>> lists = obj.accountsMerge(accounts);

        for (List<String> s : lists) {
            System.out.println(s);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();

        // setting each email as its own parent
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                parents.put(account.get(i), account.get(i));
                owner.put(account.get(i), account.get(0));
            }
        }

        for (List<String> account : accounts) {
            String parentOfFirst = find(account.get(1), parents);
            for (int i = 2; i < account.size(); i++) {
                parents.put(find(account.get(i), parents), parentOfFirst);
            }
        }

        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            String parent = find(firstEmail, parents);
            if (!unions.containsKey(parent)) {
                unions.put(parent, new TreeSet<String>());
            }
            for (int i = 1; i < account.size(); i++) {
                unions.get(parent).add(account.get(i));
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String s : unions.keySet()) {
            List<String> emails = new ArrayList<>(unions.get(s));
            emails.add(0, owner.get(s));
            res.add(emails);
        }

        return res;

    }

    private String find(String s, Map<String, String> parents) {
        if (parents.get(s).equals(s)) {
            return s;
        } else {
            return find(parents.get(s), parents);
        }
    }
}
