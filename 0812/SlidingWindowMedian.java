import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap; // 較小一半
    private PriorityQueue<Integer> minHeap; // 較大一半

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]);
            if (i >= k - 1) {
                result[i - k + 1] = getMedian();
                removeNum(nums[i - k + 1]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] arr1 = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr1, 3))); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] arr2 = {1,2,3,4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr2, 2))); // [1.5, 2.5, 3.5]
    }
}

/*
時間複雜度：
O(n log k)  
n = 陣列長度  
k = 視窗大小
*/
