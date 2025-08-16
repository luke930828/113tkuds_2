import java.util.*;
/**
 * 控制台樹形視覺化（ASCII）：
 * - 以階層方式輸出
 * - 可顯示顏色(紅/黑)或平衡因子(AVL)
 * - 提供簡單的「動畫」示範（逐步輸出）
 */
public class TreeVisualizerExercise {

    // 讓各種樹提供一個視覺節點實作此介面即可
    public interface VisualNode {
        String label();          // 例如 "42", 或 "42(R)"
        VisualNode left();
        VisualNode right();
    }

    /** 以多行文字輸出樹（層序 + 每層等距排版的簡易版） */
    public static void printTree(VisualNode root) {
        if (root == null) { System.out.println("(empty)"); return; }
        int h = height(root);
        int maxWidth = (int)Math.pow(2, h)-1;

        List<VisualNode> level = new ArrayList<>();
        level.add(root);

        for (int depth=1; depth<=h; depth++){
            int spacesBetween = (int)Math.pow(2, h-depth+1)-1;
            int leading = (int)Math.pow(2, h-depth)-1;

            printSpaces(leading);
            List<VisualNode> next = new ArrayList<>();

            for (int i=0;i<level.size();i++){
                VisualNode n = level.get(i);
                if (n==null){
                    System.out.print(" ");
                    next.add(null); next.add(null);
                }else{
                    System.out.print(n.label());
                    next.add(n.left());
                    next.add(n.right());
                }
                printSpaces(spacesBetween);
            }
            System.out.println();
            level = next;
        }
    }

    /** 以中序方式輸出（括號結構），便於觀察 BST 有序性 */
    public static void printInorder(VisualNode node){
        if (node==null){ System.out.print("·"); return; }
        System.out.print("(");
        printInorder(node.left());
        System.out.print(" " + node.label() + " ");
        printInorder(node.right());
        System.out.print(")");
    }

    /** 動畫顯示：每插入一個 key 就印一次樹 */
    public static <T> void animateInsertions(List<T> items, java.util.function.Function<T, VisualNode> afterInsertRootSupplier, long millis){
        for (T t : items){
            System.out.println("Insert: " + t);
            VisualNode root = afterInsertRootSupplier.apply(t);
            printTree(root);
            sleep(millis);
        }
    }

    // --- 工具 ---
    private static void printSpaces(int n){ for(int i=0;i<n;i++) System.out.print(" "); }
    private static int height(VisualNode n){
        if (n==null) return 0;
        return 1 + Math.max(height(n.left()), height(n.right()));
    }
    private static void sleep(long ms){ try{ Thread.sleep(ms);}catch(InterruptedException ignored){} }

    // --- 範例：用於顯示 AVL（帶平衡因子） ---
    public static class AVLAdapter implements VisualNode {
        private final HybridDataStructureExercise.AVL.Node n;
        private final HybridDataStructureExercise.AVL avl;
        public AVLAdapter(HybridDataStructureExercise.AVL.Node n, HybridDataStructureExercise.AVL avl){ this.n=n; this.avl=avl; }
        @Override public String label(){
            if (n==null) return "·";
            int bf = avl.balanceFactor(n);
            return n.key + "(bf=" + bf + ")";
        }
        @Override public VisualNode left(){ return (n!=null && n.left!=null)? new AVLAdapter(n.left,avl) : null; }
        @Override public VisualNode right(){ return (n!=null && n.right!=null)? new AVLAdapter(n.right,avl) : null; }
    }

    // --- 範例：簡易 RB 節點（若你有自寫 RB Tree，可套此 Adapter） ---
    public static class SimpleRBNode implements VisualNode {
        int key; boolean red; SimpleRBNode left,right;
        public SimpleRBNode(int k, boolean red){ this.key=k; this.red=red; }
        @Override public String label(){ return key + (red? "(R)":"(B)"); }
        @Override public VisualNode left(){ return left; }
        @Override public VisualNode right(){ return right; }
    }

    // --- Demo ---
    public static void main(String[] args) {
        // 用混合結構裡的 AVL 來視覺化
        HybridDataStructureExercise ds = new HybridDataStructureExercise();
        ds.forcePrimary(HybridDataStructureExercise.Primary.AVL_PRIMARY);
        int[] keys = { 10, 5, 15, 3, 7, 13, 18, 1, 4 };

        // 插入後用 Adapter 包裝 AVL root 顯示動畫
        List<Integer> seq = new ArrayList<>();
        for (int k : keys) seq.add(k);

        animateInsertions(seq, k -> {
            ds.insert(k);
            HybridDataStructureExercise.AVL avl = (HybridDataStructureExercise.AVL) (ds.primaryType()==HybridDataStructureExercise.Primary.AVL_PRIMARY ? ds.mainSet() : ds.shadowSet());
            return new AVLAdapter(avl.root(), avl);
        }, 300);

        System.out.println("\nInorder:");
        HybridDataStructureExercise.AVL avl = (HybridDataStructureExercise.AVL) (ds.primaryType()==HybridDataStructureExercise.Primary.AVL_PRIMARY ? ds.mainSet() : ds.shadowSet());
        printInorder(new AVLAdapter(avl.root(), avl));
        System.out.println();
    }
}
