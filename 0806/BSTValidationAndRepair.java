public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // 驗證是否為有效 BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private static boolean validate(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) return false;
        return validate(node.left, low, node.val) && validate(node.right, node.val, high);
    }

    // 找出不符合 BST 規則節點（簡單示意：找第一個違規節點）
    public static TreeNode findInvalidNode(TreeNode root) {
        return findInvalid(root, null, null);
    }

    private static TreeNode findInvalid(TreeNode node, Integer low, Integer high) {
        if (node == null) return null;
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) return node;
        TreeNode leftInvalid = findInvalid(node.left, low, node.val);
        if (leftInvalid != null) return leftInvalid;
        return findInvalid(node.right, node.val, high);
    }

    // 修復有兩個節點錯誤位置的 BST（用中序遍歷找錯誤節點再交換）
    static TreeNode first = null, second = null, prev = null;

    public static void recoverBST(TreeNode root) {
        first = second = prev = null;
        inorder(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    // 計算移除多少節點讓樹成為有效 BST（近似解：移除所有違規節點）
    public static int nodesToRemoveForValidBST(TreeNode root) {
        return countInvalidNodes(root, null, null);
    }

    private static int countInvalidNodes(TreeNode node, Integer low, Integer high) {
        if (node == null) return 0;
        int count = 0;
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) count = 1;
        return count + countInvalidNodes(node.left, low, node.val) + countInvalidNodes(node.right, node.val, high);
    }

    // 測試範例建樹
    public static TreeNode buildTestTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);  // 這裡錯誤節點
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildTestTree();

        System.out.println("是否為有效 BST: " + isValidBST(root));
        TreeNode invalidNode = findInvalidNode(root);
        System.out.println("第一個違規節點值: " + (invalidNode != null ? invalidNode.val : "無"));

        recoverBST(root);
        System.out.println("修復後是否為有效 BST: " + isValidBST(root));

        System.out.println("需要移除節點數目使 BST 有效: " + nodesToRemoveForValidBST(root));
    }
}
