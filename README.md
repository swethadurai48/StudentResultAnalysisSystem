# Student Result Analysis System

A comprehensive, full-stack Academic Results Management System designed to handle student records, subjects, and examination marks, complete with dynamic dashboards and reporting features.

## Screenshots
![Dashboard](./assets/dashboard.png)
![Students Management](./assets/students.png)
![Subjects Directory](./assets/subjects.png)
![Marks Entry](./assets/marks.png)

![Application Demo (Animated)](./assets/app_demo.webp)

## Project Architecture

This monorepo contains two complete interfaces for the system:

### 1. Web Application (`StudentResultAnalysisSystem-Web`)
A modern, responsive web portal built with a premium Glassmorphism design system.
- **Frontend**: React 19, Vite, Tailwind CSS v4, Framer Motion (for smooth micro-animations), and Recharts (for Dashboard data visualization).
- **Backend API**: Node.js and Express. Provides a secure REST API connecting seamlessly to the MySQL database.

### 2. Desktop Application (`StudentResultAnalysisSystem`)
A robust, Object-Oriented Java Desktop Application.
- **Frontend**: Java Swing (with card layouts and JTables).
- **Backend**: Pure Java with JDBC.
- **Reporting**: Apache POI (Excel Exports) and iTextPDF (PDF Generation).
- **Data Visualization**: JFreeChart.

## Database System

Both applications are powered by a central **MySQL Database** (`sras_db`).
The database schema (`database.sql`) handles all entity relationships (Students, Subjects, Marks, Admin Authentication) with strict integrity constraints.

## Getting Started

### Prerequisites
- MySQL Server (XAMPP or MySQL Workbench) running on port `3306`.
- Node.js & npm (for the web application).
- JDK 17+ and Maven (for the Java application).

### 1. Database Initialization
Run the `StudentResultAnalysisSystem/database.sql` script in your MySQL instance to create the database, tables, and default mock data.

### 2. Running the Web Application
```bash
cd StudentResultAnalysisSystem-Web
npm install
npm run build
npm start
```
*Open `http://localhost:3001` in your browser. Default login: `admin` / `admin123`.*

### 3. Running the Java Desktop Application
Import the `StudentResultAnalysisSystem` folder as a Maven project in your preferred Java IDE (IntelliJ, Eclipse, NetBeans) and run `com.sras.Main`.

## Documentation
Additional system design diagrams (ER, Class, Use Case, and Sequence diagrams) are available in the `StudentResultAnalysisSystem/docs` directory.
