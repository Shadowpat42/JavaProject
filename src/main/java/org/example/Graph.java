package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Graph extends JFrame {
    private final int DEFAULT_PADDING = 15;

    public Graph(List<Transport> transports) {
        init(transports);
    }

    private void init(List<Transport> transports) {
        DefaultCategoryDataset dataset = createDataset(transports);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        pack();
        setTitle("Средняя скорость по типам транспорта");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Средняя скорость по типам транспорта",
                "Тип транспорта",
                "Средняя скорость (км/ч)",
                dataset
        );
    }

    private DefaultCategoryDataset createDataset(List<Transport> transports) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        transports.stream()
                .collect(java.util.stream.Collectors.groupingBy(Transport::getClass, java.util.stream.Collectors.averagingDouble(Transport::getSpeed)))
                .forEach((type, avgSpeed) -> dataset.addValue(avgSpeed, "Скорость", type.getSimpleName()));
        return dataset;
    }
}

