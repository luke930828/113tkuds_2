import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // 前序 + 中序 重建
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i=0; i<inorder.length; i++) inIndexMap.put(inorder[i], i);
        return buildPreInHelper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inIndexMap);
    }

    private static TreeNode buildPreInHelper(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, Map<Integer,Integer> map) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        int inRootIndex = map.get(root.val);
        int leftTreeSize = inRootIndex - inStart;
        root.left = buildPreInHelper(pre, preStart+1, preStart+leftTreeSize, in, inStart, inRootIndex-1, map);
        root.right = buildPreInHelper(pre, preStart+leftTreeSize+1, preEnd, in, inRootIndex+1, inEnd, map);
        return root;
    }

    // 後序 + 中序 重建
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i=0; i<inorder.length; i++) inIndexMap.put(inorder[i], i);
        return buildPostInHelper(postorder, 0,
