/**
 * REC R2023 IT Curriculum - Unit V: Streams API, Lambda and JDBC
 * Topic: Predefined Functional Interfaces (Predicate, Function, Consumer, Supplier) & Stream Reductions
 *
 * Concepts Demonstrated:
 * 1. java.util.function Predefined Interfaces:
 *    - Predicate<T>: Boolean-valued test (filter).
 *    - Function<T, R>: Transformation (map).
 *    - Consumer<T>: Side-effect processor (forEach).
 *    - Supplier<T>: Lazy producer / factory.
 *    - BiFunction<T, U, R>: Two-argument mapping.
 * 2. Higher-order function composition (andThen, compose, and, or).
 * 3. Stream Reduction (reduce) & Grouping Collectors.
 */

import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

public class Unit5_Predefined_Functional_Interfaces {

    record Student(String name, String department, double cgpa) {}

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("REC R2023 IT - Unit V: Predefined Functional Interfaces & Reductions");
        System.out.println("=======================================================================");

        List<Student> students = List.of(
            new Student("Rounith", "IT", 9.2),
            new Student("Ananya",  "CSE", 8.8),
            new Student("Karthik", "IT", 8.4),
            new Student("Deepa",   "ECE", 9.0),
            new Student("Vikram",  "IT", 7.9)
        );

        // 1. Predicate<T> (Testing condition)
        Predicate<Student> isItDept = s -> "IT".equals(s.department());
        Predicate<Student> isHighAchiever = s -> s.cgpa() >= 8.5;
        Predicate<Student> itHonorsStudent = isItDept.and(isHighAchiever); // Composition

        List<Student> honors = students.stream().filter(itHonorsStudent).toList();
        System.out.println("IT Honors Students: " + honors.stream().map(Student::name).toList());
        assert honors.size() == 1 && "Rounith".equals(honors.get(0).name()) : "Predicate composition error!";

        // 2. Function<T, R> (Transformation)
        Function<Student, String> nameExtractor = Student::name;
        Function<String, String> upperCaseConverter = String::toUpperCase;
        Function<Student, String> studentUpperName = nameExtractor.andThen(upperCaseConverter);

        List<String> upperNames = students.stream().map(studentUpperName).toList();
        assert upperNames.contains("ROUNITH") : "Function composition error!";

        // 3. Consumer<T> (Accepts argument with no return value)
        Consumer<Student> printSummary = s -> System.out.printf("  [Record] %s (%s) - CGPA: %.1f%n", s.name(), s.department(), s.cgpa());
        System.out.println("\nConsumer Processing:");
        students.forEach(printSummary);

        // 4. Supplier<T> (Produces result lazily)
        Supplier<Student> defaultStudentSupplier = () -> new Student("Guest", "General", 0.0);
        Student fallback = defaultStudentSupplier.get();
        assert "Guest".equals(fallback.name()) : "Supplier error!";

        // 5. Stream Reduction (Calculate Average CGPA via reduce)
        double totalCgpa = students.stream()
                .map(Student::cgpa)
                .reduce(0.0, Double::sum);
        double avgCgpa = totalCgpa / students.size();
        System.out.printf("%nAverage CGPA across all departments: %.2f%n", avgCgpa);
        assert Math.abs(avgCgpa - 8.66) < 0.05 : "Reduction error!";

        // 6. Grouping Collector
        Map<String, List<Student>> byDept = students.stream()
                .collect(Collectors.groupingBy(Student::department));
        assert byDept.get("IT").size() == 3 : "Grouping error!";

        System.out.println("\nAll Unit V Functional Interface assertions passed successfully!");
    }
}
