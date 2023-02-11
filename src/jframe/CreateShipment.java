/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CreateShipment extends javax.swing.JFrame {

    /**
     * Creates new form CreateShipment
     */
    public CreateShipment() {
        initComponents();
    }

    //get sender details from db
    public void getSenderDetails() {
        int client_id = Integer.parseInt(txt_sender_id.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM bd2_project.client "
                    + "WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, client_id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_send_cnp.setText(rs.getString("cnp"));
                lbl_send_first_name.setText(rs.getString("first_name"));
                lbl_send_last_name.setText(rs.getString("last_name"));
                lbl_send_city.setText(rs.getString("city"));
                lbl_send_address.setText(rs.getString("address"));
                lbl_sender_error.setText("");
            } else {
                lbl_send_cnp.setText("");
                lbl_send_first_name.setText("");
                lbl_send_last_name.setText("");
                lbl_send_city.setText("");
                lbl_send_address.setText("");
                lbl_sender_error.setText("Client with given ID not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get recipient details from db
    public void getRecipientDetails() {
        int client_id = Integer.parseInt(txt_recipient_id.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM bd2_project.client "
                    + "WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, client_id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_recp_cnp.setText(rs.getString("cnp"));
                lbl_recp_first_name.setText(rs.getString("first_name"));
                lbl_recp_last_name.setText(rs.getString("last_name"));
                lbl_recp_city.setText(rs.getString("city"));
                lbl_recp_address.setText(rs.getString("address"));
                lbl_recipient_error.setText("");
            } else {
                lbl_recp_cnp.setText("");
                lbl_recp_first_name.setText("");
                lbl_recp_last_name.setText("");
                lbl_recp_city.setText("");
                lbl_recp_address.setText("");
                lbl_recipient_error.setText("Client with given ID not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for table
    int sender_id, recipient_id, done;
    float price, weight;
    //DefaultTableModel model;

    //insert row into shipment table in db
    public boolean addRow() {
        //add some validation

        //shipment_id = Integer.parseInt(txt_shipment_id.getText());    //auto
        sender_id = Integer.parseInt(txt_sender_id.getText());
        recipient_id = Integer.parseInt(txt_recipient_id.getText());
        price = Float.parseFloat(txt_price.getText());
        weight = Float.parseFloat(txt_weight.getText());
        Date u_start_date = date_start.getDatoFecha();
        Date u_end_date = date_end.getDatoFecha();
        done = 0;

        //convert java.util date data.sql
        long l1 = u_start_date.getTime();
        long l2 = u_start_date.getTime();

        java.sql.Date s_start_date = new java.sql.Date(l1);
        java.sql.Date s_end_date = new java.sql.Date(l2);

        //in this insert we will let the db give an id automatically
        boolean success = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO bd2_project.shipment "
                    + "(sender_id, recipient_id, price, weight, start_date, term_date, done) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, sender_id);
            pst.setInt(2, recipient_id);
            pst.setFloat(3, price);
            pst.setFloat(4, weight);
            pst.setDate(5, s_start_date);
            pst.setDate(6, s_end_date);
            pst.setInt(7, done);    //should probably be setBoolean but it works since we only use 0 and 1

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

    //used in field validation in ID text fields //checks for integers //from internet
    public static boolean isPositiveInt(String strNum) {
        int d;
        if (strNum == null) {
            return false;
        }
        //attempt to parse string into int
        try {
            d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        } catch (Exception e) { //me
            return false;
        }
        //check if positive
        if (d > 0) {
            return true;
        } else {
            return false;
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

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_recp_cnp = new javax.swing.JLabel();
        lbl_recp_first_name = new javax.swing.JLabel();
        lbl_recp_last_name = new javax.swing.JLabel();
        lbl_recp_city = new javax.swing.JLabel();
        lbl_recp_address = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_recipient_error = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbl_send_cnp = new javax.swing.JLabel();
        lbl_send_first_name = new javax.swing.JLabel();
        lbl_send_last_name = new javax.swing.JLabel();
        lbl_send_city = new javax.swing.JLabel();
        lbl_send_address = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lbl_sender_error = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_sender_id = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_recipient_id = new app.bolivia.swing.JCTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_price = new app.bolivia.swing.JCTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_weight = new app.bolivia.swing.JCTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        date_end = new rojeru_san.componentes.RSDateChooser();
        date_start = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("CNP");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 240, 5));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Address");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("First name");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Last name");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("City");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        lbl_recp_cnp.setBackground(new java.awt.Color(255, 255, 255));
        lbl_recp_cnp.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_recp_cnp.setForeground(new java.awt.Color(255, 255, 255));
        lbl_recp_cnp.setText("CNP");
        jPanel1.add(lbl_recp_cnp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        lbl_recp_first_name.setBackground(new java.awt.Color(255, 255, 255));
        lbl_recp_first_name.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_recp_first_name.setForeground(new java.awt.Color(255, 255, 255));
        lbl_recp_first_name.setText("First name");
        jPanel1.add(lbl_recp_first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        lbl_recp_last_name.setBackground(new java.awt.Color(255, 255, 255));
        lbl_recp_last_name.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_recp_last_name.setForeground(new java.awt.Color(255, 255, 255));
        lbl_recp_last_name.setText("Last name");
        jPanel1.add(lbl_recp_last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        lbl_recp_city.setBackground(new java.awt.Color(255, 255, 255));
        lbl_recp_city.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_recp_city.setForeground(new java.awt.Color(255, 255, 255));
        lbl_recp_city.setText("City");
        jPanel1.add(lbl_recp_city, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, -1));

        lbl_recp_address.setBackground(new java.awt.Color(255, 255, 255));
        lbl_recp_address.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_recp_address.setForeground(new java.awt.Color(255, 255, 255));
        lbl_recp_address.setText("Address");
        jPanel1.add(lbl_recp_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 28)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Recipient Details");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        lbl_recipient_error.setBackground(new java.awt.Color(255, 255, 255));
        lbl_recipient_error.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        lbl_recipient_error.setForeground(new java.awt.Color(255, 255, 0));
        jPanel1.add(lbl_recipient_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 280, 30));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 460, 810));

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("CNP");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

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

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 28)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Sender Details");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 220, 5));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("First name");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Last name");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("City");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        lbl_send_cnp.setBackground(new java.awt.Color(255, 255, 255));
        lbl_send_cnp.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_send_cnp.setForeground(new java.awt.Color(255, 255, 255));
        lbl_send_cnp.setText("CNP");
        jPanel4.add(lbl_send_cnp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        lbl_send_first_name.setBackground(new java.awt.Color(255, 255, 255));
        lbl_send_first_name.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_send_first_name.setForeground(new java.awt.Color(255, 255, 255));
        lbl_send_first_name.setText("First name");
        jPanel4.add(lbl_send_first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        lbl_send_last_name.setBackground(new java.awt.Color(255, 255, 255));
        lbl_send_last_name.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_send_last_name.setForeground(new java.awt.Color(255, 255, 255));
        lbl_send_last_name.setText("Last name");
        jPanel4.add(lbl_send_last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        lbl_send_city.setBackground(new java.awt.Color(255, 255, 255));
        lbl_send_city.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_send_city.setForeground(new java.awt.Color(255, 255, 255));
        lbl_send_city.setText("City");
        jPanel4.add(lbl_send_city, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, -1));

        lbl_send_address.setBackground(new java.awt.Color(255, 255, 255));
        lbl_send_address.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lbl_send_address.setForeground(new java.awt.Color(255, 255, 255));
        lbl_send_address.setText("Address");
        jPanel4.add(lbl_send_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Address");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        lbl_sender_error.setBackground(new java.awt.Color(255, 255, 255));
        lbl_sender_error.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        lbl_sender_error.setForeground(new java.awt.Color(255, 255, 0));
        jPanel4.add(lbl_sender_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 290, 30));

        panel_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 810));

        jPanel2.setBackground(new java.awt.Color(235, 155, 95));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 32)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Shipment Details");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 270, 5));

        jPanel8.setBackground(new java.awt.Color(255, 51, 51));

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

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, -1, 50));

        txt_sender_id.setBackground(new java.awt.Color(235, 155, 95));
        txt_sender_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_sender_id.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_sender_id.setPlaceholder("Enter sender id");
        txt_sender_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_sender_idFocusLost(evt);
            }
        });
        txt_sender_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sender_idActionPerformed(evt);
            }
        });
        jPanel2.add(txt_sender_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 380, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Sender ID");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-person-50.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 60, 50));

        txt_recipient_id.setBackground(new java.awt.Color(235, 155, 95));
        txt_recipient_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_recipient_id.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_recipient_id.setPlaceholder("Enter recipient id");
        txt_recipient_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_recipient_idFocusLost(evt);
            }
        });
        txt_recipient_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_recipient_idActionPerformed(evt);
            }
        });
        jPanel2.add(txt_recipient_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 380, -1));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Recipient ID");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-person-50.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 60, 50));

        txt_price.setBackground(new java.awt.Color(235, 155, 95));
        txt_price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_price.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_price.setPlaceholder("Enter price, use dot (.) as separator");
        txt_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_priceActionPerformed(evt);
            }
        });
        jPanel2.add(txt_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 380, -1));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Price");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-dollar-coin-50.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 60, 50));

        txt_weight.setBackground(new java.awt.Color(235, 155, 95));
        txt_weight.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_weight.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        txt_weight.setPlaceholder("Enter weight in Kg, use dot (.) as separator");
        txt_weight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_weightActionPerformed(evt);
            }
        });
        jPanel2.add(txt_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 380, -1));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Wieght");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, -1));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-weight-kg-50.png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 60, 50));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Start date");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-calendar-50.png"))); // NOI18N
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 60, 50));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Term date");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, -1, -1));

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-calendar-50.png"))); // NOI18N
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 60, 50));

        date_end.setColorBackground(new java.awt.Color(255, 51, 51));
        date_end.setColorForeground(new java.awt.Color(255, 51, 51));
        date_end.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_end.setPlaceholder("Select term date");
        jPanel2.add(date_end, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, -1));

        date_start.setColorBackground(new java.awt.Color(255, 51, 51));
        date_start.setColorForeground(new java.awt.Color(255, 51, 51));
        date_start.setFuente(new java.awt.Font("Microsoft YaHei UI", 1, 17)); // NOI18N
        date_start.setPlaceholder("Select start date");
        jPanel2.add(date_start, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, -1));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle3.setText("ADD SHIPMENT");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 700, 210, 70));

        panel_main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 490, 810));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 810));

        setSize(new java.awt.Dimension(1427, 842));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void txt_sender_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sender_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sender_idActionPerformed

    private void txt_recipient_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_recipient_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recipient_idActionPerformed

    private void txt_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_priceActionPerformed

    private void txt_weightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_weightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_weightActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (addRow() == true) {
            JOptionPane.showMessageDialog(this, "Row inserted succesfully");
        } else {
            JOptionPane.showMessageDialog(this, "Row insertion failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void txt_sender_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_sender_idFocusLost
        //if string is not empty and contains a number
        if (isPositiveInt(txt_sender_id.getText())) {
            if(!txt_sender_id.getText().equals("")) {
                getSenderDetails();
            }
        } else {
            txt_sender_id.setText("");
            //JOptionPane.showMessageDialog(this, "Only enter numeric values");
        }
    }//GEN-LAST:event_txt_sender_idFocusLost

    private void txt_recipient_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_recipient_idFocusLost
        //if string is not empty and contains a number
        if (isPositiveInt(txt_recipient_id.getText())) {
            if(!txt_recipient_id.getText().equals("")) {
                getRecipientDetails();
            }
        } else {
            txt_recipient_id.setText("");
            //JOptionPane.showMessageDialog(this, "Only enter numeric values");
        }
    }//GEN-LAST:event_txt_recipient_idFocusLost

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
            java.util.logging.Logger.getLogger(CreateShipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateShipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateShipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateShipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateShipment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_end;
    private rojeru_san.componentes.RSDateChooser date_start;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lbl_recipient_error;
    private javax.swing.JLabel lbl_recp_address;
    private javax.swing.JLabel lbl_recp_city;
    private javax.swing.JLabel lbl_recp_cnp;
    private javax.swing.JLabel lbl_recp_first_name;
    private javax.swing.JLabel lbl_recp_last_name;
    private javax.swing.JLabel lbl_send_address;
    private javax.swing.JLabel lbl_send_city;
    private javax.swing.JLabel lbl_send_cnp;
    private javax.swing.JLabel lbl_send_first_name;
    private javax.swing.JLabel lbl_send_last_name;
    private javax.swing.JLabel lbl_sender_error;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private app.bolivia.swing.JCTextField txt_price;
    private app.bolivia.swing.JCTextField txt_recipient_id;
    private app.bolivia.swing.JCTextField txt_sender_id;
    private app.bolivia.swing.JCTextField txt_weight;
    // End of variables declaration//GEN-END:variables
}
