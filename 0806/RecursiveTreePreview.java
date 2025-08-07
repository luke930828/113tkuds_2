import java.util.*;

public class RecursiveTreePreview {

    // 模擬檔案系統
    static class Folder {
        String name;
        List<Object> contents; // 可以是 Folder 或 File

        Folder(String name) {
            this.name = name;
            this.contents = new ArrayList<>();
        }

        void add(Object o) {
            contents.add(o);
        }
    }

    static class File {
        String name;
        File(String name) {
            this.name = name;
        }
    }

    // 計算總檔案數
    static int countFiles(Object node) {
        if (node instanceof File) return 1;
        int count = 0;
        Folder folder = (Folder) node;
        for (Object item : folder.contents) {
            count += countFiles(item);
        }
        return count;
    }

    // 遞迴列印選單
    static void printMenu(Object node, int level) {
        if (node instanceof File) {
            System.out.println("  ".repeat(level) + ((File) node).name);
        } else {
            Folder folder = (Folder) node;
            System.out.println("  ".repeat(level) + "[" + folder.name + "]");
            for (Object item : folder.contents) {
                printMenu(item, level + 1);
            }
        }
    }

    // 展平巢狀陣列
    static void flatten(Object[] arr, List<Object> result) {
        for (Object item : arr) {
            if (item instanceof Object[]) {
                flatten((Object[]) item, result);
            } else {
                result.add(item);
            }
        }
    }

    // 計算巢狀深度
    static int maxDepth(Object[] arr) {
        int max = 1;
        for (Object item : arr) {
            if (item instanceof Object[])
                max = Math.max(max, 1 + maxDepth((Object[]) item));
        }
        return max;
    }

    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new File("a.txt"));
        Folder sub = new Folder("sub");
        sub.add(new File("b.txt"));
        root.add(sub);

        System.out.println("總檔案數：" + countFiles(root));
        System.out.println("\n選單列印：");
        printMenu(root, 0);

        Object[] nested = {1, new Object[]{2, 3, new Object[]{4}}, 5};
        List<Object> flat = new ArrayList<>();
        flatten(nested, flat);
        System.out.println("\n展平後：" + flat);
        System.out.println("最大深度：" + maxDepth(nested));
    }
}
