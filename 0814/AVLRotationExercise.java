public class AVLRotationExercise {
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    Node root;

    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // 左旋
    Node rotateLeft(Node x) {
        // TODO: 完成左旋邏輯
        return null;
    }

    // 右旋
    Node rotateRight(Node y) {
        // TODO: 完成右旋邏輯
        return null;
    }

    // 左右旋
    Node rotateLeftRight(Node node) {
        // TODO: 先左旋，再右旋
        return null;
    }

    // 右左旋
    Node rotateRightLeft(Node node) {
        // TODO: 先右旋，再左旋
        return null;
    }

    public static void main(String[] args) {
        AVLRotationExercise tree = new AVLRotationExercise();
        // TODO: 測試各種旋轉情況
    }
}
