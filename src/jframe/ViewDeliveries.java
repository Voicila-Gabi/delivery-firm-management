/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import com.itextpdf.text.Document;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class ViewDeliveries extends javax.swing.JFrame {

    /**
     * Creates new form ViewShipments
     */
    public ViewDeliveries() {
        initComponents();
        setDatabaseToGUITable();
    }
    
    //for jcombobox
    String done_filter = "Both";
    //for employee ID field filter
    boolean use_employee_id_filter = false;
    int employee_id_filter = -1;
    
    //for table
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
                java.sql.Date _delivery_date = rs.getDate("date");
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
    
    //apply filters
    public void applyFilters() {
        //delivery takes place between dates
        Date u_date_start_min = date_start_min.getDatoFecha();
        Date u_date_start_max = date_start_max.getDatoFecha();
        int done = 2;   //will potentially be used in query

        //convert java.util date data.sql
        long l1 = u_date_start_min.getTime();
        long l2 = u_date_start_max.getTime();

        java.sql.Date s_date_start_min = new java.sql.Date(l1);
        java.sql.Date s_date_start_max = new java.sql.Date(l2);
        
        switch(done_filter) {   //uses String.equals for comparison
            case "Both":
                done = 2;
                break;
            case "Done":
                done = 1;
                break;
            case "Not Done":
                done = 0;
                break;
            default:
                System.out.println("Something went wrong with done_filter");
                System.exit(1);
        }
        
        if (done == 2) {
            try {
                Connection con = DBConnection.getConnection();
                //we don't make any checks for done
                String sql = "SELECT * FROM bd2_project.delivery "
                        + "WHERE date BETWEEN ? AND ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setDate(1, s_date_start_min);
                pst.setDate(2, s_date_start_max);
//                pst.setDate(3, s_date_term_min);
//                pst.setDate(4, s_date_term_max);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //
                    int _delivery_id = rs.getInt("id");
                    int _shipment_id = rs.getInt("shipment_id");     //column name in db
                    int _employee_id = rs.getInt("employee_id");
                    Date _delivery_date = rs.getDate("date");
                    int _done = rs.getInt("done");  //bool

                    Object[] myObjArray = {_delivery_id, _shipment_id, _employee_id,
                        _delivery_date, _done};

                    model = (DefaultTableModel) tableDeliveries.getModel();
                    if (use_employee_id_filter == false) {
                        model.addRow(myObjArray);
                    } else {
                        if (_employee_id == employee_id_filter) {
                            model.addRow(myObjArray);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {    //we filter for either "Done" or "Not Done"
            try {
                Connection con = DBConnection.getConnection();
                //we don't make any checks for done
                String sql = "SELECT * FROM bd2_project.delivery "
                        + "WHERE date BETWEEN ? AND ? "
                        + "AND done = ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setDate(1, s_date_start_min);
                pst.setDate(2, s_date_start_max);
                pst.setInt(3, done);
//                pst.setDate(3, s_date_term_min);
//                pst.setDate(4, s_date_term_max);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //
                    int _delivery_id = rs.getInt("id");
                    int _shipment_id = rs.getInt("shipment_id");     //column name in db
                    int _employee_id = rs.getInt("employee_id");
                    Date _delivery_date = rs.getDate("date");
                    int _done = rs.getInt("done");  //bool

                    Object[] myObjArray = {_delivery_id, _shipment_id, _employee_id,
                        _delivery_date, _done};

                    model = (DefaultTableModel) tableDeliveries.getModel();
                    if (use_employee_id_filter == false) {
                        model.addRow(myObjArray);
                    } else {
                        if (_employee_id == employee_id_filter) {
                            model.addRow(myObjArray);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        date_start_min = new rojeru_san.componentes.RSDateChooser();
        jLabel30 = new javax.swing.JLabel();
        date_start_max = new rojeru_san.componentes.RSDateChooser();
        combo_shipment_done = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_employee_id = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        checkbox_employee_id = new javax.swing.JCheckBox();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        panel_table = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDeliveries = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 260, 5));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 34)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel10.setText("View Deliveries");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Delivery status");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, -1));

        date_start_min.setColorBackground(new java.awt.Color(102, 102, 255));
        date_start_min.setColorForeground(new java.awt.Color(102, 102, 255));
        date_start_min.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_start_min.setPlaceholder("Select date");
        jPanel1.add(date_start_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Date begin");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        date_start_max.setColorBackground(new java.awt.Color(102, 102, 255));
        date_start_max.setColorForeground(new java.awt.Color(102, 102, 255));
        date_start_max.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_start_max.setPlaceholder("Select date");
        jPanel1.add(date_start_max, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        combo_shipment_done.setBackground(new java.awt.Color(240, 240, 240));
        combo_shipment_done.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        combo_shipment_done.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Both", "Done", "Not Done" }));
        combo_shipment_done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_shipment_doneActionPerformed(evt);
            }
        });
        jPanel1.add(combo_shipment_done, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 140, 40));

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Date end");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(102, 102, 255));
        rSMaterialButtonCircle1.setText("Filter");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 220, 70));

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jPanel8.setBackground(new java.awt.Color(102, 102, 255));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(24, 24, 24))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 0, -1, 50));

        txt_employee_id.setBackground(new java.awt.Color(255, 102, 102));
        txt_employee_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_employee_id.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_employee_id.setPlaceholder("Enter ID");
        txt_employee_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_employee_idFocusLost(evt);
            }
        });
        txt_employee_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_employee_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_employee_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 180, 120, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Employee ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 150, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 170, 60, 50));

        checkbox_employee_id.setBackground(new java.awt.Color(255, 51, 51));
        checkbox_employee_id.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        checkbox_employee_id.setForeground(new java.awt.Color(255, 255, 255));
        checkbox_employee_id.setText("Use this filter");
        checkbox_employee_id.setToolTipText("");
        checkbox_employee_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_employee_idActionPerformed(evt);
            }
        });
        jPanel1.add(checkbox_employee_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 180, -1, -1));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(102, 102, 255));
        rSMaterialButtonCircle2.setText("Generate PDF");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 150, 190, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 240));

        panel_table.setBackground(new java.awt.Color(255, 255, 255));
        panel_table.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane2.setViewportView(tableDeliveries);

        panel_table.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1560, 620));

        getContentPane().add(panel_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 1600, 660));

        setSize(new java.awt.Dimension(1600, 900));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_shipment_doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_shipment_doneActionPerformed
        // TODO add your handling code here:
        done_filter = combo_shipment_done.getSelectedItem().toString();
    }//GEN-LAST:event_combo_shipment_doneActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        clearGUItable();
        applyFilters();
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txt_employee_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_employee_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employee_idActionPerformed

    private void tableDeliveriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDeliveriesMouseClicked
        //set value from GUI table to textfields
        int rowNo = tableDeliveries.getSelectedRow();
        TableModel model = tableDeliveries.getModel();

    }//GEN-LAST:event_tableDeliveriesMouseClicked

    private void checkbox_employee_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_employee_idActionPerformed
        // TODO add your handling code here:
        use_employee_id_filter = checkbox_employee_id.isSelected();
    }//GEN-LAST:event_checkbox_employee_idActionPerformed

    private void txt_employee_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_employee_idFocusLost
        // TODO add your handling code here:
        if (! txt_employee_id.getText().isEmpty()) {
            employee_id_filter = Integer.parseInt(txt_employee_id.getText());
        }
    }//GEN-LAST:event_txt_employee_idFocusLost

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        // TODO add your handling code here:
        String path = "";
        JFileChooser myFileChooser = new JFileChooser();
        myFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = myFileChooser.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = myFileChooser.getSelectedFile().getPath();
        }
        Document doc = new Document();  //itext pdf
        try {
            String fileSeparator = FileSystems.getDefault().getSeparator(); //so it works on windows and linux
            OutputStream outputStream = new FileOutputStream(path + fileSeparator + "delivery report.pdf");
            PdfWriter.getInstance(doc, outputStream);
            doc.open();
            //generate table  
            PdfPTable tbl = new PdfPTable(5);
            tbl.addCell("ID");
            tbl.addCell("Shipment ID");
            tbl.addCell("Employee ID");
            tbl.addCell("Date");
            tbl.addCell("Done");
            for(int i = 0; i < tableDeliveries.getRowCount(); i++) {
                String id = tableDeliveries.getValueAt(i, 0).toString();
                String shipmentID = tableDeliveries.getValueAt(i, 1).toString();
                String employeeID = tableDeliveries.getValueAt(i, 2).toString();
                String date = tableDeliveries.getValueAt(i, 3).toString();
                String done = tableDeliveries.getValueAt(i, 4).toString();
                tbl.addCell(id);
                tbl.addCell(shipmentID);
                tbl.addCell(employeeID);
                tbl.addCell(date);
                tbl.addCell(done);
            }
            //end table
            //generate timestamp
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String timestampString = dtf.format(now);
            //end generate timestamp
            //generate paragraph
            Paragraph p1 = new Paragraph("Deliveries report created at " + timestampString);
            Paragraph p2 = new Paragraph("\n");
            //end generate paragraph
            //actually add element to pdf
            doc.add(p1);
            doc.add(p2);
            doc.add(tbl);
            //
            doc.close();
            outputStream.close();
            JOptionPane.showMessageDialog(this, "PDF generated");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "PDF could not be generated");
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDeliveries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDeliveries().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox_employee_id;
    private javax.swing.JComboBox<String> combo_shipment_done;
    private rojeru_san.componentes.RSDateChooser date_start_max;
    private rojeru_san.componentes.RSDateChooser date_start_min;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel_table;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojeru_san.complementos.RSTableMetro tableDeliveries;
    private app.bolivia.swing.JCTextField txt_employee_id;
    // End of variables declaration//GEN-END:variables
}
