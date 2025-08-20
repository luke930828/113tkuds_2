
import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (char c:s.toCharArray()) if (Character.isLetter(c)) sb.append(c);
        String t=sb.toString();
        System.out.println(isPal(t,0,t.length()-1)?"Yes":"No");
    }
    static boolean isPal(String s,int l,int r){
        if (l>=r) return true;
        return s.charAt(l)==s.charAt(r) && isPal(s,l+1,r-1);
    }
}
