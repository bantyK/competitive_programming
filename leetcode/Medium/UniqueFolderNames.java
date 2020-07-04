//#1487: https://leetcode.com/problems/making-file-names-unique/ 
public class UniqueFolderNames {
	public String[] getFolderNames(String[] names) {
        int len = names.length;
        if (len == 0) return new String[0];

        String[] res = new String[len];

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
                String newName = name + "(" + map.get(name) + ")";
                while(map.containsKey(newName)) {
                    map.put(name, map.get(name) + 1);
                    newName = name + "(" + map.get(name) + ")";
                }
                map.put(newName, 0);
                res[i] = newName;
            } else {
                res[i] = name;
                map.put(name, 0);
            }
        }
        return res;
    }
}
