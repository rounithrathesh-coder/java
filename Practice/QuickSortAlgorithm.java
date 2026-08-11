/**
 * Practice Implementation: In-Place Lomuto Partitioning Quick Sort
 * Revision: #60 | Date: 2026-08-11 14:41:55
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class QuickSortAlgorithm {
    public static void sort(int[] a, int l, int r) {
        if (l < r) {
            int pi = partition(a, l, r);
            sort(a, l, pi - 1);
            sort(a, pi + 1, r);
        }
    }
    private static int partition(int[] a, int l, int r) {
        int p = a[r], i = l - 1;
        for (int j = l; j < r; j++) {
            if (a[j] <= p) { int t = a[++i]; a[i] = a[j]; a[j] = t; }
        }
        int t = a[i + 1]; a[i + 1] = a[r]; a[r] = t;
        return i + 1;
    }
    public static void main(String[] args) {
        int[] nums = {10, 80, 30, 90, 40, 50, 70};
        sort(nums, 0, nums.length - 1);
        System.out.println("Sorted first: " + nums[0]);
    }
}
