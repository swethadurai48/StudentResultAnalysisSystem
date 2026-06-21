package com.sras.view;

import com.sras.dao.MarkDAO;
import com.sras.model.Mark;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MarksPanel extends JPanel {

    private JTextField markIdField, studentIdField, subjectIdField, internalField, externalField, totalField;
    private JTable table;
    private DefaultTableModel tableModel;
    private MarkDAO markDAO;

    public MarksPanel() {
        markDAO = new MarkDAO();
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Marks Entry"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Mark ID (Auto):"), gbc);
        markIdField = new JTextField(15); markIdField.setEditable(false); gbc.gridx = 1; formPanel.add(markIdField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Student ID:"), gbc);
        studentIdField = new JTextField(15); gbc.gridx = 3; formPanel.add(studentIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Subject ID:"), gbc);
        subjectIdField = new JTextField(15); gbc.gridx = 1; formPanel.add(subjectIdField, gbc);

        gbc.gridx = 2; gbc.gridy = 1; formPanel.add(new JLabel("Internal Marks:"), gbc);
        internalField = new JTextField(15); gbc.gridx = 3; formPanel.add(internalField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("External Marks:"), gbc);
        externalField = new JTextField(15); gbc.gridx = 1; formPanel.add(externalField, gbc);

        gbc.gridx = 2; gbc.gridy = 2; formPanel.add(new JLabel("Total Marks:"), gbc);
        totalField = new JTextField(15); totalField.setEditable(false); gbc.gridx = 3; formPanel.add(totalField, gbc);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnCalculate = new JButton("Calculate Total");
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");
        btnPanel.add(btnCalculate); btnPanel.add(btnAdd); btnPanel.add(btnUpdate); btnPanel.add(btnDelete); btnPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Table Panel
        String[] columns = {"Mark ID", "Student ID", "Subject ID", "Internal", "External", "Total"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTableData();

        // Button Actions
        btnCalculate.addActionListener(e -> {
            try {
                double in = Double.parseDouble(internalField.getText());
                double ex = Double.parseDouble(externalField.getText());
                totalField.setText(String.valueOf(in + ex));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Marks Input");
            }
        });

        btnAdd.addActionListener(e -> {
            Mark m = getMarkFromForm(false);
            if (m != null) {
                if (markDAO.addMark(m)) {
                    JOptionPane.showMessageDialog(this, "Mark Added");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Add Mark");
                }
            }
        });

        btnUpdate.addActionListener(e -> {
            Mark m = getMarkFromForm(true);
            if (m != null) {
                if (markDAO.updateMark(m)) {
                    JOptionPane.showMessageDialog(this, "Mark Updated");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Mark");
                }
            }
        });

        btnDelete.addActionListener(e -> {
            String idStr = markIdField.getText();
            if (!idStr.isEmpty()) {
                if (markDAO.deleteMark(Integer.parseInt(idStr))) {
                    JOptionPane.showMessageDialog(this, "Mark Deleted");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Delete Mark");
                }
            }
        });

        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                markIdField.setText(tableModel.getValueAt(row, 0).toString());
                studentIdField.setText(tableModel.getValueAt(row, 1).toString());
                subjectIdField.setText(tableModel.getValueAt(row, 2).toString());
                internalField.setText(tableModel.getValueAt(row, 3).toString());
                externalField.setText(tableModel.getValueAt(row, 4).toString());
                totalField.setText(tableModel.getValueAt(row, 5).toString());
            }
        });
    }

    private Mark getMarkFromForm(boolean isUpdate) {
        try {
            int markId = 0;
            if (isUpdate && !markIdField.getText().isEmpty()) {
                markId = Integer.parseInt(markIdField.getText());
            }
            String stuId = studentIdField.getText();
            String subId = subjectIdField.getText();
            double in = Double.parseDouble(internalField.getText());
            double ex = Double.parseDouble(externalField.getText());
            double total = in + ex;
            return new Mark(markId, stuId, subId, in, ex, total);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input Data");
            return null;
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Mark> marks = markDAO.getAllMarks();
        for (Mark m : marks) {
            tableModel.addRow(new Object[]{
                    m.getMarkId(), m.getStudentId(), m.getSubjectId(), 
                    m.getInternalMarks(), m.getExternalMarks(), m.getTotalMarks()
            });
        }
    }

    private void clearForm() {
        markIdField.setText("");
        studentIdField.setText("");
        subjectIdField.setText("");
        internalField.setText("");
        externalField.setText("");
        totalField.setText("");
    }
}
