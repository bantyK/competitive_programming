package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/subdomain-visit-count/
public class SubDomainCount {

    public static void main(String[] args) {
        String[] cpdomains = {"9001 discuss.leetcode.com"};
        List<String> strings = new SubDomainCount().subdomainVisits(cpdomains);
        System.out.println(strings);
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> visitCountMap = new HashMap<>();
        StringBuilder stringBuilder;
        for (String domain : cpdomains) {
            stringBuilder = new StringBuilder();
            String[] split = domain.split(" ");
            int count = Integer.parseInt(split[0]);
            String[] subdomains = split[1].split("\\.");
            String subD;
            for (int i = subdomains.length - 1; i >= 0; i--) {
                stringBuilder.insert(0, subdomains[i]);
                subD = stringBuilder.toString();
                if (visitCountMap.containsKey(subD)) {
                    visitCountMap.put(subD, visitCountMap.get(subD) + count);
                } else {
                    visitCountMap.put(subD, count);
                }
                stringBuilder.insert(0, ".");
            }
        }
        List<String> resultList = new ArrayList<>();

        for(String key : visitCountMap.keySet()) {
            resultList.add(visitCountMap.get(key) + " " + key);
        }

        return resultList;
    }
}
