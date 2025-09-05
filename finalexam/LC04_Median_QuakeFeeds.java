import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n, m
        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextDouble();
        }
        for (int i = 0; i < m; i++) {
            B[i] = sc.nextDouble();
        }

        double median = findMedianSortedArrays(A, B);
        System.out.printf("%.1f\n", median); // 輸出保留 1 位小數
    }

    public static double findMedianSortedArrays(double[] A, double[] B) {
        if (A.length > B.length) { 
            return findMedianSortedArrays(B, A); // 保證 A 是較短的
        }

        int n = A.length;
        int m = B.length;
        int totalLeft = (n + m + 1) / 2;

        int left = 0, right = n; 
        while (left <= right) {
            int i = (left + right) / 2;  // A 左邊的元素數量
            int j = totalLeft - i;       // B 左邊的元素數量

            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // 條件成立，找到中位數位置
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                right = i - 1; // 移動 i
            } else {
                left = i + 1;  // 移動 i
            }
        }
        return -1; // 不會到這裡
    }
}

/*
解題思路：
- 題目等價於 LeetCode #4 Median of Two Sorted Arrays。
- 核心技巧：對較短的陣列 A 做二分切割，確保切割後左邊元素數量 = (n+m+1)/2。
- 驗證是否滿足：Aleft <= Bright && Bleft <= Aright。
- 若滿足，即可根據總長度奇偶性決定中位數。
- 邊界情況：i=0、i=n、j=0、j=m 時，使用 ±∞ 作為左右邊界。

時間複雜度：O(log(min(n, m)))，二分搜尋的長度取決於較短陣列。
空間複雜度：O(1)。
*/
