/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import com.itextpdf.text.Document;
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

/**
 *
 * @author User
 */
public class ViewShipments extends javax.swing.JFrame {

    /**
     * Creates new form ViewShipments
     */
    public ViewShipments() {
        initComponents();
        setDatabaseToGUITable();
    }
    
    //for jcombobox
    String done_filter = "Both";
    //for table
//    int shipment_id, sender_id, recipient_id, done;
//    float price, weight;
//    String start_date, term_date;
    DefaultTableModel model;
    
    //write data from database into the GUI table
    public void setDatabaseToGUITable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bd2_project.shipment");
            
            while(rs.next()) {
                //
                int _shipment_id = rs.getInt("id");     //column name in db
                int _sender_id = rs.getInt("sender_id");
                int _recipient_id = rs.getInt("recipient_id");
                float _price =rs.getFloat("price");
                float _weight = rs.getFloat("weight");
                Date _start_date = rs.getDate("start_date");
                Date _term_date = rs.getDate("term_date");
                int _done = rs.getInt("done");  //bool
                
                Object[] myObjArray = {_shipment_id, _sender_id, _recipient_id, _price,
                    _weight, _start_date, _term_date, _done};
                
                
                model = (DefaultTableModel) tableShipments.getModel();
                model.addRow(myObjArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearGUItable() {
        DefaultTableModel model = (DefaultTableModel) tableShipments.getModel();
        model.setRowCount(0);
    }
    
    //apply filters
    public void applyFilters() {
        //shipment started between dates
        Date u_date_start_min = date_start_min.getDatoFecha();
        Date u_date_start_max = date_start_max.getDatoFecha();
        //shipment ended between dates
        Date u_date_term_min = date_tern_min.getDatoFecha();
        Date u_date_term_max = date_term_max.getDatoFecha();
        int done = 2;   //will potentially be used in query

        //convert java.util date data.sql
        long l1 = u_date_start_min.getTime();
        long l2 = u_date_start_max.getTime();
        long l3 = u_date_term_min.getTime();
        long l4 = u_date_term_max.getTime();

        java.sql.Date s_date_start_min = new java.sql.Date(l1);
        java.sql.Date s_date_start_max = new java.sql.Date(l2);
        java.sql.Date s_date_term_min = new java.sql.Date(l3);
        java.sql.Date s_date_term_max = new java.sql.Date(l4);
        
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
                String sql = "SELECT * FROM bd2_project.shipment "
                        + "WHERE start_date BETWEEN ? AND ? "
                        + "AND term_date BETWEEN ? AND ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setDate(1, s_date_start_min);
                pst.setDate(2, s_date_start_max);
                pst.setDate(3, s_date_term_min);
                pst.setDate(4, s_date_term_max);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //
                    int _shipment_id = rs.getInt("id");     //column name in db
                    int _sender_id = rs.getInt("sender_id");
                    int _recipient_id = rs.getInt("recipient_id");
                    float _price = rs.getFloat("price");
                    float _weight = rs.getFloat("weight");
                    Date _start_date = rs.getDate("start_date");
                    Date _term_date = rs.getDate("term_date");
                    int _done = rs.getInt("done");  //bool

                    Object[] myObjArray = {_shipment_id, _sender_id, _recipient_id, _price,
                        _weight, _start_date, _term_date, _done};

                    model = (DefaultTableModel) tableShipments.getModel();
                    model.addRow(myObjArray);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {    //we filter for either "Done" or "Not Done"
            try {
                Connection con = DBConnection.getConnection();
                //we use done in query
                String sql = "SELECT * FROM bd2_project.shipment "
                        + "WHERE start_date BETWEEN ? AND ? "
                        + "AND term_date BETWEEN ? AND ? "
                        + "AND done = ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setDate(1, s_date_start_min);
                pst.setDate(2, s_date_start_max);
                pst.setDate(3, s_date_term_min);
                pst.setDate(4, s_date_term_max);
                pst.setInt(5, done);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //
                    int _shipment_id = rs.getInt("id");     //column name in db
                    int _sender_id = rs.getInt("sender_id");
                    int _recipient_id = rs.getInt("recipient_id");
                    float _price = rs.getFloat("price");
                    float _weight = rs.getFloat("weight");
                    Date _start_date = rs.getDate("start_date");
                    Date _term_date = rs.getDate("term_date");
                    int _done = rs.getInt("done");  //bool

                    Object[] myObjArray = {_shipment_id, _sender_id, _recipient_id, _price,
                        _weight, _start_date, _term_date, _done};

                    model = (DefaultTableModel) tableShipments.getModel();
                    model.addRow(myObjArray);
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
        date_term_max = new rojeru_san.componentes.RSDateChooser();
        jLabel34 = new javax.swing.JLabel();
        date_tern_min = new rojeru_san.componentes.RSDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        panel_table = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableShipments = new rojeru_san.complementos.RSTableMetro();

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
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 280, 5));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 34)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel10.setText("View Shipments");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Shipment status");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, -1, -1));

        date_start_min.setColorBackground(new java.awt.Color(102, 102, 255));
        date_start_min.setColorForeground(new java.awt.Color(102, 102, 255));
        date_start_min.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_start_min.setPlaceholder("Select date");
        jPanel1.add(date_start_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Start date begin");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        date_start_max.setColorBackground(new java.awt.Color(102, 102, 255));
        date_start_max.setColorForeground(new java.awt.Color(102, 102, 255));
        date_start_max.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_start_max.setPlaceholder("Select date");
        jPanel1.add(date_start_max, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        combo_shipment_done.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        combo_shipment_done.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Both", "Done", "Not Done" }));
        combo_shipment_done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_shipment_doneActionPerformed(evt);
            }
        });
        jPanel1.add(combo_shipment_done, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, 140, 40));

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Start date end");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(102, 102, 255));
        rSMaterialButtonCircle1.setText("Filter");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 220, 70));

        date_term_max.setColorBackground(new java.awt.Color(102, 102, 255));
        date_term_max.setColorForeground(new java.awt.Color(102, 102, 255));
        date_term_max.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_term_max.setPlaceholder("Select date");
        jPanel1.add(date_term_max, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Term date end");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, -1));

        date_tern_min.setColorBackground(new java.awt.Color(102, 102, 255));
        date_tern_min.setColorForeground(new java.awt.Color(102, 102, 255));
        date_tern_min.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_tern_min.setPlaceholder("Select date");
        jPanel1.add(date_tern_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Term date begin");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

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

        tableShipments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Sender ID", "Recipient ID", "Price", "Weight", "Start date", "Term date", "Done"
            }
        ));
        tableShipments.setColorBackgoundHead(new java.awt.Color(235, 155, 95));
        tableShipments.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tableShipments.setColorSelBackgound(new java.awt.Color(235, 205, 145));
        tableShipments.setFont(new java.awt.Font("Yu Gothic UI", 0, 25)); // NOI18N
        tableShipments.setFuenteFilas(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableShipments.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tableShipments.setFuenteHead(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tableShipments.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableShipments.setRowHeight(40);
        tableShipments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableShipmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableShipments);

        panel_table.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1560, 620));

        getContentPane().add(panel_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 1600, 660));

        setSize(new java.awt.Dimension(1600, 900));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableShipmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShipmentsMouseClicked
        
    }//GEN-LAST:event_tableShipmentsMouseClicked

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
            OutputStream outputStream = new FileOutputStream(path + fileSeparator + "shipment report.pdf");
            PdfWriter.getInstance(doc, outputStream);
            doc.open();
            //generate table
            PdfPTable tbl = new PdfPTable(8);
            tbl.addCell("ID");
            tbl.addCell("Sender ID");
            tbl.addCell("Recipient ID");
            tbl.addCell("Price");
            tbl.addCell("Wieght");
            tbl.addCell("Start date");
            tbl.addCell("Term date");
            tbl.addCell("Done");
            for(int i = 0; i < tableShipments.getRowCount(); i++) {
                String id = tableShipments.getValueAt(i, 0).toString();
                String senderID = tableShipments.getValueAt(i, 1).toString();
                String recipientID = tableShipments.getValueAt(i, 2).toString();
                String price = tableShipments.getValueAt(i, 3).toString();
                String weight = tableShipments.getValueAt(i, 4).toString();
                String startDate = tableShipments.getValueAt(i, 5).toString();
                String termDate = tableShipments.getValueAt(i, 6).toString();
                String done = tableShipments.getValueAt(i, 7).toString();
                tbl.addCell(id);
                tbl.addCell(senderID);
                tbl.addCell(recipientID);
                tbl.addCell(price);
                tbl.addCell(weight);
                tbl.addCell(startDate);
                tbl.addCell(termDate);
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
            java.util.logging.Logger.getLogger(ViewShipments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewShipments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewShipments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewShipments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewShipments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_shipment_done;
    private rojeru_san.componentes.RSDateChooser date_start_max;
    private rojeru_san.componentes.RSDateChooser date_start_min;
    private rojeru_san.componentes.RSDateChooser date_term_max;
    private rojeru_san.componentes.RSDateChooser date_tern_min;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_table;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojeru_san.complementos.RSTableMetro tableShipments;
    // End of variables declaration//GEN-END:variables
}
