import java.util.*;
import java.io.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LC24_SwapPairs_SensorPipeline {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }

        ListNode head = swapPairs(dummy.next);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
        }
        return dummy.next;
    }
}

/*
解題思路：
- 用 dummy 節點輔助。
- 每次交換相鄰兩個節點，然後繼續往後走。
- 保持鏈結正確。

時間複雜度：O(n)，必須遍歷鏈結串列。
空間複雜度：O(1)，僅用常數額外空間。
*/
