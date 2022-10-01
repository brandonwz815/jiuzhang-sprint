package ch22;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private Map<Integer, Integer> fathers;
    private Map<Integer, Integer> sizeOfSets;
    private int numberOfSets;

    public UnionFind() {
        fathers = new HashMap<>)();
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

    private int find(int x) {
        int node = x;
        while (fathers.containsKey(node)) {
            node = fathers.get(node);
        }
        int root = node;
        
        while (x != root) {
            int currentFather = fathers.get(x);
            fathers.put(x, root);
            x = currentFather;
        }
        return root;
    }

    private void add(int x) {
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
