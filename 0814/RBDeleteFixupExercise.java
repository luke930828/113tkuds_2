public class RBDeleteFixupExercise {
    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;
        Node(int key) { this.key = key; }
    }

    Node root;

    void deleteFixup(Node x) {
        while (x != root && (x == null || x.color == Color.BLACK)) {
            if (x == x.parent.left) {
                Node w = x.parent.right; // 兄弟
                if (w.color == Color.RED) {
                    // case 1
                } else {
                    if ((w.left == null || w.left.color == Color.BLACK) &&
                        (w.right == null || w.right.color == Color.BLACK)) {
                        // case 2
                    } else {
                        if (w.right == null || w.right.color == Color.BLACK) {
                            // case 3
                        }
                        // case 4
                    }
                }
            } else {
                // 對稱情況
            }
        }
        if (x != null) x.color = Color.BLACK;
    }
}
