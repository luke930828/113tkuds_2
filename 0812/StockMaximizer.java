import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int[] prices, int k) {
        List<Integer> profits = new ArrayList<>();

        // 找出所有可以獲利的交易
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profits.add(prices[i] - prices[i - 1]);
            }
        }

        // 用 Max Heap 選出最大的 k 個
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(profits);

        int totalProfit = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            totalProfit += maxHeap.poll();
            k--;
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1}, 2)); // 2
        System.out.println(maxProfit(new int[]{3,2,6,5,0,3}, 2)); // 7
        System.out.println(maxProfit(new int[]{1,2,3,4,5}, 2)); // 4
    }
}

/*
時間複雜度：
O(n + k log n)  
n = 天數
*/

