package ch4.lc406_minimum_size_subarray_sum;

public class O_n_square {

    public static void main(String[] args) {
        System.out.println(new O_n_square().minimumSize(new int[] { 2, 3, 1, 2, 4, 3 }, 7));
    }

    public int minimumSize(int[] nums, int s) {
        int minLength = Integer.MAX_VALUE;
        int[] prefixSum = getPrefixSum(nums);
        int n = nums.length;

        for (int start = 0; start < n; start++) {
            minLength = Math.min(minLength, binarySearch(prefixSum, start, n - 1, s)); // n-1 is the last index of ***nums***
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    private int binarySearch(int[] prefixSum, int start, int end, int s) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (prefixSum[end + 1] - prefixSum[mid] >= s) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (prefixSum[end + 1] - prefixSum[start] >= s) {
            return end - start + 1;
        }
        return Integer.MAX_VALUE;
    }

    private int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        // prefixSum[0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return prefixSum;
    }

}
