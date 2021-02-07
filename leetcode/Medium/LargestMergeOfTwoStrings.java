//1754 https://leetcode.com/problems/largest-merge-of-two-strings/
public class LargestMergeOfTwoStrings {
	public static void main(String[] args) {
		// "aaaabaab" "aaaac"
		// "qqqqqqqqqeqeqqeeqqq" "qqqqqqqqeqqqeeqqeeqqqqqeq"
		// "uuurruuuruuuuuuuuruuuuu" "urrrurrrrrrrruurrrurrrurrrrruu"
		// "ssssssssssssssss" "sssksskssssssssssssesskss"
		// "nngnnnbnttnngsnnntnbgnnnn" "nnnnnnnnnnnnnntn"

		LargestMergeOfTwoStrings obj = new LargestMergeOfTwoStrings();
		System.out.println(obj.largestMerge("uuurruuuruuuuuuuuruuuuu", "urrrurrrrrrrruurrrurrrurrrrruu"));
	}

	public String largestMerge(String word1, String word2) {
		int i = 0, j = 0;
		StringBuilder builder = new StringBuilder();

		while (i < word1.length() && j < word2.length()) {
			if (word1.charAt(i) > word2.charAt(j)) {
				builder.append(word1.charAt(i));
				i++;
			} else if (word1.charAt(i) < word2.charAt(j)) {
				builder.append(word2.charAt(j));
				j++;
			} else {
				if (shouldUseWord1(word1, word2, i, j)) {
					builder.append(word1.charAt(i));
					i++;
				} else {
					builder.append(word2.charAt(j));
					j++;
				}
			}
		}

		// add the remaining chars of whichever is remaining out of word1 or word2
		builder.append(word1.substring(i));
		builder.append(word2.substring(j));

		return builder.toString();
	}

	private boolean shouldUseWord1(String word1, String word2, int i, int j) {

		while (i < word1.length() && j < word2.length()) {
			if (word1.charAt(i) == word2.charAt(j)) {
				i++;
				j++;
			} else if (word1.charAt(i) > word2.charAt(j)) {
				return true; // word1 is larger
			} else {
				return false;
			}
		}

		// if we traverse through the whole strings without finding any difference
		// the use the larger one
		if (i < word1.length()) {
			// if this condition is true, means word2 has ended, meaning word1 is larger and
			// we should append word1
			return true;
		}

		return false;
	}

}
