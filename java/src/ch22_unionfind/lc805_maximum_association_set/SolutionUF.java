package ch22_unionfind.lc805_maximum_association_set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lintcode.UnionFind;

/**
 * https://www.jiuzhang.com/solutions/maximum-association-set
 * 
 * UnionFind
 * 
 */
public class SolutionUF {

    public static void main(String[] args) {
        SolutionUF solution = new SolutionUF();
        System.out.println(solution.maximumAssociationSet(
                new String[] { "abc", "abc", "abc" },
                new String[] { "bcd", "acd", "def" })); // ["abc","acd","bcd","def"]
        System.out.println(solution.maximumAssociationSet(
                new String[] { "a", "b", "d", "e", "f" },
                new String[] { "b", "c", "e", "g", "g" })); // [d, e, f, g]
    }

    public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {
        UnionFind<String> uf = new UnionFind<>();

        for (int i = 0; i < ListA.length; i++) {
            uf.add(ListA[i]);
            uf.add(ListB[i]);
            uf.merge(ListA[i], ListB[i]);
        }

        int maxSize = 0;
        String root = null;
        for (int i = 0; i < ListA.length; i++) {
            int sizeOfSet = uf.getSizeOfSets(ListA[i]);
            if (maxSize < sizeOfSet) {
                root = ListA[i];
                maxSize = sizeOfSet;
            }

            sizeOfSet = uf.getSizeOfSets(ListB[i]);
            if (maxSize < sizeOfSet) {
                root = ListB[i];
                maxSize = sizeOfSet;
            }
        }

        Set<String> results = new HashSet<>();
        for (int i = 0; i < ListA.length; i++) {
            if (uf.isConnected(ListA[i], root)) {
                results.add(ListA[i]);
            }
            if (uf.isConnected(ListB[i], root)) {
                results.add(ListB[i]);
            }
        }
        return new ArrayList<>(results);
    }

}
