public class RBInsertFixupExercise {
    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;
        Node(int key) { this.key = key; this.color = Color.RED; }
    }

    Node root;

    void insertFixup(Node z) {
        while (z.parent != null && z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right; // 叔叔
                if (y != null && y.color == Color.RED) {
                    // case 1: 叔叔紅
                } else {
                    if (z == z.parent.right) {
                        // case 2: 叔叔黑，且 z 是右子
                    }
                    // case 3: 叔叔黑，且 z 是左子
                }
            } else {
                // 對稱情況
            }
        }
        root.color = Color.BLACK;
    }

    // TODO: 插入、旋轉實作
}
