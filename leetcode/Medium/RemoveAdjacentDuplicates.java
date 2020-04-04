import java.util.*;

// 1209 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
public class RemoveAdjacentDuplicates {
    public static void main(String[] args) {
        RemoveAdjacentDuplicates obj = new RemoveAdjacentDuplicates();
        System.out.println(obj.removeDuplicates("deeedbbcccbdaa", 3));
//        System.out.println(obj.removeDuplicates("abcd", 2));
    }

    public String removeDuplicates(String s, int k) {
        LinkedList<StackNode> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || stack.peek().c != ch) {
                stack.push(new StackNode(ch, 1));
            } else {
                final StackNode top = stack.peek();
                if (top.index == k - 1) {
                    stack.pop();
                } else {
                    top.index += 1;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        final int size = stack.size();
        for (int i = 0; i < size; i++) {
            StackNode node = stack.removeLast();
            while(node.index-- > 0)
                builder.append(node.c);
        }
        return builder.toString();
    }

    class StackNode {
        char c;
        int index;

        public StackNode(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }
}
