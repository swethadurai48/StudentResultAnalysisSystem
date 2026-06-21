CREATE DATABASE IF NOT EXISTS sras_db;
USE sras_db;

CREATE TABLE IF NOT EXISTS admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT IGNORE INTO admin (username, password) VALUES ('admin', 'admin123');

CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10),
    dob DATE,
    department VARCHAR(50),
    semester INT,
    email VARCHAR(100),
    phone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS subjects (
    subject_id VARCHAR(20) PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL,
    subject_code VARCHAR(20) NOT NULL,
    semester INT
);

CREATE TABLE IF NOT EXISTS marks (
    mark_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20),
    subject_id VARCHAR(20),
    internal_marks DOUBLE,
    external_marks DOUBLE,
    total_marks DOUBLE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE,
    UNIQUE KEY student_subject_unique (student_id, subject_id)
);

-- Sample Data (Optional)
INSERT IGNORE INTO students (student_id, name, gender, dob, department, semester, email, phone) VALUES 
('S001', 'Alice Johnson', 'Female', '2001-05-14', 'Computer Science', 5, 'alice@example.com', '1234567890'),
('S002', 'Bob Smith', 'Male', '2000-08-21', 'Computer Science', 5, 'bob@example.com', '0987654321');

INSERT IGNORE INTO subjects (subject_id, subject_name, subject_code, semester) VALUES 
('SUB01', 'Java Programming', 'CS301', 5),
('SUB02', 'Database Systems', 'CS302', 5);

INSERT IGNORE INTO marks (student_id, subject_id, internal_marks, external_marks, total_marks) VALUES 
('S001', 'SUB01', 25, 65, 90),
('S001', 'SUB02', 20, 55, 75),
('S002', 'SUB01', 18, 50, 68),
('S002', 'SUB02', 22, 60, 82);
