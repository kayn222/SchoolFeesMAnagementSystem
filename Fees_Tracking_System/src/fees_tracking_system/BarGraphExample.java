/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author USER
 */
public class BarGraphExample {
    public static void main(String[] args) {
        // create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(5, "Revenue", "January");
        dataset.setValue(10, "Revenue", "February");
        dataset.setValue(8, "Revenue", "March");
        dataset.setValue(12, "Revenue", "April");
        dataset.setValue(7, "Revenue", "May");
        
        // create a chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Revenue",      // chart title
                "Month",                // domain axis label
                "Revenue",              // range axis label
                dataset,                // data
                PlotOrientation.VERTICAL,// orientation
                true,                   // include legend
                true,                   // tooltips
                false                   // URLs
        );
        
        // customize the chart
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new GradientPaint(
                0f, 0f, Color.BLUE,
                0f, 0f, Color.CYAN
        ));
        
        // create a chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        
        // create a frame and add the chart panel to it
        JFrame frame = new JFrame("Bar Graph Example");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
