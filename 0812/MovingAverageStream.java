import java.util.*;

public class MovingAverageStream {
    private int size;
    private Queue<Integer> window;
    private double sum;

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MovingAverageStream(int size) {
        this.size = size;
        window = new LinkedList<>();
        sum = 0;

        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public double next(int val) {
        if (window.size() == size) {
            int removed = window.poll();
            sum -= removed;
            removeNum(removed);
        }
        window.offer(val);
        sum += val;
        addNum(val);
        return sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public int getMin() {
        return Math.min(maxHeap.isEmpty() ? Integer.MAX_VALUE : Collections.min(maxHeap),
                        minHeap.isEmpty() ? Integer.MAX_VALUE : Collections.min(minHeap));
    }

    public int getMax() {
        return Math.max(maxHeap.isEmpty() ? Integer.MIN_VALUE : Collections.max(maxHeap),
                        minHeap.isEmpty() ? Integer.MIN_VALUE : Collections.max(minHeap));
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
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));  // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3));  // 4.67
        System.out.println(ma.next(5));  // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}

/*
時間複雜度：
next()      → O(log k)
getMedian() → O(1)
getMin/Max()→ O(k)
*/
