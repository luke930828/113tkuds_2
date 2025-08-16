public class AVLDeleteExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int key) { this.key = key; this.height = 1; }
    }

    Node root;

    // TODO: insert、rotate 等與前面練習共用

    Node delete(Node node, int key) {
        // TODO: BST 刪除邏輯
        // - 刪葉子
        // - 刪單子節點
        // - 刪雙子節點 → 找後繼
        // TODO: 更新高度 + 做平衡 (需要旋轉)
        return node;
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        // TODO: 測試刪除情況
    }
}
