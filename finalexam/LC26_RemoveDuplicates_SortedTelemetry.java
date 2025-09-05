import java.util.*;
import java.io.*;

public class LC26_RemoveDuplicates_SortedTelemetry {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int len = removeDuplicates(nums);

        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int idx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[idx++] = nums[i];
            }
        }
        return idx;
    }
}

/*
解題思路：
- 使用雙指針，維護一個寫入位置 idx。
- 當前元素與前一個不相等，則寫入 nums[idx]。
- 最後返回 idx 作為新長度。

時間複雜度：O(n)，單次遍歷。
空間複雜度：O(1)。
*/
