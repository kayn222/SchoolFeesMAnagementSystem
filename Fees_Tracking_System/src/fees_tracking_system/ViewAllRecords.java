/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;




/**
 *
 * @author USER
 */
public class ViewAllRecords extends javax.swing.JFrame {

    /**
     * Creates new form ViewAllRecordsAdmin
     */
    
    DefaultTableModel model;
    
    
    public ViewAllRecords() {
        initComponents();
        Container c = getContentPane();
        c.setBackground(new Color(0,0,51));
        tbl_allRecord.setRowHeight(20);
        CurrentYear();
        setRecordsToTable();
        DRecordCounter1();
        DRecordCounter2();
        DRecordCounter3();
        DRecordCounter4();
        DRecordCounter5();
        DRecordCounter6();
        DRecordCounter7();
        DRecordCounter8();
        DRecordCounter9();
        DRecordCounter10();
        DRecordCounter11();
        DRecordCounter12();
        
        
    }
    
    
    public void CurrentYear() {    
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        String Ds = dtf.format(now);
        txt_dy.setText(Ds);
        
    }
    
    
    
    public void setRecordsToTable(){
        
        try {
                Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("select * from fees_details");
                ResultSet rs = pst.executeQuery();
                
             while (rs.next()){
                 
                 String receiptNo = rs.getString("receipt_no");
                 String idNo = rs.getString("roll_no");
                 String studentName = rs.getString("student_name");
                 String feeName = rs.getString("fee_name");
                 String feeName1 = rs.getString("fee_name1");
                 String feeName2 = rs.getString("fee_name2");
                 Double Tamount = rs.getDouble("total_amount");
                 String paymentMode = rs.getString("payment_mode");
                 String date = rs.getString("date");
                 String remarks = rs.getString("remark");
                  Double amount = rs.getDouble("amount");
                  Double amount1 = rs.getDouble("amount1");
                  Double amount2 = rs.getDouble("amount2");
                 
                 Object[] obj = {receiptNo,idNo,studentName,feeName,feeName1,feeName2,Tamount,paymentMode,date,remarks,amount,amount1,amount2};
                 model = (DefaultTableModel)tbl_allRecord.getModel();
                 model.addRow(obj);
                 
             }
                
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    

    
    
    
    public void DRecordCounter1(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-01")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
        
        
    }
    }
    drc1.setText(Integer.toString(count));
    trc1.setText(Double.toString(sum));
    }
    
     public void DRecordCounter2(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-02")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
        
        
    }
    }
    drc2.setText(Integer.toString(count));
    trc2.setText(Double.toString(sum));
    }
    
    public void DRecordCounter3(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-03")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc3.setText(Integer.toString(count));
    trc3.setText(Double.toString(sum));
    }
    
    public void DRecordCounter4(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-04")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc4.setText(Integer.toString(count));
    trc4.setText(Double.toString(sum));
    }
    
    public void DRecordCounter5(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-05")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc5.setText(Integer.toString(count));
    trc5.setText(Double.toString(sum));
    }
    
    public void DRecordCounter6(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-06")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc6.setText(Integer.toString(count));
    trc6.setText(Double.toString(sum)); 
    }
    
     public void DRecordCounter7(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-07")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc7.setText(Integer.toString(count));
    trc7.setText(Double.toString(sum));
    }
    
      public void DRecordCounter8(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-08")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc8.setText(Integer.toString(count));
    trc8.setText(Double.toString(sum));
    }
    
     public void DRecordCounter9(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-09")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc9.setText(Integer.toString(count));
    trc9.setText(Double.toString(sum)); 
    }
      
     public void DRecordCounter10(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-10")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
    }
    }
    drc10.setText(Integer.toString(count));
    trc10.setText(Double.toString(sum)); 
    }  
      
     public void DRecordCounter11(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-11")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
        
    }
    }
    drc11.setText(Integer.toString(count));
    trc11.setText(Double.toString(sum));
    }
     
     
     public void DRecordCounter12(){
        
    model = (DefaultTableModel) tbl_allRecord.getModel();
    int rowCount = model.getRowCount();
    int count = 0;
    double sum = 0;
    for (int i = 0; i < rowCount; i++) {
    String item = (String) model.getValueAt(i, 8); 
    if (item.contains(txt_dy.getText()+"-12")) {
        count++;
        sum += Double.parseDouble(tbl_allRecord.getValueAt(i, 6).toString());
        
    }
    }
    drc12.setText(Integer.toString(count));
    trc12.setText(Double.toString(sum));  
    } 
     
    public void UniqueRecordBarChart() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management","root","1234");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT fee_name, fee_name1, fee_name2 FROM fees_details");
            Map<String, Integer> valueCounts = new HashMap<String, Integer>();
            while (rs.next()) {
                String value1 = rs.getString("fee_name");
                String value2 = rs.getString("fee_name1");
                String value3 = rs.getString("fee_name2");
                incrementCount(valueCounts, value1);
                incrementCount(valueCounts, value2);
                incrementCount(valueCounts, value3);
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Integer> entry : valueCounts.entrySet()) {
                dataset.setValue(entry.getValue(), "Value Frequency", entry.getKey());
            }
            JFreeChart chart = ChartFactory.createBarChart("fee Frequency", "Fee Name", "Frequency", dataset, PlotOrientation.VERTICAL, false, true, false);
            ChartFrame frame = new ChartFrame("Value Frequency", chart);
            frame.setVisible(true);
            frame.setSize(500, 500);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void incrementCount(Map<String, Integer> counts, String value) {
       if (value != null && !value.isEmpty()) {
        int count = counts.getOrDefault(value, 0);
        counts.put(value, count + 1);
    }
    

}
               
