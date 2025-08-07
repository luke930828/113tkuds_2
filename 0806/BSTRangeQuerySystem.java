import java.util.*;

class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int x) {
        val = x;
    }
}

public class BSTRangeQuerySystem {

    // 插入節點
    static void insert(BSTNode root, int val) {
        if (val < root.val) {
            if (root.left == null) root.left = new BSTNode(val);
            else insert(root.left, val);
        } else {
            if (root.right == null) root.right = new BSTNode(val);
            else insert(root.right, val);
        }
    }

    // 範圍查詢
    static List<Integer> rangeQuery(BSTNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        traverseRange(root, min, max, result);
        return result;
    }

    static void traverseRange(BSTNode root, int min, int max, List<Integer> result) {
        if (root == null) return;
        if (root.val >= min) traverseRange(root.left, min, max, result);
        if (root.val >= min && root.val <= max) result.add(root.val);
        if (root.val <= max) traverseRange(root.right, min, max, result);
    }

    // 範圍內的節點數
    static int rangeCount(BSTNode root, int min, int max) {
        return rangeQuery(root, min, max).size();
    }

    // 範圍內的節點總和
    static int rangeSum(BSTNode root, int min, int max) {
        return rangeQuery(root, min, max).stream().mapToInt(Integer::intValue).sum();
    }

    // 最接近指定值的節點
    static int closestValue(BSTNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            if (target < root.val) root = root.left;
            else root = root.right;
        }
        return closest;
    }

    // 建立 BST
    public static BSTNode buildBST(int[] values) {
        if (values.length == 0) return null;
        BSTNode root = new BSTNode(values[0]);
        for (int i = 1; i < values.length; i++) {
            insert(root, values[i]);
        }
        return root;
    }

    // 主程式測試
    public static void main(String[] args) {
        int[] values = {15, 10, 20, 8, 12, 17, 25};
        BSTNode root = buildBST(values);

        int min = 10, max = 20;
        System.out.println("範圍查詢 [" + min + ", " + max + "]: " + rangeQuery(root, min, max));
        System.out.println("節點數量: " + rangeCount(root, min, max));
        System.out.println("節點總和: " + rangeSum(root, min, max));
        System.out.println("最接近 19 的值: " + closestValue(root, 19));
    }
}
