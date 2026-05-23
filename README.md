# Homework #2 — JDBC + H2

A Java application that works with an H2 database through JDBC: creates the
schema, populates it with data, and runs SELECT queries loaded from `.sql`
files.

## Requirements

- Java 21+
- Gradle 8+
- H2 Database 2.2.224 (pulled in automatically)

## Project layout

```
.
├── build.gradle
├── settings.gradle
├── sql/                            — SQL scripts (schema, data, queries)
└── src/main/java/com/homework/
    ├── Database.java               — Singleton wrapping a JDBC Connection
    ├── DatabaseInitService.java    — main: creates tables
    ├── DatabasePopulateService.java — main: inserts data
    ├── DatabaseQueryService.java   — SELECT query methods + main
    ├── util/SqlFileLoader.java     — reads .sql files
    └── model/                      — record models for query results
```

Data is stored in `./data/homework.mv.db` (H2 file mode).

## Running

```bash
gradle initDb       # create tables
gradle populateDb   # insert sample data
gradle queryDb      # run every SELECT and print the result
```

Alternatively, run the `main()` method of the corresponding class from IntelliJ.

## What is used

- **Singleton** — `Database`, via the initialization-on-demand holder idiom
- **PreparedStatement** — for every query, including the parameterized
  `findProjectsByClient(String name)`
- **try-with-resources** — for `PreparedStatement` and `ResultSet`
- **Java records** — for query result models
