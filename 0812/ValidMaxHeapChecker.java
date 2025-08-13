public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n - 2) / 2; i >= 0; i--) { // 只檢查非葉子節點
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反規則的節點索引：" + left + " 值：" + arr[left]);
                return false;
            }
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反規則的節點索引：" + right + " 值：" + arr[right]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {100, 90, 80, 70, 60, 75, 65};
        System.out.println(isValidMaxHeap(arr1)); // true

        int[] arr2 = {100, 90, 80, 95, 60, 75, 65};
        System.out.println(isValidMaxHeap(arr2)); // false

        int[] arr3 = {50};
        System.out.println(isValidMaxHeap(arr3)); // true

        int[] arr4 = {};
        System.out.println(isValidMaxHeap(arr4)); // true
    }
}

/*
時間複雜度：
isValidMaxHeap() → O(n) 只需遍歷非葉子節點
*/
