import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入第一行：票價列表
        String[] parts = sc.nextLine().split(" ");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        // 輸入第二行：目標總價
        int target = sc.nextInt();

        // 解題
        int[] result = twoSum(nums, target);

        // 輸出
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1}; // 不會發生
    }
}

/*
解題思路：
- 使用 HashMap 快速檢查是否存在配對票價。
- 每次掃描 nums[i]，檢查 target - nums[i] 是否已出現過。

時間複雜度：O(n)，因為只需遍歷一次陣列。
空間複雜度：O(n)，因為需要使用 HashMap 儲存已出現的票價。
*/
