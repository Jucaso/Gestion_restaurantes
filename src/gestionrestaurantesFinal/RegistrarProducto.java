/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrestaurantesFinal;

import Clases.GestionRestaurantes;
import Clases.Requiere;
import Clases.Producto;
import Clases.Insumos;
import static gestionrestaurantesFinal.CrearUsuario.isNumeric;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class RegistrarProducto extends javax.swing.JFrame {

    Vector insumosVector = new Vector(), reqAux = new Vector();
    int newIDproducto = 0, newIDreq = 0, acc = 1, index, repetido=0;
    
    public RegistrarProducto() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        insumoBox.setEnabled(false);
        añadirI.setEnabled(false);
        CantidadTI.setEnabled(false);
        cancelarI.setEnabled(false);
        setInsumosBox();
        asignaIDprod();
        //añadirIn.setEnabled(false);      
    }
    
    public int obtenerIdInsumo(){
        int codigo = ((Insumos)insumosVector.elementAt(insumoBox.getSelectedIndex())).verInsId();
        return codigo;
    }
    
    public String obtenerNombreInsumo(int id){
        String nombre = "";
        for (int i = 0; i < insumosVector.size(); i++) {
            if(id == ((Insumos)insumosVector.elementAt(i)).verInsId()){
                nombre = ((Insumos)insumosVector.elementAt(i)).verInsNombre();
                break;
            }
        }
        return nombre;
    }
    
    public void asignaIDprod(){
        try {
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM producto order by id;"); 
        } catch (Exception e) {
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                newIDproducto = GestionRestaurantes.rs.getInt(1) + 1;
            }
        } catch (Exception e) {
        }
    }
    
    public void setInsumosBox(){
        String auxUpdate  = "";
        insumosVector = new Vector();
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM insumos;");  
        } catch (Exception e) {
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                if(login.auxIdRes == GestionRestaurantes.rs.getInt(5)){
                    Insumos ins;
                    ins = new Insumos(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2),
                            GestionRestaurantes.rs.getString(3), GestionRestaurantes.rs.getInt(4));
                    insumosVector.add(ins);
                }              
            }
        } catch (Exception e) {
            System.out.println("Error bro... "+e);
        }
        for (int i = 0; i < insumosVector.size(); i++) {
            insumoBox.addItem(((Insumos)insumosVector.elementAt(i)).verInsNombre());
        }              
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        precioField = new javax.swing.JTextField();
        nombreField = new javax.swing.JTextField();
        AñadirP = new javax.swing.JButton();
        CancelarB = new javax.swing.JButton();
        añadirIn = new javax.swing.JButton();
        añadirI = new javax.swing.JButton();
        CantidadTI = new javax.swing.JTextField();
        cancelarI = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        insumosArea = new javax.swing.JTextArea();
        insumoBox = new javax.swing.JComboBox<>();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(precioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 94, 298, -1));
        getContentPane().add(nombreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 45, 298, -1));

        AñadirP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        AñadirP.setForeground(new java.awt.Color(255, 255, 255));
        AñadirP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        AñadirP.setText("Añadir Producto");
        AñadirP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        AñadirP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AñadirP.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        AñadirP.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        AñadirP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AñadirP.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        AñadirP.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        AñadirP.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        AñadirP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirPActionPerformed(evt);
            }
        });
        getContentPane().add(AñadirP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 120, 30));

        CancelarB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CancelarB.setForeground(new java.awt.Color(255, 255, 255));
        CancelarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        CancelarB.setText("Volver");
        CancelarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        CancelarB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CancelarB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CancelarB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        CancelarB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CancelarB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CancelarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBActionPerformed(evt);
            }
        });
        getContentPane().add(CancelarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 80, 30));

        añadirIn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añadirIn.setForeground(new java.awt.Color(255, 255, 255));
        añadirIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        añadirIn.setText("Añadir Insumo");
        añadirIn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        añadirIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        añadirIn.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        añadirIn.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        añadirIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        añadirIn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        añadirIn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        añadirIn.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        añadirIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirInActionPerformed(evt);
            }
        });
        getContentPane().add(añadirIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 173, 110, 30));

        añadirI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añadirI.setForeground(new java.awt.Color(255, 255, 255));
        añadirI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        añadirI.setText("Añadir");
        añadirI.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        añadirI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        añadirI.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        añadirI.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        añadirI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        añadirI.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        añadirI.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        añadirI.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        añadirI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirIActionPerformed(evt);
            }
        });
        getContentPane().add(añadirI, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 323, 63, 23));
        getContentPane().add(CantidadTI, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 285, 103, -1));

        cancelarI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cancelarI.setForeground(new java.awt.Color(255, 255, 255));
        cancelarI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        cancelarI.setText("Cancelar");
        cancelarI.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        cancelarI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelarI.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        cancelarI.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        cancelarI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelarI.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        cancelarI.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cancelarI.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cancelarI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarIActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarI, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 323, 63, 23));

        insumosArea.setEditable(false);
        insumosArea.setColumns(20);
        insumosArea.setRows(5);
        jScrollPane1.setViewportView(insumosArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 207, 298, 175));

        insumoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insumoBoxActionPerformed(evt);
            }
        });
        getContentPane().add(insumoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 227, 160, -1));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/AñadirP.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void añadirInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirInActionPerformed
        // TODO add your handling code here:
        añadirIn.setEnabled(false);
        AñadirP.setEnabled(false);
        insumoBox.setEnabled(true);
        añadirI.setEnabled(true);
        CantidadTI.setEnabled(true);
        cancelarI.setEnabled(true);
    }//GEN-LAST:event_añadirInActionPerformed

    private void añadirIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirIActionPerformed
        //insumosArea.setText(string);
        Requiere req;      
        añadirIn.setEnabled(true);
        AñadirP.setEnabled(true);
        insumoBox.setEnabled(false);
        añadirI.setEnabled(false);
        CantidadTI.setEnabled(false);
        cancelarI.setEnabled(false);
        
        
        // Requiere        
        for(int i=0;i<reqAux.size();i++){
                //Requiere auxrequiere = (Requiere)reqAux.get(i);
                //Insumos auxinsumo = (Insumos)insumosVector.get(i);
            if(((Requiere)reqAux.elementAt(i)).verReqIdInsumos()==((Insumos)insumosVector.elementAt(index)).verInsId()){
                ((Requiere)reqAux.elementAt(i)).modReqCanridad(((Requiere)reqAux.elementAt(i)).verReqCantidad()+Integer.parseInt(CantidadTI.getText()));
                repetido=1;
            }     
        }
        if(!CantidadTI.getText().equals("")){
            insumosArea.setText("");
            if(isNumeric(CantidadTI.getText())){   
                if(Integer.parseInt(CantidadTI.getText())>0){
                    try {
                        GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                        GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM requiere order by codigo;"); 
                    } catch (Exception e) {
                    }

                    try {
                        while(GestionRestaurantes.rs.next()){
                            newIDreq = GestionRestaurantes.rs.getInt(1) + acc;
                        }
                    } catch (Exception e) {
                    }
                    if(repetido==0){
                        req = new Requiere(newIDreq, newIDproducto, obtenerIdInsumo(), Integer.parseInt(CantidadTI.getText()));
                        reqAux.add(req);
                    }
                    acc++;
                    for (int i = 0; i < reqAux.size(); i++) {
                        insumosArea.setText(insumosArea.getText() + 
                                "ID insumo: " + ((Requiere)reqAux.elementAt(i)).verReqIdInsumos() +
                                "\nNombre insumo: " + obtenerNombreInsumo(((Requiere)reqAux.elementAt(i)).verReqIdInsumos()) +
                                "\nCantidad: " + ((Requiere)reqAux.elementAt(i)).verReqCantidad() +
                                "\n__________________________\n");
                    }
                }

                else{
                    JOptionPane.showMessageDialog(null, "LA CANTIDAD DEBE SER MAYOR A 0", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                    CantidadTI.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "CANTIDAD NO VALIDA", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                CantidadTI.setText("");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "CANTIDAD NO VALIDA", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            CantidadTI.setText("");
        }
        repetido=0;                           
    }//GEN-LAST:event_añadirIActionPerformed

    private void AñadirPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirPActionPerformed
        // TODO add your handling code here:
        
        if(!nombreField.getText().equals("")){
            if(isNumeric(precioField.getText())){
                if(Integer.parseInt(precioField.getText())>0){
                    Producto pro;
                    pro = new Producto(newIDproducto, nombreField.getText(), Integer.parseInt(precioField.getText()));
                    String auxUpdate = "";
                        auxUpdate = "INSERT INTO producto "
                            + "values(" + pro.verProId() + "," + "'" + pro.verProNombre() + "'" + "," + pro.verProPrecio() 
                            + "," + login.auxIdRes + "," + "'Preparado'" +");";                                                          

                    try {
                        GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                        GestionRestaurantes.s.executeUpdate(auxUpdate);
                    } catch (Exception e) {
                        System.out.println("Error bro..." + e);
                    }
                        
                    for (int i = 0; i < reqAux.size(); i++) {
                        auxUpdate = "INSERT INTO requiere "
                            + "values(" + ((Requiere)reqAux.elementAt(i)).verReqCodigo()
                            + "," + ((Requiere)reqAux.elementAt(i)).verReqIdProducto()
                            + "," + ((Requiere)reqAux.elementAt(i)).verReqIdInsumos()
                            + "," + ((Requiere)reqAux.elementAt(i)).verReqCantidad() 
                            + "," + login.auxIdRes + ");";
                        try {
                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                            GestionRestaurantes.s.executeUpdate(auxUpdate);
                        } catch (Exception e) {
                            System.out.println("Error bro..." + e);
                        }
                    }
                    JOptionPane.showMessageDialog(null,"PRODUCTO REGISTRADO CON EXITO","AVISO", JOptionPane.PLAIN_MESSAGE, login.CheckIcon);
                    nombreField.setText("");
                    precioField.setText("");
                    CantidadTI.setText("");
                    insumosArea.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null, "EL PRECIO DEBE SER MAYOR A 0$", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                    precioField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "PRECIO NO VALIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                precioField.setText("");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "NOMBRE NO VALIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            nombreField.setText("");
        }
        
                    
        
        
    }//GEN-LAST:event_AñadirPActionPerformed

    private void CancelarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBActionPerformed
        // TODO add your handling code here:
        AdmonRes a1 = new AdmonRes();
        a1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CancelarBActionPerformed

    private void cancelarIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarIActionPerformed
        añadirIn.setEnabled(true);
        AñadirP.setEnabled(true);
        insumoBox.setEnabled(false);
        añadirI.setEnabled(false);
        CantidadTI.setEnabled(false);
        cancelarI.setEnabled(false);
    }//GEN-LAST:event_cancelarIActionPerformed

    private void insumoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insumoBoxActionPerformed
        // TODO add your handling code here:
        index=insumoBox.getSelectedIndex();
    }//GEN-LAST:event_insumoBoxActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AñadirP;
    private javax.swing.JButton CancelarB;
    private javax.swing.JTextField CantidadTI;
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton añadirI;
    private javax.swing.JButton añadirIn;
    private javax.swing.JButton cancelarI;
    private javax.swing.JComboBox<String> insumoBox;
    private javax.swing.JTextArea insumosArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField precioField;
    // End of variables declaration//GEN-END:variables
}
