// 409 https://leetcode.com/problems/longest-palindrome/
fun longestPalindrome(s: String): Int {
    if (s.isNullOrEmpty()) return 0

    val map = mutableMapOf<Char, Int>()
    for (char in s) {
        map[char] = map.getOrDefault(char, 0) + 1
    }
    var oddElemTaken = false
    var length = 0
    for (key in map.keys) {
        length += if (map[key]!! % 2 == 0) {
            map[key]!!
        } else {
            if (!oddElemTaken) {
                oddElemTaken = true
                map[key]!!
            } else {
                map[key]!! - 1
            }
        }
    }

    return length
}

fun main() {
    println(longestPalindrome("abccccdd") == 7)
    println(longestPalindrome("bbccccdd") == 8)
    println(longestPalindrome("bbbdeccccdd") == 9)
}