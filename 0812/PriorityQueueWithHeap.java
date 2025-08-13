import java.util.*;

public class PriorityQueueWithHeap {
    private static class Task {
        String name;
        int priority;
        long timestamp; // 用來處理相同優先級的先後順序

        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }
    }

    private PriorityQueue<Task> maxHeap;
    private long timeCounter;

    public PriorityQueueWithHeap() {
        timeCounter = 0;
        maxHeap = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) return b.priority - a.priority;
            return Long.compare(a.timestamp, b.timestamp); // 先加入的先執行
        });
    }

    public void addTask(String name, int priority) {
        maxHeap.offer(new Task(name, priority, timeCounter++));
    }

    public String executeNext() {
        if (maxHeap.isEmpty()) return null;
        return maxHeap.poll().name;
    }

    public String peek() {
        return maxHeap.isEmpty() ? null : maxHeap.peek().name;
    }

    public void changePriority(String name, int newPriority) {
        List<Task> buffer = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            Task t = maxHeap.poll();
            if (t.name.equals(name)) {
                t.priority = newPriority;
            }
            buffer.add(t);
        }
        maxHeap.addAll(buffer); // 重新建堆
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println(pq.executeNext()); // 緊急修復
        System.out.println(pq.executeNext()); // 更新
        System.out.println(pq.executeNext()); // 備份
    }
}

/*
時間複雜度：
addTask()       → O(log n)
executeNext()   → O(log n)
peek()          → O(1)
changePriority()→ O(n log n) 需重建堆
*/
