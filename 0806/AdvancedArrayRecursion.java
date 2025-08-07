import java.util.*;

public class AdvancedArrayRecursion {

    // 遞迴快速排序
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    // 遞迴合併兩個已排序陣列
    static int[] mergeSortedArrays(int[] a, int[] b, int i, int j, List<Integer> result) {
        if (i == a.length) {
            while (j < b.length) result.add(b[j++]);
        } else if (j == b.length) {
            while (i < a.length) result.add(a[i++]);
        } else if (a[i] < b[j]) {
            result.add(a[i]);
            mergeSortedArrays(a, b, i + 1, j, result);
        } else {
            result.add(b[j]);
            mergeSortedArrays(a, b, i, j + 1, result);
        }
        return result.stream().mapToInt(x -> x).toArray();
    }

    // 遞迴尋找第 k 小元素（快速選擇 QuickSelect）
    static int quickSelect(int[] arr, int k, int left, int right) {
        if (left == right) return arr[left];
        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return quickSelect(arr, k, left, pivotIndex - 1);
        else return quickSelect(arr, k, pivotIndex + 1, right);
    }

    // 遞迴檢查是否存在子序列總和等於目標
    static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length || target < 0) return false;
        return subsetSum(arr, index + 1, target - arr[index]) || subsetSum(arr, index + 1, target);
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序後：" + Arrays.toString(arr));

        int[] a = {1, 3, 5}, b = {2, 4, 6};
        int[] merged = mergeSortedArrays(a, b, 0, 0, new ArrayList<>());
        System.out.println("合併後：" + Arrays.toString(merged));

        int[] q = {3, 2, 1, 5, 4};
        System.out.println("第 3 小元素：" + quickSelect(q, 2, 0, q.length - 1));

        int[] s = {3, 34, 4, 12, 5, 2};
        System.out.println("是否有子序列和為 9？" + subsetSum(s, 0, 9));
    }
}
