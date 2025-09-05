import java.util.*;
import java.io.*;

public class LC34_FindFirstLastPosition_GPSRange {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    right = mid - 1; // 繼續找更左邊
                } else {
                    left = mid + 1;  // 繼續找更右邊
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return bound;
    }
}

/*
解題思路：
- 使用二分搜尋兩次：
  - 第一次找最左邊出現位置。
  - 第二次找最右邊出現位置。
- 若不存在則返回 [-1, -1]。

時間複雜度：O(log n)，二分搜尋。
空間複雜度：O(1)。
*/
