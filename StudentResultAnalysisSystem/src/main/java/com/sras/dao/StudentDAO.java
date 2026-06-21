package com.sras.dao;

import com.sras.model.Student;
import com.sras.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    
    public boolean addStudent(Student s) {
        String query = "INSERT INTO students(student_id, name, gender, dob, department, semester, email, phone) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, s.getStudentId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getGender());
            ps.setDate(4, s.getDob());
            ps.setString(5, s.getDepartment());
            ps.setInt(6, s.getSemester());
            ps.setString(7, s.getEmail());
            ps.setString(8, s.getPhone());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudent(Student s) {
        String query = "UPDATE students SET name=?, gender=?, dob=?, department=?, semester=?, email=?, phone=? WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getGender());
            ps.setDate(3, s.getDob());
            ps.setString(4, s.getDepartment());
            ps.setInt(5, s.getSemester());
            ps.setString(6, s.getEmail());
            ps.setString(7, s.getPhone());
            ps.setString(8, s.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(String studentId) {
        String query = "DELETE FROM students WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getString("student_id"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setDob(rs.getDate("dob"));
                s.setDepartment(rs.getString("department"));
                s.setSemester(rs.getInt("semester"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Student getStudentById(String studentId) {
        Student s = null;
        String query = "SELECT * FROM students WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setStudentId(rs.getString("student_id"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setDob(rs.getDate("dob"));
                s.setDepartment(rs.getString("department"));
                s.setSemester(rs.getInt("semester"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
