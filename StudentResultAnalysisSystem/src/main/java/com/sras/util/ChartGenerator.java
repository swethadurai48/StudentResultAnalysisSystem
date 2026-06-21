package com.sras.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ChartGenerator {

    public static JFreeChart createSubjectPerformanceBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Placeholder sample data
        dataset.addValue(85, "Marks", "Java Programming");
        dataset.addValue(75, "Marks", "Database Systems");
        dataset.addValue(90, "Marks", "Operating Systems");
        dataset.addValue(65, "Marks", "Computer Networks");
        
        return ChartFactory.createBarChart(
                "Subject Performance",
                "Subject",
                "Average Marks",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
    }

    public static JFreeChart createGradeDistributionPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // Placeholder sample data
        dataset.setValue("A+", 10);
        dataset.setValue("A", 25);
        dataset.setValue("B", 40);
        dataset.setValue("C", 15);
        dataset.setValue("F", 10);

        return ChartFactory.createPieChart(
                "Grade Distribution",
                dataset,
                true, true, false
        );
    }
}
