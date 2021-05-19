import java.util.*;

// 609 https://leetcode.com/problems/find-duplicate-file-in-system/
public class DuplicateFiles {

    public static void main(String[] args) {
        DuplicateFiles obj = new DuplicateFiles();
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        List<List<String>> duplicate = obj.findDuplicate(paths);
        System.out.println(duplicate);
    }

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String[]>> map = new HashMap<>();

        for (String path : paths) {
            // root/a 1.txt(abcd)
            String[] split = path.split(" ");
            String dir = split[0];
            for (int i = 1; i < split.length; i++) {
                String file = split[i];
                String[] split2 = file.split("\\(");
                String fileName = split2[0];
                String fileContent = split2[1].substring(0, split2[1].length() - 1);
                map.putIfAbsent(fileContent, new ArrayList<>());
                map.get(fileContent).add(new String[]{dir, fileName});
            }
        }
        List<List<String>> res = new ArrayList<>();

        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                List<String> l = new ArrayList<>();
                for (String[] file : map.get(key)) {
                    l.add(file[0] + "/" + file[1]);
                }
                res.add(l);
            }
        }
        return res;
    }

}