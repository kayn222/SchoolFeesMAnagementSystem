/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author USER
 */
public class GenerateReport extends javax.swing.JFrame {

    /**
     * Creates new form GenerateReport
     */
    
 DefaultTableModel model;
    
    public GenerateReport() {
        initComponents();
        fillComboBox();
        Container c = getContentPane();
        c.setBackground(new Color(0,0,51));
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
                combo_feeDetails.addItem(rs.getString("fname"));
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void setRecordsToTable(){
        
            String fname = combo_feeDetails.getSelectedItem().toString();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String fromDate =  dateFormat.format(dateChooser_from.getDate());
            String toDate =  dateFormat.format(dateChooser_to.getDate());
            
            Float amountTotal = 0.0f;
        
        try {
                Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM fees_details WHERE date BETWEEN ? AND ? AND (fee_name = ? OR fee_name1 = ? OR fee_name2 = ?)");
                pst.setString(1, fromDate);
                pst.setString(2, toDate);
                pst.setString(3, fname);
                pst.setString(4, fname);
                pst.setString(5, fname);
                ResultSet rs = pst.executeQuery();
                
             while (rs.next()){
                 
                 String receiptNo = rs.getString("receipt_no");
                 String idNo = rs.getString("roll_no");
                 String studentName = rs.getString("student_name");
                 String feeName = rs.getString("fee_name");
                 String feeName1 = rs.getString("fee_name1");
                 String feeName2 = rs.getString("fee_name2");
                 float amount  = rs.getFloat("total_amount");
                 String remark = rs.getString("remark");
                 
                 amountTotal = amountTotal + amount;
                 
                 Object[] obj = {receiptNo,idNo,studentName,feeName,feeName1,feeName2,amount,remark};
                 model = (DefaultTableModel)tbl_feesDetails.getModel();
                 model.addRow(obj);
                 
             }
             lbl_fee.setText(fname);
             lbl_totalAmount.setText(amountTotal.toString());
             lbl_totalInWords.setText(NumberToWordsConverter.convert(amountTotal.intValue()));
             
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void clearTable(){
         DefaultTableModel model = (DefaultTableModel)tbl_feesDetails.getModel();
         model.setRowCount(1);
     }
    
    public void exportToExcel(){
    
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        
        TreeMap<String,Object[]> map = new TreeMap<>();
        
        map.put("0", new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),
                model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
         
        for (int i = 1; i < model.getRowCount(); i++) {
            
            map.put(Integer.toString(i),new Object[]{model.getValueAt(i,0), model.getValueAt(i,1),model.getValueAt(i,2)
                    ,model.getValueAt(i,3),model.getValueAt(i,4),model.getValueAt(i,5),model.getValueAt(i,6),model.getValueAt(i,7)} );
            
        }
        
        Set<String> id = map.keySet();
        
        XSSFRow fRow;
        
        int rowId = 0;
        
        for (String key : id){
            
            fRow = ws.createRow(rowId++);
            
            Object[] value = map.get(key);
            
            int cellId = 0;
            
            for (Object object : value){
                
               XSSFCell cell = fRow.createCell(cellId++);
               cell.setCellValue(object.toString());

        } 
    }
        
        try(FileOutputStream  fos = new FileOutputStream(new File(txt_filePath.getText()))){
            
             wb.write(fos);
             JOptionPane.showMessageDialog(this,"File Exported Successfully: "+txt_filePath.getText());
             
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

        panelsideBar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combo_feeDetails = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dateChooser_from = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateChooser_to = new com.toedter.calendar.JDateChooser();
        btn_submit = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        txt_filePath = new javax.swing.JTextField();
        btn_browse = new javax.swing.JButton();
        btn_exportExcel = new javax.swing.JButton();
        scrl_fee = new javax.swing.JScrollPane();
        tbl_feesDetails = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_fee = new javax.swing.JLabel();
        lbl_totalAmount = new javax.swing.JLabel();
        lbl_totalInWords = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 840));

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Select Fee: ");

        combo_feeDetails.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select Date: ");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("from: ");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("to:");

        btn_submit.setBackground(new java.awt.Color(0, 0, 102));
        btn_submit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_submit.setText("Search");
        btn_submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_submitMouseClicked(evt);
            }
        });

