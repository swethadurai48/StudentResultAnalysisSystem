package com.sras.dao;

import com.sras.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {
    
    public boolean login(String username, String password) {
        boolean status = false;
        String query = "SELECT * FROM admin WHERE username=? AND password=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
