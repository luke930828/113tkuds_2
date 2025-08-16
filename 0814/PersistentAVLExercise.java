import java.util.*;

public class PersistentAVLExercise {
    static class Node {
        final int key, height;
        final Node left, right;

        Node(int key, Node left, Node right, int height) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    List<Node> versions = new ArrayList<>();

    // 插入時建立新版本 (路徑複製)
    Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null, 1);
        if (key < node.key)
            return new Node(node.key, insert(node.left, key), node.right, node.height);
        else if (key > node.key)
            return new Node(node.key, node.left, insert(node.right, key), node.height);
        else
            return node; // 不允許重複
    }

    void newVersion(int key) {
        Node latest = versions.isEmpty() ? null : versions.get(versions.size()-1);
        Node newRoot = insert(latest, key);
        versions.add(newRoot);
    }

    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();
        tree.newVersion(10);
        tree.newVersion(20);
        tree.newVersion(5);
        System.out.println("共有版本數: " + tree.versions.size()); // 3
    }
}
