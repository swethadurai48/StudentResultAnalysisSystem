# Student Result Analysis System

A desktop application that manages student information, stores examination marks, calculates results, analyzes academic performance, generates reports, and visualizes data.

## Technologies Used
- **Java**: JDK 17
- **GUI**: Java Swing
- **Database**: MySQL, JDBC
- **Libraries**:
  - JFreeChart (Data Visualization)
  - Apache POI (Excel Export)
  - iText PDF (PDF Generation)
- **Build Tool**: Maven

## Installation Guide

1. **Database Setup**:
   - Install MySQL server.
   - Run the provided `database.sql` script to create the schema and initial data.
   - Default login: `admin` / `admin123`.

2. **Project Setup**:
   - Open the project folder in any IDE (IntelliJ IDEA, Eclipse, NetBeans).
   - Resolve Maven dependencies automatically via `pom.xml`.

3. **Database Configuration**:
   - Edit `src/main/java/com/sras/util/DBConnection.java` if your MySQL username/password differs from the default (`root`/`password`).

4. **Run**:
   - Execute `com.sras.Main` to start the application.

## Features
- Complete CRUD operations for Students, Subjects, and Marks.
- Result generation and calculation.
- JFreeChart integration for bar and pie charts.
- Export options for Excel and PDF.
