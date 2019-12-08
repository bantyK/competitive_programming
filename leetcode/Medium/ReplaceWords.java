import java.util.*;

// 648 https://leetcode.com/problems/replace-words/
public class ReplaceWords {
    public static void main(String[] args) {
        ReplaceWords obj = new ReplaceWords();
        String s = obj.replaceWords(Arrays.asList("e", "k", "c", "harqp", "h", "gsafc", "vn", "lqp", "soy", "mr", "x", "iitgm"),
                "ikkbp miszkays wqjferqoxjwvbieyk gvcfldkiavww vhokchxz dvypwyb bxahfzcfanteibiltins ueebf lqhflvwxksi dco kddxmckhvqifbuzkhstp wc ytzzlm gximjuhzfdjuamhsu gdkbmhpnvy ifvifheoxqlbosfww mengfdydekwttkhbzenk wjhmmyltmeufqvcpcxg hthcuovils ldipovluo aiprogn nusquzpmnogtjkklfhta klxvvlvyh nxzgnrveghc mpppfhzjkbucv cqcft uwmahhqradjtf iaaasabqqzmbcig zcpvpyypsmodtoiif qjuiqtfhzcpnmtk yzfragcextvx ivnvgkaqs iplazv jurtsyh gzixfeugj rnukjgtjpim hscyhgoru aledyrmzwhsz xbahcwfwm hzd ygelddphxnbh rvjxtlqfnlmwdoezh zawfkko iwhkcddxgpqtdrjrcv bbfj mhs nenrqfkbf spfpazr wrkjiwyf cw dtd cqibzmuuhukwylrnld dtaxhddidfwqs bgnnoxgyynol hg dijhrrpnwjlju muzzrrsypzgwvblf zbugltrnyzbg hktdviastoireyiqf qvufxgcixvhrjqtna ipfzhuvgo daee r nlipyfszvxlwqw yoq dewpgtcrzausqwhh qzsaobsghgm ichlpsjlsrwzhbyfhm ksenb bqprarpgnyemzwifqzz oai pnqottd nygesjtlpala qmxixtooxtbrzyorn gyvukjpc s mxhlkdaycskj uvwmerplaibeknltuvd ocnn frotscysdyclrc ckcttaceuuxzcghw pxbd oklwhcppuziixpvihihp"
        );
        System.out.println(s);
    }

    public String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        TrieNode root = new TrieNode();

        for (String word : dict) {
            insert(root, word);
        }

        for (int i = 0; i < words.length; i++) {
            words[i] = searchWord(root, words[i]);
        }

        StringBuilder builder = new StringBuilder();
        for (String s : words) builder.append(s).append(" ");
        return builder.toString().trim();
    }

    private String searchWord(TrieNode root, String word) {
        TrieNode t = root;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            TrieNode[] children = t.children;
            char c = word.charAt(i);
            int idx = c - 'a';
            if (children[idx] != null) {
                t = children[idx];
                builder.append(c);
                if (t.endOfWord) return builder.toString();
            } else {
                return word;
            }
        }

        return word;
    }

    private void insert(TrieNode root, String word) {
        TrieNode[] children = root.children;
        TrieNode t;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';

            if (children[index] != null) {
                t = children[index];
            } else {
                t = new TrieNode();
                children[index] = t;
            }
            children = t.children;

            if (i == word.length() - 1) {
                t.endOfWord = true;
            }
        }
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord;
    }
}
