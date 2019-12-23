import java.util.*;

public class BinarySearchInTree {
    public static void main(String[] args) {
        BinarySearchInTree obj = new BinarySearchInTree();

        Node root = new Node(5);
        root.left = new Node(8);
        root.right = new Node(10);
        root.left.left = new Node(13);
        root.left.right = new Node(23);
        root.right.left = new Node(25);
        root.right.right = new Node(30);
        root.left.left.left = new Node(32);
        root.left.left.right = new Node(40);
        root.left.right.left = new Node(50);
        int[] keys = new int[]{5, 8, 10, 13, 23, 25, 30, 32, 40, 50};
        for (int key : keys) {
            System.out.println(findKey(root, key));
        }

    }

    private static boolean findKey(Node root, int key) {
        // level where key may exist
        int level = findLevel(root, key);
        if (level == -1) {
            return false;
        } else if (level == -2) {
            return true;
        }
        return binarySearch(root, 0, (int) Math.pow(2, level) - 1, key, level);
    }

    private static boolean binarySearch(Node root, int start, int end, int key, int level) {

        if (end >= start) {

            int mid = (start + end) / 2;
            List<Integer> encoding = generateGray(level, mid);

            int elementFound = traverse(root, encoding);
            // element found will return -1 in case of incomplete trees, so we need to traverse only to the left side, because it is a complete binary tree
            if (elementFound == -1) return binarySearch(root, start, mid - 1, key, level);
            if (elementFound == key) {
                return true;
            } else if (elementFound < key) {
                return binarySearch(root, mid + 1, end, key, level);
            } else {
                return binarySearch(root, start, mid - 1, key, level);
            }
        }

        return false;
    }

    // O(log(n))
    private static ArrayList<Integer> generateGray(int n, int x) {
        ArrayList<Integer> gray = new ArrayList<Integer>();
        int i = 0;
        while (x > 0) {
            gray.add(x % 2);
            x = x / 2;
            i++;
        }
        // Reverse the encoding till here
        Collections.reverse(gray);

        // Leftmost digits are filled with 0
        for (int j = 0; j < n - i; j++)
            gray.add(0, 0);

        return gray;
    }

    //    log(n)
    private static int traverse(Node root, List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            int direction = path.get(i);

            if (direction == 0) {
                if (root.left == null) return -1;
                root = root.left;
            } else {
                if (root.right == null) return -1;
                root = root.right;
            }
        }
        return root.data;
    }

    private static int findLevel(Node root, int key) {
        if (key < root.data) {
            // key could not be found
            return -1;
        }

        if (key == root.data) {
            // root itself is the key.
            return 0;
        }

        int currentLevel = 0;

        while (root.left != null && root.left.data <= key) {
            root = root.left;
            currentLevel += 1;
        }

        return currentLevel;
    }


    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }
}
