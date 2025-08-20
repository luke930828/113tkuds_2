import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); sc.nextLine();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) times[i] = parse(sc.nextLine());
        int q = parse(sc.nextLine());

        int idx = Arrays.binarySearch(times, q+1);
        if (idx < 0) idx = -idx - 1;
        if (idx >= n) System.out.println("No bike");
        else System.out.printf("%02d:%02d\n", times[idx]/60, times[idx]%60);
    }

    static int parse(String s) {
        String[] p = s.split(":");
        return Integer.parseInt(p[0])*60 + Integer.parseInt(p[1]);
    }
}
