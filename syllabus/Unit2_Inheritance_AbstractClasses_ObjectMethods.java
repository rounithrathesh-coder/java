/**
 * REC R2023 IT Curriculum - Unit II: Classes and Inheritance
 * Topic: Inheritance Hierarchy, Abstract Classes, Constructor Chaining & Object Class Invariants
 *
 * Concepts Demonstrated:
 * 1. Constructor Chaining using super() and this().
 * 2. Abstract Classes with abstract and template methods.
 * 3. Method Overriding vs. Method Hiding (@Override).
 * 4. Contract for java.lang.Object methods: equals(), hashCode(), toString().
 */

import java.util.Objects;

public class Unit2_Inheritance_AbstractClasses_ObjectMethods {

    // Abstract Base Class
    public abstract static class Employee {
        private final int id;
        private final String name;
        protected double baseSalary;

        // Constructor chaining
        public Employee(int id, String name, double baseSalary) {
            this.id = id;
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            this.baseSalary = baseSalary;
        }

        // Abstract method: MUST be implemented by concrete subclasses
        public abstract double calculateTotalCompensation();

        // Concrete method accessible to all subclasses
        public int getId() { return id; }
        public String getName() { return name; }
        public double getBaseSalary() { return baseSalary; }

        // Object Class Contract: equals and hashCode consistency
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Employee employee)) return false;
            return id == employee.id && Objects.equals(name, employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return String.format("%s[id=%d, name='%s', baseSalary=%.2f]",
                    getClass().getSimpleName(), id, name, baseSalary);
        }
    }

    // Concrete Subclass 1: SoftwareEngineer
    public static class SoftwareEngineer extends Employee {
        private final double bonus;

        public SoftwareEngineer(int id, String name, double baseSalary, double bonus) {
            super(id, name, baseSalary); // Call base constructor
            this.bonus = bonus;
        }

        @Override
        public double calculateTotalCompensation() {
            return baseSalary + bonus;
        }
    }

    // Concrete Subclass 2: EngineeringManager
    public static class EngineeringManager extends Employee {
        private final int teamSize;
        private final double stockGrants;

        public EngineeringManager(int id, String name, double baseSalary, int teamSize, double stockGrants) {
            super(id, name, baseSalary);
            this.teamSize = teamSize;
            this.stockGrants = stockGrants;
        }

        @Override
        public double calculateTotalCompensation() {
            return baseSalary + (teamSize * 1500.0) + stockGrants;
        }
    }

    public static void main(String[] args) {
        System.out.println("=======================================================================");
        System.out.println("REC R2023 IT - Unit II: Classes, Inheritance & Object Class Contract");
        System.out.println("=======================================================================");

        Employee dev = new SoftwareEngineer(101, "Rounith", 95000, 15000);
        Employee mgr = new EngineeringManager(102, "Sarah", 130000, 8, 25000);

        // Polymorphic dynamic dispatch
        System.out.println("Polymorphic Execution:");
        System.out.println("  " + dev + " -> Total: $" + dev.calculateTotalCompensation());
        System.out.println("  " + mgr + " -> Total: $" + mgr.calculateTotalCompensation());

        assert dev.calculateTotalCompensation() == 110000 : "Dev compensation calculation error!";
        assert mgr.calculateTotalCompensation() == 167000 : "Manager compensation calculation error!";

        // Test Object Class equals & hashCode contract
        Employee devDuplicate = new SoftwareEngineer(101, "Rounith", 95000, 15000);
        assert dev.equals(devDuplicate) : "equals() contract violated!";
        assert dev.hashCode() == devDuplicate.hashCode() : "hashCode() consistency contract violated!";
        assert !dev.equals(mgr) : "Different entities must not be equal!";

        System.out.println("\nAll Unit II assertions passed successfully!");
    }
}
