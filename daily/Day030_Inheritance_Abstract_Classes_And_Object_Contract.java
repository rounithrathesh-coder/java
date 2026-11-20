/**
 * Daily Java Challenge - Day 030 (2026-11-20)
 * Topic: REC Unit II - Classes, Abstract Classes & Object Methods (equals/hashCode)
 * Category: Classes and Inheritance
 * Curriculum: REC R2023 IT - Unit II (Classes and Inheritance)
 * 
 * Description:
 * Demonstrates OOP inheritance hierarchies, constructor chaining with super(),
 * abstract classes with contract enforcement, and the java.lang.Object contract
 * (equals, hashCode, toString).
 */
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class Day030_Inheritance_Abstract_Classes_And_Object_Contract {

    public static abstract class Employee {
        protected final String empId;
        protected String name;
        protected double baseSalary;

        public Employee(String empId, String name, double baseSalary) {
            this.empId = Objects.requireNonNull(empId, "Employee ID cannot be null");
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            this.baseSalary = baseSalary;
        }

        // Abstract method: mandatory contract for subclasses
        public abstract double calculateTotalCompensation();

        public String getEmpId() { return empId; }
        public String getName() { return name; }
        public double getBaseSalary() { return baseSalary; }

        // java.lang.Object Contract: equals and hashCode must be consistent
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Objects.equals(empId, employee.empId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(empId);
        }

        @Override
        public String toString() {
            return String.format("%s[ID=%s, Name='%s', Base=$%.2f, Total=$%.2f]",
                    getClass().getSimpleName(), empId, name, baseSalary, calculateTotalCompensation());
        }
    }

    public static class SoftwareEngineer extends Employee {
        private final double stockBonus;

        public SoftwareEngineer(String empId, String name, double baseSalary, double stockBonus) {
            super(empId, name, baseSalary); // Constructor chaining
            this.stockBonus = stockBonus;
        }

        @Override
        public double calculateTotalCompensation() {
            return baseSalary + stockBonus;
        }
    }

    public static class EngineeringManager extends Employee {
        private final double performanceBonus;
        private final int teamSize;

        public EngineeringManager(String empId, String name, double baseSalary, double performanceBonus, int teamSize) {
            super(empId, name, baseSalary);
            this.performanceBonus = performanceBonus;
            this.teamSize = teamSize;
        }

        @Override
        public double calculateTotalCompensation() {
            return baseSalary + performanceBonus + (teamSize * 500.0);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== REC Unit II: Inheritance & Object Contract Demo ===");
        Employee dev = new SoftwareEngineer("E101", "Alex Chen", 120000, 25000);
        Employee mgr = new EngineeringManager("E102", "Sarah Connor", 150000, 40000, 8);
        Employee devClone = new SoftwareEngineer("E101", "Alex Chen (Duplicate ID)", 120000, 25000);

        System.out.println("Dev: " + dev);
        System.out.println("Mgr: " + mgr);

        // Verify Object Contract (equals and hashCode)
        System.out.println("\nChecking Equality Contract:");
        System.out.println("dev.equals(devClone) -> " + dev.equals(devClone) + " (Expected: true)");
        assert dev.equals(devClone) : "Equals contract failed!";

        Set<Employee> teamSet = new HashSet<>();
        teamSet.add(dev);
        teamSet.add(mgr);
        teamSet.add(devClone); // Should be deduplicated by hash set
        System.out.println("Set size after adding duplicate ID: " + teamSet.size() + " (Expected: 2)");
        assert teamSet.size() == 2 : "HashSet deduplication failed!";
        System.out.println("All OOP contract verifications PASSED successfully!");
    }
}
