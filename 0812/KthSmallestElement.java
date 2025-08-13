import java.util.PriorityQueue;

public class KthSmallestElement {
    // 方法 1：使用大小為 K 的 Max Heap
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    // 方法 2：使用 Min Heap 然後提取 K 次
    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int res = -1;
        for (int i = 0; i < k; i++) {
            res = minHeap.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        System.out.println(kthSmallestMaxHeap(arr1, 3)); // 7
        System.out.println(kthSmallestMinHeap(arr1, 3)); // 7

        int[] arr2 = {1};
        System.out.println(kthSmallestMaxHeap(arr2, 1)); // 1

        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println(kthSmallestMaxHeap(arr3, 4)); // 3
    }
}

/*
時間複雜度：
方法1（Max Heap, 大小 K） → O(n log K)
方法2（Min Heap, 提取 K 次） → O(n + k log n)
*/
