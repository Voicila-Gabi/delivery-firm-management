/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class ManageDeliveries extends javax.swing.JFrame {

    /**
     * Creates new form ManageClients
     */
    public ManageDeliveries() {
        initComponents();
        setDatabaseToGUITable();
    }
    
    //for table
    //int client_id;
    //String cnp, first_name, last_name, city, address;
    //
    int delivery_id, shipment_id, employee_id, done;
    String delivery_date;
    DefaultTableModel model;
    
    //write data from database into the GUI table
    public void setDatabaseToGUITable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bd2_project.delivery");
            
            while(rs.next()) {
                int _delivery_id = rs.getInt("id");
                int _shipment_id = rs.getInt("shipment_id");
                int _employee_id = rs.getInt("employee_id");
                Date _delivery_date = rs.getDate("date");
                int _done = rs.getInt("done");  //bool
                
                Object[] myObjArray = {_delivery_id, _shipment_id, 
                    _employee_id, _delivery_date, _done};
                
                model = (DefaultTableModel) tableDeliveries.getModel();
                model.addRow(myObjArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearGUItable() {
        DefaultTableModel model = (DefaultTableModel) tableDeliveries.getModel();
        model.setRowCount(0);
    }
    
    //to add row to db table
    public boolean addRow() {
        if (validateInput() == false) {
            return false;
        }
        
        delivery_id = Integer.parseInt(txt_delivery_id.getText());
        shipment_id = Integer.parseInt(txt_shipment_id.getText());
        employee_id = Integer.parseInt(txt_employee_id.getText());
        delivery_date = txt_delivery_date.getText();
        done = Integer.parseInt(txt_done.getText());
        
        Date u_delivery_date = Date.valueOf(delivery_date);
        
        boolean success = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO bd2_project.delivery VALUES(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, delivery_id);
            pst.setInt(2, shipment_id);
            pst.setInt(3, employee_id);
            pst.setDate(4, u_delivery_date);
            pst.setInt(5, done);     //should probably be setBoolean but it works since we only use 0 and 1
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                success = true;
            } else {
                success = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return success;
    }
    
    //update row in db
    public boolean UpdateRow() {
        if (validateInput() == false) {
            return false;
        }
        
        //copied from insert method
        delivery_id = Integer.parseInt(txt_delivery_id.getText());
        shipment_id = Integer.parseInt(txt_shipment_id.getText());
        employee_id = Integer.parseInt(txt_employee_id.getText());
        delivery_date = txt_delivery_date.getText();
        done = Integer.parseInt(txt_done.getText());
        
        Date u_delivery_date = Date.valueOf(delivery_date);
        
        boolean success = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE bd2_project.delivery SET shipment_id = ?, "
                    + "employee_id = ?, date = ?, done = ? "
                    + "WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, shipment_id);
            pst.setInt(2, employee_id);
            pst.setDate(3, u_delivery_date);
            pst.setInt(4, done);
            pst.setInt(5, delivery_id);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                success = true;
            } else {
                success = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
    
    //delete row in db
    public boolean deleteRow() {
        delivery_id = Integer.parseInt(txt_delivery_id.getText());
        
        boolean success = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM bd2_project.delivery WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, delivery_id);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                success = true;
            } else {
                success = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    //validate input in "Done" text field
    public boolean validateInput() {
        String input = txt_done.getText();
        if (input.equals("0") || input.equals("1")) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Please enter only 0 or 1 in 'Done' field");
        txt_done.setText("");
        return false;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_shipment_id = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_delivery_id = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_employee_id = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_done = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_delivery_date = new app.bolivia.swing.JCTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle4 = new rojerusan.RSMaterialButtonCircle();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDeliveries = new rojeru_san.complementos.RSTableMetro();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        txt_shipment_id.setBackground(new java.awt.Color(255, 102, 102));
        txt_shipment_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_shipment_id.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_shipment_id.setPlaceholder("Enter shipment ID");
        txt_shipment_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_shipment_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_shipment_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 380, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Shipment ID");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-cardboard-box-50.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 60, 50));

        txt_delivery_id.setBackground(new java.awt.Color(255, 102, 102));
        txt_delivery_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_delivery_id.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_delivery_id.setPlaceholder("Enter ID");
        txt_delivery_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_delivery_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_delivery_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 380, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-variable-50.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 60, 50));

        txt_employee_id.setBackground(new java.awt.Color(255, 102, 102));
        txt_employee_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_employee_id.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_employee_id.setPlaceholder("Enter employee iD");
        txt_employee_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_employee_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_employee_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 380, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Employee ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-person-50.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 60, 50));

        txt_done.setBackground(new java.awt.Color(255, 102, 102));
        txt_done.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_done.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_done.setPlaceholder("Enter 0 for not done, 1 for done");
        txt_done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_doneActionPerformed(evt);
            }
        });
        jPanel1.add(txt_done, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, 380, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-delivered-50.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 60, 50));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Done");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, -1, -1));

        txt_delivery_date.setBackground(new java.awt.Color(255, 102, 102));
        txt_delivery_date.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_delivery_date.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_delivery_date.setPlaceholder("Enter date");
        txt_delivery_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_delivery_dateActionPerformed(evt);
            }
        });
        jPanel1.add(txt_delivery_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 380, -1));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Date");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, -1, -1));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-calendar-50.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 60, 50));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(235, 155, 95));
        rSMaterialButtonCircle2.setText("DELETE");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 710, 130, 70));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(235, 155, 95));
        rSMaterialButtonCircle3.setText("ADD");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 710, 130, 70));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(235, 155, 95));
        rSMaterialButtonCircle4.setText("UPDATE");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 710, 130, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 60, 50));

        tableDeliveries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Shipment ID", "Employee ID", "Date", "Done"
            }
        ));
        tableDeliveries.setColorBackgoundHead(new java.awt.Color(235, 155, 95));
        tableDeliveries.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tableDeliveries.setColorSelBackgound(new java.awt.Color(235, 205, 145));
        tableDeliveries.setFont(new java.awt.Font("Yu Gothic UI", 0, 25)); // NOI18N
        tableDeliveries.setFuenteFilas(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableDeliveries.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tableDeliveries.setFuenteHead(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tableDeliveries.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableDeliveries.setRowHeight(40);
        tableDeliveries.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDeliveriesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDeliveries);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1120, 680));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 155, 95));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel3.setText("Manage Deliveries");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        jPanel5.setBackground(new java.awt.Color(235, 155, 95));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 320, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 1150, 830));

        setSize(new java.awt.Dimension(1724, 824));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_shipment_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_shipment_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_shipment_idActionPerformed

    private void txt_delivery_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_delivery_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_delivery_idActionPerformed

    private void txt_employee_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_employee_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employee_idActionPerformed

    private void txt_doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_doneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doneActionPerformed

    private void txt_delivery_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_delivery_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_delivery_dateActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
        if (UpdateRow()== true) {
            JOptionPane.showMessageDialog(this, "Row updated succesfully");
            clearGUItable();
            setDatabaseToGUITable();
        } else {
            JOptionPane.showMessageDialog(this, "Row update failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (deleteRow()== true) {
            JOptionPane.showMessageDialog(this, "Row deleted succesfully");
            clearGUItable();
            setDatabaseToGUITable();
        } else {
            JOptionPane.showMessageDialog(this, "Row deletion failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tableDeliveriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDeliveriesMouseClicked
        //set value from GUI table to textfields
        int rowNo = tableDeliveries.getSelectedRow();
        TableModel model = tableDeliveries.getModel();
        
        txt_delivery_id.setText(model.getValueAt(rowNo, 0).toString());
        txt_shipment_id.setText(model.getValueAt(rowNo, 1).toString());
        txt_employee_id.setText(model.getValueAt(rowNo, 2).toString());
        txt_delivery_date.setText(model.getValueAt(rowNo, 3).toString());
        txt_done.setText(model.getValueAt(rowNo, 4).toString());
        
    }//GEN-LAST:event_tableDeliveriesMouseClicked

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (addRow() == true) {
            JOptionPane.showMessageDialog(this, "Row inserted succesfully");
            clearGUItable();
            setDatabaseToGUITable();
        } else {
            JOptionPane.showMessageDialog(this, "Row insertion failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageDeliveries().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojeru_san.complementos.RSTableMetro tableDeliveries;
    private app.bolivia.swing.JCTextField txt_delivery_date;
    private app.bolivia.swing.JCTextField txt_delivery_id;
    private app.bolivia.swing.JCTextField txt_done;
    private app.bolivia.swing.JCTextField txt_employee_id;
    private app.bolivia.swing.JCTextField txt_shipment_id;
    // End of variables declaration//GEN-END:variables
}
