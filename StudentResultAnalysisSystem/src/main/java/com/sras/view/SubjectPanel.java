package com.sras.view;

import com.sras.dao.SubjectDAO;
import com.sras.model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SubjectPanel extends JPanel {

    private JTextField idField, nameField, codeField, semField;
    private JTable table;
    private DefaultTableModel tableModel;
    private SubjectDAO subjectDAO;

    public SubjectPanel() {
        subjectDAO = new SubjectDAO();
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Subject Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Subject ID:"), gbc);
        idField = new JTextField(15); gbc.gridx = 1; formPanel.add(idField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Subject Name:"), gbc);
        nameField = new JTextField(15); gbc.gridx = 3; formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Subject Code:"), gbc);
        codeField = new JTextField(15); gbc.gridx = 1; formPanel.add(codeField, gbc);

        gbc.gridx = 2; gbc.gridy = 1; formPanel.add(new JLabel("Semester:"), gbc);
        semField = new JTextField(15); gbc.gridx = 3; formPanel.add(semField, gbc);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");
        btnPanel.add(btnAdd); btnPanel.add(btnUpdate); btnPanel.add(btnDelete); btnPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4;
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Table Panel
        String[] columns = {"Subject ID", "Name", "Code", "Semester"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTableData();

        // Button Actions
        btnAdd.addActionListener(e -> {
            Subject s = getSubjectFromForm();
            if (s != null) {
                if (subjectDAO.addSubject(s)) {
                    JOptionPane.showMessageDialog(this, "Subject Added");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Add Subject");
                }
            }
        });

        btnUpdate.addActionListener(e -> {
            Subject s = getSubjectFromForm();
            if (s != null) {
                if (subjectDAO.updateSubject(s)) {
                    JOptionPane.showMessageDialog(this, "Subject Updated");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Subject");
                }
            }
        });

        btnDelete.addActionListener(e -> {
            String id = idField.getText();
            if (!id.isEmpty()) {
                if (subjectDAO.deleteSubject(id)) {
                    JOptionPane.showMessageDialog(this, "Subject Deleted");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Delete Subject");
                }
            }
        });

        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                codeField.setText(tableModel.getValueAt(row, 2).toString());
                semField.setText(tableModel.getValueAt(row, 3).toString());
            }
        });
    }

    private Subject getSubjectFromForm() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String code = codeField.getText();
            int sem = Integer.parseInt(semField.getText());
            return new Subject(id, name, code, sem);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input Data");
            return null;
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Subject> subjects = subjectDAO.getAllSubjects();
        for (Subject s : subjects) {
            tableModel.addRow(new Object[]{
                    s.getSubjectId(), s.getSubjectName(), s.getSubjectCode(), s.getSemester()
            });
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        codeField.setText("");
        semField.setText("");
    }
}
