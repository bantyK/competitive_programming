import java.lang.StringBuilder

//459 https://leetcode.com/problems/repeated-substring-pattern/
fun repeatedSubstringPattern(s: String): Boolean {
    val sLen = s.length
    for (i in 0 until sLen / 2) {
        val substring = s.substring(0, i + 1)
        if (sLen % substring.length == 0) {
            var repetitions = sLen / substring.length
            val builder = StringBuilder()
            while (repetitions > 0) {
                builder.append(substring)
                repetitions--
            }

            if (builder.toString() == s) return true
        }
    }
    return false
}

fun repeatedSubstringPattern2(s: String): Boolean {
    val n = s.length

    for (l in n / 2 downTo 1) {
        var i = 0

        while (i + l < n && s[i] == s[i + l]) {
            i++;
        }
        if (i + l == n) {
            return true
        }
    }
    return false
}

fun main() {
    println(repeatedSubstringPattern("abcabcab"))
    println(repeatedSubstringPattern("abcabcabc"))
    println(repeatedSubstringPattern("abcabcadc"))
    println(repeatedSubstringPattern(""))
    println(repeatedSubstringPattern("a"))
    println(repeatedSubstringPattern("abaababaab"))
}