import java.util.*;

public class LC15_ThreeSum_TeamFormation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> triplet : result) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳過重複
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}

/*
解題思路：
- 排序後固定一個數 nums[i]，用雙指針找另外兩數。
- 利用 sum==0 時記錄答案，並避開重複數字。
- 確保輸出所有唯一解組合。

時間複雜度：O(n^2)，排序 O(n log n)，主要為雙指針遍歷。
空間複雜度：O(1)，不含輸出。
*/
