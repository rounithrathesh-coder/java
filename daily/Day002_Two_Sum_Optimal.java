/**
 * Daily Java Challenge - Two Sum (Optimal O(N) Hash Table Solution)
 * Topic: Arrays, Hash Maps
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Day002_Two_Sum_Optimal {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> complementMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (complementMap.containsKey(complement)) {
                return new int[] { complementMap.get(complement), i };
            }
            complementMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution found");
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("Numbers: " + Arrays.toString(nums) + ", Target: " + target);
        System.out.println("Indices: " + Arrays.toString(result));
        assert result[0] == 0 && result[1] == 1 : "Test failed!";
        System.out.println("Test passed!");
    }
}
