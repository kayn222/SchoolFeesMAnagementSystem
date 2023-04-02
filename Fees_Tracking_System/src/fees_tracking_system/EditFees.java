/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author USER
 */
public class EditFees extends javax.swing.JFrame {

    /**
     * Creates new form EditFees
     */
    
    DefaultTableModel model;
    
    public EditFees() {
        initComponents();
        setRecordsToTable();
        tbl_feeData.setRowHeight(25);
        
        int feeIdNo = getFeeIdNo();
        txt_feeId.setText(Integer.toString(feeIdNo));
        
    }
    
     public void setRecordsToTable(){
        
        try {
                Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("select * from fees");
                ResultSet rs = pst.executeQuery();
                
             while (rs.next()){
                 
                 String feeId = rs.getString("id");
                 String fname = rs.getString("fname");
                 String cost = rs.getString("cost");
                 
                 
                 Object[] obj = {feeId,fname,cost};
                 model = (DefaultTableModel)tbl_feeData.getModel();
                 model.addRow(obj);
                 
             }
                
            
        }catch(Exception e){
            e.printStackTrace();
        }
     }
     
      public void clearTable(){
         DefaultTableModel model = (DefaultTableModel)tbl_feeData.getModel();
         model.setRowCount(0);
     }
     
     
     public void addFees(int id, String fname, double cost){
         
           try {
                Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("insert into fees values(?,?,?)");
                pst.setInt (1,id);
                pst.setString(2,fname);
                pst.setDouble(3,cost);
               
                int rowCount = pst.executeUpdate();
                
                if(rowCount == 1){
                    JOptionPane.showMessageDialog(this,"Fee Added Successfuly!");
                    clearTable();
                    setRecordsToTable();
                }else{
                    JOptionPane.showMessageDialog(this,"Fee Insertion Failed!");
                }
                
               
        }catch(Exception e){
            e.printStackTrace();
        }
         
     }
     
     public void update(int id, String fname, double cost){
           try {
                Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("update fees set fname=?, cost = ? where id = ?");
               
                pst.setString(1,fname);
                pst.setDouble(2,cost);
                pst.setInt (3,id);
               
                int rowCount = pst.executeUpdate();
                
                if(rowCount == 1){
                    JOptionPane.showMessageDialog(this,"Fee Updated Successfuly!");
                    clearTable();
                    setRecordsToTable();
                }else{
                    JOptionPane.showMessageDialog(this,"Fee Updation Failed!");
                }
                
               
        }catch(Exception e){
            e.printStackTrace();
        }
     }
     
    
      public void delete(int id){
           try {
                Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement("delete from fees where id = ?");

                pst.setInt (1,id);
               
                int rowCount = pst.executeUpdate();
                
                if(rowCount == 1){
                    JOptionPane.showMessageDialog(this,"Fee Deleted Successfuly!");
                    clearTable();
                    setRecordsToTable();
                }else{
                    JOptionPane.showMessageDialog(this,"Fee Deletion Failed!");
                }
                
               
        }catch(Exception e){
            e.printStackTrace();
        }
     }
     
     public int getFeeIdNo(){
        int FeeIdNo = 0;
        try
        {
          Connection con = DBConnection.getConnection();
          PreparedStatement pst = con.prepareStatement("select max (id) from fees");
          ResultSet rs = pst.executeQuery();  
          
          if(rs.next()== true)
          {
            FeeIdNo = rs.getInt(1);
          }    
 
        }catch(Exception e){
            e.printStackTrace();
        }
        return FeeIdNo+1;
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
        btnPrint1 = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_feeData = new javax.swing.JTable();
        txt_feeId = new javax.swing.JTextField();
        txt_feeName = new javax.swing.JTextField();
        txt_cost = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btnPrint1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnPrint1.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_tracking_system/my icons/printer-.png"))); // NOI18N
        btnPrint1.setText(" Printing Tab");
        btnPrint1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrint1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrint1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrint1MouseExited(evt);
            }
        });
        panelPrint.add(btnPrint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 3, 225, 60));

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

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        tbl_feeData.setAutoCreateRowSorter(true);
        tbl_feeData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbl_feeData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fee iD", "Fee Name", "Fee Price"
            }
        ));
        tbl_feeData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_feeDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_feeData);

        txt_feeId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_feeName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_cost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fee Id:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fee Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fee Price:");

        btn_add.setBackground(new java.awt.Color(0, 0, 102));
        btn_add.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setText("ADD");
        btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_addMouseClicked(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(0, 0, 102));
        btn_update.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("UPDATE");
        btn_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateMouseClicked(evt);
            }
        });
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(0, 0, 102));
        btn_delete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("DELETE");
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Fee Editor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_feeId)
                            .addComponent(txt_feeName)
                            .addComponent(txt_cost)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(336, 336, 336))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_feeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_feeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelsideBar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsideBar, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseClicked
        
        int id = Integer.parseInt(txt_feeId.getText());
        String fname = txt_feeName.getText();
        double cost = Double.parseDouble(txt_cost.getText());
        
        addFees(id,fname, cost);
        
    }//GEN-LAST:event_btn_addMouseClicked

    private void tbl_feeDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_feeDataMouseClicked
        int rowNo = tbl_feeData.getSelectedRow();
        TableModel model =  tbl_feeData.getModel();
        
        txt_feeId.setText(model.getValueAt(rowNo,0).toString());
        txt_feeName.setText((String)model.getValueAt(rowNo,1));
        txt_cost.setText(model.getValueAt(rowNo,2).toString());
        
       
        
    }//GEN-LAST:event_tbl_feeDataMouseClicked

    private void btn_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateMouseClicked
        int id = Integer.parseInt(txt_feeId.getText());
        String fname = txt_feeName.getText();
        double cost = Double.parseDouble(txt_cost.getText());
        
        update(id,fname, cost);
    }//GEN-LAST:event_btn_updateMouseClicked

    private void btn_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseClicked
        int id = Integer.parseInt(txt_feeId.getText());
        delete(id);
    }//GEN-LAST:event_btn_deleteMouseClicked

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

    private void btnPrint1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrint1MouseClicked
        PrintReceipt printReceipt = new PrintReceipt();
        printReceipt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPrint1MouseClicked

    private void btnPrint1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrint1MouseEntered
        Color clr = new Color(0,0,51);
        panelPrint.setBackground(clr);
    }//GEN-LAST:event_btnPrint1MouseEntered

    private void btnPrint1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrint1MouseExited
        Color clr = new Color(0,0,102);
        panelPrint.setBackground(clr);
    }//GEN-LAST:event_btnPrint1MouseExited

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
            java.util.logging.Logger.getLogger(EditFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditFees().setVisible(true);
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
    private javax.swing.JLabel btnPrint1;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnViewAllRecords;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTable tbl_feeData;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JTextField txt_feeId;
    private javax.swing.JTextField txt_feeName;
    // End of variables declaration//GEN-END:variables
}
