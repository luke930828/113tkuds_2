import java.util.Scanner;

public class RecursiveMathCalculator {

    // 組合數 C(n, k) = C(n-1, k-1) + C(n-1, k)
    static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 卡塔蘭數 Catalan(n) = Σ(Catalan(i) * Catalan(n - 1 - i))
    static long catalan(int n) {
        if (n <= 1) return 1;
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 漢諾塔移動步數：hanoi(n) = 2 * hanoi(n - 1) + 1
    static long hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 判斷是否為回文數（整數）
    static boolean isPalindrome(int num) {
        return num == reverse(num, 0);
    }

    static int reverse(int num, int rev) {
        if (num == 0) return rev;
        return reverse(num / 10, rev * 10 + num % 10);
    }

    // 主程式測試
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 組合數
        System.out.print("請輸入 n 與 k 計算 C(n, k): ");
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println("C(" + n + ", " + k + ") = " + combination(n, k));

        // 卡塔蘭數
        System.out.print("請輸入 n 計算第 n 個卡塔蘭數: ");
        int cn = scanner.nextInt();
        System.out.println("Catalan(" + cn + ") = " + catalan(cn));

        // 漢諾塔
        System.out.print("請輸入盤子數量 n 計算漢諾塔步數: ");
        int hn = scanner.nextInt();
        System.out.println("Hanoi(" + hn + ") = " + hanoiMoves(hn));

        // 回文數判斷
        System.out.print("請輸入一個整數判斷是否為回文數: ");
        int pal = scanner.nextInt();
        System.out.println(pal + (isPalindrome(pal) ? " 是回文數" : " 不是回文數"));

        scanner.close();
    }
}
