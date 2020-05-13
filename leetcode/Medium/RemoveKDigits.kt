//402 https://leetcode.com/problems/remove-k-digits/
class RemoveKDigits {
    fun removeKdigits(num: String?, _k: Int): String? {
        var k = _k
        num?.let {
            if(num.length == k) return "0"

            val list = it.toCharArray().toMutableList()

            while(k > 0) {
                if(list.size > 1 && list[1] == '0') {
                    list.removeAt(0)
                    var i = 0
                    while(i < list.size && list[0] == '0') {
                        list.removeAt(0)
                    }
                } else {
                    var i = 0
                    while(i < list.size - 1 && list[i] <= list[i + 1]) {
                        i++
                    }
                    list.removeAt(i)
                }

                k--
            }

            val str = list.joinToString(separator = "")
            return if(str.equals("")) "0" else str
        }

        return null

    }
}

fun main() {
    val obj = RemoveKDigits();
    println(obj.removeKdigits("200343", 1))
    println(obj.removeKdigits("4873648", 3))
    println(obj.removeKdigits("1463787", 3))
    println(obj.removeKdigits("10", 2))
    println(obj.removeKdigits("10", 1))
    println(obj.removeKdigits("1432219",3))
}