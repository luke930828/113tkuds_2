import java.util.*;

public class M09_AVLValidate {
    static class Node{int val;Node l,r;Node(int v){val=v;}}
    static Node build(int[] arr,int i){
        if(i>=arr.length||arr[i]==-1)return null;
        Node n=new Node(arr[i]);
        n.l=build(arr,2*i+1);
        n.r=build(arr,2*i+2);
        return n;
    }

    static boolean bstOk=true,avlOk=true;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=sc.nextInt();
        Node root=build(arr,0);
        if(!isBST(root,Long.MIN_VALUE,Long.MAX_VALUE)){
            System.out.println("Invalid BST");
            return;
        }
        if(height(root)==-9999){
            System.out.println("Invalid AVL");
            return;
        }
        System.out.println("Valid");
    }

    static boolean isBST(Node node,long min,long max){
        if(node==null) return true;
        if(node.val<=min||node.val>=max) return false;
        return isBST(node.l,min,node.val)&&isBST(node.r,node.val,max);
    }

    static int height(Node node){
        if(node==null) return 0;
        int lh=height(node.l), rh=height(node.r);
        if(lh==-9999||rh==-9999||Math.abs(lh-rh)>1) return -9999;
        return Math.max(lh,rh)+1;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每節點檢查 BST 與高度，遞迴訪問一次 O(n)。
 */
