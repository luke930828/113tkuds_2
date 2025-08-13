import java.util.*;

public class MultiLevelCacheSystem {
    static class CacheItem {
        int key;
        String value;
        int frequency;
        long timestamp;

        CacheItem(int key, String value, int frequency, long timestamp) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            this.timestamp = timestamp;
        }
    }

    static class LRUCache {
        int capacity;
        PriorityQueue<CacheItem> heap;
        Map<Integer, CacheItem> map;
        long timeCounter;

        LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.timeCounter = 0;
            this.heap = new PriorityQueue<>((a, b) -> {
                if (a.frequency != b.frequency) return a.frequency - b.frequency;
                return Long.compare(a.timestamp, b.timestamp);
            });
        }

        CacheItem get(int key) {
            if (!map.containsKey(key)) return null;
            CacheItem item = map.get(key);
            item.frequency++;
            item.timestamp = timeCounter++;
            rebuildHeap();
            return item;
        }

        void put(int key, String value) {
            if (map.containsKey(key)) {
                CacheItem item = map.get(key);
                item.value = value;
                item.frequency++;
                item.timestamp = timeCounter++;
            } else {
                if (map.size() >= capacity) {
                    CacheItem evict = heap.poll();
                    map.remove(evict.key);
                }
                CacheItem newItem = new CacheItem(key, value, 1, timeCounter++);
                map.put(key, newItem);
            }
            rebuildHeap();
        }

        void rebuildHeap() {
            heap.clear();
            heap.addAll(map.values());
        }
    }

    private LRUCache L1;
    private LRUCache L2;
    private LRUCache L3;

    public MultiLevelCacheSystem() {
        L1 = new LRUCache(2);
        L2 = new LRUCache(5);
        L3 = new LRUCache(10);
    }

    public String get(int key) {
        CacheItem item = L1.get(key);
        if (item != null) return item.value;

        item = L2.get(key);
        if (item != null) {
            L1.put(key, item.value);
            return item.value;
        }

        item = L3.get(key);
        if (item != null) {
            L1.put(key, item.value);
            return item.value;
        }
        return null;
    }

    public void put(int key, String value) {
        L1.put(key, value);
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println(cache.get(1)); // A
        System.out.println(cache.get(2)); // B
    }
}

/*
時間複雜度：
get() / put() → O(log n)
*/
