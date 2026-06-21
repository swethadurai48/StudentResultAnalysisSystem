package com.sras.dao;

import com.sras.model.Subject;
import com.sras.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    
    public boolean addSubject(Subject s) {
        String query = "INSERT INTO subjects(subject_id, subject_name, subject_code, semester) VALUES(?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, s.getSubjectId());
            ps.setString(2, s.getSubjectName());
            ps.setString(3, s.getSubjectCode());
            ps.setInt(4, s.getSemester());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSubject(Subject s) {
        String query = "UPDATE subjects SET subject_name=?, subject_code=?, semester=? WHERE subject_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, s.getSubjectName());
            ps.setString(2, s.getSubjectCode());
            ps.setInt(3, s.getSemester());
            ps.setString(4, s.getSubjectId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSubject(String subjectId) {
        String query = "DELETE FROM subjects WHERE subject_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, subjectId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        String query = "SELECT * FROM subjects";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getString("subject_id"));
                s.setSubjectName(rs.getString("subject_name"));
                s.setSubjectCode(rs.getString("subject_code"));
                s.setSemester(rs.getInt("semester"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
