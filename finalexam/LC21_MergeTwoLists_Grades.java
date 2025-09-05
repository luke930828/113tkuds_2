import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LC21_MergeTwoLists_Grades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy1 = new ListNode(0), cur = dummy1;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }

        int m = sc.nextInt();
        ListNode dummy2 = new ListNode(0);
        cur = dummy2;
        for (int i = 0; i < m; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }

        ListNode merged = mergeTwoLists(dummy1.next, dummy2.next);
        while (merged != null) {
            System.out.print(merged.val + (merged.next != null ? " " : ""));
            merged = merged.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}

/*
解題思路：
- 使用兩指針逐步比較兩鏈表節點。
- 小的節點接到新鏈表後移動對應指針。
- 最後接上剩下的鏈表。

時間複雜度：O(n+m)，n,m 為兩鏈表長度。
空間複雜度：O(1)，僅使用固定額外指標。
*/
