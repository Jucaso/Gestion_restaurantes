/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrestaurantesFinal;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Clases.GestionRestaurantes;
import Clases.Producto;
import Clases.Insumos;
import java.awt.Color;

/**
 *
 * @author danie
 */
public class AdmonRes extends javax.swing.JFrame {

    Vector productos;
    Vector insumos;
    /**
     * Creates new form InterfacePedidos
     */
    public AdmonRes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegistrarProductosB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        productosArea = new javax.swing.JTextArea();
        ModificarProductosB = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        insumosArea = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        SalirB = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RegistrarProductosB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        RegistrarProductosB.setForeground(new java.awt.Color(255, 255, 255));
        RegistrarProductosB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        RegistrarProductosB.setText("Registrar productos preparados");
        RegistrarProductosB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        RegistrarProductosB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegistrarProductosB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegistrarProductosB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        RegistrarProductosB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        RegistrarProductosB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        RegistrarProductosB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarProductosBActionPerformed(evt);
            }
        });
        getContentPane().add(RegistrarProductosB, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 79, 210, 55));

        productosArea.setEditable(false);
        productosArea.setBackground(new java.awt.Color(204, 204, 204));
        productosArea.setColumns(20);
        productosArea.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        productosArea.setRows(5);
        jScrollPane1.setViewportView(productosArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 79, 271, 277));

        ModificarProductosB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModificarProductosB.setForeground(new java.awt.Color(255, 255, 255));
        ModificarProductosB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        ModificarProductosB.setText("Modificar productos preparados");
        ModificarProductosB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        ModificarProductosB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarProductosB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarProductosB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        ModificarProductosB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        ModificarProductosB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        ModificarProductosB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarProductosBActionPerformed(evt);
            }
        });
        getContentPane().add(ModificarProductosB, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 140, 210, 55));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton4.setText("Mostrar Productos");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 140, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton3.setText("Añadir Insumos");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 79, 160, 48));

        insumosArea.setEditable(false);
        insumosArea.setBackground(new java.awt.Color(204, 204, 204));
        insumosArea.setColumns(20);
        insumosArea.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        insumosArea.setRows(5);
        jScrollPane2.setViewportView(insumosArea);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 79, 271, 272));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton5.setText("Mostrar Insumos");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 360, 140, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton1.setText("Registrar productos no preparados");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 201, 210, 55));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton6.setText("Añadir insumos existentes");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 138, 160, 50));

        SalirB.setBackground(new java.awt.Color(153, 153, 153));
        SalirB.setForeground(new java.awt.Color(153, 153, 153));
        SalirB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/cerrar.png"))); // NOI18N
        SalirB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        SalirB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalirB.setFocusable(false);
        SalirB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalirBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalirBMouseExited(evt);
            }
        });
        SalirB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirBActionPerformed(evt);
            }
        });
        getContentPane().add(SalirB, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 380, 30, 30));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/AdmonRes.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegistrarProductosBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarProductosBActionPerformed
        // TODO add your handling code here:
        RegistrarProducto r1 = new RegistrarProducto();
        //RegistrarProductosB.setEnabled(false);
        r1.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_RegistrarProductosBActionPerformed

    private void ModificarProductosBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarProductosBActionPerformed
        // TODO add your handling code here:
        ModificarProducto m1 = new ModificarProducto();
        m1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ModificarProductosBActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //GestionRestaurantes.conexion();
        productos = new Vector();
        productosArea.setText("");
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM producto order by id;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }        
        
        try {
            while(GestionRestaurantes.rs.next()){
                if(GestionRestaurantes.rs.getInt(4) == login.auxIdRes){
                    Producto pro;
                    pro = new Producto(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2),
                    GestionRestaurantes.rs.getInt(3));
                    productos.add(pro);
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
        }
        
        for(int i=0;i<productos.size();i++){
            productosArea.setText(productosArea.getText()+
            "\nCódigo producto: " + ((Producto)productos.elementAt(i)).verProId()+
            "\nNombre: " + ((Producto)productos.elementAt(i)).verProNombre() +
            "\nValor venta: " + ((Producto)productos.elementAt(i)).verProPrecio() +
            "\n______________________________\n"
            );
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //GestionRestaurantes.conexion();
        insumos = new Vector();
        insumosArea.setText("");
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM insumos order by id;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }        
        
        try {
            while(GestionRestaurantes.rs.next()){
                if(GestionRestaurantes.rs.getInt(5) == login.auxIdRes){
                    Insumos ins;
                    ins = new Insumos(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2),
                    GestionRestaurantes.rs.getString(3), GestionRestaurantes.rs.getInt(4));
                    insumos.add(ins);
                }
                    
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
        }
        
        for(int i=0;i<insumos.size();i++){
            insumosArea.setText(insumosArea.getText()+
            "\nCódigo insumo: " + ((Insumos)insumos.elementAt(i)).verInsId()+
            "\nNombre: " + ((Insumos)insumos.elementAt(i)).verInsNombre() +
            "\nCosto adquisición: " + ((Insumos)insumos.elementAt(i)).verInsCosto() +
            "\nCantidad disponible (unidad): " + ((Insumos)insumos.elementAt(i)).verInsCantidad() +
            "\n______________________________\n"
            );
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        añadirIns ai = new añadirIns();
        ai.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        regPnoPreparado rpnp = new regPnoPreparado();
        rpnp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        añadirInsEx aie = new añadirInsEx();
        aie.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void SalirBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBMouseEntered
        // TODO add your handling code here:
        SalirB.setBackground(new Color (204,204,204));
    }//GEN-LAST:event_SalirBMouseEntered

    private void SalirBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBMouseExited
        // TODO add your handling code here:
        SalirB.setBackground(new Color (153,153,153));
    }//GEN-LAST:event_SalirBMouseExited

    private void SalirBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBActionPerformed
        // TODO add your handling code here:
        this.dispose();
        login log;
        try {
            log = new login();
            log.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(gestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SalirBActionPerformed

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
            java.util.logging.Logger.getLogger(AdmonRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdmonRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdmonRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdmonRes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdmonRes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton ModificarProductosB;
    private javax.swing.JButton RegistrarProductosB;
    private javax.swing.JButton SalirB;
    private javax.swing.JTextArea insumosArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea productosArea;
    // End of variables declaration//GEN-END:variables
}
