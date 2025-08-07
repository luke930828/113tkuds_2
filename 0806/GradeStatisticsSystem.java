public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int sum = 0, max = scores[0], min = scores[0];
        int[] gradeCount = new int[5]; // A, B, C, D, F

        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;

            // 統計等第
            if (score >= 90) gradeCount[0]++;       // A
            else if (score >= 80) gradeCount[1]++;  // B
            else if (score >= 70) gradeCount[2]++;  // C
            else if (score >= 60) gradeCount[3]++;  // D
            else gradeCount[4]++;                   // F
        }

        double average = sum / (double)scores.length;

        // 高於平均人數
        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > average) aboveAverageCount++;
        }

        // 輸出報表
        System.out.printf("平均成績：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("等第統計：A=" + gradeCount[0] + " B=" + gradeCount[1] +
                " C=" + gradeCount[2] + " D=" + gradeCount[3] + " F=" + gradeCount[4]);
        System.out.println("高於平均的人數：" + aboveAverageCount);
        System.out.print("所有成績：");
        for (int score : scores) System.out.print(score + " ");
    }
}
