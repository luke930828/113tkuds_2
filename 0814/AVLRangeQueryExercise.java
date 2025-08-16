import java.util.*;

public class AVLRangeQueryExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int key) { this.key = key; this.height = 1; }
    }

    Node root;

    List<Integer> rangeQuery(Node node, int min, int max) {
        List<Integer> result = new ArrayList<>();
        if (node == null) return result;

        if (node.key > min) result.addAll(rangeQuery(node.left, min, max));
        if (node.key >= min && node.key <= max) result.add(node.key);
        if (node.key < max) result.addAll(rangeQuery(node.right, min, max));

        return result;
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        // TODO: 插入節點
        // TODO: 測試範圍查詢
    }
}
