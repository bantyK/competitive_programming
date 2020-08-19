import java.lang.StringBuilder

fun toGoatLatin(s:String): String {
    val builder = StringBuilder()
    val vowels = setOf<Char>('a','e','i','o','u','A','I','E','O','U')
    var last = "a"
    for(word in s.split(" ")) {
        if(vowels.contains(word[0])) {
            builder.append(word)
        } else {
           builder.append(word.substring(1)).append(word[0])
        }
        builder.append("ma").append(last).append(" ")
        last += "a"
    }
    return builder.toString().trim()
}

fun main() {
    println(toGoatLatin("I speak Goat Latin"))
}