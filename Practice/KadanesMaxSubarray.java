/**
 * Practice Implementation: Maximum Subarray Linear Dynamic Programming (Kadane)
 * Revision: #208 | Date: 2026-10-19 20:13:54
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class KadanesMaxSubarray {
    public static int maxSubArray(int[] nums) {
        int max = nums[0], curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            max = Math.max(max, curr);
        }
        return max;
    }
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max Subarray Sum: " + maxSubArray(nums));
    }
}
