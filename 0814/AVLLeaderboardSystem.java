
public class AVLLeaderboardSystem {
    static class Node {
        int score, size, height;
        String player;
        Node left, right;

        Node(String player, int score) {
            this.player = player;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    Node root;

    // TODO: 插入 + 更新分數
    // TODO: 維護子樹 size (方便排名計算)
    // TODO: select(k) 找出第 k 名
    // TODO: rank(score) 查分數排名

    public static void main(String[] args) {
        AVLLeaderboardSystem board = new AVLLeaderboardSystem();
        // TODO: 測試排行榜
    }
}
