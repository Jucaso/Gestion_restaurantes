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
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class ModificarProducto extends javax.swing.JFrame {

    Vector insumosVector, productoVector, insumosAddVector, reqAux = new Vector(), eliminaAud = new Vector(), requiereCod = new Vector();
    int newIDreq = 0, newIDproducto = 0, acc = 1, auxAñade = 0, auxElimina = 0;
    public ModificarProducto() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //productoBox.setEnabled(false);
        NombreText.setEnabled(false);
        PrecioText.setEnabled(false);
        insumoAddBox.setEnabled(false);
        AñadirB.setEnabled(false);
        EliminarText.setEnabled(false);
        CancelarB.setEnabled(false);
        ModificarB.setEnabled(false);
        setInsumosAddBox();
        setProductoBox();
        
    }
    
    public int obtenerIdInsumo(){
        int codigo = ((Insumos)insumosAddVector.elementAt(insumoAddBox.getSelectedIndex())).verInsId();
        return codigo;
    }
    
    public void asignaIDprod(){
        try {
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM producto;"); 
        } catch (Exception e) {
        }
        
        try {
            while(GestionRestaurantes.rs.next()){                                                
                if(((Producto)productoVector.elementAt(productoBox.getSelectedIndex())).verProId() == GestionRestaurantes.rs.getInt(1)){                   
                    newIDproducto = GestionRestaurantes.rs.getInt(1);                  
                }
                
                
                
            }
        } catch (Exception e) {
        }
    }
    
    public void setInsumosPBox(){
        String auxUpdate  = "";
        int check = 0;
        auxUpdate = "select insumos.id, insumos.nombre,insumos.costoadquisicion,insumos.cantidadunidad, insumos.idrest, requiere.codigo\n" +
                    "from requiere inner join insumos on requiere.idinsumos = insumos.id\n" +
                    "where idproducto =" + obtenerIdProducto(productoBox.getSelectedIndex()) + ";";
        insumosVector = new Vector();
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxUpdate); 
        } catch (Exception e) {
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                if(login.auxIdRes == GestionRestaurantes.rs.getInt(5)){  
                    for (int i = 0; i < eliminaAud.size(); i++) {
                        if((Integer)eliminaAud.elementAt(i) == GestionRestaurantes.rs.getInt(6)){
                            check = 1;
                        }
                    }
                    if(check == 0){
                        Insumos ins;
                        ins = new Insumos(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2),
                                GestionRestaurantes.rs.getString(3), GestionRestaurantes.rs.getInt(4));
                        insumosVector.add(ins);
                        requiereCod.add(GestionRestaurantes.rs.getInt(6));
                    }
                    
                } 
                check = 0;
            }
        } catch (Exception e) {
            System.out.println("Error bro... "+e);
        }
        for (int i = 0; i < insumosVector.size(); i++) {
            insumoBox.addItem(((Insumos)insumosVector.elementAt(i)).verInsNombre());
        }    
    }
    
    public void setInsumosAddBox(){
        String auxUpdate  = "";
        insumosAddVector = new Vector();
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
                    insumosAddVector.add(ins);
                }              
            }
        } catch (Exception e) {
            System.out.println("Error bro... "+e);
        }
        for (int i = 0; i < insumosAddVector.size(); i++) {
            insumoAddBox.addItem(((Insumos)insumosAddVector.elementAt(i)).verInsNombre());
        }              
    }
    
    public int obtenerIdProducto(int index){
        int codigo = ((Producto)productoVector.elementAt(productoBox.getSelectedIndex())).verProId();
        return codigo;
    }
    
    public void setProductoBox(){
        String auxUpdate  = "";
        productoVector = new Vector();
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM producto;");  
        } catch (Exception e) {
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                if(login.auxIdRes == GestionRestaurantes.rs.getInt(4)){
                    Producto pro;
                    pro = new Producto(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2),
                            GestionRestaurantes.rs.getInt(3));
                    productoVector.add(pro);
                }              
            }
        } catch (Exception e) {
            System.out.println("Error bro... "+e);
        }
        for (int i = 0; i < productoVector.size(); i++) {
            productoBox.addItem(((Producto)productoVector.elementAt(i)).verProNombre());
        }              
    }
    
    public String obtenerNombreInsumo(int id){
        String nombre = "";
        for (int i = 0; i < insumosAddVector.size(); i++) {
            if(id == ((Insumos)insumosAddVector.elementAt(i)).verInsId()){
                nombre = ((Insumos)insumosAddVector.elementAt(i)).verInsNombre();
                break;
            }
        }
        return nombre;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insumoBox = new javax.swing.JComboBox<>();
        NombreText = new javax.swing.JTextField();
        PrecioText = new javax.swing.JTextField();
        AñadirB = new javax.swing.JButton();
        EliminarText = new javax.swing.JButton();
        CargarB = new javax.swing.JButton();
        CancelarB = new javax.swing.JButton();
        ModificarB = new javax.swing.JButton();
        SalirB = new javax.swing.JButton();
        productoBox = new javax.swing.JComboBox<>();
        insumoAddBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        insumosArea = new javax.swing.JTextArea();
        CantidadTI = new javax.swing.JTextField();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(495, 430));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(495, 430));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(insumoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 334, 129, -1));
        getContentPane().add(NombreText, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 82, 141, -1));
        getContentPane().add(PrecioText, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 116, 141, -1));

        AñadirB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        AñadirB.setForeground(new java.awt.Color(255, 255, 255));
        AñadirB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        AñadirB.setText("Añadir");
        AñadirB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        AñadirB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AñadirB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AñadirB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        AñadirB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        AñadirB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        AñadirB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirBActionPerformed(evt);
            }
        });
        getContentPane().add(AñadirB, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 235, 60, 30));

        EliminarText.setText("Eliminar");
        EliminarText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarTextActionPerformed(evt);
            }
        });
        getContentPane().add(EliminarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 333, 101, -1));

        CargarB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CargarB.setForeground(new java.awt.Color(255, 255, 255));
        CargarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        CargarB.setText("Cargar");
        CargarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        CargarB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CargarB.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CargarB.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CargarB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CargarB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        CargarB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CargarB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CargarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarBActionPerformed(evt);
            }
        });
        getContentPane().add(CargarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 80, 30));

        CancelarB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CancelarB.setForeground(new java.awt.Color(255, 255, 255));
        CancelarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        CancelarB.setText("Cancelar");
        CancelarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        CancelarB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CancelarB.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CancelarB.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CancelarB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CancelarB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        CancelarB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CancelarB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        CancelarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBActionPerformed(evt);
            }
        });
        getContentPane().add(CancelarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 81, 30));

        ModificarB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModificarB.setForeground(new java.awt.Color(255, 255, 255));
        ModificarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        ModificarB.setText("Guardar cambios");
        ModificarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        ModificarB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarB.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        ModificarB.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        ModificarB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        ModificarB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        ModificarB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        ModificarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarBActionPerformed(evt);
            }
        });
        getContentPane().add(ModificarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 129, 30));

        SalirB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SalirB.setForeground(new java.awt.Color(255, 255, 255));
        SalirB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        SalirB.setText("Volver");
        SalirB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        SalirB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalirB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SalirB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        SalirB.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        SalirB.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        SalirB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirBActionPerformed(evt);
            }
        });
        getContentPane().add(SalirB, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 80, 30));

        getContentPane().add(productoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 210, 20));

        insumoAddBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insumoAddBoxActionPerformed(evt);
            }
        });
        getContentPane().add(insumoAddBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 194, 160, 20));

        insumosArea.setEditable(false);
        insumosArea.setColumns(20);
        insumosArea.setRows(5);
        jScrollPane1.setViewportView(insumosArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 200, 123));
        getContentPane().add(CantidadTI, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 72, -1));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/ModificarP.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 437));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarBActionPerformed
        // TODO add your handling code here:
        CargarB.setEnabled(false);
        productoBox.setEnabled(false);
        NombreText.setEnabled(true);
        PrecioText.setEnabled(true);
        insumoAddBox.setEnabled(true);
        AñadirB.setEnabled(true);
        EliminarText.setEnabled(true);
        CancelarB.setEnabled(true);
        ModificarB.setEnabled(true);
                     
        for (int i = 0; i < productoVector.size(); i++) {
            if(obtenerIdProducto(productoBox.getSelectedIndex()) == ((Producto)productoVector.elementAt(i)).verProId()){
                NombreText.setText(((Producto)productoVector.elementAt(i)).verProNombre());
                PrecioText.setText(Integer.toString(((Producto)productoVector.elementAt(i)).verProPrecio()));
                break;
            }
        }
        setInsumosPBox();
        asignaIDprod();
    }//GEN-LAST:event_CargarBActionPerformed

    private void EliminarTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarTextActionPerformed
        //System.out.println("insumoBox selected index: " + insumoBox.getSelectedIndex());
        auxElimina = 1;
        eliminaAud.add(requiereCod.elementAt(insumoBox.getSelectedIndex()));
        
        for (int i = 0; i < eliminaAud.size(); i++) {
            System.out.println(eliminaAud.elementAt(i));
        }
        insumoBox.removeAllItems();
        insumosVector.removeAllElements();
        requiereCod.removeAllElements();
        //System.out.println("insumosVector size: "+ insumosVector.size());
        setInsumosPBox();
        
    }//GEN-LAST:event_EliminarTextActionPerformed

    private void CancelarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBActionPerformed
        // TODO add your handling code here:
        productoBox.setEnabled(true);
        NombreText.setEnabled(false);
        PrecioText.setEnabled(false);
        insumoAddBox.setEnabled(false);
        AñadirB.setEnabled(false);
        EliminarText.setEnabled(false);
        CargarB.setEnabled(true);
        ModificarB.setEnabled(false);
        CancelarB.setEnabled(false);
        
        insumoBox.removeAllItems();
        reqAux = new Vector();
        eliminaAud = new Vector();
        requiereCod = new Vector();
        insumosArea.setText("");
        CantidadTI.setText("");
    }//GEN-LAST:event_CancelarBActionPerformed

    private void ModificarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBActionPerformed
        // Producto (nombre y valor de venta)
        if(!NombreText.getText().isEmpty() && !CrearUsuario.isNumeric(NombreText.getText())){
            if(!PrecioText.getText().isEmpty() && CrearUsuario.isNumeric(PrecioText.getText())){
                String auxUpdate = "UPDATE producto"
                + " SET nombre = " + "'" + NombreText.getText() + "'"
                + " WHERE id = " + newIDproducto + ";";
        
                String auxUpdate2 = "UPDATE producto"
                        + " SET valorventa = " + PrecioText.getText()
                        + " WHERE id = " + newIDproducto + ";";

                try {
                    GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                    GestionRestaurantes.s.executeUpdate(auxUpdate);
                    GestionRestaurantes.s.executeUpdate(auxUpdate2);
                } catch (Exception e) {
                    System.out.println("Error bro..." + e);
                }

                // Agregar insumos al producto
                if(auxAñade == 1){
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
                    auxAñade = 0;
                }

                //Eliminar insumos del producto
                if(auxElimina == 1){
                    for (int i = 0; i < eliminaAud.size(); i++) {
                        auxUpdate = "DELETE "
                                + "FROM requiere "
                                + "WHERE codigo = " + (Integer)eliminaAud.elementAt(i) + "";

                        try {
                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                            GestionRestaurantes.s.executeUpdate(auxUpdate);
                        } catch (Exception e) {
                            System.out.println("Error bro..." + e);
                        }

                     }
                    auxElimina = 0;
                }
                this.dispose();
                ModificarProducto mp = new ModificarProducto();
                mp.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "PRECIO NO VÁLIDO", "ADVERTENCIA",JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "NOMBRE NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
        }
        
                              
    }//GEN-LAST:event_ModificarBActionPerformed

    private void AñadirBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirBActionPerformed
        Requiere req;
        insumosArea.setText("");
        auxAñade = 1;
        if(!CantidadTI.getText().isEmpty() && CrearUsuario.isNumeric(CantidadTI.getText())){
            try {
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM requiere;"); 
            } catch (Exception e) {
            }

            try {
                while(GestionRestaurantes.rs.next()){
                    newIDreq = GestionRestaurantes.rs.getInt(1) + acc;
                }
            } catch (Exception e) {
            }

            req = new Requiere(newIDreq, newIDproducto, obtenerIdInsumo(), Integer.parseInt(CantidadTI.getText()));
            reqAux.add(req);
            acc++;
            for (int i = 0; i < reqAux.size(); i++) {
                insumosArea.setText(insumosArea.getText() + 
                        "ID insumo: " + ((Requiere)reqAux.elementAt(i)).verReqIdInsumos() +
                        "\nNombre insumo: " + obtenerNombreInsumo(((Requiere)reqAux.elementAt(i)).verReqIdInsumos()) +
                        "\nCantidad: " + ((Requiere)reqAux.elementAt(i)).verReqCantidad() +
                        "\n__________________________\n");
            }
            acc++;
        }
        else{
            JOptionPane.showMessageDialog(null, "CANTIDAD DEL INSUMO NO VÁLIDA", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
        }
        
    }//GEN-LAST:event_AñadirBActionPerformed

    private void SalirBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBActionPerformed
        // TODO add your handling code here:
        AdmonRes a1 = new AdmonRes();
        a1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SalirBActionPerformed

    private void insumoAddBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insumoAddBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insumoAddBoxActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AñadirB;
    private javax.swing.JButton CancelarB;
    private javax.swing.JTextField CantidadTI;
    private javax.swing.JButton CargarB;
    private javax.swing.JButton EliminarText;
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton ModificarB;
    private javax.swing.JTextField NombreText;
    private javax.swing.JTextField PrecioText;
    private javax.swing.JButton SalirB;
    private javax.swing.JComboBox<String> insumoAddBox;
    private javax.swing.JComboBox<String> insumoBox;
    private javax.swing.JTextArea insumosArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> productoBox;
    // End of variables declaration//GEN-END:variables
}
