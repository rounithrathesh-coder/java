/**
 * Practice Implementation: Linear Time O(1) Space Trapping Rain Water
 * Revision: #115 | Date: 2026-09-15 17:54:58
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class TrappingRainWaterTwoPointer {
    public static int trap(int[] height) {
        int l = 0, r = height.length - 1, lMax = 0, rMax = 0, total = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= lMax) lMax = height[l];
                else total += lMax - height[l];
                l++;
            } else {
                if (height[r] >= rMax) rMax = height[r];
                else total += rMax - height[r];
                r--;
            }
        }
        return total;
    }
    public static void main(String[] args) {
        int[] h = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped water: " + trap(h));
    }
}
