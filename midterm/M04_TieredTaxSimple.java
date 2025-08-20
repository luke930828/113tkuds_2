import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] income = new int[n];
        long sum=0;
        for (int i=0;i<n;i++) income[i]=sc.nextInt();
        for (int x:income) {
            long tax = calc(x);
            sum+=tax;
            System.out.println("Tax: "+tax);
        }
        System.out.println("Average: "+(sum/n));
    }

    static long calc(int x) {
        if (x<=120000) return Math.round(x*0.05);
        else if (x<=500000) return Math.round(120000*0.05+(x-120000)*0.12);
        else if (x<=1000000) return Math.round(120000*0.05+(500000-120000)*0.12+(x-500000)*0.20);
        else return Math.round(120000*0.05+(500000-120000)*0.12+(1000000-500000)*0.20+(x-1000000)*0.30);
    }
}

/*
 * Time Complexity: O(n)
 * 說明：逐一計算每筆收入稅額 O(1)，總計 O(n)。
 */
