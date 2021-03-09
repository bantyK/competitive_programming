import java.util.*;

// 1600 https://leetcode.com/problems/throne-inheritance/
class ThroneInheritance {

    Map<String, Boolean> deathMap;
    Map<String, List<String>> tree;
    String kingName;
    
    public ThroneInheritance(String kingName) {
        deathMap = new HashMap<>();    
        tree = new HashMap<>();
        this.kingName = kingName;
    }
    
    public void birth(String parentName, String childName) {
        tree.putIfAbsent(parentName, new ArrayList<>());
        tree.get(parentName).add(childName);
    }
    
    public void death(String name) {
        deathMap.put(name, true);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        getOrder(kingName, order);
        return order;
    }
    
    private void getOrder(String root, List<String> order) {
        if(root == null) return;
        if(!deathMap.containsKey(root)) {
            order.add(root);
        }
        for(String child : tree.getOrDefault(root, new ArrayList<>())) {
            getOrder(child, order);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
