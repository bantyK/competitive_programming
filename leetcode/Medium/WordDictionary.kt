//https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/549/week-1-august-1st-august-7th/3413/
class WordDictionary {
    val root = TrieNode('!')

    fun addWord(word: String) {
        addWordHelper(word, root, 0)
    }

    private fun addWordHelper(word: String, root: TrieNode, currentIndex: Int) {
        if (currentIndex >= word.length) return

        val childIndex = word[currentIndex] - 'a'
        if (root.children[childIndex] == null) {
            root.children[childIndex] = TrieNode(word[currentIndex])
        }

        if (currentIndex == word.length - 1) {
            root.children[childIndex]!!.isWord = true
        }

        addWordHelper(word, root.children[childIndex]!!, currentIndex + 1)
    }

    fun search(word: String): Boolean {
        if (word.isEmpty()) return true
        return searchHelper(word, root, 0)
    }

    private fun searchHelper(word: String, root: TrieNode, currentIndex: Int): Boolean {
        if (currentIndex == word.length) {
            return root.isWord
        }
        if (word[currentIndex] == '.') {
            for (child in root.children) {
                if (child != null) {
                    if (searchHelper(word, child, currentIndex + 1)) {
                        return true
                    }
                }
            }
        } else {
            val charIndex = word[currentIndex] - 'a'
            if (root.children[charIndex] == null) return false
            return searchHelper(word, root.children[charIndex]!!, currentIndex + 1)
        }
        return false
    }

    class TrieNode(val c: Char) {
        val children = Array<TrieNode?>(26) { null }
        var isWord: Boolean = false
    }
}

fun main() {
    val obj = WordDictionary()
    obj.addWord("bad")
    obj.addWord("mad")
    obj.addWord("masd")
    obj.addWord("masds")
    println(obj.search("."))
}