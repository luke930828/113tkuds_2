import java.util.*;

public class MergeKSortedArrays {
    static class Node {
        int value;
        int arrayIndex;  // 來自第幾個陣列
        int elementIndex; // 該陣列的第幾個元素

        Node(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        // 初始化，每個陣列放第一個元素
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Node(arrays[i][0], i, 0));
            }
        }

        // 從 heap 中取最小，然後加下一個元素
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.offer(new Node(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arrays1 = {{1,4,5}, {1,3,4}, {2,6}};
        System.out.println(mergeKSortedArrays(arrays1)); // [1,1,2,3,4,4,5,6]

        int[][] arrays2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(mergeKSortedArrays(arrays2)); // [1,2,3,4,5,6,7,8,9]

        int[][] arrays3 = {{1}, {0}};
        System.out.println(mergeKSortedArrays(arrays3)); // [0,1]
    }
}

/*
時間複雜度：
O(N log K)  
N = 總元素數量  
K = 陣列數量
*/
