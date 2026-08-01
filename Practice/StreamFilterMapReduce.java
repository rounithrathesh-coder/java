/**
 * Practice Implementation: Java 8 Stream Pipeline Filter, Map, and Reduce
 * Summer Revision: #133 | Date: 2026-08-01 22:22:30
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.Arrays;
import java.util.List;
public class StreamFilterMapReduce {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfEvenSquares = nums.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .reduce(0, Integer::sum);
        System.out.println("Sum of even squares: " + sumOfEvenSquares);
    }
}
