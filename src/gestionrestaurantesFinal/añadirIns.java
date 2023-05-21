/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrestaurantesFinal;

import Clases.GestionRestaurantes;
import javax.swing.JOptionPane;

/**
 *
 * @author Jucaso
 */
public class añadirIns extends javax.swing.JFrame {

    int idGenerator = 0;
    public añadirIns() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        asignarID();
        System.out.println(idGenerator);
    }
    
    public void asignarID(){
        try {
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM insumos order by id;"); 
        } catch (Exception e) {
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                idGenerator = GestionRestaurantes.rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println("Error bro.. " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        nombreField = new javax.swing.JTextField();
        costoField = new javax.swing.JTextField();
        cantidadField = new javax.swing.JTextField();
        cancelB = new javax.swing.JButton();
        guardarB = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(390, 170));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 356, 10));
        getContentPane().add(nombreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 45, 100, -1));
        getContentPane().add(costoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 105, 100, -1));
        getContentPane().add(cantidadField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 75, 100, -1));

        cancelB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cancelB.setForeground(new java.awt.Color(255, 255, 255));
        cancelB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        cancelB.setText("Volver");
        cancelB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        cancelB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        cancelB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cancelB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBActionPerformed(evt);
            }
        });
        getContentPane().add(cancelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 79, 23));

        guardarB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        guardarB.setForeground(new java.awt.Color(255, 255, 255));
        guardarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        guardarB.setText("Guardar");
        guardarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        guardarB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        guardarB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        guardarB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        guardarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBActionPerformed(evt);
            }
        });
        getContentPane().add(guardarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 79, 23));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/AñadirI.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBActionPerformed
        int check = 0;
        if(!nombreField.getText().isEmpty() && !CrearUsuario.isNumeric(nombreField.getText())){
            if(!costoField.getText().isEmpty() && CrearUsuario.isNumeric(costoField.getText())){
                if(!cantidadField.getText().isEmpty() && CrearUsuario.isNumeric(cantidadField.getText())){
                    check = 1;
                }
                else{
                    JOptionPane.showMessageDialog(null, "CANTIDAD NO VÁLIDA", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "COSTO NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "NOMBRE NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
        }
        
        if(check == 1){
            String auxUpdate = "INSERT INTO insumos "
                + "values(" + idGenerator + "," + "'" + nombreField.getText() + "'" 
                + "," + "'" + costoField.getText() + "'" + "," + cantidadField.getText() + "," + login.auxIdRes +");";

            try {
                GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                GestionRestaurantes.s.executeUpdate(auxUpdate); 
            } catch (Exception e) {
                System.out.println("Error bro.. " + e);
            }
            JOptionPane.showMessageDialog(null, "INSUMO REGISTRADO CON ÉXITO", "AVISO", JOptionPane.PLAIN_MESSAGE, login.CheckIcon);
            this.dispose();
        }
        
    }//GEN-LAST:event_guardarBActionPerformed

    private void cancelBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBActionPerformed
        this.dispose();
        AdmonRes a1 = new AdmonRes();
        a1.setVisible(true);
    }//GEN-LAST:event_cancelBActionPerformed

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
            java.util.logging.Logger.getLogger(añadirIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(añadirIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(añadirIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(añadirIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new añadirIns().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton cancelB;
    private javax.swing.JTextField cantidadField;
    private javax.swing.JTextField costoField;
    private javax.swing.JButton guardarB;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombreField;
    // End of variables declaration//GEN-END:variables
}
