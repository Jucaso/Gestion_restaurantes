/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrestaurantesFinal;

import Clases.Cliente;
import Clases.GestionRestaurantes;
import Clases.Insumos;
import Clases.Pedido;
import Clases.Producto;
import Clases.Requiere;
import Clases.Restaurante;
import Clases.Tiene;
import Clases.pedidoDetalle;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
//import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Jucaso
 */
public class Operario extends javax.swing.JFrame {

    Vector producto = new Vector(), requiere = new Vector(), insumos = new Vector(), tiene = new Vector(),
           pedido = new Vector(), insumosAux = new Vector();
    Cliente cliente = new Cliente(0, "---", "---");
    int cantAux = 1, tieneID = 8510, pedidoID = 2510, auxCodP = 0, clienteIDaux  = 0, acc  = 1, accTiene = 1, lastCodAux = 0;
    String  restName = "", tipoPedido = "";
    Calendar fecha; 
    Vector restaurantes;
    public Operario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cantField.setText(Integer.toString(cantAux));
        setVectorProducto();
        setVectorRequiere();
        setVectorInsumos();
        asignaIDpedido();
        asignaIDpedidoTiene();
        setRestVector();
        
    }
    
    public int verificarProducto(int codigo){
        int aux = 0;
        for (int i = 0; i < producto.size(); i++) {
            if(((Producto)producto.elementAt(i)).verProId() == codigo){
                aux = 1;
            }
        }       
        if(aux == 1){
            return 1;
        }
        else{
            return 0;
        }              
    }
    public void setLastCodigoAud(){
        try {
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();    
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM aud_insertupdinsumos order by id;");
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                lastCodAux = GestionRestaurantes.rs.getInt(1);
                
            }
            System.out.println("lastCodAux: " + lastCodAux);
        } catch (Exception e) {
        }
    }
    public void getTipoProducto(int codigo){
        try {
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();    
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM producto order by id;"); 
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                if(codigo == GestionRestaurantes.rs.getInt(1)){
                    tipoPedido = GestionRestaurantes.rs.getString(5);
                }
            }
        } catch (Exception e) {
        }
        
    }
    public void generarYguardarPedido() throws FileNotFoundException, DocumentException{
        //FileOutputStream archivo = new FileOutputStream(pedidoD.verPedIdCliente() + ".pdf");
        //Document documento = new Document 
        //PdfWriter.getInstance(documento, archivo);
    }
    
    public void setRestVector(){
        restaurantes = new Vector();
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM restaurante order by nrolocal;");          
             //GestionRestaurantes.s.close();
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }        
        
        try {
            while(GestionRestaurantes.rs.next()){               
                    Restaurante rest;
                    rest = new Restaurante( GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getInt(1));
                    restaurantes.add(rest);                              
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar bro... " + e);
        }
    }
    public void asignaIDpedido(){
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM pedido order by id;");          
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }  
        
        try {
            while(GestionRestaurantes.rs.next()){
                pedidoID = GestionRestaurantes.rs.getInt(1) + acc;
            }
        } catch (Exception e) {
        }
        
    }
    
    public void asignaIDpedidoTiene(){
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM pedidotiene order by codigo;");          
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }  
        
        try {
            while(GestionRestaurantes.rs.next()){
                tieneID = GestionRestaurantes.rs.getInt(1) + accTiene;
            }
        } catch (Exception e) {
        }
        
    }
    public Producto getProducto(int idProducto){
        Producto pro = new Producto(0, "", 0);
        for (int i = 0; i < producto.size(); i++) {
            if(((Producto)producto.elementAt(i)).verProId() == idProducto){
                pro = new Producto(((Producto)producto.elementAt(i)).verProId(),
                ((Producto)producto.elementAt(i)).verProNombre(), ((Producto)producto.elementAt(i)).verProPrecio());
                break;
            }
        }        
        return pro;
    }
    
    public void setVectorProducto(){
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM producto order by id;");          
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }        
        
        try {
            while(GestionRestaurantes.rs.next()){
                if(GestionRestaurantes.rs.getInt(4) == login.auxIdRes){
                    Producto pro;
                    pro = new Producto(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2),
                    GestionRestaurantes.rs.getInt(3));
                    producto.add(pro);
                }               
            }
        } catch (Exception e) {
        }           
    }
    
    public void setVectorRequiere(){
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM requiere order by codigo;");          
            } catch (Exception e) {
                System.out.println("Error bro... " + e);
            }        

            try {
                while(GestionRestaurantes.rs.next()){
                    if(GestionRestaurantes.rs.getInt(5) == login.auxIdRes){
                        Requiere req;
                        req = new Requiere(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getInt(2),
                        GestionRestaurantes.rs.getInt(3), GestionRestaurantes.rs.getInt(4));
                        requiere.add(req);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al mostrar bro... " + e);
            }
    }
    
    public void setVectorInsumos(){
        try {
             GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
             GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM insumos order by id;");          
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
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        codProductoField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordenArea = new javax.swing.JTextArea();
        añadirP = new javax.swing.JButton();
        consultarDis = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        idField = new javax.swing.JTextField();
        nombreOpcBoton = new javax.swing.JTextField();
        regClienteBoton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        terminarBoton = new javax.swing.JButton();
        setClienteOrdenBoton = new javax.swing.JButton();
        consultarP = new javax.swing.JButton();
        cantField = new javax.swing.JTextField();
        masField = new javax.swing.JButton();
        menosField = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        clienteArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        SalirB = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(790, 489));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        codProductoField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(codProductoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 82, 200, -1));

        ordenArea.setEditable(false);
        ordenArea.setColumns(20);
        ordenArea.setRows(5);
        jScrollPane1.setViewportView(ordenArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 79, 233, 350));

        añadirP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añadirP.setForeground(new java.awt.Color(255, 255, 255));
        añadirP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        añadirP.setText("Añadir producto");
        añadirP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        añadirP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        añadirP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        añadirP.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        añadirP.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        añadirP.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        añadirP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirPActionPerformed(evt);
            }
        });
        getContentPane().add(añadirP, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 167, 179, 23));

        consultarDis.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        consultarDis.setForeground(new java.awt.Color(255, 255, 255));
        consultarDis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        consultarDis.setText("Consultar disponibilidad");
        consultarDis.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        consultarDis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        consultarDis.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        consultarDis.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        consultarDis.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        consultarDis.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        consultarDis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarDisActionPerformed(evt);
            }
        });
        getContentPane().add(consultarDis, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 199, 179, 19));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 181, 10));
        getContentPane().add(idField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 126, -1));
        getContentPane().add(nombreOpcBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 436, 128, -1));

        regClienteBoton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        regClienteBoton.setForeground(new java.awt.Color(255, 255, 255));
        regClienteBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        regClienteBoton.setText("Registrar cliente");
        regClienteBoton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        regClienteBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regClienteBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        regClienteBoton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        regClienteBoton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        regClienteBoton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        regClienteBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regClienteBotonActionPerformed(evt);
            }
        });
        getContentPane().add(regClienteBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 345, 181, 23));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 379, 181, 10));

        terminarBoton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        terminarBoton.setForeground(new java.awt.Color(255, 255, 255));
        terminarBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        terminarBoton.setText("TERMINAR");
        terminarBoton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        terminarBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        terminarBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        terminarBoton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        terminarBoton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        terminarBoton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        terminarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarBotonActionPerformed(evt);
            }
        });
        getContentPane().add(terminarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, 97, 23));

        setClienteOrdenBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setClienteOrdenBoton.setForeground(new java.awt.Color(255, 255, 255));
        setClienteOrdenBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        setClienteOrdenBoton.setText("->");
        setClienteOrdenBoton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        setClienteOrdenBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setClienteOrdenBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setClienteOrdenBoton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        setClienteOrdenBoton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        setClienteOrdenBoton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        setClienteOrdenBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setClienteOrdenBotonActionPerformed(evt);
            }
        });
        getContentPane().add(setClienteOrdenBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 40, 20));

        consultarP.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        consultarP.setForeground(new java.awt.Color(255, 255, 255));
        consultarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        consultarP.setText("Consultar productos");
        consultarP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        consultarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        consultarP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        consultarP.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        consultarP.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        consultarP.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        consultarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarPActionPerformed(evt);
            }
        });
        getContentPane().add(consultarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 210, 80));

        cantField.setEditable(false);
        cantField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(cantField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 126, 27, -1));

        masField.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        masField.setForeground(new java.awt.Color(255, 255, 255));
        masField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        masField.setText("+");
        masField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        masField.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        masField.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        masField.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        masField.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        masField.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        masField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masFieldActionPerformed(evt);
            }
        });
        getContentPane().add(masField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 126, 40, 20));

        menosField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        menosField.setForeground(new java.awt.Color(255, 255, 255));
        menosField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        menosField.setText("-");
        menosField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        menosField.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menosField.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menosField.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        menosField.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        menosField.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        menosField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosFieldActionPerformed(evt);
            }
        });
        getContentPane().add(menosField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 126, 37, 20));

        clienteArea.setEditable(false);
        clienteArea.setColumns(20);
        clienteArea.setRows(5);
        jScrollPane2.setViewportView(clienteArea);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 81, 254, 150));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton1.setText("->");
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 435, 47, 23));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton3.setText("REINICIAR");
        jButton3.setActionCommand("REINICIAR");
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
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 87, 23));

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
        getContentPane().add(SalirB, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 30, 30));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 228, 181, 10));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/Operario.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void masFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masFieldActionPerformed
            cantAux++;
            cantField.setText(Integer.toString(cantAux));              
    }//GEN-LAST:event_masFieldActionPerformed

    private void menosFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosFieldActionPerformed
        if(cantAux > 1){
        cantAux--;
        cantField.setText(Integer.toString(cantAux));
        }
    }//GEN-LAST:event_menosFieldActionPerformed

    private void añadirPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirPActionPerformed
        if(!codProductoField.getText().isEmpty() && CrearUsuario.isNumeric(codProductoField.getText())){
            if(verificarProducto(Integer.parseInt(codProductoField.getText())) == 1){
                auxCodP = Integer.parseInt(codProductoField.getText());
                Vector reqProuctoAux = new Vector();
           
                // Verificar si existen insumos para la preparación del producto (en caso de ser preparado)
                int check = 0;        
                for (int i = 0; i < requiere.size(); i++) {
                    for (int j = 0; j < insumos.size(); j++) {
                        if(((Requiere)requiere.elementAt(i)).verReqIdInsumos() == ((Insumos)insumos.elementAt(j)).verInsId()
                                && ((Requiere)requiere.elementAt(i)).verReqIdProducto() == auxCodP){
                            if(((Requiere)requiere.elementAt(i)).verReqCantidad() * Integer.parseInt(cantField.getText())
                                    > ((Insumos)insumos.elementAt(j)).verInsCantidad()){
                                check = 1;

                                break;
                                }
                                else{
                                    ((Insumos)insumos.elementAt(j)).modInsCantidad(((Insumos)insumos.elementAt(j)).verInsCantidad() - 
                                    ((Requiere)requiere.elementAt(i)).verReqCantidad() * Integer.parseInt(cantField.getText()));
                                     insumosAux.add(insumos.elementAt(j));
                                }
                            }
                        }
                        if(check == 1){
                            break;
                        }           
                    }

                    if(check == 0){
                        System.out.println("Hay disponibilidad");
                        System.out.println("tieneID: " + tieneID);
                        tieneID = tieneID + accTiene;
                        Tiene tie = new Tiene(tieneID, pedidoID, auxCodP, Integer.parseInt(cantField.getText()));
                        tiene.add(tie);

                        ordenArea.setText(ordenArea.getText() + 
                                "\nID producto: " + getProducto(auxCodP).verProId() +
                                "\nNombre: " + getProducto(auxCodP).verProNombre() +
                                "\nCantidad: " + cantField.getText() + 
                                "\n__________________________\n");
                        codProductoField.setText("");
                    }
                    else{
                         System.out.println("No hay disponibilidad");
                         JOptionPane.showMessageDialog(null, "PRODUCTO NO DISPONIBLE PARA ESA CANTIDAD", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
                    }
                    cantField.setText("1");
            }
            else{
                JOptionPane.showMessageDialog(null, "CÓDIGO NO ENCONTRADO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "CÓDIGO NO VALIDO", "ERROR", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            codProductoField.setText("");
        }
        
    }//GEN-LAST:event_añadirPActionPerformed

    private void setClienteOrdenBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setClienteOrdenBotonActionPerformed
        if(!idField.getText().isEmpty() && CrearUsuario.isNumeric(idField.getText())){
            int check = 0;
            try {
                GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery("SELECT * FROM cliente order by id");
            } catch (Exception e) {
                System.out.println("Error bro... " + e);
            }
            try {
                while(GestionRestaurantes.rs.next()){
                    if(Integer.parseInt(idField.getText()) == GestionRestaurantes.rs.getInt(1)
                            && login.auxIdRes == GestionRestaurantes.rs.getInt(4)){
                        cliente = new Cliente(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2),
                            GestionRestaurantes.rs.getString(3)); 
                        check = 1;
                        break;
                    }             
                }
            } catch (Exception e) {
                System.out.println("Error bro... " + e);
            }

            if(check == 1){
                clienteArea.setText("ID: " + cliente.verCliId() +
                        "\nNombre: " + cliente.verCliNombre() + 
                        "\nApellido: " + cliente.verCliApellido());
                clienteIDaux = cliente.verCliId();
            }
            else{
                JOptionPane.showMessageDialog(null, "CLIENTE NO ENCONTRADO", "ERROR", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "ID NO VÁLIDO", "ERROR", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
        }
        
        
        
    }//GEN-LAST:event_setClienteOrdenBotonActionPerformed

    private void regClienteBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regClienteBotonActionPerformed
        regCliente rc = new regCliente();
        rc.setVisible(true);
    }//GEN-LAST:event_regClienteBotonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!nombreOpcBoton.getText().isEmpty() && !CrearUsuario.isNumeric(nombreOpcBoton.getText())){
            clienteArea.setText("ID: " + "---" +
                    "\nNombre: " + nombreOpcBoton.getText());
            cliente.modCliNombre(nombreOpcBoton.getText());
        }
        else{
            JOptionPane.showMessageDialog(null, "NOMBRE NO VÁLIDO", "ERROR", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void consultarDisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarDisActionPerformed
        if(!codProductoField.getText().isEmpty() && CrearUsuario.isNumeric(codProductoField.getText())){
            if(verificarProducto(Integer.parseInt(codProductoField.getText())) == 1){
                int auxDiv = Integer.MAX_VALUE;      
                auxCodP = Integer.parseInt(codProductoField.getText());
                for (int i = 0; i < requiere.size(); i++) {
                    for (int j = 0; j < insumos.size(); j++) {
                        if(((Requiere)requiere.elementAt(i)).verReqIdInsumos() == ((Insumos)insumos.elementAt(j)).verInsId()
                                && ((Requiere)requiere.elementAt(i)).verReqIdProducto() == auxCodP){
                            //System.out.println("Division: "+ ((Insumos)insumos.elementAt(j)).verInsCantidad() / ((Requiere)requiere.elementAt(i)).verReqCantidad());
                            if(((Insumos)insumos.elementAt(j)).verInsCantidad() / ((Requiere)requiere.elementAt(i)).verReqCantidad() > 0){         
                                if(((Insumos)insumos.elementAt(j)).verInsCantidad() / ((Requiere)requiere.elementAt(i)).verReqCantidad() < auxDiv){
                                    auxDiv = ((Insumos)insumos.elementAt(j)).verInsCantidad() / ((Requiere)requiere.elementAt(i)).verReqCantidad();
                                    //System.out.println("auxDiv: "+auxDiv);
                                }                                      
                            }
                            else{
                                auxDiv = 0;
                                break;
                            }
                        }
                    }  
                    if(auxDiv == 0){
                        break;
                    }
                }

                JOptionPane.showMessageDialog(null, "SE PUEDEN PREPARAR ["+auxDiv+"] PRODUCTOS DEL CODIGO INGRESADO", "DISPONIBILIDAD", JOptionPane.PLAIN_MESSAGE, login.CheckIcon); 
            }
            else{
                JOptionPane.showMessageDialog(null, "CÓDIGO NO ENCONTRADO", "ADVERTENCIA", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "CÓDIGO NO VÁLIDO", "ERROR", JOptionPane.PLAIN_MESSAGE, login.WrongIcon);
            codProductoField.setText("");
        }
        
    }//GEN-LAST:event_consultarDisActionPerformed

    private void terminarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBotonActionPerformed
        if(!ordenArea.getText().isEmpty()){
            fecha = Calendar.getInstance();
            int dia = fecha.get(fecha.DAY_OF_MONTH);
            int mes = fecha.get(fecha.MONTH);
            int año = fecha.get(fecha.YEAR);
            String fechaAct = Integer.toString(año) + "-" + Integer.toString(mes + 1) + "-" + Integer.toString(dia);
            Pedido pedido = new Pedido(pedidoID, fechaAct);
            //System.out.println("Fecha actual: " + pedido.verPedFecha());
            pedidoDetalle pedidoD = new pedidoDetalle(pedidoID, login.auxIdEmp, clienteIDaux, login.auxIdRes);       

            // Agregar cambios a la base de datos

            // Ingresar pedido
            String auxUpdate = "INSERT INTO pedido values(" + pedido.verPedId() + "," +
                       "'" + pedido.verPedFecha() + "'" + "," + login.auxIdRes + ");";
            try {          
               GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
               GestionRestaurantes.s.executeUpdate(auxUpdate); 
            } catch (Exception e) {
                System.out.println("Error bro 1... " + e);
            }
            // Ingresar pedidoDetalle
            if(pedidoD.verPedIdCliente() == 0){
                String auxCliente = null;
                auxUpdate = "INSERT INTO pedidodetalle values(" + pedidoD.verPedId()+ "," +
                        auxCliente + "," + pedidoD.verPedIdEmpleado() + "," + pedidoD.verPedNroLocal() + ");";
            }
            else{         
                auxUpdate = "INSERT INTO pedidodetalle values(" + pedidoD.verPedId()+ "," +
                        pedidoD.verPedIdCliente() + ","+ pedidoD.verPedIdEmpleado() + "," +pedidoD.verPedNroLocal() + ");";
            }

            try {          
               GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
               GestionRestaurantes.s.executeUpdate(auxUpdate); 
            } catch (Exception e) {
                System.out.println("Error bro 2... " + e);
            }
            // Ingresar tiene
            for (int i = 0; i < tiene.size(); i++) {
                int codigo = ((Tiene)tiene.elementAt(i)).verTieCodigo();
                int idPedido = ((Tiene)tiene.elementAt(i)).verTieIdPedido();
                int idProducto = ((Tiene)tiene.elementAt(i)).verTieIdProducto();
                int idrest = login.auxIdRes;
                int cantidad = ((Tiene)tiene.elementAt(i)).vertieCantidad();
                auxUpdate = "INSERT INTO pedidotiene values(" + codigo + "," +
                        idPedido + "," + idProducto + "," + idrest + "," + cantidad +");";
                try {          
                   GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                   GestionRestaurantes.s.executeUpdate(auxUpdate); 
                } catch (Exception e) {
                    System.out.println("Error bro 3... " + e);
                }
            }
            // Actualizar inventario de insumos
            int auxDelete = insumosAux.size();
            for (int i = 0; i < insumosAux.size(); i++) {
                int id = ((Insumos)insumosAux.elementAt(i)).verInsId();
                int cantidadunidad  = ((Insumos)insumosAux.elementAt(i)).verInsCantidad();
                auxUpdate = "UPDATE insumos"
                        + " SET cantidadunidad = " + cantidadunidad
                        + " WHERE id = " + id + ";";  

                try {          
                   GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                   GestionRestaurantes.s.executeUpdate(auxUpdate); 
                } catch (Exception e) {
                    System.out.println("Error bro 4... " + e);
                }
            }
            setLastCodigoAud();
            for (int i = auxDelete; i > 0; i--) {
                auxUpdate = "DELETE "
                        + "FROM aud_insertupdinsumos "
                        + "WHERE id = " + lastCodAux + ";";
                try {
                    GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
                    GestionRestaurantes.s.executeUpdate(auxUpdate); 
                } catch (Exception e) {
                    System.out.println("Error bro... " + e);
                }
                lastCodAux--;
            }

            acc++;        

            codProductoField.setText("");
            idField.setText("");
            cantField.setText("1");
            nombreOpcBoton.setText("");
            ordenArea.setText("");
            clienteArea.setText("");

            for (int i = 0; i < restaurantes.size(); i++) {
                if(login.auxIdRes == ((Restaurante)restaurantes.elementAt(i)).verResId()){
                    restName = ((Restaurante)restaurantes.elementAt(i)).verResNombre();
                }
            }

            // Generación del archivo PDF de la factura
            try {  
                int totalAux = 0;
                FileOutputStream archivo = new FileOutputStream(pedido.verPedId() + ".pdf");
                Document documento = new Document(); 
                PdfWriter.getInstance(documento, archivo);
                documento.open();

                Paragraph parrafo = new Paragraph("FACTURA DE PEDIDO No° " + pedido.verPedId());
                parrafo.setAlignment(1);
                documento.add(parrafo);

                Paragraph parrafo1 = new Paragraph(restName);
                parrafo1.setAlignment(1);
                documento.add(parrafo1);

                Paragraph parrafo2 = new Paragraph("Local #" + Integer.toString(login.auxIdRes));
                parrafo2.setAlignment(1);
                documento.add(parrafo2);

                Paragraph parrafo3 = new Paragraph();
                parrafo3.setAlignment(1);
                documento.add(parrafo3);

                Paragraph parrafo4 = new Paragraph("ARTICULOS\n"
                        + "--------------------------------------------------------------------------------------------------------------------");
                documento.add(parrafo4);

                for (int i = 0; i < tiene.size(); i++) {
                    documento.add(new Paragraph(getProducto(((Tiene)tiene.elementAt(i)).verTieIdProducto()).verProNombre()
                    + "     x" + ((Tiene)tiene.elementAt(i)).vertieCantidad() 
                    + "     $" + getProducto(((Tiene)tiene.elementAt(i)).verTieIdProducto()).verProPrecio() * ((Tiene)tiene.elementAt(i)).vertieCantidad()));
                    totalAux = totalAux + getProducto(((Tiene)tiene.elementAt(i)).verTieIdProducto()).verProPrecio() * ((Tiene)tiene.elementAt(i)).vertieCantidad();
                }
                documento.add(new Paragraph("MONTO TOTAL PAGADO: $" + totalAux));
                Paragraph parrafo5 = new Paragraph("--------------------------------------------------------------------------------------------------------------------\n");
                documento.add(parrafo5);

                Paragraph parrafo6 = new Paragraph("Cliente: " + cliente.verCliNombre() + " " + cliente.verCliApellido());
                documento.add(parrafo6);

                Paragraph parrafo7 = new Paragraph("Fecha del pedido: " + pedido.verPedFecha());
                documento.add(parrafo7);

                documento.close();
                JOptionPane.showMessageDialog(null, "ORDEN REGISTRADA CON EXITO", "AVISO", JOptionPane.PLAIN_MESSAGE, login.CheckIcon);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        else{
            JOptionPane.showMessageDialog(null, "PEDIDO VACÍO NO VÁLIDO", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_terminarBotonActionPerformed

    private void consultarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarPActionPerformed
        mostrarProducto mp = new mostrarProducto();
        mp.setVisible(true);
    }//GEN-LAST:event_consultarPActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tiene.removeAllElements();
        insumos.removeAllElements();
        requiere.removeAllElements();
        ordenArea.setText("");
        clienteArea.setText("");
        codProductoField.setText("");
        idField.setText("");
        nombreOpcBoton.setText("");
        setVectorProducto();
        setVectorRequiere();
        setVectorInsumos();
        asignaIDpedido();
        asignaIDpedidoTiene();
        setRestVector();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Operario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Operario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Operario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Operario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Operario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SalirB;
    private javax.swing.JButton añadirP;
    private javax.swing.JTextField cantField;
    private javax.swing.JTextArea clienteArea;
    private javax.swing.JTextField codProductoField;
    private javax.swing.JButton consultarDis;
    private javax.swing.JButton consultarP;
    private javax.swing.JLabel fondo;
    private javax.swing.JTextField idField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton masField;
    private javax.swing.JButton menosField;
    private javax.swing.JTextField nombreOpcBoton;
    private javax.swing.JTextArea ordenArea;
    private javax.swing.JButton regClienteBoton;
    private javax.swing.JButton setClienteOrdenBoton;
    private javax.swing.JButton terminarBoton;
    // End of variables declaration//GEN-END:variables
}
