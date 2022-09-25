package ch2.lc1507_shortest_subarray_with_sum_at_least_k;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class ValueIndexPair {
    int val, index;
    public ValueIndexPair(int val, int index) {
        this.val = val;
        this.index = index;
    } 
}

class Heap {
    private Queue<ValueIndexPair> minheap;
    private Set<Integer> deleteSet;
    public Heap() {
        minheap = new PriorityQueue<>((p1, p2) -> (p1.val - p2.val));
        deleteSet = new HashSet<>();
    }
    
    public void push(int index, int val) {
        minheap.add(new ValueIndexPair(val, index));
    }
    
    private void lazyDeletion() {
        while (minheap.size() != 0 && deleteSet.contains(minheap.peek().index)) {
            ValueIndexPair pair = minheap.poll();
            deleteSet.remove(pair.index);
        }
    }
    
    public ValueIndexPair top() {
        lazyDeletion();
        return minheap.peek();
    }
    
    public void pop() {
        lazyDeletion();
        minheap.poll();
    }
    
    public void delete(int index) {
        deleteSet.add(index);
    }
    
    public boolean isEmpty() {
        return minheap.size() == 0;
    }
}

public class Solution {
    
    public static void main(String[] args) {
        new Solution().shortestSubarray(new int[]{5, -1, 2, 3, -2}, 8);
    }

    /**
     * @param A: the array
     * @param K: sum
     * @return: the length
     */
    public int shortestSubarray(int[] A, int K) {
        int[] prefixSum = getPrefixSum(A);
        
        int start = 1, end = A.length;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (isValid(prefixSum, K, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isValid(prefixSum, K, start)) {
            return start;
        }
        if (isValid(prefixSum, K, end)) {
            return end;
        }
        return -1;
    }
    
    private boolean isValid(int[] prefixSum, int K, int length) {
        Heap minheap = new Heap();
        for (int end = 0; end < prefixSum.length; end++) {
            int index = end - length - 1;
            minheap.delete(index);
            if (!minheap.isEmpty() && prefixSum[end] - minheap.top().val >= K) {
                return true;
            }
            minheap.push(end, prefixSum[end]);
        }
        return false;
    }
    
    private int[] getPrefixSum(int[] A) {
        int[] prefixSum = new int[A.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < A.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }
        return prefixSum;
    }
}
