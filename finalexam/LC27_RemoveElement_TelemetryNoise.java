import java.util.*;
import java.io.*;

public class LC27_RemoveElement_TelemetryNoise {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), val = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int len = removeElement(nums, val);

        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int num : nums) {
            if (num != val) {
                nums[idx++] = num;
            }
        }
        return idx;
    }
}

/*
解題思路：
- 使用雙指針，跳過等於 val 的元素。
- 覆寫原陣列，返回新長度。

時間複雜度：O(n)。
空間複雜度：O(1)。
*/
