import java.util.*;

public class LC20_ValidParentheses_HTMLCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        System.out.println(isValid(s) ? "true" : "false");
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }
}

/*
解題思路：
- 使用堆疊存對應括號。
- 左括號入堆疊時放入對應右括號。
- 遇到右括號檢查是否匹配。

時間複雜度：O(n)，n 為字串長度。
空間複雜度：O(n)，最壞情況全部入堆疊。
*/
