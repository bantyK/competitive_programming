
//1009 https://leetcode.com/problems/complement-of-base-10-integer/
import java.util.*;

public class BitwiseComplement {
	public static void main(String[] args) {
		BitwiseComplement obj = new BitwiseComplement();
		System.out.println(obj.bitwiseComplement(10));
		System.out.println(obj.bitwiseComplement(7));
		System.out.println(obj.bitwiseComplement(1000000000));
	}
	public int bitwiseComplement(int N) {
		String binary = Integer.toBinaryString(N);
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < binary.length(); i++) {
			builder.append((binary.charAt(i) == '1') ? '0' : '1');
		}
		return Integer.parseInt(builder.toString(), 2);
	}
}
