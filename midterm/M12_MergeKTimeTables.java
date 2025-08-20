import java.util.*;

public class M12_MergeKTimeTables {
    static class Node implements Comparable<Node>{
        int val,list,idx;
        Node(int v,int l,int i){val=v;list=l;idx=i;}
        public int compareTo(Node o){return val-o.val;}
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int K=sc.nextInt();
        List<int[]> lists=new ArrayList<>();
        for(int i=0;i<K;i++){
            int len=sc.nextInt();
            int[] arr=new int[len];
            for(int j=0;j<len;j++) arr[j]=sc.nextInt();
            lists.add(arr);
        }
        PriorityQueue<Node> pq=new PriorityQueue<>();
        for(int i=0;i<K;i++) if(lists.get(i).length>0) pq.add(new Node(lists.get(i)[0],i,0));
        List<Integer> res=new ArrayList<>();
        while(!pq.isEmpty()){
            Node cur=pq.poll();
            res.add(cur.val);
            if(cur.idx+1<lists.get(cur.list).length)
                pq.add(new Node(lists.get(cur.list)[cur.idx+1],cur.list,cur.idx+1));
        }
        for(int i=0;i<res.size();i++){
            if(i>0) System.out.print(" ");
            System.out.print(res.get(i));
        }
    }
}
