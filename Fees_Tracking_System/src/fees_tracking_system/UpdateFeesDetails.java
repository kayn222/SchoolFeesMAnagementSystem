/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import static javax.management.Query.value;

/**
 *
 * @author USER
 */
public class UpdateFeesDetails extends JFrame {

    /**
     * Creates new form AddFees
     */
    public UpdateFeesDetails() {
        initComponents();
        displayCashFirst();
        fillComboBox();
        
        int receiptNo = getReceiptNo();
        txt_receiptNo.setText(Integer.toString(receiptNo));
        
        
        setRecords();
        
    }
    
    public void displayCashFirst()
    {
        
        lbl_DDno.setVisible(false);
        lbl_chequeNo.setVisible(false);
        lbl_bankName.setVisible(false);
       
        txt_DDNo.setVisible(false);
        txt_ChequeNo.setVisible(false);
        txt_bankName.setVisible(false);
        
        
    }
    
    
    public boolean validation()
    {
        if(txt_recievedFrom.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter a name");
            return false;
        }
        if(dateChooser.getDate()== null)
        {
             JOptionPane.showMessageDialog(this,"Please select a Date");
             return false;
        }
        if(txt_amount.getText().equals(""))
        {
             JOptionPane.showMessageDialog(this,"Please enter amount (in numbers)");
             return false;
        }
        if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("cheque"))
        {
            if(txt_ChequeNo.getText().equals(""))
            {
                txt_DDNo.setText("");
                JOptionPane.showMessageDialog(this,"Please enter cheque number");
                return false;
            }
            if(txt_bankName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please enter Bank name");
                return false;
            }
            
        }
        
