//623 https://leetcode.com/problems/add-one-row-to-tree/
public class AddTreeRow {
    private int DIR_LEFT = 0;
    private int DIR_RIGHT = 1;

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        } else {
            return helper(root, v, d, -1);
        }
    }

    private TreeNode helper(TreeNode root, int v, int d, int dir) {
        if(root == null) {
            return d == 1 ? new TreeNode(v) :  null;
        }
        if(d == 1) {
            TreeNode node = new TreeNode(v);
            if(dir == DIR_LEFT) {
                node.left = root;
            } else if(dir == DIR_RIGHT){
                node.right = root;
            }
            return node;
        }

        root.left = helper(root.left, v, d-1, DIR_LEFT);
        root.right = helper(root.right, v, d-1, DIR_RIGHT);
        return root;
    }


}