public class RBValidationExercise {
    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;

        Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }
    }

    Node root;

    // 檢查根節點必須是黑色
    boolean checkRootBlack() {
        return root == null || root.color == Color.BLACK;
    }

    // 檢查紅色節點的子節點必須是黑色
    boolean checkNoRedRed(Node node) {
        if (node == null) return true;
        if (node.color == Color.RED) {
            if ((node.left != null && node.left.color == Color.RED) ||
                (node.right != null && node.right.color == Color.RED)) {
                return false;
            }
        }
        return checkNoRedRed(node.left) && checkNoRedRed(node.right);
    }

    // 檢查所有路徑黑高度是否一致
    int checkBlackHeight(Node node) {
        if (node == null) return 1;
        int left = checkBlackHeight(node.left);
        int right = checkBlackHeight(node.right);
        if (left == 0 || right == 0 || left != right) return 0;
        return left + (node.color == Color.BLACK ? 1 : 0);
    }

    boolean validateRBTree() {
        return checkRootBlack() && checkNoRedRed(root) && checkBlackHeight(root) != 0;
    }

    public static void main(String[] args) {
        RBValidationExercise tree = new RBValidationExercise();
        // TODO: 測試不同樹
    }
}
