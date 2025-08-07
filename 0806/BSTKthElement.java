import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // 中序走訪取得排序元素列表
    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    // 第 k 小元素
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k - 1);
    }

    // 第 k 大元素
    public static int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(list.size() - k);
    }

    // 第 k 小到第 j 小元素列表
    public static List<Integer> kthRange(TreeNode root, int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.subList(k - 1, j);
    }

    // 簡易支援動態插入刪除的結構（不完備示意）
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) root.left = deleteNode(root.left, key);
        else if (key > root.val) root.right = deleteNode(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.val = minValue(root.right);
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private static int minValue(TreeNode node) {
        int minv = node.val;
        while (node.left != null) {
            node = node.left;
            minv = node.val;
        }
        return minv;
    }

    // 測試建樹與功能
    public static void main(String[] args) {
        TreeNode root = null;
        int[] vals = {20, 8, 22, 4, 12, 10, 14};
        for (int v : vals) root = insert(root, v);

        System.out.println("第3小元素: " + kthSmallest(root, 3));
        System.out.println("第2大元素: " + kthLargest(root, 2));
        System.out.println("第2小到第5小元素: " + kthRange(root, 2, 5));

        root = deleteNode(root, 10);
        System.out.println("刪除10後，第3小元素: " + kthSmallest(root, 3));
    }
}
