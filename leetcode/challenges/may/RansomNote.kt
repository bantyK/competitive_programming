// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3318/
class RansomNote {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val countArr = IntArray(26)
        for(char in magazine.toCharArray()) {
            countArr[char - 'a']++;
        }

        for(char in ransomNote.toCharArray()) {
            val index = char - 'a'
            if(countArr[index] == 0) return false
            countArr[index]--
        }
        return true
    }
}

fun main() {
    val ransom = RansomNote()
    println(ransom.canConstruct("aa","aabc"))
    println(ransom.canConstruct("af","aabc"))
}
