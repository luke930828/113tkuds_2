import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreeBasicOperations {

    // 計算總和與節點數
    static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    static double average(TreeNode root) {
        return (double) sum(root) / countNodes(root);
    }

    // 最大最小值節點
    static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 計算樹的寬度（每層最大節點數）
    static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return maxWidth;
    }

    // 檢查是否為完全二元樹
    static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reachedNull = false;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                reachedNull = true;
            } else {
                if (reachedNull) return false; // 若曾遇到 null，後面不能再出現節點
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        return true;
    }

    // 測試用建樹
    public static TreeNode sampleTree() {
        /*
              10
             /  \
            5    20
           / \   / \
          3  7  15 30
         */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(30);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = sampleTree();

        System.out.println("節點總和: " + sum(root));
        System.out.println("節點平均值: " + average(root));
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));
        System.out.println("最大寬度: " + maxWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteTree(root));
    }
}
