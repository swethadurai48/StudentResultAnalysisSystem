package com.sras.view;

import com.sras.dao.StudentDAO;
import com.sras.model.Student;
import com.sras.util.ReportGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ReportPanel extends JPanel {

    private StudentDAO studentDAO;

    public ReportPanel() {
        studentDAO = new StudentDAO();
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Reports Generation", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton btnClassReport = new JButton("Generate Class Report (Excel)");
        JButton btnStudentReport = new JButton("Generate Student Report (PDF)");
        JTextField studentIdField = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        centerPanel.add(btnClassReport, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        centerPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        centerPanel.add(studentIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        centerPanel.add(btnStudentReport, gbc);

        add(centerPanel, BorderLayout.CENTER);

        btnClassReport.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Class Report");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String path = fileToSave.getAbsolutePath();
                if (!path.endsWith(".xlsx")) path += ".xlsx";
                List<Student> students = studentDAO.getAllStudents();
                ReportGenerator.generateClassExcelReport(students, path);
                JOptionPane.showMessageDialog(this, "Excel Report Generated at: " + path);
            }
        });

        btnStudentReport.addActionListener(e -> {
            String stuId = studentIdField.getText();
            if (stuId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Student ID");
                return;
            }
            Student student = studentDAO.getStudentById(stuId);
            if (student == null) {
                JOptionPane.showMessageDialog(this, "Student Not Found");
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Student Report");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String path = fileToSave.getAbsolutePath();
                if (!path.endsWith(".pdf")) path += ".pdf";
                ReportGenerator.generateStudentPDFReport(student, path);
                JOptionPane.showMessageDialog(this, "PDF Report Generated at: " + path);
            }
        });
    }
}
