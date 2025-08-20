import java.util.*;

public class M08_BSTRangedSum {
    static class Node{int val;Node l,r;Node(int v){val=v;}}
    static Node build(int[] arr,int i){
        if(i>=arr.length||arr[i]==-1)return null;
        Node n=new Node(arr[i]);
        n.l=build(arr,2*i+1);
        n.r=build(arr,2*i+2);
        return n;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++)arr[i]=sc.nextInt();
        int L=sc.nextInt(),R=sc.nextInt();
        Node root=build(arr,0);
        System.out.println("Sum: "+dfs(root,L,R));
    }
    static int dfs(Node node,int L,int R){
        if(node==null) return 0;
        if(node.val<L) return dfs(node.r,L,R);
        if(node.val>R) return dfs(node.l,L,R);
        return node.val+dfs(node.l,L,R)+dfs(node.r,L,R);
    }
}
