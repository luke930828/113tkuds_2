import java.util.*;
import java.io.*;

public class LC40_CombinationSum2_RecipeUnique {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();

        List<List<Integer>> res = combinationSum2(candidates, target);

        for (List<Integer> comb : res) {
            for (int num : comb) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序方便去重
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
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 去重
            if (candidates[i] > target) break;

            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, result); // 不能重複使用，所以用 i+1
            path.remove(path.size() - 1);
        }
    }
}

/*
解題思路：
- 與題目 39 類似，但不能重複使用元素。
- 先排序，確保相同數字相鄰。
- 每層遞迴時，若當前元素與前一個相同，則跳過，避免重複解。

時間複雜度：O(S)，S 為所有可能組合的總數量。
空間複雜度：O(n)，遞迴深度最大為 n。
*/
 
}
