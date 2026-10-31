# ☕ Java Fundamentals & JVM Architecture Guide

A comprehensive architectural reference covering the Java execution model, JVM memory regions, primitive data types, type conversions, and operator precedence.

---

## 1. The Java Execution Architecture (Write Once, Run Anywhere)

Java source code (`.java`) is compiled by `javac` into platform-independent bytecode (`.class`). The **Java Virtual Machine (JVM)** interprets and Just-In-Time (JIT) compiles this bytecode into native machine instructions at runtime.

```
+--------------------+
|  Source Code       |  User writes Hello.java
+--------------------+
          |
          v [javac compiler]
+--------------------+
|  Java Bytecode     |  Portable bytecode (Hello.class)
+--------------------+
          |
          v [Class Loader Subsystem]
+-----------------------------------------------------------+
|               JVM Runtime Data Areas                      |
|  +-------------------+  +-------------------+             |
|  | Method Area       |  | Heap Memory       |             |
|  | (Classes/Static)  |  | (All Objects)     |             |
|  +-------------------+  +-------------------+             |
|  +-------------------+  +-------------------+  +--------+ |
|  | JVM Stacks        |  | PC Registers      |  | Native | |
|  | (Frames/Locals)   |  | (Next Bytecode)   |  | Stacks | |
|  +-------------------+  +-------------------+  +--------+ |
+-----------------------------------------------------------+
          |
          v [Execution Engine: Interpreter + JIT Compiler + GC]
+--------------------+
| Native Machine Code|  Direct hardware execution on OS / CPU
+--------------------+
```

---

## 2. JVM Memory Organization

| Memory Region | Scope | Contents & Lifecycle |
| :--- | :--- | :--- |
| **Heap Memory** | JVM-wide (Shared) | All instantiated objects, array instances, and strings. Managed by the Garbage Collector (GC). |
| **Method Area (Metaspace)** | JVM-wide (Shared) | Class metadata, runtime constant pool, static variables, and compiled bytecode methods. |
| **JVM Stack** | Per-Thread (Isolated) | Stores stack frames for method invocations. Contains local primitive variables and object references. Destroyed when the method exits. |
| **Program Counter (PC)** | Per-Thread (Isolated) | Holds the memory address of the current JVM bytecode instruction being executed. |
| **Native Method Stack** | Per-Thread (Isolated) | Holds C/C++ native method call frames invoked through the Java Native Interface (JNI). |

---

## 3. Java Primitive Data Types & Memory Sizes

Java strictly defines primitive sizes regardless of the host CPU architecture (32-bit or 64-bit):

| Data Type | Bit Width | Byte Size | Value Range | Default Value | Wrapper Class |
| :--- | :---: | :---: | :--- | :---: | :--- |
| `byte` | 8 bits | 1 byte | $-128$ to $+127$ | `0` | `java.lang.Byte` |
| `short` | 16 bits | 2 bytes | $-32,768$ to $+32,767$ | `0` | `java.lang.Short` |
| `int` | 32 bits | 4 bytes | $-2^{31}$ to $+2^{31}-1$ | `0` | `java.lang.Integer` |
| `long` | 64 bits | 8 bytes | $-2^{63}$ to $+2^{63}-1$ | `0L` | `java.lang.Long` |
| `float` | 32 bits | 4 bytes | IEEE 754 floating-point | `0.0f` | `java.lang.Float` |
| `double` | 64 bits | 8 bytes | IEEE 754 double-precision | `0.0d` | `java.lang.Double` |
| `char` | 16 bits | 2 bytes | `\u0000` to `\uffff` (0 to 65,535) | `\u0000` | `java.lang.Character` |
| `boolean` | JVM-dependent | ~1 byte | `true` or `false` | `false` | `java.lang.Boolean` |

---

## 4. Type Casting: Widening vs Narrowing

1. **Widening Conversion (Implicit / Safe):**
   - Automatically performed when converting smaller types to larger types without precision loss.
   - Hierarchy: `byte` $\to$ `short` $\to$ `int` $\to$ `long` $\to$ `float` $\to$ `double`.
   ```java
   int count = 42;
   double preciseCount = count; // Automatic widening
   ```

2. **Narrowing Conversion (Explicit / Unsafe):**
   - Requires explicit casting because data truncation or integer overflow can occur.
   ```java
   double pi = 3.14159;
   int approx = (int) pi; // Explicit narrowing: truncates to 3
   ```

---

## 5. Bitwise Operators & Bit Manipulation

Bitwise operators manipulate individual binary bits of integer data types:

| Operator | Symbol | Example (`a = 5 [0101]`, `b = 3 [0011]`) | Result |
| :--- | :---: | :--- | :---: |
| **Bitwise AND** | `&` | `5 & 3` $\to$ `0001` | `1` |
| **Bitwise OR** | `\|` | `5 \| 3` $\to$ `0111` | `7` |
| **Bitwise XOR** | `^` | `5 ^ 3` $\to$ `0110` | `6` |
| **Bitwise NOT** | `~` | `~5` $\to$ `1010` (Two's complement) | `-6` |
| **Left Shift** | `<<` | `5 << 1` $\to$ `1010` ($5 \times 2^1$) | `10` |
| **Right Shift (Arithmetic)** | `>>` | `-8 >> 1` (Preserves sign bit) | `-4` |
| **Unsigned Right Shift** | `>>>` | `-8 >>> 1` (Fills with zero) | `2147483644` |

---
*Maintained by [@rounithrathesh-coder](https://github.com/rounithrathesh-coder)*
