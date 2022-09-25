package ch4.lc406_minimum_size_subarray_sum;

public class MinimumSizeBF {

    public static void main(String[] args) {
        System.out.println(new MinimumSizeBF().minimumSize(new int[]{2, 3, 1, 2, 4, 3}, 7));
    }

    public int minimumSize(int[] nums, int s) {
        int minLength = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                int sumOfSubarray = 0;
                for (int i = start; i <= end; i++) {
                    sumOfSubarray += nums[i];
                }
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

}
