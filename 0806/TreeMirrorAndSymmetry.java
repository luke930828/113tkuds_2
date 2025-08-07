class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class TreeMirrorAndSymmetry {

    // 判斷是否為對稱樹
    static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // 兩棵樹是否為鏡像
    static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.val == b.val)
            && isMirror(a.left, b.right)
            && isMirror(a.right, b.left);
    }

    // 鏡像一棵樹
    static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(temp);
        return root;
    }

    // 比較兩棵樹是否相同
    static boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val
            && isSame(a.left, b.left)
            && isSame(a.right, b.right);
    }

    // 判斷 tree2 是否為 tree1 的子樹
    static boolean isSubtree(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null) return false;
        if (isSame(tree1, tree2)) return true;
        return isSubtree(tree1.left, tree2) || isSubtree(tree1.right, tree2);
    }

    // 測試主程式
    public static void main(String[] args) {
        // 建立對稱樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root));

        TreeNode mirrored = mirror(new TreeNode(1));
        mirrored.left = new TreeNode(2);
        mirrored.right = new TreeNode(3);
        mirror(mirrored);
        System.out.println("鏡像轉換後 root 左右: " +
            mirrored.left.val + ", " + mirrored.right.val);

        TreeNode a = new TreeNode(1);
        a.left = new TreeNode(2);
        a.right = new TreeNode(3);

        TreeNode b = new TreeNode(1);
        b.left = new TreeNode(3);
        b.right = new TreeNode(2);

        System.out.println("a 和 b 是否為鏡像: " + isMirror(a, b));
        System.out.println("b 是否為 a 的子樹: " + isSubtree(a, b));
    }
}
