import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int comparisons = 0;
        int swaps = 0;

        System.out.println("原始陣列：" + Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            System.out.printf("第 %d 輪結果：%s\n", i + 1, Arrays.toString(arr));
        }

        System.out.println("排序完成！");
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
    }
}
