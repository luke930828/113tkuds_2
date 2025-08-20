import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node{
        int val; char color; Node l,r;
        Node(int v,char c){val=v;color=c;}
    }
    static Node build(int[] vals,char[] cols,int i){
        if(i>=vals.length||vals[i]==-1) return null;
        Node n=new Node(vals[i],cols[i]);
        n.l=build(vals,cols,2*i+1);
        n.r=build(vals,cols,2*i+2);
        return n;
    }
    static boolean redRed=false,blackMismatch=false;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] vals=new int[n]; char[] cols=new char[n];
        for(int i=0;i<n;i++){vals[i]=sc.nextInt(); cols[i]=sc.next().charAt(0);}
        Node root=build(vals,cols,0);
        if(root!=null && root.color!='B'){System.out.println("RootNotBlack");return;}
        if(check(root)==-1){if(redRed) System.out.println("RedRedViolation"); else System.out.println("BlackHeightMismatch");return;}
        System.out.println("RB Valid");
    }
    static int check(Node n){
        if(n==null) return 1;
        int l=check(n.l), r=check(n.r);
        if(l==-1||r==-1||l!=r){blackMismatch=true;return -1;}
        if(n.color=='R'){
            if((n.l!=null&&n.l.color=='R')||(n.r!=null&&n.r.color=='R')){redRed=true;return -1;}
            return l;
        } else return l+1;
    }
}
