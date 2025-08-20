import java.util.*;

public class M03_TopKConvenience {
    static class Item implements Comparable<Item> {
        String name; int qty;
        Item(String n, int q) { name=n; qty=q; }
        public int compareTo(Item o) {
            if (qty != o.qty) return qty - o.qty; // Min-Heap by qty
            return name.compareTo(o.name);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), K = sc.nextInt();
        PriorityQueue<Item> pq = new PriorityQueue<>();
        for (int i=0;i<n;i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            pq.add(new Item(name, qty));
            if (pq.size()>K) pq.poll();
        }
        List<Item> list = new ArrayList<>(pq);
        list.sort((a,b)-> b.qty==a.qty? a.name.compareTo(b.name): b.qty-a.qty);
        for (Item it:list) System.out.println(it.name+" "+it.qty);
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：每個輸入 push/pop Heap O(log K)，總共 n 筆 → O(n log K)。
 */
