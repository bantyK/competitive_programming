//3 https://leetcode.com/problems/longest-substring-without-repeating-characters/

fun lengthOfLongestSubstring(s: String): Int {
    var right = 0
    var left = 0
    var maxLen = 0
    val seen : MutableSet<Char> = HashSet()

    while(right < s.length) {
        val rightChar = s[right]
        if(!seen.contains(rightChar)) {
            seen.add(rightChar)
            maxLen = Math.max(right - left + 1, maxLen)
            right++
        } else {
            seen.remove(s[left++])
        }
    }

    return maxLen
}

fun main() {
    println(lengthOfLongestSubstring("pwwkew"))

}