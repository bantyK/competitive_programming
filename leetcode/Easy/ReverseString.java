public class ReverseString {
	public void reverseString(char[] s) {
        int last = s.length - 1;
        int first = 0;
        while(first < last) {
            char temp = s[last];
            s[last] = s[first];
            s[first] = temp;
            
            last--;
            first++;
       }
	}
}
