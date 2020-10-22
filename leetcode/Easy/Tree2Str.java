import input.TreeNode;

//606 https://leetcode.com/problems/construct-string-from-binary-tree/
public class Tree2Str {
    public static void main(String[] args) {
        Tree2Str obj = new Tree2Str();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);

        System.out.println(obj.tree2str(new TreeNode(1)));
    }

    public String tree2str(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        helper(root, builder);
        if (builder.length() == 0) return "";
        return builder.substring(1, builder.length() - 1);
    }

    private void helper(TreeNode root, StringBuilder builder) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            builder.append("(").append(root.val).append(")");
            return;
        } else {
            builder.append("(");
            builder.append(root.val);
        }

        if (root.left == null) {
            builder.append("()");
        } else {
            helper(root.left, builder);
        }

        if (root.right != null) {
            helper(root.right, builder);
        }

        builder.append(")");
    }

}
