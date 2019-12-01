//925  https://leetcode.com/problems/long-pressed-name/
public class LongPressedName {
    public static void main(String[] args) {
        LongPressedName obj = new LongPressedName();
        System.out.println(obj.isLongPressedName2("alex", "aleeexxxxxxg"));
    }

    private boolean isLongPressedName2(String name, String typed) {
        int i = 0, m = name.length(), n = typed.length();
        for (int j = 0; j < n; ++j)
            if (i < m && name.charAt(i) == typed.charAt(j))
                ++i;
            else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
                return false;
        return i == m;
    }


    public boolean isLongPressedName(String name, String typed) {
        int namePtr = 0;
        if (name.length() == 0) return typed.length() == 0;
        if (typed.length() == 0) return false;

        int i = 0;
        while (i < typed.length()) {
            if (namePtr == name.length()) return true;
            if (name.charAt(namePtr) == typed.charAt(i)) {
                while (namePtr < name.length() && i < typed.length() && name.charAt(namePtr) == typed.charAt(i)) {
                    i++;
                    namePtr++;
                }

                while (i < typed.length() - 1 && typed.charAt(i) == typed.charAt(i - 1)) {
                    i++;
                }
            } else {
                return false;
            }
        }
        return namePtr == name.length();
    }
}
