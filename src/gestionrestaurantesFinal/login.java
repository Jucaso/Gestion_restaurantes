/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrestaurantesFinal;

import Clases.GestionRestaurantes;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Jucaso
 */
public class login extends javax.swing.JFrame {
    
    static ImageIcon WrongIcon = new ImageIcon("src/Diseño/wrong2.png");
    static ImageIcon CheckIcon = new ImageIcon("src/Diseño/check.png");
    static int auxIdRes = 0, auxIdEmp  = 0;
    /**
     * Creates new form login
     */
    public login() throws IOException {
        super("INICIAR SESIÓN");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //verificarCambioPass(400555);
        UIManager.put("OptionPane.background",new ColorUIResource(51,51,51));
             UIManager.put("Panel.background",new ColorUIResource(51,51,51));
             UIManager.put("OptionPane.messageForeground",Color.WHITE);
             UIManager.put("Button.background",Color.BLACK);
             UIManager.put("Button.foreground",Color.WHITE);
    }
    
   

    public int verificarCambioPass(int id) throws FileNotFoundException, IOException{
        int diaInicial = 0;
        int mesInicial = 0;
        int añoInicial = 0;
        int dias = 0;
        String auxQuery = "SELECT fechainicial"
                + " FROM usuarioingreso"
                + " WHERE id = " + id + ";";
        
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        try {
            while(GestionRestaurantes.rs.next()){
                diaInicial = Integer.parseInt(obtenerDia(GestionRestaurantes.rs.getString(1)));
                mesInicial = Integer.parseInt(obtenerMes(GestionRestaurantes.rs.getString(1)));
                añoInicial = Integer.parseInt(obtenerAño(GestionRestaurantes.rs.getString(1)));
            }
        } catch (Exception e) {
        }
        
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(añoInicial, mesInicial-1, diaInicial);
        fechaInicio.set(Calendar.HOUR, 0);
        fechaInicio.set(Calendar.HOUR_OF_DAY, 0);
        fechaInicio.set(Calendar.MINUTE, 0);
        fechaInicio.set(Calendar.SECOND, 0);
        
        Calendar fechaHoy = Calendar.getInstance();
        fechaHoy.set(Calendar.HOUR, 0);
        fechaHoy.set(Calendar.HOUR_OF_DAY, 0);
        fechaHoy.set(Calendar.MINUTE, 0);
        fechaHoy.set(Calendar.SECOND, 0);
        
        long finMS = fechaHoy.getTimeInMillis();
        long inicioMS = fechaInicio.getTimeInMillis();
        
        dias = (int) (Math.abs(finMS - inicioMS)) / (1000*60*60*24);
        
        String nroDias = "";
        FileReader archivo = new FileReader("tiempoCambio.txt");
        BufferedReader buffer = new BufferedReader(archivo);
        nroDias = buffer.readLine();
        System.out.println("nroDias: "+nroDias);
        if(dias > Integer.parseInt(nroDias)){
            return 1;          
        }
        else{
            return 0;
        }              
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuarioField = new javax.swing.JTextField();
        ingresar = new javax.swing.JButton();
        gestionUsuarios = new javax.swing.JButton();
        passField = new javax.swing.JPasswordField();
        SalirB = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(299, 381));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(290, 370));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usuarioField.setBackground(new java.awt.Color(23, 22, 22));
        usuarioField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        usuarioField.setForeground(new java.awt.Color(255, 255, 255));
        usuarioField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuarioField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(usuarioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 150, 28));

        ingresar.setBackground(new java.awt.Color(51, 51, 51));
        ingresar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ingresar.setForeground(new java.awt.Color(255, 255, 255));
        ingresar.setText("INGRESAR");
        ingresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ingresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ingresarMouseExited(evt);
            }
        });
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });
        ingresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ingresarKeyPressed(evt);
            }
        });
        getContentPane().add(ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 270, 80, 20));

        gestionUsuarios.setBackground(new java.awt.Color(51, 51, 51));
        gestionUsuarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        gestionUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        gestionUsuarios.setText("GESTIONAR USUARIOS");
        gestionUsuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        gestionUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gestionUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gestionUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gestionUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gestionUsuariosMouseExited(evt);
            }
        });
        gestionUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionUsuariosActionPerformed(evt);
            }
        });
        gestionUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gestionUsuariosKeyPressed(evt);
            }
        });
        getContentPane().add(gestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 300, 130, 20));

        passField.setBackground(new java.awt.Color(23, 22, 22));
        passField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        passField.setForeground(new java.awt.Color(255, 255, 255));
        passField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(passField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 150, 31));

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
        getContentPane().add(SalirB, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 30, 30));

        Fondo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Fondo.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoLogin.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        String usuarioAux = usuarioField.getText();
        char[] arrayC = passField.getPassword();
        String tipoUsuario = "";
        String passAux = new String(arrayC);
        int codigo = 0;
       // String passAux = passField.getPassword();
        
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT usuario, contraseña, tipousuario, idrest, estado, id FROM empleado;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        String userBD = "";
        String passBD = "";
        int aux = 1, check = 0, check2 = 0;
        try {
            while(GestionRestaurantes.rs.next()){
                if(GestionRestaurantes.rs.getString(1).equals(usuarioAux) && GestionRestaurantes.rs.getString(2).equals(passAux)){
                    userBD = GestionRestaurantes.rs.getString(1);
                    codigo = GestionRestaurantes.rs.getInt(6);
                    passBD = GestionRestaurantes.rs.getString(2);
                    tipoUsuario = GestionRestaurantes.rs.getString(3);
                    auxIdRes = GestionRestaurantes.rs.getInt(4);
                    auxIdEmp = GestionRestaurantes.rs.getInt(6);
                    check = 1;
                    if(GestionRestaurantes.rs.getString(5).equals("Activo")){
                    check2 = 1;
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
        }
        /*System.out.println("userBD: "+userBD);
        System.out.println("passBD: "+passBD);
        
        System.out.println("userAux: "+usuarioAux);
        System.out.println("passAux: "+passAux);*/
        
        if(check == 1 && check2 == 1){
            int auxPass = 0;
            try {
                if(verificarCambioPass(codigo) == 1){
                    auxPass = 1;
                }
            } catch (IOException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(auxPass == 0){
               if(tipoUsuario.equals("Admon restaurante")){
                    this.dispose();
                    AdmonRes ar = new AdmonRes();
                    ar.setVisible(true);
                }

                if(tipoUsuario.equals("Operario")){
                    this.dispose();
                    Operario op = new Operario();
                    op.setVisible(true);
                }

                if(tipoUsuario.equals("Admon centro comercial")){
                    this.dispose();
                    AdmonCC aCC = new AdmonCC();
                    aCC.setVisible(true);
                }                 
            }
            else{
                
            
              
                JOptionPane.showMessageDialog(null, "DEBE CAMBIAR SU CONTRASEÑA","AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
                
                this.dispose();
                modificarPass mp = new modificarPass();
                mp.setVisible(true);
            }
            
                
            //JOptionPane.showMessageDialog(this, "ACCESO PERMITIDO");
            //System.out.println("Acceso exitoso"); 
            //System.out.println("recuerde que el usuario debe ser el correo");
        }
        else if(check == 1 && check2 == 0){
             
            
            JOptionPane.showMessageDialog(this, "USUARIO INACTIVO", "AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
            
        }
        else{
            
             
            JOptionPane.showMessageDialog(this, "USUARIO O CONTRASEÑA INCORRECTOS", "AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
            System.out.println("Error al entrar");
            System.out.println("recuerde que el usuario debe ser el correo");
        }
             
    }//GEN-LAST:event_ingresarActionPerformed

    private void gestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionUsuariosActionPerformed
        String usuarioAux = usuarioField.getText();
        int superAdmin = 0;
        char[] arrayC = passField.getPassword();
        String tipoUsuario = "";
        String passAux = new String(arrayC);
       // String passAux = passField.getPassword();
        
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT usuario, contraseña, tipousuario, idrest, superadmin FROM empleado;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        String userBD = "";
        String passBD = "";
        int aux = 1, check = 0;
        try {
            while(GestionRestaurantes.rs.next()){
                if(GestionRestaurantes.rs.getString(1).equals(usuarioAux) && GestionRestaurantes.rs.getString(2).equals(passAux)){
                    userBD = GestionRestaurantes.rs.getString(1);
                    passBD = GestionRestaurantes.rs.getString(2);
                    tipoUsuario = GestionRestaurantes.rs.getString(3);
                    auxIdRes = GestionRestaurantes.rs.getInt(4);
                    superAdmin = GestionRestaurantes.rs.getInt(5);
                    check = 1;
                }               
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
        }
        /*System.out.println("userBD: "+userBD);
        System.out.println("passBD: "+passBD);
        
        System.out.println("userAux: "+usuarioAux);
        System.out.println("passAux: "+passAux);*/
        
        if(check == 1 && superAdmin == 1){
            this.dispose();
            gestionUsuarios gu = new gestionUsuarios();           
            gu.setVisible(true);               
            //JOptionPane.showMessageDialog(this, "ACCESO PERMITIDO");
            //System.out.println("Acceso exitoso"); 
            //System.out.println("recuerde que el usuario debe ser el correo");
        }
        else{
            
            
            JOptionPane.showMessageDialog(this, "ACCESO DENEGADO","AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
            System.out.println("Error al entrar");
            System.out.println("recuerde que el usuario debe ser el correo");
        }
        
    }//GEN-LAST:event_gestionUsuariosActionPerformed

    private void SalirBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirBActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_SalirBActionPerformed

    private void ingresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresarKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
                  String usuarioAux = usuarioField.getText();
        char[] arrayC = passField.getPassword();
        String tipoUsuario = "";
        String passAux = new String(arrayC);
        int codigo = 0;
       // String passAux = passField.getPassword();
        
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT usuario, contraseña, tipousuario, idrest, estado, id FROM empleado;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        String userBD = "";
        String passBD = "";
        int aux = 1, check = 0, check2 = 0;
        try {
            while(GestionRestaurantes.rs.next()){
                if(GestionRestaurantes.rs.getString(1).equals(usuarioAux) && GestionRestaurantes.rs.getString(2).equals(passAux)){
                    userBD = GestionRestaurantes.rs.getString(1);
                    codigo = GestionRestaurantes.rs.getInt(6);
                    passBD = GestionRestaurantes.rs.getString(2);
                    tipoUsuario = GestionRestaurantes.rs.getString(3);
                    auxIdRes = GestionRestaurantes.rs.getInt(4);
                    auxIdEmp = GestionRestaurantes.rs.getInt(6);
                    check = 1;
                    if(GestionRestaurantes.rs.getString(5).equals("Activo")){
                    check2 = 1;
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
        }
        /*System.out.println("userBD: "+userBD);
        System.out.println("passBD: "+passBD);
        
        System.out.println("userAux: "+usuarioAux);
        System.out.println("passAux: "+passAux);*/
        
        if(check == 1 && check2 == 1){
            int auxPass = 0;
            try {
                if(verificarCambioPass(codigo) == 1){
                    auxPass = 1;
                }
            } catch (IOException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(auxPass == 0){
               if(tipoUsuario.equals("Admon restaurante")){
                    this.dispose();
                    AdmonRes ar = new AdmonRes();
                    ar.setVisible(true);
                }

                if(tipoUsuario.equals("Operario")){
                    this.dispose();
                    Operario op = new Operario();
                    op.setVisible(true);
                }

                if(tipoUsuario.equals("Admon centro comercial")){
                    this.dispose();
                    AdmonCC aCC = new AdmonCC();
                    aCC.setVisible(true);
                }                 
            }
            else{
               
                JOptionPane.showMessageDialog(null, "DEBE CAMBIAR SU CONTRASEÑA","AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
                this.dispose();
                modificarPass mp = new modificarPass();
                mp.setVisible(true);
            }
            
                
            //JOptionPane.showMessageDialog(this, "ACCESO PERMITIDO");
            //System.out.println("Acceso exitoso"); 
            //System.out.println("recuerde que el usuario debe ser el correo");
        }
        else if(check == 1 && check2 == 0){
           
            JOptionPane.showMessageDialog(this, "USUARIO INACTIVO","AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
            
        }
        else{
            
            JOptionPane.showMessageDialog(this, "USUARIO O CONTRASEÑA INCORRECTOS","AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
            System.out.println("Error al entrar");
            System.out.println("recuerde que el usuario debe ser el correo");
        }
        }
    }//GEN-LAST:event_ingresarKeyPressed

    private void gestionUsuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gestionUsuariosKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
         String usuarioAux = usuarioField.getText();
        int superAdmin = 0;
        char[] arrayC = passField.getPassword();
        String tipoUsuario = "";
        String passAux = new String(arrayC);
       // String passAux = passField.getPassword();
        
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT usuario, contraseña, tipousuario, idrest, superadmin FROM empleado;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        String userBD = "";
        String passBD = "";
        int aux = 1, check = 0;
        try {
            while(GestionRestaurantes.rs.next()){
                if(GestionRestaurantes.rs.getString(1).equals(usuarioAux) && GestionRestaurantes.rs.getString(2).equals(passAux)){
                    userBD = GestionRestaurantes.rs.getString(1);
                    passBD = GestionRestaurantes.rs.getString(2);
                    tipoUsuario = GestionRestaurantes.rs.getString(3);
                    auxIdRes = GestionRestaurantes.rs.getInt(4);
                    superAdmin = GestionRestaurantes.rs.getInt(5);
                    check = 1;
                }               
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
        }
        /*System.out.println("userBD: "+userBD);
        System.out.println("passBD: "+passBD);
        
        System.out.println("userAux: "+usuarioAux);
        System.out.println("passAux: "+passAux);*/
        
        if(check == 1 && superAdmin == 1){
            this.dispose();
            gestionUsuarios gu = new gestionUsuarios();           
            gu.setVisible(true);               
            //JOptionPane.showMessageDialog(this, "ACCESO PERMITIDO");
            //System.out.println("Acceso exitoso"); 
            //System.out.println("recuerde que el usuario debe ser el correo");
        }
        else{
            
            JOptionPane.showMessageDialog(this, "ACCESO DENEGADO","AVISO", JOptionPane.PLAIN_MESSAGE, WrongIcon);
            System.out.println("Error al entrar");
            System.out.println("recuerde que el usuario debe ser el correo");
        }
        }
    }//GEN-LAST:event_gestionUsuariosKeyPressed

    private void ingresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingresarMouseEntered
        // TODO add your handling code here:
       ingresar.setBackground(new Color (102,102,102));
    }//GEN-LAST:event_ingresarMouseEntered

    private void ingresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingresarMouseExited
        // TODO add your handling code here:
        ingresar.setBackground(new Color (51,51,51));
    }//GEN-LAST:event_ingresarMouseExited

    private void gestionUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gestionUsuariosMouseEntered
        // TODO add your handling code here:
        gestionUsuarios.setBackground(new Color (102,102,102));
    }//GEN-LAST:event_gestionUsuariosMouseEntered

    private void gestionUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gestionUsuariosMouseExited
        // TODO add your handling code here:
        gestionUsuarios.setBackground(new Color (51,51,51));
    }//GEN-LAST:event_gestionUsuariosMouseExited

    private void SalirBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBMouseEntered
        // TODO add your handling code here:
        SalirB.setBackground(new Color (204,204,204));
    }//GEN-LAST:event_SalirBMouseEntered

    private void SalirBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirBMouseExited
        // TODO add your handling code here:
        SalirB.setBackground(new Color (153,153,153));
    }//GEN-LAST:event_SalirBMouseExited

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new login().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton SalirB;
    private javax.swing.JButton gestionUsuarios;
    private javax.swing.JButton ingresar;
    private javax.swing.JPasswordField passField;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}
