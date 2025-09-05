import java.util.*;

public class LC11_MaxArea_FishingPort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n
        int n = sc.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }

        int result = maxArea(height);
        System.out.println(result);
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            maxArea = Math.max(maxArea, h * width);

            // 移動較小的那一邊，因為高度受限於較短的木板
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}

/*
解題思路：
- 兩端指針分別放在最左與最右。
- 每次計算容積 = min(height[left], height[right]) * (right - left)。
- 為了嘗試更大容積，移動高度較小的指針，因為寬度縮小但可能換來更高的邊。

時間複雜度：O(n)，每個位置最多被訪問一次。
空間複雜度：O(1)。
*/
