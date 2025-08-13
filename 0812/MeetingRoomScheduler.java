import java.util.*;

public class MeetingRoomScheduler {
    public static int minMeetingRooms(int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        for (int[] m : meetings) {
            if (!endTimes.isEmpty() && endTimes.peek() <= m[0]) {
                endTimes.poll();
            }
            endTimes.offer(m[1]);
        }
        return endTimes.size();
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}}));   // 2
        System.out.println(minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}}));     // 2
    }
}

/*
時間複雜度：
O(n log n)
*/
