import java.util.*;

//535 https://leetcode.com/problems/encode-and-decode-tinyurl/
public class Codec {

    private Map<Integer, String> urlMapper = new HashMap<>();

    public static void main(String[] args) {
        Codec obj = new Codec();
        final String testUrl = "https://leetcode.com/problems/design-tinyurl";
        final String encode = obj.encode(testUrl);
        System.out.println(obj.decode(encode).equals(testUrl));
    }


    public String encode(String longUrl) {
        final int code = longUrl.hashCode();
        urlMapper.put(code, longUrl);
        return "http://tinyurl.com/" + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        shortUrl = shortUrl.replace("http://tinyurl.com/", "");
        return urlMapper.get(Integer.parseInt(shortUrl));
    }
}
