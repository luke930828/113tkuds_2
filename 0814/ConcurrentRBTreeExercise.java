import java.util.concurrent.locks.*;

public class ConcurrentRBTreeExercise {
    static class Node {
        int key;
        Node left, right, parent;
        boolean color;
    }

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private Node root;

    public void insert(int key) {
        lock.writeLock().lock();
        try {
            // TODO: 插入邏輯
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean search(int key) {
        lock.readLock().lock();
        try {
            // TODO: 搜尋邏輯
            return false;
        } finally {
            lock.readLock().unlock();
        }
    }
}
