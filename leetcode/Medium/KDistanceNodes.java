// 863 : https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
import java.util.*;
public class KDistanceNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode target = new TreeNode(5);
        root.left = target;
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        List<Integer> integers = distanceK(root, target, 3);
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        visited.put(root.val, false);
        getParentChildMap(root, parentMap, visited);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(target);
        visited.put(target.val, true);
        int i = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            if (i == K) {
                for (int j = 0; j < n; j++) {
                    result.add(queue.poll().val);
                }
                break;
            } else {
                for (int j = 0; j < n; j++) {
                    TreeNode curr = queue.poll();
                    
                    if (curr.left != null && !visited.get(curr.left.val)) {
                        queue.offer(curr.left);
                    }

                    if (curr.right != null && !visited.get(curr.right.val)) {
                        queue.offer(curr.right);
                    }

                    TreeNode parent = parentMap.get(curr.val);
                    if (parent != null && visited.containsKey(parent.val) && !visited.get(parent.val)) {
                        queue.offer(parent);
                    }

                    visited.put(curr.val, true);
                }

                i++;
            }

        }

        return result;
    }

    private static void getParentChildMap(TreeNode root, Map<Integer, TreeNode> map, Map<Integer, Boolean> visited) {
        if (root == null) return;

        if (root.left != null) {
            map.put(root.left.val, root);
            visited.put(root.left.val, false);
            getParentChildMap(root.left, map, visited);
        }

        if (root.right != null) {
            map.put(root.right.val, root);
            visited.put(root.right.val, false);
            getParentChildMap(root.right, map, visited);
        }
    }
}