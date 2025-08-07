import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {2, 4, 6, 8};

        // 移除重複元素
        int[] unique = removeDuplicates(arr1);
        System.out.println("移除重複後的陣列：" + Arrays.toString(unique));

        // 合併兩個已排序陣列
        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("合併後的陣列：" + Arrays.toString(merged));

        // 出現頻率最高的元素
        int mostFrequent = mostFrequentElement(arr1);
        System.out.println("出現頻率最高的元素：" + mostFrequent);

        // 分割陣列
        splitArray(arr1);
    }

    static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) result[i++] = num;
        return result;
    }

    static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j])
                result[k++] = arr1[i++];
            else
                result[k++] = arr2[j++];
        }
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        return result;
    }

    static int mostFrequentElement(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxFreq = 0, result = arr[0];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    static void splitArray(int[] arr) {
        int total = Arrays.stream(arr).sum();
        int halfSum = total / 2;
        int runningSum = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : arr) {
            if (runningSum + num <= halfSum) {
                left.add(num);
                runningSum += num;
            } else {
                right.add(num);
            }
        }
        System.out.println("分割後的陣列：");
        System.out.println("左邊：" + left);
        System.out.println("右邊：" + right);
    }
}
