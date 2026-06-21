/**
 * Practice Implementation: Usage of Predicate, Consumer, Function, Supplier
 * Revision: #18 | Date: 2026-06-21 11:32:49
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.function.*;
public class PredefinedFunctionalInterfaces {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = x -> x > 0;
        Function<String, Integer> lengthFunc = String::length;
        Consumer<String> logger = System.out::println;
        Supplier<Double> rand = Math::random;

        if (isPositive.test(10)) {
            logger.accept("Length: " + lengthFunc.apply("Hello") + ", Rand: " + rand.get());
        }
    }
}
