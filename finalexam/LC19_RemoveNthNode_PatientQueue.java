import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LC19_RemoveNthNode_PatientQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 鏈表長度
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        int k = sc.nextInt(); // 倒數第 k 個
        ListNode head = removeNthFromEnd(dummy.next, k);

        // 輸出結果
        cur = head;
        while (cur != null) {
            System.out.print(cur.val + (cur.next != null ? " " : ""));
            cur = cur.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}

/*
解題思路：
- 使用快慢指針。
- 快指針先走 n+1 步，之後快慢指針一起走。
- 當快指針到尾，慢指針正好停在欲刪除節點前一個。

時間複雜度：O(L)，L 為鏈表長度。
空間複雜度：O(1)。
*/
