
import java.util.*;

public class M07_BinaryTreeLeftView {
    static class Node {
        int val; Node left,right;
        Node(int v){val=v;}
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=sc.nextInt();
        if(n==0){System.out.println("LeftView:");return;}
        Node root=build(arr,0);
        List<Integer> view=new ArrayList<>();
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int sz=q.size();
            for(int i=0;i<sz;i++){
                Node cur=q.poll();
                if(cur==null) continue;
                if(i==0) view.add(cur.val);
                if(cur.left!=null) q.add(cur.left);
                if(cur.right!=null) q.add(cur.right);
            }
        }
        System.out.print("LeftView:");
        for(int v:view) System.out.print(" "+v);
    }
    static Node build(int[] arr,int i){
        if(i>=arr.length||arr[i]==-1) return null;
        Node node=new Node(arr[i]);
        node.left=build(arr,2*i+1);
        node.right=build(arr,2*i+2);
        return node;
    }
}
