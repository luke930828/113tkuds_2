import java.util.*;

public class AdvancedStringRecursion {

    // 字串所有排列
    static void permute(String str, String result) {
        if (str.isEmpty()) {
            System.out.println(result);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permute(str.substring(0, i) + str.substring(i + 1), result + str.charAt(i));
        }
    }

    // 簡易遞迴字串匹配
    static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        if (pattern.charAt(0) == '*')
            return match(text, pattern.substring(1)) || (!text.isEmpty() && match(text.substring(1), pattern));
        else
            return !text.isEmpty() && (text.charAt(0) == pattern.charAt(0)) &&
                    match(text.substring(1), pattern.substring(1));
    }

    // 遞迴移除重複字符
    static String removeDuplicates(String str, Set<Character> seen) {
        if (str.isEmpty()) return "";
        char ch = str.charAt(0);
        if (seen.contains(ch)) return removeDuplicates(str.substring(1), seen);
        seen.add(ch);
        return ch + removeDuplicates(str.substring(1), seen);
    }

    // 遞迴列出所有子字串組合
    static void allSubstrings(String str, String curr, int index) {
        if (index == str.length()) {
            if (!curr.isEmpty()) System.out.println(curr);
            return;
        }
        allSubstrings(str, curr + str.charAt(index), index + 1); // include
        allSubstrings(str, curr, index + 1); // exclude
    }

    public static void main(String[] args) {
        System.out.println("排列：");
        permute("abc", "");

        System.out.println("\n字串匹配 *ab vs cab: " + match("cab", "*ab"));

        System.out.println("\n移除重複：" + removeDuplicates("banana", new HashSet<>()));

        System.out.println("\n所有子字串：");
        allSubstrings("abc", "", 0);
    }
}

