package solutions.medium;

import java.util.*;

//1257: https://leetcode.com/problems/smallest-common-region/
public class SmallestCommonRegion {
    
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        if (regions == null || regions.size() == 0) return null;
        Map<String, List<String>> map = new HashMap<>();

        for (List<String> region : regions) {
            map.put(region.get(0), region.subList(1, region.size()));
        }
        List<String> link1 = new ArrayList<>();
        link1.add(region1);
        makeLink(map, region1, link1);
        List<String> link2 = new ArrayList<>();
        link2.add(region2);
        makeLink(map, region2, link2);

        for (String s : link1) {
            if (link2.contains(s)) {
                return s;
            }
        }

        return "";
    }

    private void makeLink(Map<String, List<String>> regions, String region, List<String> link) {
        for (String key : regions.keySet()) {
            if (regions.get(key).contains(region)) {
                link.add(key);
                makeLink(regions, key, link);
            }
        }
    }
}
