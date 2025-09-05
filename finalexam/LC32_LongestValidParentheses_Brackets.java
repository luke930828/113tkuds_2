import java.util.*;
import java.io.*;

public class LC32_LongestValidParentheses_Brackets {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 起始標記

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}

/*
解題思路：
- 使用棧來記錄左括號索引。
- 遇到右括號時，嘗試配對，並更新最長長度。
- 若棧空，則把當前索引作為新的基準。

時間複雜度：O(n)，遍歷一次字串。
空間複雜度：O(n)，最壞情況棧存所有索引。
*/
