/**
 * Daily Java Challenge - Stream API Deep Dive
 * Topic: Functional Programming in Modern Java
 */
import java.util.*;
import java.util.stream.Collectors;

public class Day004_Java_Streams_And_Lambdas {

    record Employee(String name, String department, double salary) {}

    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Alice", "Engineering", 120000),
            new Employee("Bob", "Engineering", 110000),
            new Employee("Charlie", "HR", 75000),
            new Employee("Diana", "Marketing", 85000),
            new Employee("Evan", "Engineering", 135000)
        );

        // Group by department and find average salary
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::department,
                Collectors.averagingDouble(Employee::salary)
            ));

        System.out.println("Average Salary by Department:");
        avgSalaryByDept.forEach((dept, avg) -> System.out.printf("  %s: $%,.2f%n", dept, avg));

        // Highest paid employee in Engineering
        employees.stream()
            .filter(e -> "Engineering".equals(e.department()))
            .max(Comparator.comparingDouble(Employee::salary))
            .ifPresent(top -> System.out.println("Top Earner in Eng: " + top.name() + " ($" + top.salary() + ")"));
    }
}
