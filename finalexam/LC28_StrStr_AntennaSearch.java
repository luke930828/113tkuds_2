import java.util.*;
import java.io.*;

public class LC28_StrStr_AntennaSearch {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.next();
        String needle = sc.next();

        int idx = strStr(haystack, needle);
        System.out.println(idx);
    }

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        int[] lps = buildLPS(needle);
        int i = 0, j = 0;

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
                if (j == needle.length()) return i - j;
            } else {
                if (j > 0) j = lps[j - 1];
                else i++;
            }
        }
        return -1;
    }

    private static int[] buildLPS(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0, i = 1;

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            } else {
                if (len > 0) len = lps[len - 1];
                else lps[i++] = 0;
            }
        }
        return lps;
    }
}

/*
解題思路：
- 使用 KMP 演算法，避免 O(n*m) 暴力比對。
- 建立部分匹配表 (LPS)。
- 線性掃描 haystack，比對 needle。

時間複雜度：O(n + m)，其中 n = haystack 長度，m = needle 長度。
空間複雜度：O(m)，儲存 LPS 陣列。
*/

