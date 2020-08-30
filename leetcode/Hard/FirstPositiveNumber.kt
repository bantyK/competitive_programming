//41 https://leetcode.com/problems/first-missing-positive/

// Using sorting. This method requires no extra space
fun firstMissingPositive(nums: IntArray): Int {
    nums.sort()
    var i = 0
    while (i < nums.size && nums[i] <= 0) {
        i++
    }

    var j = 1
    while (i < nums.size) {
        if (nums[i] != j) {
            return j
        }
        while (i < nums.size && nums[i] == j) {
            i++
        }
        j++
    }
    return j
}

// Using extra space
fun firstMissingPositiveWithExtraSpace(nums: IntArray): Int {
    val set = nums.toSet()
    var i = 1
    while (true) {
        if (!set.contains(i)) {
            return i
        } else {
            i++
        }
    }
}

// Linear complexity with no space
fun firstMissingPositiveOptimal(nums: IntArray): Int {
    val DUMMY_VALUE = nums.size + 1
    // pre process the array and remove the zero and negative values and replace those with dummy value

    for (i in nums.indices) {
        if (nums[i] <= 0) {
            nums[i] = DUMMY_VALUE
        }
    }

    for (i in nums.indices) {
        val num = Math.abs(nums[i]) - 1
        if (num >= nums.size || nums[num] <= 0) continue
        nums[num] *= -1
    }

    for (i in nums.indices) {
        if (nums[i] > 0) {
            return i + 1
        }
    }
    return nums.size + 1 // this will not execute if the input is valid
}

fun main() {
    println(firstMissingPositiveOptimal(intArrayOf(0, 1, 2)))
    println(firstMissingPositiveOptimal(intArrayOf(0, 1, -1, 2, 3)))
    println(firstMissingPositiveOptimal(intArrayOf(0, -1, 2, 3)))
    println(firstMissingPositiveOptimal(intArrayOf(1, 7, 8, 9, 11, 12)))
    println(firstMissingPositiveOptimal(intArrayOf(0, 2, 2, 1, 1, 3, 4, 4, 4, 4, 5)))
}

/*
0 1 2 Dummy = 4
4 1 2
4 -1 -2
 */