        btn_print.setBackground(new java.awt.Color(0, 0, 102));
        btn_print.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setText("Print");
        btn_print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_printMouseClicked(evt);
            }
        });

        btn_browse.setBackground(new java.awt.Color(0, 0, 102));
        btn_browse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_browse.setForeground(new java.awt.Color(255, 255, 255));
        btn_browse.setText("Browse");
        btn_browse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_browseMouseClicked(evt);
            }
        });

        btn_exportExcel.setBackground(new java.awt.Color(0, 0, 102));
        btn_exportExcel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_exportExcel.setForeground(new java.awt.Color(255, 255, 255));
        btn_exportExcel.setText("Export to Excel");
        btn_exportExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exportExcelMouseClicked(evt);
            }
        });
        btn_exportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportExcelActionPerformed(evt);
            }
        });

        tbl_feesDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Receipt No.", "ID No.", "Student Name", "Fee Name", "Fee Name1", "Fee Name 2", "Total Amount", "Remark"
            }
        ));
        scrl_fee.setViewportView(tbl_feesDetails);

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fee Selected: ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Amount Collected:  ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Amount in Words:  ");

        lbl_fee.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_fee.setForeground(new java.awt.Color(255, 255, 255));

        lbl_totalAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_totalAmount.setForeground(new java.awt.Color(255, 255, 255));

        lbl_totalInWords.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbl_totalInWords.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_totalInWords, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_totalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_fee, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(lbl_fee, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(lbl_totalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_totalInWords, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrl_fee, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_filePath, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_browse)
                                .addGap(18, 18, 18)
                                .addComponent(btn_exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(dateChooser_from, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(dateChooser_to, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(combo_feeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(btn_submit)
                                .addGap(79, 79, 79)
                                .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(combo_feeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addComponent(dateChooser_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(dateChooser_to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_print)
                            .addComponent(btn_submit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_browse)
                            .addComponent(btn_exportExcel)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrl_fee, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 1000, 840));

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

    private void panelAllViewRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAllViewRecordsMouseEntered
        Color clr = new Color(0,0,51);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_panelAllViewRecordsMouseEntered

    private void panelAllViewRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAllViewRecordsMouseExited
        Color clr = new Color(0,0,102);
        panelAllViewRecords.setBackground(clr);
    }//GEN-LAST:event_panelAllViewRecordsMouseExited

    private void panelPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrintMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrintMouseEntered

    private void panelPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrintMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrintMouseExited

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

    private void btn_exportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_exportExcelActionPerformed

    private void btn_submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_submitMouseClicked
        clearTable();
        setRecordsToTable();
        
    }//GEN-LAST:event_btn_submitMouseClicked

    private void btn_printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_printMouseClicked
        SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-DD"); 
        String datefrom=  Date_Format.format(dateChooser_from.getDate());
        String dateto=  Date_Format.format(dateChooser_to.getDate());
       
        MessageFormat header=new MessageFormat("Report From "+datefrom+" To " +dateto);
        MessageFormat footer=new MessageFormat("page{0,number,integer}");
        try {
            tbl_feesDetails.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        } catch (Exception e) {
            e.getMessage();
        } 

    }//GEN-LAST:event_btn_printMouseClicked

    private void btn_browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_browseMouseClicked
       
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYY-MM-dd");
        String date = dateFormat.format(new Date());
        try{
            
            File f = fileChooser.getSelectedFile();
            String path = f.getAbsolutePath();
            
            path = path+"_"+date+".xls";
            txt_filePath.setText(path);
            
            
        }catch(Exception e){
         e.printStackTrace();
        }
        
         
    }//GEN-LAST:event_btn_browseMouseClicked

    private void btn_exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exportExcelMouseClicked
       exportToExcel();
    }//GEN-LAST:event_btn_exportExcelMouseClicked

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
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateReport().setVisible(true);
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
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_exportExcel;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_submit;
    private javax.swing.JComboBox<String> combo_feeDetails;
    private com.toedter.calendar.JDateChooser dateChooser_from;
    private com.toedter.calendar.JDateChooser dateChooser_to;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_fee;
    private javax.swing.JLabel lbl_totalAmount;
    private javax.swing.JLabel lbl_totalInWords;
    private javax.swing.JPanel panelAllViewRecords;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelListFees;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelPrint;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JScrollPane scrl_fee;
    private javax.swing.JTable tbl_feesDetails;
    private javax.swing.JTextField txt_filePath;
    // End of variables declaration//GEN-END:variables
}
