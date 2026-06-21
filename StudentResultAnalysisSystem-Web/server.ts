import express from 'express';
import cors from 'cors';
import mysql from 'mysql2/promise';
import path from 'path';

const app = express();
const PORT = 3001;

app.use(cors());
app.use(express.json());

// Create database connection pool
const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: 'root123',
  database: 'sras_db',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

// Helper to handle queries safely
const queryDB = async (sql: string, params: any[] = []) => {
  try {
    const [rows] = await pool.query(sql, params);
    return rows;
  } catch (error) {
    console.error('Database Error:', error);
    throw error;
  }
};

// Authentication
app.post('/api/auth/login', async (req, res) => {
  const { username, password } = req.body;
  try {
    const users: any = await queryDB('SELECT * FROM admin WHERE username=? AND password=?', [username, password]);
    if (users.length > 0) {
      res.json({ success: true, message: 'Login successful' });
    } else {
      res.status(401).json({ success: false, message: 'Invalid credentials' });
    }
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

// Students
app.get('/api/students', async (req, res) => {
  try {
    const students = await queryDB('SELECT * FROM students');
    res.json(students);
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.post('/api/students', async (req, res) => {
  const { student_id, name, gender, dob, department, semester, email, phone } = req.body;
  try {
    await queryDB('INSERT INTO students (student_id, name, gender, dob, department, semester, email, phone) VALUES (?,?,?,?,?,?,?,?)',
      [student_id, name, gender, dob, department, semester, email, phone]);
    res.json({ success: true, message: 'Student added' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.put('/api/students/:id', async (req, res) => {
  const { id } = req.params;
  const { name, gender, dob, department, semester, email, phone } = req.body;
  try {
    // dob from UI is likely YYYY-MM-DD
    const safeDob = dob ? dob.substring(0, 10) : null;
    await queryDB('UPDATE students SET name=?, gender=?, dob=?, department=?, semester=?, email=?, phone=? WHERE student_id=?',
      [name, gender, safeDob, department, semester, email, phone, id]);
    res.json({ success: true, message: 'Student updated' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.delete('/api/students/:id', async (req, res) => {
  try {
    await queryDB('DELETE FROM students WHERE student_id=?', [req.params.id]);
    res.json({ success: true, message: 'Student deleted' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

// Subjects
app.get('/api/subjects', async (req, res) => {
  try {
    const subjects = await queryDB('SELECT * FROM subjects');
    res.json(subjects);
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.post('/api/subjects', async (req, res) => {
  const { subject_id, subject_name, subject_code, semester } = req.body;
  try {
    await queryDB('INSERT INTO subjects (subject_id, subject_name, subject_code, semester) VALUES (?,?,?,?)',
      [subject_id, subject_name, subject_code, semester]);
    res.json({ success: true, message: 'Subject added' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.put('/api/subjects/:id', async (req, res) => {
  const { id } = req.params;
  const { subject_name, subject_code, semester } = req.body;
  try {
    await queryDB('UPDATE subjects SET subject_name=?, subject_code=?, semester=? WHERE subject_id=?',
      [subject_name, subject_code, semester, id]);
    res.json({ success: true, message: 'Subject updated' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.delete('/api/subjects/:id', async (req, res) => {
  try {
    await queryDB('DELETE FROM subjects WHERE subject_id=?', [req.params.id]);
    res.json({ success: true, message: 'Subject deleted' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

// Marks
app.get('/api/marks', async (req, res) => {
  try {
    const marks = await queryDB('SELECT * FROM marks');
    res.json(marks);
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.post('/api/marks', async (req, res) => {
  const { student_id, subject_id, internal_marks, external_marks } = req.body;
  const total_marks = Number(internal_marks) + Number(external_marks);
  try {
    await queryDB('INSERT INTO marks (student_id, subject_id, internal_marks, external_marks, total_marks) VALUES (?,?,?,?,?)',
      [student_id, subject_id, internal_marks, external_marks, total_marks]);
    res.json({ success: true, message: 'Mark added' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.put('/api/marks/:id', async (req, res) => {
  const { id } = req.params;
  const { internal_marks, external_marks } = req.body;
  const total_marks = Number(internal_marks) + Number(external_marks);
  try {
    await queryDB('UPDATE marks SET internal_marks=?, external_marks=?, total_marks=? WHERE mark_id=?',
      [internal_marks, external_marks, total_marks, id]);
    res.json({ success: true, message: 'Mark updated' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.delete('/api/marks/:id', async (req, res) => {
  try {
    await queryDB('DELETE FROM marks WHERE mark_id=?', [req.params.id]);
    res.json({ success: true, message: 'Mark deleted' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

// Analysis Endpoints
app.get('/api/analysis', async (req, res) => {
  try {
    const studentsCount: any = await queryDB('SELECT COUNT(*) as count FROM students');
    const subjectsCount: any = await queryDB('SELECT COUNT(*) as count FROM subjects');
    const marksData: any = await queryDB('SELECT subject_id, AVG(total_marks) as avgMarks FROM marks GROUP BY subject_id');
    const gradeCounts: Record<string, number> = { 'A+': 0, 'A': 0, 'B': 0, 'C': 0, 'D': 0, 'F': 0 };
    
    const allMarks: any = await queryDB('SELECT total_marks FROM marks');
    let passCount = 0;
    
    allMarks.forEach((m: any) => {
      const percentage = m.total_marks; // assuming out of 100 max
      if (percentage >= 50) passCount++;
      if (percentage >= 90) gradeCounts['A+']++;
      else if (percentage >= 80) gradeCounts['A']++;
      else if (percentage >= 70) gradeCounts['B']++;
      else if (percentage >= 60) gradeCounts['C']++;
      else if (percentage >= 50) gradeCounts['D']++;
      else gradeCounts['F']++;
    });

    const totalMarks = allMarks.length;
    const passPercentage = totalMarks > 0 ? (passCount / totalMarks) * 100 : 0;

    res.json({
      totalStudents: studentsCount[0].count,
      totalSubjects: subjectsCount[0].count,
      passPercentage: passPercentage.toFixed(2),
      subjectPerformance: marksData,
      gradeDistribution: Object.keys(gradeCounts).map(name => ({ name, value: gradeCounts[name] }))
    });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
});

app.use(express.static(path.join(process.cwd(), 'dist')));
app.get('*', (req, res) => {
  res.sendFile(path.join(process.cwd(), 'dist', 'index.html'));
});

app.listen(PORT, () => {
  console.log(`Express server running on http://localhost:${PORT}`);
});
