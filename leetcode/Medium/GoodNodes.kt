import input.TreeNode

//1448 https://leetcode.com/problems/count-good-nodes-in-binary-tree/
class GoodNodes {
    fun goodNodes(root: TreeNode?): Int {
        return if (root == null) 0 else helper(root, root.`val`)
    }

    private fun helper(root: TreeNode?, highest: Int): Int {
        if (root == null) return 0
        var count = 0
        if (root.`val` >= highest) {
            count++
        }
        val max = Math.max(highest, root.`val`)
        return count + helper(root.left, max) + helper(root.right, max)
    }
}