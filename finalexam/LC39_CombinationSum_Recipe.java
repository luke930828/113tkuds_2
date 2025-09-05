import java.util.*;
import java.io.*;

public class LC39_CombinationSum_Recipe {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();

        List<List<Integer>> res = combinationSum(candidates, target);

        for (List<Integer> comb : res) {
            for (int num : comb) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 避免重複排列
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start,
                                  List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // 剪枝
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, path, result); // 可以重複使用
            path.remove(path.size() - 1);
        }
    }
}

/*
解題思路：
- 使用回溯法 (Backtracking)。
- 每次嘗試加入一個候選數字，遞迴搜尋剩餘目標。
- 若 target == 0，加入答案。
- 因為允許重複使用，所以遞迴呼叫仍從 i 開始。

時間複雜度：O(S)，S 為所有可能組合的總數量。
空間複雜度：O(target)，遞迴深度最深為 target/candidate。
*/
