/**
 * Daily Java Challenge - Day 018 (2026-11-12)
 * Topic: Interval Scheduling - Merge Overlapping Intervals & Insert Interval
 * Category: Algorithms / Greedy / Sorting
 *
 * Description:
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 * Also includes an optimal O(n) insertInterval() implementation.
 *
 * Time Complexity:
 * - merge(): O(N log N) dominated by sorting
 * - insert(): O(N) linear sweep without re-sorting
 * Space Complexity:
 * - O(N) auxiliary space for storing the merged output
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day018_Merge_Intervals {

    /**
     * Merges all overlapping intervals.
     *
     * Invariant:
     * After sorting by start time, if intervals[i][start] <= current_merged[end],
     * they must overlap and can be merged into:
     *   [current_merged[start], max(current_merged[end], intervals[i][end])]
     *
     * @param intervals 2D array of [start, end] pairs
     * @return 2D array of merged non-overlapping intervals
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. Sort intervals based on starting boundary
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = new int[] { intervals[0][0], intervals[0][1] };
        merged.add(currentInterval);

        for (int i = 1; i < intervals.length; i++) {
            int[] nextInterval = intervals[i];

            // Check if current and next interval overlap
            if (nextInterval[0] <= currentInterval[1]) {
                // Overlapping: extend the end boundary if needed
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                // Disjoint: start a new interval
                currentInterval = new int[] { nextInterval[0], nextInterval[1] };
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * Inserts a newInterval into intervals (which is already sorted and non-overlapping),
     * merging if necessary, in O(n) time and O(n) space.
     *
     * @param intervals sorted, non-overlapping intervals
     * @param newInterval interval to insert
     * @return updated non-overlapping intervals
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1. Add all intervals ending before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. Merge all overlapping intervals with newInterval
        int[] mergedInterval = new int[] { newInterval[0], newInterval[1] };
        while (i < n && intervals[i][0] <= mergedInterval[1]) {
            mergedInterval[0] = Math.min(mergedInterval[0], intervals[i][0]);
            mergedInterval[1] = Math.max(mergedInterval[1], intervals[i][1]);
            i++;
        }
        result.add(mergedInterval);

        // 3. Add all remaining intervals starting after newInterval ends
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("Daily Java Challenge - Day 018: Merge Intervals");
        System.out.println("=================================================");

        // Test 1: Standard overlapping intervals
        int[][] input1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected1 = {{1, 6}, {8, 10}, {15, 18}};
        int[][] actual1 = merge(input1);
        System.out.println("Test 1 Input:    " + Arrays.deepToString(input1));
        System.out.println("Test 1 Result:   " + Arrays.deepToString(actual1));
        assert Arrays.deepEquals(actual1, expected1) : "Test 1 failed!";
        System.out.println("-> Test 1 Passed!");

        // Test 2: Consecutive touching intervals [1, 4] and [4, 5]
        int[][] input2 = {{1, 4}, {4, 5}};
        int[][] expected2 = {{1, 5}};
        int[][] actual2 = merge(input2);
        System.out.println("\nTest 2 Input:    " + Arrays.deepToString(input2));
        System.out.println("Test 2 Result:   " + Arrays.deepToString(actual2));
        assert Arrays.deepEquals(actual2, expected2) : "Test 2 failed!";
        System.out.println("-> Test 2 Passed!");

        // Test 3: Completely nested intervals
        int[][] input3 = {{1, 10}, {2, 3}, {4, 8}, {9, 12}};
        int[][] expected3 = {{1, 12}};
        int[][] actual3 = merge(input3);
        System.out.println("\nTest 3 Input:    " + Arrays.deepToString(input3));
        System.out.println("Test 3 Result:   " + Arrays.deepToString(actual3));
        assert Arrays.deepEquals(actual3, expected3) : "Test 3 failed!";
        System.out.println("-> Test 3 Passed!");

        // Test 4: Insert interval into non-overlapping sorted list
        int[][] input4 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval4 = {4, 8};
        int[][] expected4 = {{1, 2}, {3, 10}, {12, 16}};
        int[][] actual4 = insert(input4, newInterval4);
        System.out.println("\nTest 4 Insert:   " + Arrays.toString(newInterval4) + " into " + Arrays.deepToString(input4));
        System.out.println("Test 4 Result:   " + Arrays.deepToString(actual4));
        assert Arrays.deepEquals(actual4, expected4) : "Test 4 failed!";
        System.out.println("-> Test 4 Passed!");

        // Test 5: Single element / empty edge cases
        int[][] input5 = {{1, 5}};
        assert Arrays.deepEquals(merge(input5), new int[][]{{1, 5}}) : "Test 5 failed!";
        System.out.println("\nTest 5 (Single Interval) Passed!");

        System.out.println("\n=================================================");
        System.out.println("ALL 5 ASSERTIONS PASSED SUCCESSFULLY! (O(N log N))");
        System.out.println("=================================================");
    }
}
