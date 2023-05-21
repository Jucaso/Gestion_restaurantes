/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrestaurantesFinal;

import Clases.GestionRestaurantes;
import Clases.Restaurante;
import Clases.Empleado;
import static gestionrestaurantesFinal.CrearUsuario.isNumeric;
import static gestionrestaurantesFinal.CrearUsuario.restVec;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Jucaso
 */
public class modificarUsuario extends javax.swing.JFrame {

    int index = 0;
    static Vector restVec;
    Vector empleados, idRestAux;
    int auxID = 0;
    
    public modificarUsuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setRestaurantesComboBox();
        setDatos();
        setIdBox();
    }
    public String obtenerAño(String fecha){
        String año = "";
        for (int i = 0; i < fecha.length(); i++) {
            if(fecha.charAt(i) != '-'){
                año = año + fecha.charAt(i);
            }
            else{
                break;
            }                      
        }       
        return año;
    }
    
    public String obtenerMes(String fecha){
        String mes = "";
        int aux = 0;
        for (int i = 0; i < fecha.length(); i++) {
            if(fecha.charAt(i) == '-'){
                    aux++;
                }
            
            if(fecha.charAt(i) != '-' && aux > 0){               
                mes = mes + fecha.charAt(i);                 
            }
            else if(aux == 2){
                break;
            }                      
        }       
        return mes;
    }
    
    public String obtenerDia(String fecha){
        String dia = "";
        int aux = 0;
        for (int i = 0; i < fecha.length(); i++) {
            if(fecha.charAt(i) == ' '){
                break;
            }
            
            if(fecha.charAt(i) == '-'){
                    aux++;
            }           
            
            if(fecha.charAt(i) != '-' && aux > 1){               
                dia = dia + fecha.charAt(i);                 
            }
            else if(aux == 3){
                break;
            }                      
        }       
        return dia;
    }
    
    public void setIdBox(){
        for (int i = 0; i < empleados.size(); i++) {
            empBox.addItem(Integer.toString(((Empleado)empleados.elementAt(i)).verEmpId()));
        }
    }
    public void setDatos(){
        empleados = new Vector();
        idRestAux = new Vector();
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT id, tipoid, nombre, apellido, email, "
                     + " direccion, celular, fechaingreso, usuario, contraseña, tipousuario, idrest FROM empleado;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        
        try {
            while(GestionRestaurantes.rs.next()){              
                    Empleado emp;
                    emp = new Empleado(GestionRestaurantes.rs.getString(3), GestionRestaurantes.rs.getString(4),
                    Integer.parseInt(GestionRestaurantes.rs.getString(1)), GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getString(5),
                    GestionRestaurantes.rs.getString(6), GestionRestaurantes.rs.getLong(7), GestionRestaurantes.rs.getString(8),
                    GestionRestaurantes.rs.getString(9), GestionRestaurantes.rs.getString(10), GestionRestaurantes.rs.getString(11));
                    empleados.add(emp);
                    idRestAux.add(GestionRestaurantes.rs.getInt(12));
                
                
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
            JOptionPane.showMessageDialog(null, "OCURRIÓ UN ERROR AL AGREGAR", "ERROR", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
        } 
    }
     public static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    public static boolean isNumericLong(String cadena){
	try {
		Long.parseLong(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    public void setRestaurantesComboBox(){
        restVec = new Vector();
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM restaurante;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                Restaurante res;
                res = new Restaurante(GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getInt(1));
                restVec.add(res);
            } 
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        } 
        for (int i = 0; i < restVec.size(); i++) {
            restauranteBox.addItem(Integer.toString(((Restaurante)restVec.elementAt(i)).verResId()));
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        direccionField = new javax.swing.JTextField();
        celularField = new javax.swing.JTextField();
        añoBox = new javax.swing.JComboBox<>();
        mesBox = new javax.swing.JComboBox<>();
        diaBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        usuarioField = new javax.swing.JTextField();
        passField = new javax.swing.JTextField();
        tipoUserBox = new javax.swing.JComboBox<>();
        cancelarBoton = new javax.swing.JButton();
        guardarBoton = new javax.swing.JButton();
        tipoIdBox = new javax.swing.JComboBox<>();
        restauranteBox = new javax.swing.JComboBox<>();
        nombreField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        empBox = new javax.swing.JComboBox<>();
        cargarUserBoton = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(550, 420));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(direccionField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 191, -1));
        getContentPane().add(celularField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 129, -1));

        añoBox.setBackground(new java.awt.Color(102, 102, 102));
        añoBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añoBox.setForeground(new java.awt.Color(255, 255, 255));
        añoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", " " }));
        añoBox.setName(""); // NOI18N
        añoBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                añoBisiesto(evt);
            }
        });
        getContentPane().add(añoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 204, -1, -1));

        mesBox.setBackground(new java.awt.Color(102, 102, 102));
        mesBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesBox.setForeground(new java.awt.Color(255, 255, 255));
        mesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesBisiesto(evt);
            }
        });
        mesBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesBoxActionPerformed(evt);
            }
        });
        getContentPane().add(mesBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 204, -1, -1));

        diaBox.setBackground(new java.awt.Color(102, 102, 102));
        diaBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diaBox.setForeground(new java.awt.Color(255, 255, 255));
        diaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(diaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 204, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("/");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 204, 10, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("/");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 204, -1, 20));
        getContentPane().add(apellidoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 191, -1));

        usuarioField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioFieldActionPerformed(evt);
            }
        });
        getContentPane().add(usuarioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 245, 129, -1));
        getContentPane().add(passField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 245, 191, -1));

        tipoUserBox.setBackground(new java.awt.Color(102, 102, 102));
        tipoUserBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tipoUserBox.setForeground(new java.awt.Color(255, 255, 255));
        tipoUserBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operario", "Admon restaurante", "Admon centro comercial" }));
        getContentPane().add(tipoUserBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 286, -1, -1));

        cancelarBoton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cancelarBoton.setForeground(new java.awt.Color(255, 255, 255));
        cancelarBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        cancelarBoton.setText("CANCELAR");
        cancelarBoton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        cancelarBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelarBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelarBoton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        cancelarBoton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cancelarBoton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 363, 89, 30));

        guardarBoton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        guardarBoton.setForeground(new java.awt.Color(255, 255, 255));
        guardarBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        guardarBoton.setText("GUARDAR");
        guardarBoton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        guardarBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarBoton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        guardarBoton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        guardarBoton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        guardarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBotonActionPerformed(evt);
            }
        });
        getContentPane().add(guardarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 363, 89, 30));

        tipoIdBox.setBackground(new java.awt.Color(102, 102, 102));
        tipoIdBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tipoIdBox.setForeground(new java.awt.Color(255, 255, 255));
        tipoIdBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cedula de ciudadania", "Cedula de extranjeria", "Pasaporte", "Tarjeta de identidad" }));
        getContentPane().add(tipoIdBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 128, 191, -1));

        restauranteBox.setBackground(new java.awt.Color(102, 102, 102));
        restauranteBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        restauranteBox.setForeground(new java.awt.Color(255, 255, 255));
        restauranteBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                restauranteBoxrestaurante(evt);
            }
        });
        restauranteBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restauranteBoxActionPerformed(evt);
            }
        });
        getContentPane().add(restauranteBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 284, 52, -1));
        restauranteBox.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(nombreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 126, 129, -1));
        getContentPane().add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 204, 129, -1));

        empBox.setBackground(new java.awt.Color(102, 102, 102));
        empBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        empBox.setForeground(new java.awt.Color(255, 255, 255));
        empBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empBoxActionPerformed(evt);
            }
        });
        getContentPane().add(empBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 160, -1));

        cargarUserBoton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cargarUserBoton.setForeground(new java.awt.Color(255, 255, 255));
        cargarUserBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        cargarUserBoton.setText("Cargar usuario");
        cargarUserBoton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        cargarUserBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargarUserBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cargarUserBoton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        cargarUserBoton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cargarUserBoton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        cargarUserBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarUserBotonActionPerformed(evt);
            }
        });
        getContentPane().add(cargarUserBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 90, 23));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/ModificarU.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 550, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mesBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mesBoxActionPerformed

    private void cancelarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBotonActionPerformed
        this.dispose();
        gestionUsuarios g1 = new gestionUsuarios();
        g1.setVisible(true);
    }//GEN-LAST:event_cancelarBotonActionPerformed

    private void guardarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBotonActionPerformed
        int auxAñade = 1;
        if(!nombreField.getText().isEmpty() && !isNumeric(nombreField.getText())){
            String nombre= "'"+nombreField.getText()+"'";
            
            if(!apellidoField.getText().isEmpty() && !isNumeric(apellidoField.getText())){
                    String apellido = "'"+apellidoField.getText()+"'";          
                           
                    
                    if(isNumericLong(celularField.getText()) ){
                        long celular = Long.parseLong(celularField.getText());
                        
                        if(!direccionField.getText().isEmpty()){
                            String direccion = "'"+direccionField.getText()+"'";
                            
                            if(emailField.getText().contains("@")){
                                    String email = "'"+emailField.getText()+"'"; 
                                    
                                if(!usuarioField.getText().isEmpty()){
                                    String usuario = "'"+usuarioField.getText()+"'";
                                                                                                                          
                                    if(!passField.getText().isEmpty()){
                                        String auxUpdate = "";
        // Modificar tipo de ID
                                        auxUpdate = "UPDATE empleado"
                                                + " SET tipoid = " + "'" + tipoIdBox.getSelectedItem() + "" + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error en tipo id bro... " + e);
                                        }
                                        // Modificar nombre  
                                        auxUpdate = "UPDATE empleado"
                                                + " SET nombre = " + "'" + nombreField.getText() + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }       
                                        // Modificar apellido
                                        auxUpdate = "UPDATE empleado"
                                                + " SET apellido = " + "'" + apellidoField.getText() + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar email
                                        auxUpdate = "UPDATE empleado"
                                                + " SET email = " + "'" +emailField.getText() + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar dirección
                                        auxUpdate = "UPDATE empleado"
                                                + " SET direccion = " + "'" + direccionField.getText() + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar celular
                                        auxUpdate = "UPDATE empleado"
                                                + " SET celular = " + Long.parseLong(celularField.getText()) +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar fecha ingreso
                                        String año = añoBox.getSelectedItem()+"", mes = mesBox.getSelectedItem()+"", dia = diaBox.getSelectedItem()+"";
                                        String fecha = año + "-" + mes + "-" + dia; 
                                        auxUpdate = "UPDATE empleado"
                                                + " SET fechaingreso = " + "'" + fecha + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar usuario
                                        auxUpdate = "UPDATE empleado"
                                                + " SET usuario = " + "'" + usuarioField.getText() + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar contraseña
                                        auxUpdate = "UPDATE empleado"
                                                + " SET contraseña = " + "'" + passField.getText() + "'" +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar tipo de usuario
                                        auxUpdate = "UPDATE empleado"
                                                + " SET tipousuario = " + "'" + tipoUserBox.getSelectedItem() + "" + "'"
                                                + " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        // Modificar id restaurante
                                        auxUpdate = "UPDATE empleado"
                                                + " SET idrest = " + Integer.parseInt(restauranteBox.getSelectedItem() + "") +
                                                " WHERE id = " + auxID + ";";
                                        try {
                                            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                                            GestionRestaurantes.s.executeUpdate(auxUpdate); 
                                        } catch (Exception e) {
                                            System.out.println("Error bro... " + e);
                                        }
                                        this.dispose();
                                    } 
                                    else{
                                        JOptionPane.showMessageDialog(null, "CONTRASEÑA NO VÁLIDA", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon); 
                                        passField.setText("");
                                        }
                                } 
                                else{
                                    JOptionPane.showMessageDialog(null, "USUARIO NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon); 
                                    usuarioField.setText("");
                                    }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "EMAIL NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon); 
                                emailField.setText("");
                                }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "DIRECCIÓN NO VÁLIDA", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon); 
                            direccionField.setText("");
                            }                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "CELULAR NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                        celularField.setText("");
                        }
                

            } 
            else{
                JOptionPane.showMessageDialog(null, "APELLIDO NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                apellidoField.setText("");
                }
        } 
        else{
            JOptionPane.showMessageDialog(null, "NOMBRE NO VÁLIDO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            nombreField.setText("");
        }
        
    }//GEN-LAST:event_guardarBotonActionPerformed

    private void restauranteBoxrestaurante(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_restauranteBoxrestaurante

    }//GEN-LAST:event_restauranteBoxrestaurante

    private void restauranteBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restauranteBoxActionPerformed

    }//GEN-LAST:event_restauranteBoxActionPerformed

    private void usuarioFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioFieldActionPerformed

    private void empBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empBoxActionPerformed

    private void cargarUserBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarUserBotonActionPerformed
        int auxEmp = empBox.getSelectedIndex();
        auxID = Integer.parseInt(empBox.getSelectedItem()+"");
        añoBox.setSelectedIndex(0);
        mesBox.setSelectedIndex(0);
        diaBox.setSelectedIndex(0);
        tipoIdBox.setSelectedIndex(0);
        tipoUserBox.setSelectedIndex(0);
        restauranteBox.setSelectedIndex(0);
        System.out.println(tipoUserBox.getItemCount());
        System.out.println(((Empleado)empleados.elementAt(auxEmp)).verEmpTipo());
        nombreField.setText(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpNombre());
        apellidoField.setText(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpApellido());
        celularField.setText(Long.toString(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpCelular()));        
        for (int i = 0; i < 4; i++) {
            if(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpTipoId().equals(tipoIdBox.getItemAt(i)+"")){
                tipoIdBox.setSelectedIndex(i);
                break;
            }
        }
        emailField.setText(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpEmail());
        direccionField.setText(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpDireccion());
        usuarioField.setText(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpUsuario());
        passField.setText(((Empleado)empleados.elementAt(empBox.getSelectedIndex())).verEmpContraseña());        
        for (int i = 0; i < añoBox.getItemCount()-1; i++) {
            if(obtenerAño(((Empleado)empleados.elementAt(auxEmp)).verEmpFecha()).equals(añoBox.getItemAt(i)+"")){
                añoBox.setSelectedIndex(i);
                break;
            }
        }   
        for (int i = 0; i < mesBox.getItemCount(); i++) {
            if(obtenerMes(((Empleado)empleados.elementAt(auxEmp)).verEmpFecha()).equals(mesBox.getItemAt(i)+"")){
                mesBox.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < diaBox.getItemCount(); i++) {
            if(obtenerDia(((Empleado)empleados.elementAt(auxEmp)).verEmpFecha()).equals(diaBox.getItemAt(i)+"")){
                diaBox.setSelectedIndex(i);
                break;
            }
        } 
        for (int i = 0; i < tipoUserBox.getItemCount(); i++) {
            if(((Empleado)empleados.elementAt(auxEmp)).verEmpTipo().equals(tipoUserBox.getItemAt(i)+"")){
                tipoUserBox.setSelectedIndex(i);
                break;
            }
        } 
        for (int i = 0; i < restauranteBox.getItemCount(); i++) {
            if((Integer)idRestAux.elementAt(auxEmp) == Integer.parseInt(restauranteBox.getItemAt(i))){
                restauranteBox.setSelectedIndex(i);
                break;
            }
        } 
    }//GEN-LAST:event_cargarUserBotonActionPerformed

    private void añoBisiesto(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_añoBisiesto
        mesBox.setSelectedIndex(0);
        diaBox.setSelectedIndex(0);
    }//GEN-LAST:event_añoBisiesto

    private void mesBisiesto(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesBisiesto
        int año = Integer.parseInt(añoBox.getSelectedItem()+"");
        int mes = Integer.parseInt(mesBox.getSelectedItem()+"");

        diaBox.removeAllItems();
        if(((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) &&  mes == 02){
            for (int i = 0; i < 29; i++) {               
                diaBox.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 02){
            for (int i = 0; i < 28; i++) {               
                diaBox.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            for (int i = 0; i < 30; i++) {               
                diaBox.addItem(Integer.toString(i+1));
            }
        }
        else{
            for (int i = 0; i < 31; i++) {               
                diaBox.addItem(Integer.toString(i+1));
            }
        }
    }//GEN-LAST:event_mesBisiesto

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
            java.util.logging.Logger.getLogger(modificarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modificarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JComboBox<String> añoBox;
    private javax.swing.JButton cancelarBoton;
    private javax.swing.JButton cargarUserBoton;
    private javax.swing.JTextField celularField;
    private javax.swing.JComboBox<String> diaBox;
    private javax.swing.JTextField direccionField;
    private javax.swing.JTextField emailField;
    private javax.swing.JComboBox<String> empBox;
    private javax.swing.JButton guardarBoton;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JComboBox<String> mesBox;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField passField;
    private javax.swing.JComboBox<String> restauranteBox;
    private javax.swing.JComboBox<String> tipoIdBox;
    private javax.swing.JComboBox<String> tipoUserBox;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}
