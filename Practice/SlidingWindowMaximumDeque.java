/**
 * Practice Implementation: Monotonic Decreasing Deque for Sliding Window Maximum
 * Revision: #41 | Date: 2026-07-17 22:48:39
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.*;
public class SlidingWindowMaximumDeque {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            dq.offerLast(i);
            if (i >= k - 1) res[i - k + 1] = nums[dq.peekFirst()];
        }
        return res;
    }
    public static void main(String[] args) {
        int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] r = maxSlidingWindow(a, 3);
        System.out.println("Window max: " + Arrays.toString(r));
    }
}
