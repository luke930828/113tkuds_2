import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 多路樹節點
    static class MultiWayNode {
        String val;
        List<MultiWayNode> children;
        MultiWayNode(String v) {
            val = v;
            children = new ArrayList<>();
        }
    }

    // 建立多路樹深度優先走訪 (DFS)
    public static void dfs(MultiWayNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiWayNode child : root.children) {
            dfs(child);
        }
    }

    // 建立多路樹廣度優先走訪 (BFS)
    public static void bfs(MultiWayNode root) {
        if (root == null) return;
        Queue<MultiWayNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            MultiWayNode node = q.poll();
            System.out.print(node.val + " ");
            for (MultiWayNode child : node.children) {
                q.offer(child);
            }
        }
    }

    // 計算多路樹高度
    public static int height(MultiWayNode root) {
        if (root == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayNode child : root.children) {
            maxChildHeight = Math.max(maxChildHeight, height(child));
        }
        return maxChildHeight + 1;
    }

    // 計算每個節點的度數 (子節點數量)
    public static Map<String, Integer> degreeCount(MultiWayNode root) {
        Map<String, Integer> map = new HashMap<>();
        degreeHelper(root, map);
        return map;
    }

    private static void degreeHelper(MultiWayNode node, Map<String, Integer> map) {
        if (node == null) return;
        map.put(node.val, node.children.size());
        for (MultiWayNode child : node.children) {
            degreeHelper(child, map);
        }
    }

    // 簡易決策樹模擬（猜數字遊戲）
    static class DecisionNode {
        String question;
        Map<String, DecisionNode> answers;
        String guess; // 猜測結果

        DecisionNode(String q) {
            question = q;
            answers = new HashMap<>();
            guess = null;
        }
    }

    public static void playGuessNumberGame() {
        DecisionNode root = new DecisionNode("Is it greater than 50?");
        DecisionNode left = new DecisionNode("Is it greater than 25?");
        DecisionNode right = new DecisionNode("Is it greater than 75?");
        DecisionNode leaf1 = new DecisionNode(null);
        leaf1.guess = "Number is between 0 and 25";
        DecisionNode leaf2 = new DecisionNode(null);
        leaf2.guess = "Number is between 26 and 50";
        DecisionNode leaf3 = new DecisionNode(null);
        leaf3.guess = "Number is between 51 and 75";
        DecisionNode leaf4 = new DecisionNode(null);
        leaf4.guess = "Number is between 76 and 100";

        root.answers.put("Yes", right);
        root.answers.put("No", left);
        left.answers.put("Yes", leaf2);
        left.answers.put("No", leaf1);
        right.answers.put("Yes", leaf4);
        right.answers.put("No", leaf3);

        Scanner sc = new Scanner(System.in);
        DecisionNode current = root;
        while (current.guess == null) {
            System.out.println(current.question + " (Yes/No)");
            String ans = sc.nextLine().trim();
            if (current.answers.containsKey(ans)) {
                current = current.answers.get(ans);
            } else {
                System.out.println("請輸入 'Yes' 或 'No'");
            }
        }
        System.out.println("猜測結果: " + current.guess);
        sc.close();
    }

    // 測試多路樹建立與遍歷
    public static MultiWayNode buildSampleMultiWayTree() {
        MultiWayNode root = new MultiWayNode("A");
        MultiWayNode b = new MultiWayNode("B");
        MultiWayNode c = new MultiWayNode("C");
        MultiWayNode d = new MultiWayNode("D");
        MultiWayNode e = new MultiWayNode("E");

        root.children.add(b);
        root.children.add(c);
        b.children.add(d);
        b.children.add(e);

        return root;
    }

    public static void main(String[] args) {
        MultiWayNode root = buildSampleMultiWayTree();

        System.out.print("DFS: ");
        dfs(root);
        System.out.println();

        System.out.print("BFS: ");
        bfs(root);
        System.out.println();

        System.out.println("多路樹高度: " + height(root));

        Map<String, Integer> degrees = degreeCount(root);
        System.out.println("每個節點度數: " + degrees);

        System.out.println("開始猜數字遊戲:");
        playGuessNumberGame();
    }
}