public void FeeRevenueCounter() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management","root","1234");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT fee_name, fee_name1, fee_name2, AMOUNT, AMOUNT1, AMOUNT2 FROM fees_details");

        DataSet dataSet = new DataSet(3, 1);
        while (rs.next()) {
            double fee1 = rs.getDouble("AMOUNT");
            double fee2 = rs.getDouble("AMOUNT1");
            double fee3 = rs.getDouble("AMOUNT2");
            double sum = fee1 + fee2 + fee3;
            double[] input = new double[] { fee1, fee2, fee3 };
            double[] output = new double[] { sum };
            dataSet.addRow(new DataSetRow(input, output));
        }

        NeuralNetwork neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 3, 3, 1);
        neuralNetwork.learn(dataSet);
        
        // Once the network is trained, you can use it to predict the sum of fees for a given input
        double[] input = new double[] { 100, 200, 300 };
        neuralNetwork.setInput(input);
        neuralNetwork.calculate();
        double[] output = neuralNetwork.getOutput();
        System.out.println("Predicted sum: " + output[0]);
        
        // Create a bar chart
        DefaultCategoryDataset dataset = createDataset(output[0]);
        CategoryPlot plot = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(new JFreeChart(plot));
        JFrame frame = new JFrame("Fee Revenue");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

private static DefaultCategoryDataset createDataset(double predictedSum) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(predictedSum, "Sum", "Predicted Sum");
    return dataset;
}

