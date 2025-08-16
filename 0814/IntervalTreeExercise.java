public class IntervalTreeExercise {
    static class Interval {
        int low, high;
        Interval(int l, int h) { low = l; high = h; }
    }

    static class Node {
        Interval interval;
        int max;
        Node left, right, parent;
        boolean color; // true=RED, false=BLACK
        Node(Interval i) { interval = i; max = i.high; }
    }

    Node root;

    // TODO: 插入 / 刪除，維護 max
    // TODO: 查詢重疊區間
}
