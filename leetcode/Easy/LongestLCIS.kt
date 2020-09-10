//647 https://leetcode.com/problems/longest-continuous-increasing-subsequence/
fun findLengthOfLCIS(nums: IntArray): Int {
    if(nums.isEmpty()) return 0
    var maxCount = 1
    var currentMaxLen = 1
    for(i in 1 until nums.size) {
        if(nums[i] > nums[i - 1]) {
            currentMaxLen++
        } else {
            currentMaxLen = 1
        }
        maxCount = Math.max(maxCount, currentMaxLen)
    }
    return maxCount
}

fun main() {

}