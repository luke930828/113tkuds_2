import java.util.*;

public class RBTreeIteratorExercise implements Iterator<Integer> {
    static class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private Stack<Node> stack = new Stack<>();
    private Node current;

    public RBTreeIteratorExercise(Node root) {
        this.current = root;
    }

    @Override
    public boolean hasNext() {
        return current != null || !stack.isEmpty();
    }

    @Override
    public Integer next() {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        Node node = stack.pop();
        current = node.right;
        return node.key;
    }

    // TODO: 反向遍歷 / 範圍遍歷
}
