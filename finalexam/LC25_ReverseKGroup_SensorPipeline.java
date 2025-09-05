import java.util.*;
import java.io.*;

class ListNode25 {
    int val;
    ListNode25 next;
    ListNode25(int x) { val = x; }
}

public class LC25_ReverseKGroup_SensorPipeline {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), k = sc.nextInt();
        ListNode25 dummy = new ListNode25(0), cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode25(sc.nextInt());
            cur = cur.next;
        }

        ListNode25 head = reverseKGroup(dummy.next, k);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode25 reverseKGroup(ListNode25 head, int k) {
        ListNode25 dummy = new ListNode25(0);
        dummy.next = head;
        ListNode25 prev = dummy, end = dummy;

        while (true) {
            int count = 0;
            while (count < k && end != null) {
                end = end.next;
                count++;
            }
            if (end == null) break;

            ListNode25 start = prev.next;
            ListNode25 next = end.next;
            end.next = null;

            prev.next = reverse(start);
            start.next = next;

            prev = start;
            end = prev;
        }
        return dummy.next;
    }

    private static ListNode25 reverse(ListNode25 head) {
        ListNode25 prev = null, cur = head;
        while (cur != null) {
            ListNode25 next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}

/*
解題思路：
- 每次找 k 個節點為一組，斷開後反轉，再接回去。
- 不足 k 個節點則不反轉。

時間複雜度：O(n)，每個節點最多被反轉一次。
空間複雜度：O(1)，僅用常數額外空間。
*/
