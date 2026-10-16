/**
 * Practice Implementation: Divide-and-Conquer Recursive Merge Sort
 * Revision: #198 | Date: 2026-10-16 10:41:06
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class MergeSortAlgorithm {
    public static void sort(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(a, l, m);
            sort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }
    private static void merge(int[] a, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) temp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i <= m) temp[k++] = a[i++];
        while (j <= r) temp[k++] = a[j++];
        System.arraycopy(temp, 0, a, l, temp.length);
    }
    public static void main(String[] args) {
        int[] nums = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 45};
        sort(nums, 0, nums.length - 1);
        System.out.println("First element: " + nums[0]);
    }
}
