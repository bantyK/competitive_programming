import input.TreeNode

//1373 https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
private var sum: Int = 0

fun maxSumBST(root: TreeNode?): Int {
    if (root == null) return 0
    dfs(root)
    return sum
}

private fun dfs(root: TreeNode?): Node {
    if (root == null) {
        return Node(true, 0, 0, 0)
    }
    var isBST: Boolean

    if (root.left == null && root.right == null) {
        sum = Math.max(sum, root.`val`)
        return Node(true, root.`val`, root.`val`, root.`val`)
    }

    if (root.left == null) {
        val right = dfs(root.right)
        isBST = right.isBST && root.`val` < right.min
        if (isBST) sum = Math.max(sum, root.`val` + right.sum)
        return Node(isBST, root.`val` + right.sum, right.max, root.`val`)
    }

    if (root.right == null) {
        val left = dfs(root.left)
        isBST = left.isBST && root.`val` > left.max
        if (isBST) sum = Math.max(sum, root.`val` + left.sum)
        return Node(isBST, root.`val` + left.sum, root.`val`, left.min)
    }

    val left = dfs(root.left)
    val right = dfs(root.right)
    isBST = left.isBST && right.isBST && root.`val` > left.max && right.min > root.`val`
    val currentSum = left.sum + root.`val` + right.sum
    if (isBST) sum = Math.max(sum, currentSum)
    return Node(isBST, currentSum, right.max, left.min)
}

private data class Node(
        val isBST: Boolean, // if the Tree is BST or not
        val sum: Int, // the sum of all the nodes in the subtree
        val max: Int, // the maximum value node in the right subtree
        val min: Int // the minimum value node in the left subtree
)