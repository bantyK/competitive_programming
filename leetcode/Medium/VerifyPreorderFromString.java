import java.util.Stack;
//331  https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
public class VerifyPreorderFromString {
    public static void main(String[] args) {
        VerifyPreorderFromString obj = new VerifyPreorderFromString();
        System.out.println(obj.isValidSerialization("1,#"));
        System.out.println(obj.isValidSerialization("9,#,#,1"));
        System.out.println(obj.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(obj.isValidSerialization("1,2,5,#,6,#,#,#,3,4,#,7,#,#,#"));
        System.out.println(obj.isValidSerialization("9,#,92,#,#"));
    }

    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int edges = 1;

        for(String node : nodes) {
            edges--; // one node is added, so one edge will be consumed
            if(edges < 0) return false;
            if(!node.equals("#")) {
                // this is a non-null node, so there will be 2 outgoing edges from this node
                edges += 2;
            }
        }
        return true;
    }

    // Using stack
    public boolean isValidSerialization2(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] split = preorder.split(",");

        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("#")) {
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) return false;
                    stack.pop();
                }

            }
            stack.push(split[i]);
        }
        if (!stack.isEmpty() && stack.peek().equals("#")) stack.pop();
        return stack.isEmpty();
    }
}