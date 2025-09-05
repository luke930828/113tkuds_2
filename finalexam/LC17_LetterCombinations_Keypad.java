import java.util.*;

public class LC17_LetterCombinations_Keypad {
    private static final String[] mapping = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        List<String> result = letterCombinations(digits);
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    private static void backtrack(String digits, int idx, StringBuilder path, List<String> res) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }
        String letters = mapping[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, idx + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

/*
解題思路：
- 建立數字對應字母表。
- 使用回溯（DFS）生成所有可能組合。
- digits 每位對應一組字母，逐步展開。

時間複雜度：O(4^n)，n 為輸入長度（最多 4 種字母）。
空間複雜度：O(n)，遞迴深度。
*/
