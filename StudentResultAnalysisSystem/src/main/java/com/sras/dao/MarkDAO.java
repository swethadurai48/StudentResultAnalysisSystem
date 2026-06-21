package com.sras.dao;

import com.sras.model.Mark;
import com.sras.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarkDAO {

    public boolean addMark(Mark m) {
        String query = "INSERT INTO marks(student_id, subject_id, internal_marks, external_marks, total_marks) VALUES(?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, m.getStudentId());
            ps.setString(2, m.getSubjectId());
            ps.setDouble(3, m.getInternalMarks());
            ps.setDouble(4, m.getExternalMarks());
            ps.setDouble(5, m.getTotalMarks());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMark(Mark m) {
        String query = "UPDATE marks SET internal_marks=?, external_marks=?, total_marks=? WHERE mark_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, m.getInternalMarks());
            ps.setDouble(2, m.getExternalMarks());
            ps.setDouble(3, m.getTotalMarks());
            ps.setInt(4, m.getMarkId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMark(int markId) {
        String query = "DELETE FROM marks WHERE mark_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, markId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Mark> getAllMarks() {
        List<Mark> list = new ArrayList<>();
        String query = "SELECT * FROM marks";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mark m = new Mark();
                m.setMarkId(rs.getInt("mark_id"));
                m.setStudentId(rs.getString("student_id"));
                m.setSubjectId(rs.getString("subject_id"));
                m.setInternalMarks(rs.getDouble("internal_marks"));
                m.setExternalMarks(rs.getDouble("external_marks"));
                m.setTotalMarks(rs.getDouble("total_marks"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Mark> getMarksByStudentId(String studentId) {
        List<Mark> list = new ArrayList<>();
        String query = "SELECT * FROM marks WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();
                m.setMarkId(rs.getInt("mark_id"));
                m.setStudentId(rs.getString("student_id"));
                m.setSubjectId(rs.getString("subject_id"));
                m.setInternalMarks(rs.getDouble("internal_marks"));
                m.setExternalMarks(rs.getDouble("external_marks"));
                m.setTotalMarks(rs.getDouble("total_marks"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
