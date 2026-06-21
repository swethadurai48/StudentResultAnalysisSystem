package com.sras.view;

import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainContentPanel;

    public MainApplication() {
        setTitle("Student Result Analysis System - Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Side Navigation Menu
        JPanel sideNav = new JPanel();
        sideNav.setLayout(new GridLayout(7, 1, 10, 10));
        sideNav.setBackground(new Color(45, 52, 54));
        sideNav.setPreferredSize(new Dimension(200, getHeight()));

        JButton btnDashboard = createNavButton("Dashboard");
        JButton btnStudents = createNavButton("Manage Students");
        JButton btnSubjects = createNavButton("Manage Subjects");
        JButton btnMarks = createNavButton("Manage Marks");
        JButton btnAnalysis = createNavButton("Result Analysis");
        JButton btnReports = createNavButton("Reports");
        JButton btnLogout = createNavButton("Logout");

        sideNav.add(btnDashboard);
        sideNav.add(btnStudents);
        sideNav.add(btnSubjects);
        sideNav.add(btnMarks);
        sideNav.add(btnAnalysis);
        sideNav.add(btnReports);
        sideNav.add(btnLogout);

        add(sideNav, BorderLayout.WEST);

        // Main Content Area (CardLayout)
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        mainContentPanel.add(createDashboardOverview(), "Dashboard");
        mainContentPanel.add(new StudentPanel(), "Manage Students");
        mainContentPanel.add(new SubjectPanel(), "Manage Subjects");
        mainContentPanel.add(new MarksPanel(), "Manage Marks");
        mainContentPanel.add(new AnalysisPanel(), "Result Analysis");
        mainContentPanel.add(new ReportPanel(), "Reports");

        add(mainContentPanel, BorderLayout.CENTER);

        // Button actions
        btnDashboard.addActionListener(e -> cardLayout.show(mainContentPanel, "Dashboard"));
        btnStudents.addActionListener(e -> cardLayout.show(mainContentPanel, "Manage Students"));
        btnSubjects.addActionListener(e -> cardLayout.show(mainContentPanel, "Manage Subjects"));
        btnMarks.addActionListener(e -> cardLayout.show(mainContentPanel, "Manage Marks"));
        btnAnalysis.addActionListener(e -> cardLayout.show(mainContentPanel, "Result Analysis"));
        btnReports.addActionListener(e -> cardLayout.show(mainContentPanel, "Reports"));
        
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(45, 52, 54));
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }

    private JPanel createDashboardOverview() {
        JPanel p = new JPanel(new BorderLayout());
        JLabel l = new JLabel("Welcome to Student Result Analysis System", SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 28));
        p.add(l, BorderLayout.CENTER);
        return p;
    }
}
