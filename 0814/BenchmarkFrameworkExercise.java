import java.util.*;
/**
 * 樹結構效能基準框架：
 * - 支援多實作（AVL、RB(TreeMap)）
 * - 多種場景與統計
 */
public class BenchmarkFrameworkExercise {

    // 統一介面
    public interface BalancedTree {
        boolean insert(int key);
        boolean delete(int key);
        boolean contains(int key);
        int size();
        void clear();
        String name();
    }

    // AVL Adapter（重用前檔案的 AVL）
    static class AVLAdapter implements BalancedTree {
        private final HybridDataStructureExercise.AVL avl = new HybridDataStructureExercise.AVL();
        @Override public boolean insert(int key){ return avl.insert(key); }
        @Override public boolean delete(int key){ return avl.delete(key); }
        @Override public boolean contains(int key){ return avl.contains(key); }
        @Override public int size(){ return avl.size; }
        @Override public void clear(){ avl.clear(); }
        @Override public String name(){ return "AVL"; }
    }

    // RB Adapter（TreeMap）
    static class RBAdapter implements BalancedTree {
        private final TreeMap<Integer,Boolean> map = new TreeMap<>();
        @Override public boolean insert(int key){ return map.putIfAbsent(key,Boolean.TRUE)==null; }
        @Override public boolean delete(int key){ return map.remove(key)!=null; }
        @Override public boolean contains(int key){ return map.containsKey(key); }
        @Override public int size(){ return map.size(); }
        @Override public void clear(){ map.clear(); }
        @Override public String name(){ return "RB(TreeMap)"; }
    }

    // 場景：純插入
    public static Result benchPureInsert(BalancedTree t, int n, Random rng){
        t.clear();
        long memBefore = usedMem();
        long st = System.nanoTime();
        for (int i=0;i<n;i++){
            int key = rng.nextInt();
            t.insert(key);
        }
        long ed = System.nanoTime();
        long memAfter = usedMem();
        return new Result(t.name(),"PureInsert", n, st, ed, memAfter-memBefore);
    }

    // 場景：混合操作（50% 查、25% 插、25% 刪）
    public static Result benchMixed(BalancedTree t, int nOps, Random rng){
        t.clear();
        // 先放一些基底資料
        for (int i=0;i<nOps/2;i++) t.insert(rng.nextInt());

        long memBefore = usedMem();
        long st = System.nanoTime();
        for (int i=0;i<nOps;i++){
            int r = rng.nextInt(100);
            int key = rng.nextInt();
            if (r < 50) t.contains(key);
            else if (r < 75) t.insert(key);
            else t.delete(key);
        }
        long ed = System.nanoTime();
        long memAfter = usedMem();
        return new Result(t.name(),"Mixed50/25/25", nOps, st, ed, memAfter-memBefore);
    }

    // 場景：遞增序插入（對樹型結構是壓力測試）
    public static Result benchSortedInsert(BalancedTree t, int n){
        t.clear();
        long memBefore = usedMem();
        long st = System.nanoTime();
        for (int i=0;i<n;i++) t.insert(i);
        long ed = System.nanoTime();
        long memAfter = usedMem();
        return new Result(t.name(),"SortedInsert(0..n-1)", n, st, ed, memAfter-memBefore);
    }

    // 結果
    static class Result {
        String impl, scenario;
        int ops;
        long nsStart, nsEnd, memBytes;
        Result(String impl, String scenario, int ops, long st, long ed, long mem){
            this.impl=impl; this.scenario=scenario; this.ops=ops; this.nsStart=st; this.nsEnd=ed; this.memBytes=mem;
        }
        double seconds(){ return (nsEnd-nsStart)/1_000_000_000.0; }
        double qps(){ return ops / Math.max(1e-9, seconds()); }
        @Override public String toString(){
            return String.format(Locale.US,
                    "%-12s | %-22s | ops=%8d | time=%.3fs | qps=%.0f | Δmem=%.2f MB",
                    impl, scenario, ops, seconds(), qps(), memBytes/1024.0/1024.0);
        }
    }

    // 記憶體粗估
    private static long usedMem(){
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

    // --- Demo ---
    public static void main(String[] args) {
        int N = 200_000;
        Random rng = new Random(42);

        List<BalancedTree> impls = List.of(new AVLAdapter(), new RBAdapter());
        List<Result> results = new ArrayList<>();

        for (BalancedTree t : impls){
            results.add(benchPureInsert(t, N, new Random(1)));
            results.add(benchMixed(t, N, new Random(2)));
            results.add(benchSortedInsert(t, N));
        }

        System.out.println("=== Benchmark Report ===");
        for (Result r : results) System.out.println(r);
    }
}
