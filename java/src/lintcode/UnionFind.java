package lintcode;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private Map<Integer, Integer> fathers;
    private Map<Integer, Integer> sizeOfSets;
    private int numberOfSets;

    public UnionFind() {
        fathers = new HashMap<>();
        sizeOfSets = new HashMap<>();
    }

    public void merge(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            fathers.put(rootX, rootY);
            numberOfSets--;
            sizeOfSets.put(rootY, sizeOfSets.get(rootX) + sizeOfSets.get(rootY));
        }
    }

    public int find(Integer x) {
        Integer node = x;
        // while (fathers.containsKey(node)) {  // ATTN ensure that node/root is not null
        while (fathers.get(node) != null) {
            node = fathers.get(node);
        }
        Integer root = node;

        while (x != root) {
            Integer currentFather = fathers.get(x);
            fathers.put(x, root);
            x = currentFather;
        }
        return root;
    }

    public void add(int x) {
        if (!fathers.containsKey(x)) {
            fathers.put(x, null);
            sizeOfSets.put(x, 1);
            numberOfSets++;
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public int getSizeOfSets(int x) {
        return sizeOfSets.get(find(x));
    }
}
