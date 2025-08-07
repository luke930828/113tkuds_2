import java.util.*;

public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2},
            {3, 4}
        };

        int[][] B = {
            {5, 6},
            {7, 8}
        };

        // 加法
        int[][] sum = addMatrix(A, B);
        System.out.println("矩陣加法結果：");
        printMatrix(sum);

        // 乘法
        int[][] product = multiplyMatrix(A, B);
        System.out.println("矩陣乘法結果：");
        printMatrix(product);

        // 轉置
        int[][] transpose = transposeMatrix(A);
        System.out.println("矩陣轉置結果：");
        printMatrix(transpose);

        // 最大值與最小值
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] row : A) {
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }
        System.out.println("矩陣中的最大值：" + max);
        System.out.println("矩陣中的最小值：" + min);
    }

    static int[][] addMatrix(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B[0].length; j++)
                for (int k = 0; k < A[0].length; k++)
                    result[i][j] += A[i][k] * B[k][j];
        return result;
    }

    static int[][] transposeMatrix(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                result[j][i] = matrix[i][j];
        return result;
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }
}
