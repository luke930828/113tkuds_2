import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // 找所有根到葉路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, new ArrayList<>(), res);
        return res;
    }

    private static void helper(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            helper(node.left, path, res);
            helper(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    // 判斷是否存在根到葉路徑和為目標值
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 找最大根到葉路徑和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 計算樹的直徑（任意兩節點最大路徑和）
    static int maxDiameter = Integer.MIN_VALUE;

    public static int treeDiameter(TreeNode root) {
        maxDiameter = Integer.MIN_VALUE;
        diameterHelper(root);
        return maxDiameter;
    }

    private static int diameterHelper(TreeNode node) {
        if (node == null) return 0;
        int leftMax = diameterHelper(node.left);
        int rightMax = diameterHelper(node.right);
        int currentSum = leftMax + rightMax + node.val;
        maxDiameter = Math.max(maxDiameter, currentSum);
        return node.val + Math.max(leftMax, rightMax);
    }

    // 測試建樹
    public static TreeNode buildSample() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildSample();

        System.out.println("所有根到葉路徑: " + allRootToLeafPaths(root));
        System.out.println("存在根到葉和為22? " + hasPathSum(root, 22));
        System.out.println("最大根到葉路徑和: " + maxRootToLeafSum(root));
        System.out.println("樹的最大路徑和(直徑): " + treeDiameter(root));
    }
}

