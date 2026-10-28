/**
 * Practice Implementation: Stream Collectors GroupingBy and PartitioningBy
 * Revision: #226 | Date: 2026-10-28 14:32:32
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class StreamGroupingByCollector {
    static class Employee {
        String dept; int salary;
        Employee(String d, int s) { dept = d; salary = s; }
    }
    public static void main(String[] args) {
        List<Employee> emps = Arrays.asList(
            new Employee("IT", 90000), new Employee("HR", 60000),
            new Employee("IT", 85000), new Employee("Finance", 75000)
        );
        Map<String, Double> avgByDept = emps.stream().collect(
            Collectors.groupingBy(e -> e.dept, Collectors.averagingInt(e -> e.salary))
        );
        System.out.println("IT Avg: " + avgByDept.get("IT"));
    }
}
