// 410 https://leetcode.com/problems/split-array-largest-sum/

// Binary search
fun splitArray(nums: IntArray, m: Int): Int {
    var low = nums.max()!!
    var high = nums.sum()

    while (low < high) {
        val mid = low + (high - low) / 2
        // this mid represent the largest sum that we are considering
        // we will split the array considering that the subarrays wont have the sum larger than this
        val pieces = split(nums, mid)
        // pieces represent the number of divisions that we have to do if we consider mid as largest sum

        if (pieces > m) {
            // pieces that we have to split the array is greater than the maximum pieces allowed. This means that the
            // sum that we are considering is too low. Increase the sum.
            low = mid + 1
        } else {
            // pieces that we got is less than or equal to what is allowed. So try to minimise the sum
            high = mid;
        }
    }

    // at the end low and high will converge to one value that is the answer, return either one of them
    return low // or high

}

private fun split(nums: IntArray, largestSum: Int): Int {
    var pieces = 1
    var tempSum = 0

    for (num in nums) {
        if (tempSum + num > largestSum) {
            tempSum = num
            pieces++
        } else {
            tempSum += num
        }
    }
    return pieces
}

fun main() {
    println(splitArray(intArrayOf(7, 2, 5, 10, 8), 2))
}