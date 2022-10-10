package lintcode;

import java.util.HashMap;
import java.util.Map;

public class UnionFind<T> {
    private Map<T, T> fathers;
    private Map<T, Integer> sizeOfSets;
    private int numberOfSets;

    public UnionFind() {
        fathers = new HashMap<>();
        sizeOfSets = new HashMap<>();
    }

    public void merge(T x, T y) {
        T rootX = find(x);
        T rootY = find(y);
        if (rootX != rootY) {
            fathers.put(rootX, rootY);
            numberOfSets--;
            sizeOfSets.put(rootY, sizeOfSets.get(rootX) + sizeOfSets.get(rootY));
        }
    }

    public T find(T x) {
        T node = x;
        // while (fathers.containsKey(node)) { // ATTN ensure that node/root is not null
        while (fathers.get(node) != null) {
            node = fathers.get(node);
        }
        T root = node;

        while (!x.equals(root)) {
            T currentFather = fathers.get(x);
            fathers.put(x, root);
            x = currentFather;
        }
        return root;
    }

    public void add(T x) {
        if (!fathers.containsKey(x)) {
            fathers.put(x, null);
            sizeOfSets.put(x, 1);
            numberOfSets++;
        }
    }

    public boolean isConnected(T x, T y) {
        return find(x).equals(find(y));
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public Integer getSizeOfSets(T x) {
        return sizeOfSets.get(find(x));
    }
    
}
