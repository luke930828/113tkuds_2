import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); // 乘客搭乘紀錄字串
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}

/*
解題思路：
- 使用滑動窗口（雙指針）避免重複字元。
- left 與 right 指標維護一個無重複子字串。

時間複雜度：O(n)，每個字元最多進出集合一次。
空間複雜度：O(min(n, k))，k 為字母表大小。
*/
