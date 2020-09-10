//299 https://leetcode.com/problems/bulls-and-cows/
fun getHint(secret: String, guess: String): String {
    var bullCount = 0
    var cowCount = 0
    val secretCharMap: MutableMap<Char, Int> = HashMap()

    for (i in secret.indices) {
        val secretChar = secret[i]
        if (secretChar == guess[i]) {
            bullCount++
        } else {
            secretCharMap[secretChar] = secretCharMap.getOrDefault(secretChar, 0) + 1
        }
    }

    for (i in secret.indices) {
        val key = guess[i]
        if (secretCharMap.containsKey(key) && secretCharMap[key]!! > 0) {
            cowCount++
            secretCharMap[key] = secretCharMap[key]!! - 1
        }
    }

    return "${bullCount}A${cowCount}B"
}

fun main() {
    println(getHint("1807","7810"))
    println(getHint("1123", "0111"))
    println(getHint("0123", "0111"))
    println(getHint("1111", "1111"))
    println(getHint("1111", "2222"))
}