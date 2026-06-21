package com.sras.view;

import com.sras.util.ChartGenerator;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;

public class AnalysisPanel extends JPanel {

    public AnalysisPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Result Analysis Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        JPanel chartsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        chartsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ChartPanel barChartPanel = new ChartPanel(ChartGenerator.createSubjectPerformanceBarChart());
        ChartPanel pieChartPanel = new ChartPanel(ChartGenerator.createGradeDistributionPieChart());

        chartsPanel.add(barChartPanel);
        chartsPanel.add(pieChartPanel);

        add(chartsPanel, BorderLayout.CENTER);
        
        JButton refreshBtn = new JButton("Refresh Charts");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(refreshBtn);
        add(bottomPanel, BorderLayout.SOUTH);
        
        refreshBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Charts Refreshed (Simulation)");
        });
    }
}
