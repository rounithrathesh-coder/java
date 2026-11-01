# 🏛️ Object-Oriented Programming (OOP) Principles & Design Guide

A comprehensive architectural reference covering Object-Oriented Programming pillars, access protection matrices, class contracts, and polymorphic dispatch mechanics in Java.

---

## 1. The Four Pillars of OOP

```
                   +---------------------------+
                   |  Object-Oriented Design   |
                   +---------------------------+
                      /       |         |        \
                     /        |         |         \
         +--------------+ +---------------+ +---------------+ +---------------+
         | Encapsulation| |  Inheritance  | | Polymorphism  | |  Abstraction  |
         | Data Hiding  | | Code Reuse    | | Dynamic Bind  | | Hide Details  |
         +--------------+ +---------------+ +---------------+ +---------------+
```

1. **Encapsulation:**
   - Bundling data (state) and methods (behavior) within a single class while restricting direct access to mutable fields via `private` access specifiers and validating mutator methods (getters and setters).

2. **Inheritance:**
   - Enabling a child class (`subclass`) to acquire properties and behaviors of a parent class (`superclass`) via the `extends` keyword. Fosters code reusability and semantic taxonomy (`is-a` relationship).

3. **Polymorphism:**
   - Ability of an entity (method, object) to take on multiple forms:
     - **Compile-Time Polymorphism (Static Binding):** Method overloading (different signatures in the same class).
     - **Runtime Polymorphism (Dynamic Binding):** Method overriding (resolved at runtime via virtual method table / vtable lookup).

4. **Abstraction:**
   - Hiding internal complexity and exposing only essential interfaces to users via `abstract classes` and `interfaces`.

---

## 2. Java Access Protection Matrix

| Access Modifier | Same Class | Same Package | Subclass (Different Package) | World (Everywhere) |
| :--- | :---: | :---: | :---: | :---: |
| `private` | **Yes** | No | No | No |
| *(default / package-private)* | **Yes** | **Yes** | No | No |
| `protected` | **Yes** | **Yes** | **Yes** | No |
| `public` | **Yes** | **Yes** | **Yes** | **Yes** |

---

## 3. The `java.lang.Object` Class Contract

Every class in Java directly or indirectly inherits from `java.lang.Object`. Implementing robust enterprise classes requires adhering to core contracts:

### A. The `equals()` & `hashCode()` Contract
- **Reflexive:** `x.equals(x)` must return `true`.
- **Symmetric:** `x.equals(y)` returns `true` if and only if `y.equals(x)` returns `true`.
- **Transitive:** If `x.equals(y)` and `y.equals(z)`, then `x.equals(z)`.
- **Consistent:** Multiple invocations return consistent results unless object state is modified.
- **NonNullity:** `x.equals(null)` must return `false`.
- **Hash Invariant:** If `x.equals(y)` is `true`, then `x.hashCode() == y.hashCode()` **MUST** hold true. If two objects have different hash codes, they cannot be equal.

---

## 4. Method Overloading vs Dynamic Method Dispatch

```java
class Printer {
    // 1. Compile-time Overloading: static binding
    void print(String s) { System.out.println("String: " + s); }
    void print(int n)    { System.out.println("Integer: " + n); }
}

class Shape {
    // 2. Runtime Overriding: dynamic method dispatch via vtable
    void draw() { System.out.println("Drawing generic shape"); }
}

class Circle extends Shape {
    @Override
    void draw() { System.out.println("Drawing circle"); }
}
```

---
*Maintained by [@rounithrathesh-coder](https://github.com/rounithrathesh-coder)*
