import java.util.*;

public class M01_BuildHeap {
    static String type;
    static int n;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        type = sc.next();
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        for (int i = n/2 - 1; i >= 0; i--) heapify(i, n);

        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
    }

    static void heapify(int i, int size) {
        int extreme = i;
        int l = 2*i+1, r = 2*i+2;

        if (type.equals("max")) {
            if (l < size && arr[l] > arr[extreme]) extreme = l;
            if (r < size && arr[r] > arr[extreme]) extreme = r;
        } else {
            if (l < size && arr[l] < arr[extreme]) extreme = l;
            if (r < size && arr[r] < arr[extreme]) extreme = r;
        }

        if (extreme != i) {
            int tmp = arr[i]; arr[i] = arr[extreme]; arr[extreme] = tmp;
            heapify(extreme, size);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，每個節點 heapify 平均 O(1)，總和 O(n)。
 */
