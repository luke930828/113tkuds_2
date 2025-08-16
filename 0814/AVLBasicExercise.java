public class AVLBasicExercise {
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // 初始高度
        }
    }

    Node root;

    // 計算節點高度
    int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    // 插入節點 (先當作 BST 插入，再更新高度)
    Node insert(Node node, int key) {
        // TODO: 實作 BST 插入
        // TODO: 更新高度
        // TODO: 不處理旋轉，單純 BST + 高度
        return node;
    }

    // 搜尋節點
    boolean search(Node node, int key) {
        // TODO: 實作遞迴搜尋
        return false;
    }

    // 檢查是否為合法 AVL
    boolean isAVL(Node node) {
        if (node == null) return true;
        int balance = height(node.left) - height(node.right);
        if (balance < -1 || balance > 1) return false;
        return isAVL(node.left) && isAVL(node.right);
    }

    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        // TODO: 測試插入 + 搜尋 + AVL 驗證
    }
}
