import java.util.*;
import java.io.*;

public class LC23_MergeKSorted_TelemetryFeeds {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int n = sc.nextInt();
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(sc.nextInt());
            }
            lists.add(row);
        }

        List<Integer> merged = mergeKLists(lists);

        for (int val : merged) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // 使用 PriorityQueue (最小堆) 合併 K 個有序數列
    public static List<Integer> mergeKLists(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // 初始化：將每個串流的第一個元素丟進堆
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new int[]{lists.get(i).get(0), i, 0});
            }
        }

        // 每次取出最小值，並把來源串流的下一個元素加入
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int val = cur[0], listIdx = cur[1], elemIdx = cur[2];
            result.add(val);

            if (elemIdx + 1 < lists.get(listIdx).size()) {
                pq.offer(new int[]{lists.get(listIdx).get(elemIdx + 1), listIdx, elemIdx + 1});
            }
        }
        return result;
    }
}

/*
解題思路：
- 使用最小堆維護 k 個串流的當前最小值。
- 每次取出最小值並將來源串流下一個元素加入堆。
- 最終得到合併後的排序序列。

時間複雜度：O(N log k)，其中 N 為總元素數量，k 為串流數。
空間複雜度：O(k)，堆的大小。
*/
