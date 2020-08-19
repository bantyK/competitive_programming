//128 : https://leetcode.com/problems/longest-consecutive-sequence/
fun longestConsecutive(nums: IntArray): Int {
    // we need to remove the duplicates because we need a sequence of consecutive numbers
    val set = HashSet<Int>(nums.toList())

    // The logic here is that we are going to count upwards.
    // So if we have number n, we will check if n + 1 exist, if it does we will increase the length
    // if we are looking at number 'n' and we find that n - 1 exist, this means that the number 'n' will be counted when we check the streak of
    // number 'n - 1' so no need to check this now.
    // This will make sure that we are not doing the repetitive job
    var longest = 0
    for (n in nums) {
        // check if (num - 1) exist in the set. If it does,we can simply ignore the current num as it will be counted when we count the num - 1
        if (set.contains(n - 1)) {
            continue
        } else {
            var current = 1
            var currentNum = n
            var streak = true
            while (streak) {
                if (set.contains(currentNum + 1)) {
                    current++;
                    currentNum += 1
                } else {
                    streak = false
                }
            }
            longest = Math.max(longest, current)
        }

    }

    return longest
}

// Trivial sorting approach(O(nlogn))
fun longestConsecutiveSort(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    nums.sort()
    val set = HashSet<Int>(nums.toList())
    var currentElem = nums[0]
    var longest = 1
    var currentLen = 1

    for (n in nums) {
        while (set.contains(currentElem + 1)) {
            currentLen += 1
            currentElem += 1
            longest = Math.max(longest, currentLen)
        }
        currentElem = n
        currentLen = 1
    }

    return longest
}

fun main() {
//    println(longestConsecutive(intArrayOf(200, 1, 100, 4, 3, 2)))
    println(longestConsecutiveSort(intArrayOf(200, 1, 100, 4, 3, 2)))
    println(longestConsecutiveSort(intArrayOf(1, 2, 0, 1)))
    println(longestConsecutiveSort(intArrayOf(1, 2, 3, 1)))
    println(longestConsecutiveSort(intArrayOf(1, 2, 3, 5, 4, 7, 6, 6, 1)))
}