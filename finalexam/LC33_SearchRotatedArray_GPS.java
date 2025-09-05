import java.util.*;
import java.io.*;

public class LC33_SearchRotatedArray_GPS {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // 左半部分有序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // 右半部分有序
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

/*
解題思路：
- 使用二分搜尋。
- 根據中點判斷哪一半有序，縮小搜尋範圍。
- 保證每次能排除一半數字。

時間複雜度：O(log n)，二分搜尋。
空間複雜度：O(1)。
*/
