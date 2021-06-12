// 427 https://leetcode.com/problems/construct-quad-tree/
public class QuadTree {
    public static void main(String[] args) {
        QuadTree obj = new QuadTree();
        int[][] grid = new int[][]{
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {0, 0, 1, 1}};
        int[][] grid12 = {
                {0, 1},
                {1, 0}};
        Node node = obj.construct(grid12);
        System.out.println(node);
    }

    public Node construct(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        return builder(grid, 0, rows - 1, 0, cols - 1);
    }


    private Node builder(int[][] grid, int rowS, int rowE, int colS, int colE) {
        if (rowS == rowE) {
            boolean val = grid[rowS][colS] == 1;
            return new Node(val, true);
        }

        int rowMid = (rowS + rowE) / 2;
        int colMid = (colS + colE) / 2;

        Node topLeft = builder(grid, rowS, rowMid, colS, colMid);
        Node topRight = builder(grid, rowS, rowMid, colMid + 1, colE);
        Node bottomLeft = builder(grid, rowMid + 1, rowE, colS, colMid);
        Node bottomRight = builder(grid, rowMid + 1, rowE, colMid + 1, colE);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
            if (topLeft.val && topRight.val && bottomLeft.val && bottomRight.val) {
                return new Node(true, true);
            } else if (!topLeft.val && !topRight.val && !bottomLeft.val && !bottomRight.val) {
                return new Node(false, true);
            }
        }

        Node node = new Node(true, false);
        node.topLeft = topLeft;
        node.topRight = topRight;
        node.bottomLeft = bottomLeft;
        node.bottomRight = bottomRight;
        return node;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}