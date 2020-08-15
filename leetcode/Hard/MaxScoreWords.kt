//1255 https://leetcode.com/problems/maximum-score-words-formed-by-letters/
fun maxScoreWords(words: Array<String>, letters: CharArray, score: IntArray): Int {
    val charCountMap = mutableMapOf<Char, Int>()

    for (char in letters) {
        val prevCount = charCountMap.getOrDefault(char, 0)
        charCountMap[char] = prevCount + 1
    }

    return helper(words, charCountMap, score, 0)
}

fun helper(words: Array<String>, map: MutableMap<Char, Int>, scoreArr: IntArray, currentWordIndex: Int): Int {
    if (currentWordIndex >= words.size) {
        return 0
    }

    // Choice 1: Taking the word present in the words array at position currentWordIndex
    var scoreOfCurrentWord = 0
    val newMap = mutableMapOf<Char, Int>()
    newMap.putAll(map)
    for (char in words[currentWordIndex]) {
        if (!newMap.containsKey(char) || newMap[char] == 0) {
            scoreOfCurrentWord = 0
            break
        }
        newMap[char] = newMap[char]!! - 1
        scoreOfCurrentWord += scoreArr[char - 'a']
    }

    val choice1 = scoreOfCurrentWord + helper(words, newMap, scoreArr, currentWordIndex + 1)

    // Choice 2: Not taking the word at index currentWordIndex
    val choice2 = helper(words, map, scoreArr, currentWordIndex + 1)

    return Math.max(choice1, choice2)
}


fun main() {
    var words = arrayOf("dog", "cat", "dad", "good")
    var letters = charArrayOf('a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o')
    var score = intArrayOf(1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    println(maxScoreWords(words, letters, score))

    words = arrayOf("xxxz", "ax", "bx", "cx")
    letters = charArrayOf('z', 'a', 'b', 'c', 'x', 'x', 'x')
    score = intArrayOf(4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10)
    println(maxScoreWords(words, letters, score))

}