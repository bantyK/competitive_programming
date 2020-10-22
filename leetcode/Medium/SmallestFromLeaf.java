import input.TreeNode;

//988 https://leetcode.com/problems/smallest-string-starting-from-leaf/
public class SmallestFromLeaf {
    public static void main(String[] args) {
        SmallestFromLeaf obj = new SmallestFromLeaf();

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);

        System.out.println(obj.smallestFromLeaf(root));

    }

    public String smallestFromLeaf(TreeNode root) {
        return dfs(root, "");
    }

    public String dfs(TreeNode node, String suffix) {
        if (null == node) {
            return suffix;
        }
        suffix = "" + (char) ('a' + node.val) + suffix;

        if (null == node.left && null == node.right) {
            //leaf node
            return suffix;
        }
        if (null == node.left || null == node.right) {
            return (null == node.left) ? dfs(node.right, suffix) : dfs(node.left, suffix);
        }

        String left = dfs(node.left, suffix);
        String right = dfs(node.right, suffix);

        return left.compareTo(right) <= 0 ? left : right;
    }

}

/*
[25,21,24,13,12,14,15,21,1,3,4,1,2,9,7]
[0,null,1]
[]
[0]
[2,2,1,null,1,0,null,0]
[0,1,2,3,4,3,4]
[25,1,3,1,3,0,2]
 */