package com.sras.view;

import com.sras.dao.StudentDAO;
import com.sras.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class StudentPanel extends JPanel {

    private JTextField idField, nameField, deptField, semField, emailField, phoneField;
    private JComboBox<String> genderBox;
    private JSpinner dobSpinner;
    private JTable table;
    private DefaultTableModel tableModel;
    private StudentDAO studentDAO;

    public StudentPanel() {
        studentDAO = new StudentDAO();
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Student ID:"), gbc);
        idField = new JTextField(15); gbc.gridx = 1; formPanel.add(idField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15); gbc.gridx = 3; formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Gender:"), gbc);
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"}); gbc.gridx = 1; formPanel.add(genderBox, gbc);

        gbc.gridx = 2; gbc.gridy = 1; formPanel.add(new JLabel("DOB (yyyy-mm-dd):"), gbc);
        dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(dateEditor);
        gbc.gridx = 3; formPanel.add(dobSpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Department:"), gbc);
        deptField = new JTextField(15); gbc.gridx = 1; formPanel.add(deptField, gbc);

        gbc.gridx = 2; gbc.gridy = 2; formPanel.add(new JLabel("Semester:"), gbc);
        semField = new JTextField(15); gbc.gridx = 3; formPanel.add(semField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(15); gbc.gridx = 1; formPanel.add(emailField, gbc);

        gbc.gridx = 2; gbc.gridy = 3; formPanel.add(new JLabel("Phone:"), gbc);
        phoneField = new JTextField(15); gbc.gridx = 3; formPanel.add(phoneField, gbc);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");
        btnPanel.add(btnAdd); btnPanel.add(btnUpdate); btnPanel.add(btnDelete); btnPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Table Panel
        String[] columns = {"ID", "Name", "Gender", "DOB", "Dept", "Sem", "Email", "Phone"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTableData();

        // Button Actions
        btnAdd.addActionListener(e -> {
            Student s = getStudentFromForm();
            if (s != null) {
                if (studentDAO.addStudent(s)) {
                    JOptionPane.showMessageDialog(this, "Student Added");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Add Student");
                }
            }
        });

        btnUpdate.addActionListener(e -> {
            Student s = getStudentFromForm();
            if (s != null) {
                if (studentDAO.updateStudent(s)) {
                    JOptionPane.showMessageDialog(this, "Student Updated");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Student");
                }
            }
        });

        btnDelete.addActionListener(e -> {
            String id = idField.getText();
            if (!id.isEmpty()) {
                if (studentDAO.deleteStudent(id)) {
                    JOptionPane.showMessageDialog(this, "Student Deleted");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Delete Student");
                }
            }
        });

        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                genderBox.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                deptField.setText(tableModel.getValueAt(row, 4).toString());
                semField.setText(tableModel.getValueAt(row, 5).toString());
                emailField.setText(tableModel.getValueAt(row, 6).toString());
                phoneField.setText(tableModel.getValueAt(row, 7).toString());
            }
        });
    }

    private Student getStudentFromForm() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String gender = genderBox.getSelectedItem().toString();
            java.util.Date dobUtil = (java.util.Date) dobSpinner.getValue();
            Date dob = new Date(dobUtil.getTime());
            String dept = deptField.getText();
            int sem = Integer.parseInt(semField.getText());
            String email = emailField.getText();
            String phone = phoneField.getText();
            return new Student(id, name, gender, dob, dept, sem, email, phone);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input Data");
            return null;
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.getAllStudents();
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getStudentId(), s.getName(), s.getGender(), s.getDob(),
                    s.getDepartment(), s.getSemester(), s.getEmail(), s.getPhone()
            });
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        deptField.setText("");
        semField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }
}