        if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("dd"))
        {
            if(txt_DDNo.getText().equals(""))
            {
                txt_ChequeNo.setText("");
                JOptionPane.showMessageDialog(this,"Please enter DD no.");
                return false;
            }
            if(txt_bankName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please enter Bank name.");
                return false;
            }
        } 
        
        if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("card"))
        { 
            if(txt_bankName.getText().equals(""))
            {
                txt_DDNo.setText("");
                txt_ChequeNo.setText("");
                JOptionPane.showMessageDialog(this,"Please enter Bank name.");
                return false;
            }
        } 
           return true;
        
    }
    
    public void fillComboBox()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management","root","1234");
            PreparedStatement pst = con.prepareStatement("select fname from fees");
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                comboFees.addItem(rs.getString("fname"));
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public int getReceiptNo(){
        int receiptNo = 0;
        try
        {
          Connection con = DBConnection.getConnection();
          PreparedStatement pst = con.prepareStatement("select max (receipt_no) from fees_details");
          ResultSet rs = pst.executeQuery();  
          
          if(rs.next()== true)
          {
            receiptNo = rs.getInt(1);
          }    
 
        }catch(Exception e){
            e.printStackTrace();
        }
        return receiptNo+1;
    }
    
    public String updateData(){
        
        String status = "";
        
        int receiptNo = Integer.parseInt(txt_receiptNo.getText());
        String studentName = txt_recievedFrom.getText();
        String idNo = txt_idNo.getText();
        String paymentMode = combo_PaymentMode.getSelectedItem().toString();
        String chequeNo = txt_ChequeNo.getText();
        String bankName = txt_bankName.getText();   
        String ddNo = txt_DDNo.getText();
        String feesName = txt_feesName.getText();
        String feesName1 = txt_feesName1.getText();
        String feesName2 = txt_feesName2.getText();
        float totalAmount = Float.parseFloat(txt_total.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        String Date = dateFormat.format(dateChooser.getDate());
        float initialAmount = Float.parseFloat(txt_amount.getText());
        float initialAmount1 = Float.parseFloat(txt_amount1.getText());
        float initialAmount2 = Float.parseFloat(txt_amount2.getText());
        String totalInWords = txt_total_words.getText();
        String remark = txt_remarks.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());
        
        try
        {
          Connection con = DBConnection.getConnection();
          PreparedStatement pst = con.prepareStatement("update fees_details set student_name = ?, roll_no= ? , payment_mode = ?, cheque_no = ?,bank_name = ?"
                  + ",dd_no = ? , fees_name= ?, fees_name1= ? , fees_name2= ? , total_amount = ?, date = ?, amount= ? , amount1= ? , amount2= ? , total_in_words = ?, remark = ?, year1 = ?, year2 = ? where receipt_no = ?");
          
          
          pst.setString(1,studentName);
          pst.setString(2,idNo);
          pst.setString(3,paymentMode); 
          pst.setString(4,chequeNo); 
          pst.setString(5,bankName); 
          pst.setString(6, ddNo); 
          pst.setString(7,feesName); 
          pst.setString(8,feesName1); 
          pst.setString(9,feesName2); 
          pst.setFloat(10, totalAmount);
          pst.setString(11,Date); 
          pst.setFloat(12, initialAmount);
          pst.setFloat(13, initialAmount1);
          pst.setFloat(14, initialAmount2);
          pst.setString(15,totalInWords); 
          pst.setString(16,remark); 
          pst.setInt(17, year1);
          pst.setInt(18, year2);
          pst.setInt(19,receiptNo);
          
         int rowcount = pst.executeUpdate();
         if(rowcount == 1){ 
             status = "Success";
         }else{
             status = "Failed";
         }
                 
                 
                 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
    
    public void setRecords(){
        
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management","root","1234");
            PreparedStatement pst = con.prepareStatement("select * from fees_details order by receipt_no desc fetch first 1 row only");;
            ResultSet rs = pst.executeQuery();
            rs.next();
            txt_receiptNo.setText(rs.getString("receipt_no"));
            combo_PaymentMode.setSelectedItem(rs.getString("payment_mode"));
            txt_ChequeNo.setText(rs.getString("cheque_no"));
            txt_DDNo.setText(rs.getString("dd_no"));
            txt_bankName.setText(rs.getString("bank_name"));
            txt_recievedFrom.setText(rs.getString("student_name"));
            dateChooser.setDate(rs.getDate("date"));
            txt_year1.setText(rs.getString("year1"));
            txt_year2.setText(rs.getString("year2"));
            txt_idNo.setText(rs.getString("roll_no"));
            comboFees.setSelectedItem(rs.getString("fee_name"));
            txt_amount.setText(rs.getString("amount"));
            txt_amount1.setText(rs.getString("amount1"));
            txt_amount2.setText(rs.getString("amount2"));
            txt_total.setText(rs.getString("total_amount"));
            txt_total_words.setText(rs.getString("total_in_words"));
            txt_remarks.setText(rs.getString("remark"));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fees_managementPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("fees_managementPU").createEntityManager();
        feesQuery = java.beans.Beans.isDesignTime() ? null : fees_managementPUEntityManager.createQuery("SELECT f FROM Fees f");
        feesList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : feesQuery.getResultList();
        panelsideBar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelParent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_DDno = new javax.swing.JLabel();
        lbl_chequeNo = new javax.swing.JLabel();
        lbl_bankName = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_GSTINo = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        txt_ChequeNo = new javax.swing.JTextField();
        txt_bankName = new javax.swing.JTextField();
        combo_PaymentMode = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        panelChild = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        txt_year2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        comboFees = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txt_feesName = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_total = new javax.swing.JTextField();
        txt_total_words = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remarks = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        txt_recievedFrom = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_idNo = new javax.swing.JTextField();
        txt_feesName1 = new javax.swing.JTextField();
        txt_amount1 = new javax.swing.JTextField();
        txt_feesName2 = new javax.swing.JTextField();
        txt_amount2 = new javax.swing.JTextField();
        comboFees1 = new javax.swing.JComboBox<>();
        comboFees2 = new javax.swing.JComboBox<>();
        txt_DDNo = new javax.swing.JTextField();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        panelEdit = new javax.swing.JPanel();
        btnEdit = new javax.swing.JLabel();
        panelListFees = new javax.swing.JPanel();
        btnListFees = new javax.swing.JLabel();
        panelAllViewRecords = new javax.swing.JPanel();
        btnViewAllRecords = new javax.swing.JLabel();
        panelPrint = new javax.swing.JPanel();
        btnPrint = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelsideBar.setBackground(new java.awt.Color(0, 0, 102));
        panelsideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/WITILOGO.png"))); // NOI18N
        panelsideBar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 14, -1, -1));

        panelParent.setBackground(new java.awt.Color(255, 255, 255));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Receipt No.: SOC -");
        panelParent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 26, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Mode of Payment: ");
        panelParent.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 66, -1, -1));

        lbl_DDno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_DDno.setText("DD no.: ");
        panelParent.add(lbl_DDno, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 108, -1, -1));

        lbl_chequeNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_chequeNo.setText("Cheque No.: ");
        panelParent.add(lbl_chequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 108, -1, -1));

        lbl_bankName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_bankName.setText("Bank Name:");
        panelParent.add(lbl_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Date: ");
        panelParent.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(714, 44, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("GSTIN: ");
        panelParent.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, -1));

        txt_GSTINo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_GSTINo.setText("22HVSJH55");
        panelParent.add(txt_GSTINo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 92, -1));

        txt_receiptNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelParent.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 182, -1));

        txt_ChequeNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_ChequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ChequeNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 182, -1));

        txt_bankName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelParent.add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 182, -1));

        combo_PaymentMode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card", " " }));
        combo_PaymentMode.setSelectedIndex(2);
        combo_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_PaymentModeActionPerformed(evt);
            }
        });
        panelParent.add(combo_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 61, 189, -1));
        panelParent.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(758, 33, 148, -1));

        panelChild.setBackground(new java.awt.Color(255, 255, 255));
        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Recieved From: ");
        panelChild.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("The following payment in the office registrar for the year ");
        panelChild.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txt_year1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelChild.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 70, -1));

        txt_year2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelChild.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 39, 80, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("to");
        panelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Fees being payed");
        panelChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        comboFees.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboFees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFeesActionPerformed(evt);
            }
        });
        panelChild.add(comboFees, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 75, 410, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Student ID No.:");
        panelChild.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        panelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 880, 10));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setMinimumSize(new java.awt.Dimension(1, 1));
        panelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 880, 10));

        txt_feesName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_feesName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_feesNameActionPerformed(evt);
            }
        });
        panelChild.add(txt_feesName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 340, -1));

        txt_amount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_amount.setText("0");
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 140, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("SR No.");
        panelChild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Fee being payed");
        panelChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Amount");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, -1, -1));
        panelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 270, 10));

        txt_total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, 140, -1));

        txt_total_words.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        panelChild.add(txt_total_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 340, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Total in words:");
        panelChild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Remarks:");
        panelChild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        txt_remarks.setColumns(20);
        txt_remarks.setRows(5);
        txt_remarks.setText("No Remarks");
        jScrollPane1.setViewportView(txt_remarks);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 378, 350, 60));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        panelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, 270, 10));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Reciever's Signature");
        panelChild.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, -1, -1));

        btn_print.setBackground(new java.awt.Color(0, 0, 102));
        btn_print.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setText("Print");
        btn_print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_printMouseClicked(evt);
            }
        });
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        panelChild.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 460, 90, -1));

        txt_recievedFrom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelChild.add(txt_recievedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 310, -1));

        btn_update.setBackground(new java.awt.Color(0, 0, 102));
        btn_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("Update");
        btn_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateMouseClicked(evt);
            }
        });
        panelChild.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, 90, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total");
        panelChild.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, -1, -1));

        txt_idNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelChild.add(txt_idNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, 182, -1));

        txt_feesName1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_feesName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_feesName1ActionPerformed(evt);
            }
        });
        panelChild.add(txt_feesName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 340, -1));

        txt_amount1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_amount1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_amount1.setText("0");
        txt_amount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amount1ActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 140, -1));

        txt_feesName2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_feesName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_feesName2ActionPerformed(evt);
            }
        });
        panelChild.add(txt_feesName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 340, 30));

        txt_amount2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_amount2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_amount2.setText("0");
        txt_amount2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amount2ActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, 140, 30));

        comboFees1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboFees1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFees1ActionPerformed(evt);
            }
        });
        panelChild.add(comboFees1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 75, 410, -1));

        comboFees2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboFees2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFees2ActionPerformed(evt);
            }
        });
        panelChild.add(comboFees2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 75, 410, -1));

        panelParent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 194, 929, 508));

        txt_DDNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelParent.add(txt_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 182, -1));

        panelsideBar.add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 0, -1, -1));

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
        panelHome.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 220, 60));

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
        btnBack.setBorder(null);
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
        btnLogout.setBorder(null);

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
            .addComponent(panelsideBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void panelAllViewRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAllViewRecordsMouseEntered
         Color clr = new Color(0,0,51);
         panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_panelAllViewRecordsMouseEntered

    private void panelAllViewRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAllViewRecordsMouseExited
        Color clr = new Color(0,0,102);
         panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_panelAllViewRecordsMouseExited

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

    private void txt_feesNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_feesNameActionPerformed
        comboFees.setVisible(true);
        comboFees1.setVisible(false);
        comboFees2.setVisible(false);
    }//GEN-LAST:event_txt_feesNameActionPerformed

    private void txt_ChequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ChequeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ChequeNoActionPerformed

    private void combo_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_PaymentModeActionPerformed
        
        if(combo_PaymentMode.getSelectedIndex()==0)
        {
             lbl_DDno.setVisible(true);
             txt_DDNo.setVisible(true);
             lbl_chequeNo.setVisible(false);
             txt_ChequeNo.setVisible(false);
             lbl_bankName.setVisible(true);
             txt_bankName.setVisible(true);
             
        }
        if(combo_PaymentMode.getSelectedIndex()==1)
        {
             lbl_DDno.setVisible(false);
             txt_DDNo.setVisible(false);
             lbl_chequeNo.setVisible(true);
             txt_ChequeNo.setVisible(true);
             lbl_bankName.setVisible(true);
             txt_bankName.setVisible(true);
             
        }
        if(combo_PaymentMode.getSelectedIndex()==2)
        {
             lbl_DDno.setVisible(false);
             txt_DDNo.setVisible(false);
             lbl_chequeNo.setVisible(false);
             txt_ChequeNo.setVisible(false);
             lbl_bankName.setVisible(false);
             txt_bankName.setVisible(false);
             
        }
        if(combo_PaymentMode.getSelectedIndex()==3)
        {
             lbl_DDno.setVisible(false);
             txt_DDNo.setVisible(false);
             lbl_chequeNo.setVisible(false);
             txt_ChequeNo.setVisible(false);
             lbl_bankName.setVisible(true);
             txt_bankName.setVisible(true);
             
        }
        
        
    }//GEN-LAST:event_combo_PaymentModeActionPerformed

    private void btn_printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_printMouseClicked
        if(validation()==true)
        {
             
             PrintReceipt p = new PrintReceipt();
             p.setVisible(true);
             this.dispose();
 
            }

       
    }//GEN-LAST:event_btn_printMouseClicked

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
             
            Float amnt = Float.parseFloat(txt_amount.getText());    
            float total = amnt;
            
            txt_total.setText(Float.toString(total));
            txt_total_words.setText(NumberToWords1.convert((int)total)+" Only");
         
    }//GEN-LAST:event_txt_amountActionPerformed

    private void btn_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateMouseClicked
         if(validation()==true)
        {   
            String result = updateData();
            
             
             Float amnt = Float.parseFloat(txt_amount.getText());    
            float total = amnt;
            
            txt_total.setText(Float.toString(total));
            
            txt_total_words.setText(NumberToWords1.convert((int)total)+" Only");
            JOptionPane.showMessageDialog(this,"Validation Successful!");
            
            if(result.equals("Success")){
             JOptionPane.showMessageDialog(this,"Record Update Successfully");
            }else{
              JOptionPane.showMessageDialog(this,"Record Update Failed");
                
            }
                  
                
            
        }
    }//GEN-LAST:event_btn_updateMouseClicked

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_printActionPerformed

    private void comboFeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFeesActionPerformed
        txt_feesName.setText(comboFees.getSelectedItem().toString());
  
    }//GEN-LAST:event_comboFeesActionPerformed

    private void panelPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrintMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrintMouseEntered

    private void panelPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrintMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrintMouseExited

    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
        PrintReceipt printReceipt = new PrintReceipt();
        printReceipt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPrintMouseClicked

    private void btnPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseEntered
        Color clr = new Color(0,0,51);
        panelPrint.setBackground(clr);
    }//GEN-LAST:event_btnPrintMouseEntered

    private void btnPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseExited
        Color clr = new Color(0,0,102);
        panelPrint.setBackground(clr);
    }//GEN-LAST:event_btnPrintMouseExited

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

    private void panelSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseExited
        Color clr = new Color(0,0,102);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseExited

    private void panelSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseEntered
        Color clr = new Color(0,0,51);
        panelSearch.setBackground(clr);
    }//GEN-LAST:event_panelSearchMouseEntered

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

    private void btnViewAllRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseClicked
        ViewAllRecords  ViewRecords = new ViewAllRecords();
        ViewRecords.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAllRecordsMouseClicked

    private void btnViewAllRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseEntered
        Color clr = new Color(0,0,51);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordsMouseEntered

    private void btnViewAllRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseExited
        Color clr = new Color(0,0,102);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordsMouseExited

    private void txt_feesName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_feesName1ActionPerformed
       txt_feesName1.setText("");
        comboFees.setVisible(false);
        comboFees1.setVisible(true);
        comboFees2.setVisible(false);
    }//GEN-LAST:event_txt_feesName1ActionPerformed

    private void txt_amount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amount1ActionPerformed
       Float amnt = Float.parseFloat(txt_amount.getText());    
            Float amnt1 = Float.parseFloat(txt_amount1.getText());    
            float total = amnt+amnt1;
            
            txt_total.setText(Float.toString(total));
            txt_total_words.setText(NumberToWords1.convert((int)total)+" Only");
    }//GEN-LAST:event_txt_amount1ActionPerformed

    private void txt_feesName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_feesName2ActionPerformed
        txt_feesName2.setText("");
        comboFees.setVisible(false);
        comboFees1.setVisible(false);
        comboFees2.setVisible(true);
    }//GEN-LAST:event_txt_feesName2ActionPerformed

    private void txt_amount2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amount2ActionPerformed
            Float amnt = Float.parseFloat(txt_amount.getText());    
            Float amnt1 = Float.parseFloat(txt_amount1.getText());
            Float amnt2 = Float.parseFloat(txt_amount2.getText());    
            float total = amnt+amnt1+amnt2;
            
            txt_total.setText(Float.toString(total));
            txt_total_words.setText(NumberToWords1.convert((int)total)+" Only");  
    }//GEN-LAST:event_txt_amount2ActionPerformed

    private void comboFees1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFees1ActionPerformed
        txt_feesName1.setText(comboFees1.getSelectedItem().toString());
    }//GEN-LAST:event_comboFees1ActionPerformed

    private void comboFees2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFees2ActionPerformed
         txt_feesName2.setText(comboFees2.getSelectedItem().toString());
    }//GEN-LAST:event_comboFees2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
      
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateFeesDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnListFees;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnPrint;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnViewAllRecords;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> comboFees;
    private javax.swing.JComboBox<String> comboFees1;
    private javax.swing.JComboBox<String> comboFees2;
    private javax.swing.JComboBox<String> combo_PaymentMode;
    private com.toedter.calendar.JDateChooser dateChooser;
    private java.util.List<fees_tracking_system.Fees> feesList;
    private javax.persistence.Query feesQuery;
    private javax.persistence.EntityManager fees_managementPUEntityManager;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_DDno;
    private javax.swing.JLabel lbl_bankName;
    private javax.swing.JLabel lbl_chequeNo;
    private javax.swing.JPanel panelAllViewRecords;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelListFees;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelPrint;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTextField txt_ChequeNo;
    private javax.swing.JTextField txt_DDNo;
    private javax.swing.JLabel txt_GSTINo;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_amount1;
    private javax.swing.JTextField txt_amount2;
    private javax.swing.JTextField txt_bankName;
    private javax.swing.JTextField txt_feesName;
    private javax.swing.JTextField txt_feesName1;
    private javax.swing.JTextField txt_feesName2;
    private javax.swing.JTextField txt_idNo;
    private javax.swing.JTextField txt_receiptNo;
    private javax.swing.JTextField txt_recievedFrom;
    private javax.swing.JTextArea txt_remarks;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_words;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables
}
