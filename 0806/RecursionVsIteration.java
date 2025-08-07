public class RecursionVsIteration {

    // 遞迴與迭代版 C(n, k)
    static int combRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combRecursive(n - 1, k - 1) + combRecursive(n - 1, k);
    }

    static int combIterative(int n, int k) {
        int res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    // 陣列乘積
    static int productRecursive(int[] arr, int i) {
        if (i == arr.length) return 1;
        return arr[i] * productRecursive(arr, i + 1);
    }

    static int productIterative(int[] arr) {
        int prod = 1;
        for (int num : arr) prod *= num;
        return prod;
    }

    // 計算元音數量
    static int vowelRecursive(String str, int i) {
        if (i == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(i));
        return (isVowel(c) ? 1 : 0) + vowelRecursive(str, i + 1);
    }

    static int vowelIterative(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray())
            if (isVowel(c)) count++;
        return count;
    }

    static boolean isVowel(char c) {
        return "aeiou".indexOf(c) >= 0;
    }

    // 檢查括號是否配對
    static boolean isBalancedRecursive(String str, int i, int count) {
        if (i == str.length()) return count == 0;
        if (str.charAt(i) == '(') count++;
        else if (str.charAt(i) == ')') count--;
        if (count < 0) return false;
        return isBalancedRecursive(str, i + 1, count);
    }

    static boolean isBalancedIterative(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println("C(5,2)：遞迴：" + combRecursive(5, 2) + "，迭代：" + combIterative(5, 2));
        int[] nums = {2, 3, 4};
        System.out.println("乘積：遞迴：" + productRecursive(nums, 0) + "，迭代：" + productIterative(nums));
        System.out.println("元音數量：遞迴：" + vowelRecursive("Education", 0) + "，迭代：" + vowelIterative("Education"));
        System.out.println("括號配對：遞迴：" + isBalancedRecursive("(()())", 0, 0) + "，迭代：" + isBalancedIterative("(()())"));
    }
}
