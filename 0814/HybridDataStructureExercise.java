import java.util.*;
/**
 * 混合資料結構：讀多用 AVL、寫多用 RB(TreeMap)。支援動態切換與同步策略。
 */
public class HybridDataStructureExercise {

    // --- 公用介面 ---
    interface IntSet {
        boolean insert(int key);
        boolean delete(int key);
        boolean contains(int key);
        int size();
        void clear();
        Iterable<Integer> inOrder(); // 給重建/比對用
    }

    // --- AVL 實作（簡版，含插入/刪除/查詢/中序） ---
    static class AVL implements IntSet {
        static class Node {
            int key, height;
            Node left, right;
            Node(int k){ key=k; height=1; }
        }
        Node root;
        int size=0;

        private int h(Node n){ return n==null?0:n.height; }
        private int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }
        private void upd(Node n){ n.height = 1 + Math.max(h(n.left), h(n.right)); }

        private Node rotR(Node y){
            Node x = y.left, T2 = x.right;
            x.right = y; y.left = T2;
            upd(y); upd(x); return x;
        }
        private Node rotL(Node x){
            Node y = x.right, T2 = y.left;
            y.left = x; x.right = T2;
            upd(x); upd(y); return y;
        }
        private Node balance(Node n){
            upd(n);
            int b = bf(n);
            if (b > 1) { // 左重
                if (bf(n.left) < 0) n.left = rotL(n.left); // LR
                return rotR(n); // LL
            } else if (b < -1) { // 右重
                if (bf(n.right) > 0) n.right = rotR(n.right); // RL
                return rotL(n); // RR
            }
            return n;
        }

        private Node insert(Node n, int key, boolean[] added){
            if (n==null){ added[0]=true; return new Node(key); }
            if (key < n.key) n.left = insert(n.left, key, added);
            else if (key > n.key) n.right = insert(n.right, key, added);
            else return n; // no dup
            return balance(n);
        }
        @Override public boolean insert(int key){
            boolean[] added = {false};
            root = insert(root,key,added);
            if (added[0]) size++;
            return added[0];
        }

        private Node minNode(Node n){ while(n.left!=null) n=n.left; return n; }

        private Node delete(Node n, int key, boolean[] removed){
            if (n==null) return null;
            if (key < n.key) n.left = delete(n.left,key,removed);
            else if (key > n.key) n.right = delete(n.right,key,removed);
            else{
                removed[0]=true;
                if (n.left==null || n.right==null){
                    n = (n.left!=null)? n.left : n.right;
                }else{
                    Node m = minNode(n.right);
                    n.key = m.key;
                    n.right = delete(n.right, m.key, new boolean[1]);
                }
            }
            if (n==null) return null;
            return balance(n);
        }
        @Override public boolean delete(int key){
            boolean[] removed = {false};
            root = delete(root,key,removed);
            if (removed[0]) size--;
            return removed[0];
        }

        @Override public boolean contains(int key){
            Node cur=root;
            while(cur!=null){
                if (key<cur.key) cur=cur.left;
                else if (key>cur.key) cur=cur.right;
                else return true;
            }
            return false;
        }
        @Override public int size(){ return size; }
        @Override public void clear(){ root=null; size=0; }

        @Override public Iterable<Integer> inOrder(){
            List<Integer> res=new ArrayList<>();
            Deque<Node> st=new ArrayDeque<>();
            Node cur=root;
            while(cur!=null || !st.isEmpty()){
                while(cur!=null){ st.push(cur); cur=cur.left; }
                Node n=st.pop(); res.add(n.key); cur=n.right;
            }
            return res;
        }

