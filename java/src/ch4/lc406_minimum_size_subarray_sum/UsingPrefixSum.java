package ch4.lc406_minimum_size_subarray_sum;

public class UsingPrefixSum {

    public static void main(String[] args) {
        int answer = new UsingPrefixSum().minimumSize(new int[] { 2, 3, 1, 2, 4, 3 }, 7);
        System.out.println("testing result: " + answer);
    }

    public int minimumSize(int[] nums, int s) {
        int minLength = Integer.MAX_VALUE;
        int[] prefixSum = getPrefixSum(nums);

        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                int sumOfSubarray = prefixSum[end + 1] - prefixSum[start];
                if (sumOfSubarray >= s) {
                    minLength = Math.min(minLength, end - start + 1);
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }
        return minLength;
    }

    private int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return prefixSum;
    }

}
