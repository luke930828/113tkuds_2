import java.util.*;

public class M11_HeapSortWithTie {
    static class Pair implements Comparable<Pair>{
        int score, idx;
        Pair(int s,int i){score=s;idx=i;}
        public int compareTo(Pair o){
            if(score!=o.score) return o.score-score; // Max-Heap by score
            return o.idx-idx; // smaller index higher priority
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++) pq.add(new Pair(sc.nextInt(),i));
        int[] ans=new int[n]; int pos=n-1;
        while(!pq.isEmpty()) ans[pos--]=pq.poll().score;
        for(int i=0;i<n;i++){
            if(i>0) System.out.print(" ");
            System.out.print(ans[i]);
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：每筆資料插入 Heap O(log n)，總共 n 筆 → O(n log n)。
 */