private static CategoryPlot createChart(DefaultCategoryDataset dataset) {
    CategoryAxis categoryAxis = new CategoryAxis("");
    NumberAxis numberAxis = new NumberAxis("Sum");
    BarRenderer renderer = new BarRenderer();
    CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, numberAxis, renderer);
    return plot;
}

     


     
     
    public void BarGraphFrequency() {
        String lt1 = rc1.getText();
        String lt2 = rc2.getText();
        String lt3 = rc3.getText();
        String lt4 = rc4.getText();
        String lt5 = rc5.getText();
        String lt6 = rc6.getText();
        
        int iv1 = Integer.parseInt(lt1);
        int iv2 = Integer.parseInt(lt2);
        int iv3 = Integer.parseInt(lt3);
        int iv4 = Integer.parseInt(lt4);
        int iv5 = Integer.parseInt(lt5);
        int iv6 = Integer.parseInt(lt6);
        
        
        // create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(iv1, "Tuition Fee", "Tuition Fee");
        dataset.setValue(iv2, "Standard Uniform", "Standard Uniform");
        dataset.setValue(iv3, "P.E Uniform", "P.E Uniform");
        dataset.setValue(iv4, "Graduation Fee", "Graduation Fee");
        dataset.setValue(iv5, "Documents", "Documents");
        dataset.setValue(iv6, "Others", "Others");
        
        // create a chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Fee Frequency",      // chart title
                "Fee    ",                // domain axis label
                "Frequency",              // range axis label
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
               
               
            }
        });
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    

}
    
    public void BarGraphDateAmount() {
        String lt1 = trc1.getText();
        String lt2 = trc2.getText();
        String lt3 = trc3.getText();
        String lt4 = trc4.getText();
        String lt5 = trc5.getText();
        String lt6 = trc6.getText();
        String lt7 = trc7.getText();
        String lt8 = trc8.getText();
        String lt9 = trc9.getText();
        String lt10 = trc10.getText();
        String lt11 = trc11.getText();
        String lt12 = trc12.getText();
        
        double iv1 = Double.parseDouble(lt1);
        double iv2 = Double.parseDouble(lt2);
        double iv3 = Double.parseDouble(lt3);
        double iv4 = Double.parseDouble(lt4);
        double iv5 = Double.parseDouble(lt5);
        double iv6 = Double.parseDouble(lt6);
        double iv7 = Double.parseDouble(lt7);
        double iv8 = Double.parseDouble(lt8);
        double iv9 = Double.parseDouble(lt9);
        double iv10 = Double.parseDouble(lt10);
        double iv11 = Double.parseDouble(lt11);
        double iv12 = Double.parseDouble(lt12);
        
        
        // create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(iv1, "January", "January");
        dataset.setValue(iv2, "February", "February");
        dataset.setValue(iv3, "March", "March");
        dataset.setValue(iv4, "April", "April");
        dataset.setValue(iv5, "May", "May");
        dataset.setValue(iv6, "June", "June");
        dataset.setValue(iv7, "July", "July");
        dataset.setValue(iv8, "August", "August");
        dataset.setValue(iv9, "September", "September");
        dataset.setValue(iv10, "October", "October");
        dataset.setValue(iv11, "November", "November");
        dataset.setValue(iv12, "December", "December");
        
        // create a chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Revenue",      // chart title
                "Month    ",                // domain axis label
                "Amount",              // range axis label
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
        chartPanel.setPreferredSize(new Dimension(1000, 600));
        
        // create a frame and add the chart panel to it
        JFrame frame = new JFrame("Bar Graph Example");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
               
               
            }
        });
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    

}
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_allRecord = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rc1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rc2 = new javax.swing.JLabel();
        rc3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rc4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rc5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rc6 = new javax.swing.JLabel();
        BarGraphFrq_btn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        drc1 = new javax.swing.JLabel();
        trc1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        drc2 = new javax.swing.JLabel();
        trc2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        drc3 = new javax.swing.JLabel();
        trc3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        drc4 = new javax.swing.JLabel();
        trc4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        drc5 = new javax.swing.JLabel();
        trc5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        drc6 = new javax.swing.JLabel();
        trc6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        drc7 = new javax.swing.JLabel();
        trc7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        drc8 = new javax.swing.JLabel();
        trc8 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        drc9 = new javax.swing.JLabel();
        trc9 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        drc10 = new javax.swing.JLabel();
        trc10 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        trc11 = new javax.swing.JLabel();
        drc11 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        trc12 = new javax.swing.JLabel();
        drc12 = new javax.swing.JLabel();
        BarGraphDA_btn = new javax.swing.JButton();
        txt_dy = new javax.swing.JLabel();
        Fr_btn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        panelsideBar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        btnSearch1 = new javax.swing.JLabel();
        panelEdit = new javax.swing.JPanel();
        btnEdit = new javax.swing.JLabel();
        panelListFees = new javax.swing.JPanel();
        btnListFees = new javax.swing.JLabel();
        panelAllViewRecords = new javax.swing.JPanel();
        btnViewAllRecords = new javax.swing.JLabel();
        btnViewAllRecords1 = new javax.swing.JLabel();
        panelPrint = new javax.swing.JPanel();
        btnPrint = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_allRecord.setAutoCreateRowSorter(true);
        tbl_allRecord.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbl_allRecord.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt No.", "Roll No.", "Student Name", "Fee Name", "Fee Name1", "Fee Name2", "Total Amount", "Payment Mode", "Date", "Remark", "Amount", "Amount1", "Amount2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, true, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_allRecord);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 950, 650));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SYSTEM RECORDS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Tuition Fee:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, -1, -1));

        rc1.setForeground(new java.awt.Color(0, 0, 51));
        rc1.setText("jLabel4");
        jPanel1.add(rc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Standard Unidorm:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, -1, -1));

        rc2.setForeground(new java.awt.Color(0, 0, 51));
        rc2.setText("jLabel4");
        jPanel1.add(rc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 50, -1));

        rc3.setForeground(new java.awt.Color(0, 0, 51));
        rc3.setText("jLabel4");
        jPanel1.add(rc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 110, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("P.E Uniform:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Graduation Fee:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 140, -1, -1));

        rc4.setForeground(new java.awt.Color(0, 0, 51));
        rc4.setText("jLabel4");
        jPanel1.add(rc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, -1, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("Documents:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, -1, -1));

        rc5.setForeground(new java.awt.Color(0, 0, 51));
        rc5.setText("jLabel4");
        jPanel1.add(rc5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, -1, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setText("Others: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 200, -1, -1));

        rc6.setForeground(new java.awt.Color(0, 0, 51));
        rc6.setText("jLabel4");
        jPanel1.add(rc6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, -1, -1));

        BarGraphFrq_btn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        BarGraphFrq_btn.setText("Show Fee Freq.");
        BarGraphFrq_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BarGraphFrq_btnActionPerformed(evt);
            }
        });
        jPanel1.add(BarGraphFrq_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 130, 50));

        jLabel11.setForeground(new java.awt.Color(0, 0, 51));
        jLabel11.setText("January: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 680, -1, -1));

        jLabel12.setForeground(new java.awt.Color(0, 0, 51));
        jLabel12.setText("Total: ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 700, -1, -1));

        drc1.setForeground(new java.awt.Color(0, 0, 51));
        drc1.setText("jLabel4");
        jPanel1.add(drc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 680, -1, -1));

        trc1.setForeground(new java.awt.Color(0, 0, 51));
        trc1.setText("jLabel11");
        jPanel1.add(trc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 700, -1, -1));

        jLabel13.setForeground(new java.awt.Color(0, 0, 51));
        jLabel13.setText("February: ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 720, -1, -1));

        jLabel14.setForeground(new java.awt.Color(0, 0, 51));
        jLabel14.setText("Total: ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 740, -1, -1));

        drc2.setForeground(new java.awt.Color(0, 0, 51));
        drc2.setText("jLabel4");
        jPanel1.add(drc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 720, -1, -1));

        trc2.setForeground(new java.awt.Color(0, 0, 51));
        trc2.setText("jLabel11");
        jPanel1.add(trc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 740, -1, -1));

        jLabel15.setForeground(new java.awt.Color(0, 0, 51));
        jLabel15.setText("March: ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 370, -1, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 51));
        jLabel16.setText("Total: ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 390, -1, -1));

        drc3.setForeground(new java.awt.Color(0, 0, 51));
        drc3.setText("jLabel4");
        jPanel1.add(drc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 370, -1, -1));

        trc3.setForeground(new java.awt.Color(0, 0, 51));
        trc3.setText("jLabel11");
        jPanel1.add(trc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 390, -1, -1));

        jLabel17.setForeground(new java.awt.Color(0, 0, 51));
        jLabel17.setText("April");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 410, -1, -1));

        jLabel18.setForeground(new java.awt.Color(0, 0, 51));
        jLabel18.setText("Total: ");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 430, -1, -1));

        drc4.setForeground(new java.awt.Color(0, 0, 51));
        drc4.setText("jLabel4");
        jPanel1.add(drc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 410, -1, -1));

        trc4.setForeground(new java.awt.Color(0, 0, 51));
        trc4.setText("jLabel11");
        jPanel1.add(trc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 430, -1, -1));

        jLabel19.setForeground(new java.awt.Color(0, 0, 51));
        jLabel19.setText("May:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, -1, -1));

        jLabel20.setForeground(new java.awt.Color(0, 0, 51));
        jLabel20.setText("Total: ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 470, -1, -1));

        drc5.setForeground(new java.awt.Color(0, 0, 51));
        drc5.setText("jLabel4");
        jPanel1.add(drc5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, -1, -1));

        trc5.setForeground(new java.awt.Color(0, 0, 51));
        trc5.setText("jLabel11");
        jPanel1.add(trc5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 470, -1, -1));

        jLabel21.setForeground(new java.awt.Color(0, 0, 51));
        jLabel21.setText("June:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 490, -1, -1));

        jLabel22.setForeground(new java.awt.Color(0, 0, 51));
        jLabel22.setText("Total: ");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 510, -1, -1));

        drc6.setForeground(new java.awt.Color(0, 0, 51));
        drc6.setText("jLabel4");
        jPanel1.add(drc6, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 490, -1, -1));

        trc6.setForeground(new java.awt.Color(0, 0, 51));
        trc6.setText("jLabel11");
        jPanel1.add(trc6, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 510, -1, -1));

        jLabel23.setForeground(new java.awt.Color(0, 0, 51));
        jLabel23.setText("July:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 530, -1, -1));

        jLabel24.setForeground(new java.awt.Color(0, 0, 51));
        jLabel24.setText("Total: ");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 550, -1, -1));

        drc7.setForeground(new java.awt.Color(0, 0, 51));
        drc7.setText("jLabel4");
        jPanel1.add(drc7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 530, -1, -1));

        trc7.setForeground(new java.awt.Color(0, 0, 51));
        trc7.setText("jLabel11");
        jPanel1.add(trc7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 550, -1, -1));

        jLabel25.setForeground(new java.awt.Color(0, 0, 51));
        jLabel25.setText("August");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 570, -1, -1));

        jLabel26.setForeground(new java.awt.Color(0, 0, 51));
        jLabel26.setText("Total: ");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 590, -1, -1));

        drc8.setForeground(new java.awt.Color(0, 0, 51));
        drc8.setText("jLabel4");
        jPanel1.add(drc8, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 570, -1, -1));

        trc8.setForeground(new java.awt.Color(0, 0, 51));
        trc8.setText("jLabel11");
        jPanel1.add(trc8, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 590, -1, -1));

        jLabel27.setForeground(new java.awt.Color(0, 0, 51));
        jLabel27.setText("September");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 610, -1, -1));

        jLabel28.setForeground(new java.awt.Color(0, 0, 51));
        jLabel28.setText("Total: ");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 630, -1, -1));

        drc9.setForeground(new java.awt.Color(0, 0, 51));
        drc9.setText("jLabel4");
        jPanel1.add(drc9, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 610, -1, -1));

        trc9.setForeground(new java.awt.Color(0, 0, 51));
        trc9.setText("jLabel11");
        jPanel1.add(trc9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 630, -1, -1));

        jLabel29.setForeground(new java.awt.Color(0, 0, 51));
        jLabel29.setText("October");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 650, -1, -1));

        jLabel30.setForeground(new java.awt.Color(0, 0, 51));
        jLabel30.setText("Total: ");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 670, -1, -1));

        drc10.setForeground(new java.awt.Color(0, 0, 51));
        drc10.setText("jLabel4");
        jPanel1.add(drc10, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 650, -1, -1));

        trc10.setForeground(new java.awt.Color(0, 0, 51));
        trc10.setText("jLabel11");
        jPanel1.add(trc10, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 670, -1, -1));

        jLabel31.setForeground(new java.awt.Color(0, 0, 51));
        jLabel31.setText("November");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 690, -1, -1));

        jLabel32.setForeground(new java.awt.Color(0, 0, 51));
        jLabel32.setText("Total: ");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 710, -1, -1));

        trc11.setForeground(new java.awt.Color(0, 0, 51));
        trc11.setText("jLabel11");
        jPanel1.add(trc11, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 710, -1, -1));

        drc11.setForeground(new java.awt.Color(0, 0, 51));
        drc11.setText("jLabel4");
        jPanel1.add(drc11, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 690, -1, -1));

        jLabel33.setForeground(new java.awt.Color(0, 0, 51));
        jLabel33.setText("December");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 690, -1, -1));

        jLabel34.setForeground(new java.awt.Color(0, 0, 51));
        jLabel34.setText("Total: ");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 710, -1, -1));

        trc12.setForeground(new java.awt.Color(0, 0, 51));
        trc12.setText("jLabel11");
        jPanel1.add(trc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 710, -1, -1));

        drc12.setForeground(new java.awt.Color(0, 0, 51));
        drc12.setText("jLabel4");
        jPanel1.add(drc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 690, -1, -1));

        BarGraphDA_btn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        BarGraphDA_btn.setText("Monthly Revenue");
        BarGraphDA_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BarGraphDA_btnActionPerformed(evt);
            }
        });
        jPanel1.add(BarGraphDA_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 130, 50));

        txt_dy.setForeground(new java.awt.Color(0, 0, 51));
        txt_dy.setText("jLabel9");
        jPanel1.add(txt_dy, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, -1, -1));

        Fr_btn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        Fr_btn.setText("Fee Revenue");
        Fr_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Fr_btnActionPerformed(evt);
            }
        });
        jPanel1.add(Fr_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 130, 50));

        jButton1.setText("FEE REV MONTH");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 130, 50));

        jLabel9.setText("jLabel9");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, -1, -1));

        panelsideBar.setBackground(new java.awt.Color(0, 0, 102));
        panelsideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/WITILOGO.png"))); // NOI18N
        panelsideBar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 14, -1, -1));

        panelHome.setBackground(new java.awt.Color(0, 0, 102));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/home.png"))); // NOI18N
        btnHome.setText(" Home");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        panelHome.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 60));

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 145, 238, 65));

        panelSearch.setBackground(new java.awt.Color(0, 0, 102));
        panelSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSearchMouseExited(evt);
            }
        });
        panelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/search2.png"))); // NOI18N
        btnSearch.setText(" Search Record");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchMouseExited(evt);
            }
        });
        panelSearch.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 3, 225, 60));

        btnSearch1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/search2.png"))); // NOI18N
        btnSearch1.setText(" Search Record");
        btnSearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearch1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearch1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearch1MouseExited(evt);
            }
        });
        panelSearch.add(btnSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 3, 225, 60));

        panelsideBar.add(panelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 226, 238, -1));

        panelEdit.setBackground(new java.awt.Color(0, 0, 102));
        panelEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelEditMouseExited(evt);
            }
        });
        panelEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEdit.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/edit2.png"))); // NOI18N
        btnEdit.setText(" Edit Fees");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditMouseExited(evt);
            }
        });
        panelEdit.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 2, 225, 61));

        panelsideBar.add(panelEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 238, -1));

        panelListFees.setBackground(new java.awt.Color(0, 0, 102));
        panelListFees.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelListFees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelListFeesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelListFeesMouseExited(evt);
            }
        });
        panelListFees.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnListFees.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnListFees.setForeground(new java.awt.Color(255, 255, 255));
        btnListFees.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/list.png"))); // NOI18N
        btnListFees.setText(" List of Fees");
        btnListFees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListFeesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnListFeesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnListFeesMouseExited(evt);
            }
        });
        panelListFees.add(btnListFees, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 225, 61));

        panelsideBar.add(panelListFees, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 238, -1));

        panelAllViewRecords.setBackground(new java.awt.Color(0, 0, 102));
        panelAllViewRecords.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelAllViewRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAllViewRecordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAllViewRecordsMouseExited(evt);
            }
        });
        panelAllViewRecords.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewAllRecords.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnViewAllRecords.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/view all record.png"))); // NOI18N
        btnViewAllRecords.setText(" View All Records");
        btnViewAllRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewAllRecordsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllRecordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllRecordsMouseExited(evt);
            }
        });
        panelAllViewRecords.add(btnViewAllRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 3, 225, 60));

        btnViewAllRecords1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnViewAllRecords1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecords1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/view all record.png"))); // NOI18N
        btnViewAllRecords1.setText(" View All Records");
        btnViewAllRecords1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewAllRecords1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllRecords1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllRecords1MouseExited(evt);
            }
        });
        panelAllViewRecords.add(btnViewAllRecords1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 3, 225, 60));

        panelsideBar.add(panelAllViewRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 238, -1));

        panelPrint.setBackground(new java.awt.Color(0, 0, 102));
        panelPrint.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelPrintMouseExited(evt);
            }
        });
        panelPrint.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPrint.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/printer-.png"))); // NOI18N
        btnPrint.setText(" Printing Tab");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrintMouseExited(evt);
            }
        });
        panelPrint.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 3, 225, 60));

        panelsideBar.add(panelPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 238, -1));

        panelBack.setBackground(new java.awt.Color(0, 0, 102));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBackMouseExited(evt);
            }
        });
        panelBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/left-arrow.png"))); // NOI18N
        btnBack.setText("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        panelBack.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 50));

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 120, 50));

        panelLogout.setBackground(new java.awt.Color(0, 0, 102));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLogoutMouseExited(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/logout.png"))); // NOI18N
        btnLogout.setText("Logout");

        javax.swing.GroupLayout panelLogoutLayout = new javax.swing.GroupLayout(panelLogout);
        panelLogout.setLayout(panelLogoutLayout);
        panelLogoutLayout.setHorizontalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelLogoutLayout.setVerticalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 630, 120, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelsideBar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 986, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        home Home = new home();
        Home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        Color clr = new Color(0,0,51);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        Color clr = new Color(0,0,102);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseExited

    private void panelSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseEntered
        Color clr = new Color(0,0,51);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseEntered

    private void panelSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseExited
        Color clr = new Color(0,0,102);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseExited

    private void panelEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditMouseEntered
        Color clr = new Color(0,0,51);
        panelEdit.setBackground(clr);
    }//GEN-LAST:event_panelEditMouseEntered

    private void panelEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditMouseExited
        Color clr = new Color(0,0,102);
        panelEdit.setBackground(clr);
    }//GEN-LAST:event_panelEditMouseExited

    private void panelListFeesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelListFeesMouseEntered
        Color clr = new Color(0,0,51);
        panelListFees.setBackground(clr);
    }//GEN-LAST:event_panelListFeesMouseEntered

    private void panelListFeesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelListFeesMouseExited
        Color clr = new Color(0,0,102);
        panelListFees.setBackground(clr);
    }//GEN-LAST:event_panelListFeesMouseExited

    private void panelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseEntered
        Color clr = new Color(0,0,51);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseEntered

    private void panelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseExited
        Color clr = new Color(0,0,102);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseExited

    private void panelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseEntered
        Color clr = new Color(0,0,51);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseEntered

    private void panelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseExited
        Color clr = new Color(0,0,102);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseExited

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        SearchRecord  search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseEntered
        Color clr = new Color(0,0,51);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_btnSearchMouseEntered

    private void btnSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseExited
        Color clr = new Color(0,0,102);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_btnSearchMouseExited

    private void btnSearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearch1MouseClicked
        SearchRecord  search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearch1MouseClicked

    private void btnSearch1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearch1MouseEntered
        Color clr = new Color(0,0,51);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_btnSearch1MouseEntered

    private void btnSearch1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearch1MouseExited
        Color clr = new Color(0,0,102);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_btnSearch1MouseExited

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        EditFees  edit = new EditFees();
        edit.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        Color clr = new Color(0,0,51);
        panelEdit.setBackground(clr);
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        Color clr = new Color(0,0,102);
        panelEdit.setBackground(clr);
    }//GEN-LAST:event_btnEditMouseExited

    private void btnListFeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListFeesMouseClicked
        EditFees editFee = new EditFees();
        editFee.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnListFeesMouseClicked

    private void btnListFeesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListFeesMouseEntered
        Color clr = new Color(0,0,51);
        panelListFees.setBackground(clr);
    }//GEN-LAST:event_btnListFeesMouseEntered

    private void btnListFeesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListFeesMouseExited
        Color clr = new Color(0,0,102);
        panelListFees.setBackground(clr);
    }//GEN-LAST:event_btnListFeesMouseExited

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        home Home = new home();
        Home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        Color clr = new Color(0,0,51);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        Color clr = new Color(0,0,102);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseExited

    private void panelPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrintMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrintMouseExited

    private void panelPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrintMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrintMouseEntered

    private void btnPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseExited
        Color clr = new Color(0,0,102);
        panelPrint.setBackground(clr);
    }//GEN-LAST:event_btnPrintMouseExited

    private void btnPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseEntered
        Color clr = new Color(0,0,51);
        panelPrint.setBackground(clr);
    }//GEN-LAST:event_btnPrintMouseEntered

    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
        PrintReceipt printReceipt = new PrintReceipt();
        printReceipt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPrintMouseClicked

    private void panelAllViewRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAllViewRecordsMouseExited
        Color clr = new Color(0,0,102);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_panelAllViewRecordsMouseExited

    private void panelAllViewRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAllViewRecordsMouseEntered
        Color clr = new Color(0,0,51);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_panelAllViewRecordsMouseEntered

    private void btnViewAllRecords1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecords1MouseExited
        Color clr = new Color(0,0,102);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecords1MouseExited

    private void btnViewAllRecords1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecords1MouseEntered
        Color clr = new Color(0,0,51);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecords1MouseEntered

    private void btnViewAllRecords1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecords1MouseClicked
        ViewAllRecords  ViewRecords = new ViewAllRecords();
        ViewRecords.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAllRecords1MouseClicked

    private void btnViewAllRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseExited
        Color clr = new Color(0,0,102);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordsMouseExited

    private void btnViewAllRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseEntered
        Color clr = new Color(0,0,51);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordsMouseEntered

    private void btnViewAllRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseClicked
        ViewAllRecords  ViewRecords = new ViewAllRecords();
        ViewRecords.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAllRecordsMouseClicked

    private void BarGraphFrq_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarGraphFrq_btnActionPerformed
        txt_dy.getText();
        UniqueRecordBarChart();
    }//GEN-LAST:event_BarGraphFrq_btnActionPerformed

    private void BarGraphDA_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarGraphDA_btnActionPerformed
       txt_dy.getText();
        BarGraphDateAmount();
    }//GEN-LAST:event_BarGraphDA_btnActionPerformed

    private void Fr_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Fr_btnActionPerformed
        Thread t = new Thread(new Runnable() {
    public void run() {
        FeeRevenueCounter();
    }
});
t.start();
    }//GEN-LAST:event_Fr_btnActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAllRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BarGraphDA_btn;
    private javax.swing.JButton BarGraphFrq_btn;
    private javax.swing.JButton Fr_btn;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnListFees;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnPrint;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnSearch1;
    private javax.swing.JLabel btnViewAllRecords;
    private javax.swing.JLabel btnViewAllRecords1;
    private javax.swing.JLabel drc1;
    private javax.swing.JLabel drc10;
    private javax.swing.JLabel drc11;
    private javax.swing.JLabel drc12;
    private javax.swing.JLabel drc2;
    private javax.swing.JLabel drc3;
    private javax.swing.JLabel drc4;
    private javax.swing.JLabel drc5;
    private javax.swing.JLabel drc6;
    private javax.swing.JLabel drc7;
    private javax.swing.JLabel drc8;
    private javax.swing.JLabel drc9;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAllViewRecords;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelListFees;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelPrint;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JLabel rc1;
    private javax.swing.JLabel rc2;
    private javax.swing.JLabel rc3;
    private javax.swing.JLabel rc4;
    private javax.swing.JLabel rc5;
    private javax.swing.JLabel rc6;
    private javax.swing.JTable tbl_allRecord;
    private javax.swing.JLabel trc1;
    private javax.swing.JLabel trc10;
    private javax.swing.JLabel trc11;
    private javax.swing.JLabel trc12;
    private javax.swing.JLabel trc2;
    private javax.swing.JLabel trc3;
    private javax.swing.JLabel trc4;
    private javax.swing.JLabel trc5;
    private javax.swing.JLabel trc6;
    private javax.swing.JLabel trc7;
    private javax.swing.JLabel trc8;
    private javax.swing.JLabel trc9;
    private javax.swing.JLabel txt_dy;
    // End of variables declaration//GEN-END:variables
}
