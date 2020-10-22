import java.util.*;

// 1490 https://leetcode.com/problems/clone-n-ary-tree/
class CloneNarayTree {
    public Node cloneTree(Node root) {
        // Map<Node, Node> cloneMap = new HashMap<>();
        return bfs(root);
    }
    
    private Node cloneNodes(Node root, Map<Node, Node> map) {
        if(root == null) return null;
        
        if(map.containsKey(root)) return map.get(root);
        
        Node copy = new Node(root.val);
        map.put(root, copy);
        
        for(Node node : root.children) {
            Node childCopy = cloneNodes(node, map);
            copy.children.add(childCopy);
        }
        
        return map.get(root);
    }
    
    private Node bfs(Node root) {
        if(root == null) return null;
        
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> cloneMap = new HashMap<>();
        
        queue.offer(root);
        cloneMap.put(root, new Node(root.val));
        
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            for(Node child : curr.children) {
                if(!cloneMap.containsKey(child)) {
                    cloneMap.put(child, new Node(child.val));
                    queue.offer(child);
                }
                cloneMap.get(curr).children.add(cloneMap.get(child));
            }
        }
        
        return cloneMap.get(root);
    }
}