        // 提供視覺化時可顯示平衡因子
        public Node root(){ return root; }
        public int balanceFactor(Node n){ return bf(n); }
    }

    // --- Red-Black 採用 Java TreeMap（底層為 RB Tree），只用 key 當作值 ---
    static class RB implements IntSet {
        private final TreeMap<Integer,Boolean> map = new TreeMap<>();
        @Override public boolean insert(int key){ return map.putIfAbsent(key,Boolean.TRUE)==null; }
        @Override public boolean delete(int key){ return map.remove(key)!=null; }
        @Override public boolean contains(int key){ return map.containsKey(key); }
        @Override public int size(){ return map.size(); }
        @Override public void clear(){ map.clear(); }
        @Override public Iterable<Integer> inOrder(){ return map.navigableKeySet(); }
        public NavigableSet<Integer> keySet(){ return map.navigableKeySet(); }
    }

    // --- 策略引擎：滾動視窗統計最近 ops 的讀/寫比 ---
    enum Primary { AVL_PRIMARY, RB_PRIMARY }
    enum Op { READ, WRITE }

    static class Policy {
        private final int window;
        private final Deque<Op> ops = new ArrayDeque<>();
        private int reads=0, writes=0;
        private double readBiasThreshold = 0.6; // >60% 讀用 AVL
        Primary current = Primary.AVL_PRIMARY;

        Policy(int window){ this.window=window; }

        void record(Op op){
            ops.addLast(op);
            if (op==Op.READ) reads++; else writes++;
            if (ops.size()>window){
                Op out=ops.removeFirst();
                if (out==Op.READ) reads--; else writes--;
            }
            decide();
        }
        private void decide(){
            int total = Math.max(1, reads+writes);
            double rRatio = (double)reads/total;
            Primary next = (rRatio>=readBiasThreshold)? Primary.AVL_PRIMARY : Primary.RB_PRIMARY;
            current = next;
        }
        Primary primary(){ return current; }
        void setReadBiasThreshold(double v){ this.readBiasThreshold = Math.max(0.0, Math.min(1.0, v)); }
    }

    // --- 混合容器 ---
    private final AVL avl = new AVL();
    private final RB rb = new RB();
    private final Policy policy;
    private boolean dualWrite = true;   // true=寫雙邊；false=只寫主樹，切換再重建次樹
    private Primary manualOverride = null; // 若非空，強制主樹

    public HybridDataStructureExercise(){ this(200); }
    public HybridDataStructureExercise(int window){ this.policy = new Policy(window); }

    public void setDualWrite(boolean on){ this.dualWrite = on; }
    public void forcePrimary(Primary p){ this.manualOverride = p; }
    public void clearPrimaryOverride(){ this.manualOverride = null; }

    private Primary primaryNow(){
        return (manualOverride!=null)? manualOverride : policy.primary();
    }

    private IntSet mainSet(){ return (primaryNow()==Primary.AVL_PRIMARY)? avl : rb; }
    private IntSet shadowSet(){ return (primaryNow()==Primary.AVL_PRIMARY)? rb : avl; }

    // 切換主樹時的重建（僅在 dualWrite=false 時需要）
    private void rebuildShadowIfNeeded(){
        if (!dualWrite){
            IntSet main = mainSet();
            IntSet shadow = shadowSet();
            if (shadow.size()!=main.size()){
                shadow.clear();
                for (int k : main.inOrder()) shadow.insert(k);
            }
        }
    }

    public boolean insert(int key){
        policy.record(Op.WRITE);
        boolean res = mainSet().insert(key);
        if (dualWrite) shadowSet().insert(key);
        rebuildShadowIfNeeded();
        return res;
    }

    public boolean delete(int key){
        policy.record(Op.WRITE);
        boolean res = mainSet().delete(key);
        if (dualWrite) shadowSet().delete(key);
        rebuildShadowIfNeeded();
        return res;
    }

    public boolean contains(int key){
        policy.record(Op.READ);
        return mainSet().contains(key);
    }

    public int size(){
        // 以主樹為準
        return mainSet().size();
    }

    public void clear(){
        avl.clear(); rb.clear();
    }

    public Primary primaryType(){ return primaryNow(); }

    // 範例：把目前內容輸出（中序）
    public List<Integer> toSortedList(){
        List<Integer> out=new ArrayList<>();
        for (int k : mainSet().inOrder()) out.add(k);
        return out;
    }

    // --- Demo ---
    public static void main(String[] args) {
        HybridDataStructureExercise ds = new HybridDataStructureExercise(20);
        ds.setDualWrite(false); // 示範僅主樹寫入 + 切換時重建

        // 模擬讀少寫多（應傾向 RB）
        for (int i=0;i<100;i++) ds.insert(i);
        System.out.println("Primary after writes: " + ds.primaryType()); // 可能 RB_PRIMARY

        // 模擬讀多
        for (int i=0;i<200;i++) ds.contains(i/2);
        System.out.println("Primary after reads: " + ds.primaryType()); // 可能 AVL_PRIMARY

        System.out.println("Size: " + ds.size());
        System.out.println("First 10 sorted: " + ds.toSortedList().subList(0, Math.min(10, ds.size())));
    }
}
