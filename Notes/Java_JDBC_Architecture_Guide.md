# Java Database Connectivity (JDBC) Architecture

## Core Interfaces
- `DriverManager`: Manages list of database drivers.
- `Connection`: Session with the relational database; manages transaction commit boundary.
- `Statement`: General SQL statement execution.
- `PreparedStatement`: Precompiled SQL statement with parameterized bindings (`?`). Prevents SQL injection.
- `CallableStatement`: Executes database stored procedures and functions.
- `ResultSet`: Cursor over query results.


<!-- Summer Authored: 2026-06-16 11:55:27 -->

<!-- Topic Section Update: 2026-07-11 15:33:40 -->

<!-- Topic Section Update: 2026-07-16 08:09:07 -->

<!-- Topic Section Update: 2026-07-31 16:30:47 -->

<!-- Topic Section Update: 2026-08-20 09:08:06 -->
