
public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // 1. 將 BST 轉換為排序的雙向鏈結串列（in-order）
    static TreeNode head = null, prev = null;

    public static TreeNode bstToDoublyLinkedList(TreeNode root) {
        head = null;
        prev = null;
        inorderConvert(root);
        return head;
    }

    private static void inorderConvert(TreeNode node) {
        if (node == null) return;
        inorderConvert(node.left);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inorderConvert(node.right);
    }

    // 2. 將排序陣列轉換為平衡 BST
    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBSTFromSortedArray(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBSTFromSortedArray(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBSTFromSortedArray(nums, left, mid - 1);
        node.right = buildBSTFromSortedArray(nums, mid + 1, right);
        return node;
    }

    // 3. 檢查 BST 是否平衡，並計算平衡因子（左右子樹高度差）
    static class BalanceStatusWithHeight {
        boolean isBalanced;
        int height;
        BalanceStatusWithHeight(boolean b, int h) {
            isBalanced = b; height = h;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root).isBalanced;
    }

    private static BalanceStatusWithHeight checkBalance(TreeNode node) {
        if (node == null) return new BalanceStatusWithHeight(true, 0);

        BalanceStatusWithHeight left = checkBalance(node.left);
        BalanceStatusWithHeight right = checkBalance(node.right);

        boolean balanced = left.isBalanced && right.isBalanced &&
                           Math.abs(left.height - right.height) <= 1;

        int height = 1 + Math.max(left.height, right.height);
        return new BalanceStatusWithHeight(balanced, height);
    }

    // 計算平衡因子（左右子樹高度差）
    public static int balanceFactor(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return leftHeight - rightHeight;
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 4. 將 BST 中每個節點值改為所有大於等於該節點值的總和（逆中序遍歷）
    static int sum = 0;
    public static void convertBSTToGreaterTree(TreeNode root) {
        sum = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInorder(node.left);
    }

    // 測試用建樹
    public static TreeNode buildBST(int[] values) {
        TreeNode root = null;
        for (int v : values) {
            root = insert(root, v);
        }
        return root;
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // 中序列印
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 測試主程式
    public static void main(String[] args) {
        int[] vals = {5, 3, 8, 2, 4, 7, 9};
        TreeNode root = buildBST(vals);

        System.out.print("原 BST 中序: ");
        inorderPrint(root);
        System.out.println();

        // 轉換為雙向鏈結串列
        TreeNode dll = bstToDoublyLinkedList(root);
        System.out.print("雙向鏈結串列 (往右): ");
        TreeNode cur = dll;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();

        // 平衡檢查與平衡因子
        System.out.println("BST 是否平衡? " + isBalanced(root));
        System.out.println("根節點平衡因子: " + balanceFactor(root));

        // 排序陣列轉平衡 BST
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedBST = sortedArrayToBST(sortedArr);
        System.out.print("排序陣列轉平衡 BST 中序: ");
        inorderPrint(balancedBST);
        System.out.println();

        // 改為大於等於該節點值總和
        convertBSTToGreaterTree(root);
        System.out.print("轉換後的大於等於總和 BST 中序: ");
        inorderPrint(root);
        System.out.println();
    }
}
