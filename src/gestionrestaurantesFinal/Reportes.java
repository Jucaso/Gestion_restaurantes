/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrestaurantesFinal;

import Clases.GestionRestaurantes;
import Clases.graficoReporte2;
import Clases.tablaReporte1;
import Clases.tablaReporte2;
import Clases.tablaReporte3;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 *
 * @author Jucaso
 */
public class Reportes extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    static Vector reporte = new Vector(), reporte1 = new Vector(), reporte2 = new Vector(), reporte3 = new Vector(), reporte4 = new Vector();
    Vector filtroDia = new Vector();
    static int auxGrafico = 0;
    static String fechaI = "", fechaF = "";
    
    public Reportes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
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
    
    public void generarPDF1(){
        String añoDesde = añoDesde1.getSelectedItem()+"";
        String mesDesde = mesDesde1.getSelectedItem()+"";
        String diaDesde = diaDesde1.getSelectedItem()+"";
        
        String añoHasta = añoHasta1.getSelectedItem()+"";
        String mesHasta = mesHasta1.getSelectedItem()+"";
        String diaHasta = diaHasta1.getSelectedItem()+"";
        
        String fechaDesde = añoDesde+"-"+mesDesde+"-"+diaDesde;
        String fechaHasta = añoHasta+"-"+mesHasta+"-"+diaHasta;
        
        // Obtener los datos entre fechas
        
        try {
            String auxQuery = "select aux2.id, aux2.nombre, sum(aux2.cantidad)\n" +
                            "from (select aux.id, aux.nombre, aux.cantidad\n" +
                            "	from (select producto.id, producto.nombre, pedidotiene.idrest, pedidotiene.cantidad, pedido.fecha\n" +
                            "		from pedidotiene inner join producto on pedidotiene.idproducto = producto.id\n" +
                            "		inner join pedido on pedido.id = pedidotiene.idpedido) as aux\n" +
                            "	where aux.fecha between " + "'" + fechaDesde + "'" + " and " + "'" + fechaHasta + "'" + ") as aux2\n" +
                            "group by aux2.id, aux2.nombre;";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        /*
        try {
            while(GestionRestaurantes.rs.next()){
                tablaReporte1 tb1 = new tablaReporte1(GestionRestaurantes.rs.getInt(1),
                        GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getInt(3));
                reporte.add(tb1);
            }
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        for (int i = 0; i < reporte.size(); i++) {
            System.out.println(((tablaReporte1)reporte.elementAt(i)).verIdProducto());
            System.out.println(((tablaReporte1)reporte.elementAt(i)).verNombreProducto());
            System.out.println(((tablaReporte1)reporte.elementAt(i)).verTotal());       
        }*/
        
        try {             
            int totalAux = 0;
            FileOutputStream archivo = new FileOutputStream("reporteTipo1.pdf");
            Document documento = new Document(); 
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            
            Paragraph parrafo = new Paragraph("CONSOLIDADO DE INFORMACIÓN DE PRODUCTOS VENDIDOS ENTRE LAS FECHAS \n" + fechaDesde + " HASTA " + fechaHasta + "\n\n");
            parrafo.setAlignment(1);
            documento.add(parrafo);
            
            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Id producto");
            tabla.addCell("Nombre");
            tabla.addCell("Cantidad vendida");
            
            try {
            while(GestionRestaurantes.rs.next()){
                tabla.addCell(Integer.toString(GestionRestaurantes.rs.getInt(1)));
                tabla.addCell(GestionRestaurantes.rs.getString(2));
                tabla.addCell(Integer.toString(GestionRestaurantes.rs.getInt(3)));               
            }
            } catch (Exception e) {
                System.out.println("Error bro... " + e);
            }
            documento.add(tabla);                                 
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte generado con exito");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void generarPDF2() throws FileNotFoundException, DocumentException{
        String añoDesde = añoDesde2.getSelectedItem()+"";
        String mesDesde = mesDesde2.getSelectedItem()+"";
        String diaDesde = diaDesde2.getSelectedItem()+"";
        
        String añoHasta = añoHasta2.getSelectedItem()+"";
        String mesHasta = mesHasta2.getSelectedItem()+"";
        String diaHasta = diaHasta2.getSelectedItem()+"";
        
        String fechaDesde = añoDesde+"-"+mesDesde+"-"+diaDesde;
        String fechaHasta = añoHasta+"-"+mesHasta+"-"+diaHasta;
        try {
            String auxQuery = "select aux2.id, aux2.nombre, aux2.fecha, sum(aux2.cantidad)\n" +
                            "from (select aux.id, aux.nombre, aux.cantidad, aux.fecha\n" +
                            "    from (select producto.id, producto.nombre, pedidotiene.idrest, pedidotiene.cantidad, pedido.fecha\n" +
                            "        from pedidotiene inner join producto on pedidotiene.idproducto = producto.id\n" +
                            "        inner join pedido on pedido.id = pedidotiene.idpedido) as aux\n" +
                            "    where aux.fecha between " + "'" + fechaDesde + "'" + " and " + "'" + fechaHasta + "'" +") as aux2\n" +
                            "group by aux2.id, aux2.nombre, aux2.fecha\n" +
                            "order by fecha asc;";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                tablaReporte2 tb2 = new tablaReporte2(GestionRestaurantes.rs.getInt(1),
                GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getString(3), GestionRestaurantes.rs.getInt(4));
                
                reporte.add(tb2);
            }
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        // Generación del consolidado en formato PDF
        FileOutputStream archivo = new FileOutputStream("reporteTipo2.pdf");
        Document documento = new Document(); 
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        
        Paragraph parrafo = new Paragraph("CONSOLIDADO DE INFORMACIÓN DE PRODUCTOS VENDIDOS ENTRE LAS FECHAS \n" + fechaDesde + " HASTA " + fechaHasta + " POR DÍA DE LA SEMANA\n\n");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        
        Paragraph parrafo1 = new Paragraph("LUNES:\n\n");       
        documento.add(parrafo1);
        
        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell("Id producto");
        tabla.addCell("Nombre");
        tabla.addCell("Fecha");
        tabla.addCell("Cantidad vendida");
                    
        // Filtro del día lunes
        int diaSemana = -1;
        Calendar fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 2){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tabla.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verIdProducto()));
            tabla.addCell(((tablaReporte2)filtroDia.elementAt(i)).verNombreProducto());
            String fecha = obtenerAño(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte2)filtroDia.elementAt(i)).verFecha());
            tabla.addCell(fecha);
            tabla.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verTotal()));
            }
            documento.add(tabla);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY VENTAS REGISTRADAS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día martes
        Paragraph parrafo2 = new Paragraph("MARTES:\n\n");       
        documento.add(parrafo2);
        
        PdfPTable tablaMartes = new PdfPTable(4);
        tablaMartes.addCell("Id producto");
        tablaMartes.addCell("Nombre");
        tablaMartes.addCell("Fecha");
        tablaMartes.addCell("Cantidad vendida");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 3){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaMartes.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verIdProducto()));
            tablaMartes.addCell(((tablaReporte2)filtroDia.elementAt(i)).verNombreProducto());
            String fecha = obtenerAño(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte2)filtroDia.elementAt(i)).verFecha());
            tablaMartes.addCell(fecha);
            tablaMartes.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verTotal()));
            }
            documento.add(tablaMartes);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY VENTAS REGISTRADAS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día miercoles
        Paragraph parrafo3 = new Paragraph("MIERCOLES:\n\n");       
        documento.add(parrafo3);
        
        PdfPTable tablaMiercoles = new PdfPTable(4);
        tablaMiercoles.addCell("Id producto");
        tablaMiercoles.addCell("Nombre");
        tablaMiercoles.addCell("Fecha");
        tablaMiercoles.addCell("Cantidad vendida");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 4){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaMiercoles.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verIdProducto()));
            tablaMiercoles.addCell(((tablaReporte2)filtroDia.elementAt(i)).verNombreProducto());
            String fecha = obtenerAño(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte2)filtroDia.elementAt(i)).verFecha());
            tablaMiercoles.addCell(fecha);
            tablaMiercoles.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verTotal()));
            }
            documento.add(tablaMiercoles);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY VENTAS REGISTRADAS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día jueves
        Paragraph parrafo4 = new Paragraph("JUEVES:\n\n");       
        documento.add(parrafo4);
        
        PdfPTable tablaJueves = new PdfPTable(4);
        tablaJueves.addCell("Id producto");
        tablaJueves.addCell("Nombre");
        tablaJueves.addCell("Fecha");
        tablaJueves.addCell("Cantidad vendida");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 5){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaJueves.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verIdProducto()));
            tablaJueves.addCell(((tablaReporte2)filtroDia.elementAt(i)).verNombreProducto());
            String fecha = obtenerAño(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte2)filtroDia.elementAt(i)).verFecha());
            tablaJueves.addCell(fecha);
            tablaJueves.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verTotal()));
            }
            documento.add(tablaJueves);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY VENTAS REGISTRADAS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día miercoles
        Paragraph parrafo5 = new Paragraph("VIERNES:\n\n");       
        documento.add(parrafo5);
        
        PdfPTable tablaViernes = new PdfPTable(4);
        tablaViernes.addCell("Id producto");
        tablaViernes.addCell("Nombre");
        tablaViernes.addCell("Fecha");
        tablaViernes.addCell("Cantidad vendida");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 6){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaViernes.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verIdProducto()));
            tablaViernes.addCell(((tablaReporte2)filtroDia.elementAt(i)).verNombreProducto());
            String fecha = obtenerAño(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte2)filtroDia.elementAt(i)).verFecha());
            tablaViernes.addCell(fecha);
            tablaViernes.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verTotal()));
            }
            documento.add(tablaViernes);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY VENTAS REGISTRADAS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día miercoles
        Paragraph parrafo6 = new Paragraph("SÁBADO:\n\n");       
        documento.add(parrafo6);
        
        PdfPTable tablaSabado = new PdfPTable(4);
        tablaSabado.addCell("Id producto");
        tablaSabado.addCell("Nombre");
        tablaSabado.addCell("Fecha");
        tablaSabado.addCell("Cantidad vendida");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 7){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaSabado.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verIdProducto()));
            tablaSabado.addCell(((tablaReporte2)filtroDia.elementAt(i)).verNombreProducto());
            String fecha = obtenerAño(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte2)filtroDia.elementAt(i)).verFecha());
            tablaSabado.addCell(fecha);
            tablaSabado.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verTotal()));
            }
            documento.add(tablaSabado);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY VENTAS REGISTRADAS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día miercoles
        Paragraph parrafo7 = new Paragraph("DOMINGO:\n\n");       
        documento.add(parrafo7);
        
        PdfPTable tablaDomingo= new PdfPTable(4);
        tablaDomingo.addCell("Id producto");
        tablaDomingo.addCell("Nombre");
        tablaDomingo.addCell("Fecha");
        tablaDomingo.addCell("Cantidad vendida");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 1){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaDomingo.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verIdProducto()));
            tablaDomingo.addCell(((tablaReporte2)filtroDia.elementAt(i)).verNombreProducto());
            String fecha = obtenerAño(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte2)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte2)filtroDia.elementAt(i)).verFecha());
            tablaDomingo.addCell(fecha);
            tablaDomingo.addCell(Integer.toString(((tablaReporte2)filtroDia.elementAt(i)).verTotal()));
            }
            documento.add(tablaDomingo);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY VENTAS REGISTRADAS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        reporte.removeAllElements();
        JOptionPane.showMessageDialog(null, "Reporte generado con éxito");
        documento.close();
    }
    
    public void generarPDF3() throws FileNotFoundException, DocumentException{
        String añoDesde = añoDesde3.getSelectedItem()+"";
        String mesDesde = mesDesde3.getSelectedItem()+"";
        String diaDesde = diaDesde3.getSelectedItem()+"";
        
        String añoHasta = añoHasta3.getSelectedItem()+"";
        String mesHasta = mesHasta3.getSelectedItem()+"";
        String diaHasta = diaHasta3.getSelectedItem()+"";
        
        String fechaDesde = añoDesde+"-"+mesDesde+"-"+diaDesde;
        String fechaHasta = añoHasta+"-"+mesHasta+"-"+diaHasta;
        
        try {
            String auxQuery = "select ins.id, ins.nombre, aud.cantidadagregada, aud.fecha\n" +
                            "from insumos ins inner join aud_insertupdinsumos as aud on ins.id = aud.codinsumo\n" +
                            "where aud.fecha between " + "'" +  fechaDesde + "'" + " and " + "'" + fechaHasta + "'" + ";";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        try {
            while(GestionRestaurantes.rs.next()){
                tablaReporte3 tr3 = new tablaReporte3(GestionRestaurantes.rs.getInt(1),
                        GestionRestaurantes.rs.getString(2),
                        GestionRestaurantes.rs.getInt(3),
                        GestionRestaurantes.rs.getString(4));
                reporte.add(tr3);
            }
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        
        // Generación del consolidado en formato PDF
        FileOutputStream archivo = new FileOutputStream("reporteTipo3.pdf");
        Document documento = new Document(); 
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        
        Paragraph parrafo = new Paragraph("CONSOLIDADO DE INFORMACIÓN DE INSUMOS REGISTRADOS ENTRE LAS FECHAS \n" + fechaDesde + " HASTA " + fechaHasta + " POR DÍA DE LA SEMANA\n\n");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        
        Paragraph parrafo1 = new Paragraph("LUNES:\n\n");       
        documento.add(parrafo1);
        
        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell("Id insumo");
        tabla.addCell("Nombre");
        tabla.addCell("Fecha");
        tabla.addCell("Cantidad registrada");
                    
        // Filtro del día lunes
        int diaSemana = -1;
        Calendar fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 2){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tabla.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verIdInsumo()));
            tabla.addCell(((tablaReporte3)filtroDia.elementAt(i)).verNombreInsumo());
            String fecha = obtenerAño(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte3)filtroDia.elementAt(i)).verFecha());
            tabla.addCell(fecha);
            tabla.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada()));
            }
            documento.add(tabla);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY INSUMOS REGISTRADOS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día martes
        Paragraph parrafo2 = new Paragraph("MARTES:\n\n");       
        documento.add(parrafo2);
        
        PdfPTable tablaMartes = new PdfPTable(4);
        tablaMartes.addCell("Id insumo");
        tablaMartes.addCell("Nombre");
        tablaMartes.addCell("Fecha");
        tablaMartes.addCell("Cantidad registrada");
        
        
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 3){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaMartes.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verIdInsumo()));
            tablaMartes.addCell(((tablaReporte3)filtroDia.elementAt(i)).verNombreInsumo());
            String fecha = obtenerAño(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte3)filtroDia.elementAt(i)).verFecha());
            tablaMartes.addCell(fecha);
            tablaMartes.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada()));
            }
            documento.add(tablaMartes);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY INSUMOS REGISTRADOS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día miercoles
        Paragraph parrafo3 = new Paragraph("MIERCOLES:\n\n");       
        documento.add(parrafo3);
        
        PdfPTable tablaMiercoles = new PdfPTable(4);
        tablaMiercoles.addCell("Id insumo");
        tablaMiercoles.addCell("Nombre");
        tablaMiercoles.addCell("Fecha");
        tablaMiercoles.addCell("Cantidad registrada");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 4){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaMiercoles.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verIdInsumo()));
            tablaMiercoles.addCell(((tablaReporte3)filtroDia.elementAt(i)).verNombreInsumo());
            String fecha = obtenerAño(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte3)filtroDia.elementAt(i)).verFecha());
            tablaMiercoles.addCell(fecha);
            tablaMiercoles.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada()));
            }
            documento.add(tablaMiercoles);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY INSUMOS REGISTRADOS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día jueves
        Paragraph parrafo4 = new Paragraph("JUEVES:\n\n");       
        documento.add(parrafo4);
        
        PdfPTable tablaJueves = new PdfPTable(4);
        tablaJueves.addCell("Id insumo");
        tablaJueves.addCell("Nombre");
        tablaJueves.addCell("Fecha");
        tablaJueves.addCell("Cantidad registrada");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 5){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaJueves.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verIdInsumo()));
            tablaJueves.addCell(((tablaReporte3)filtroDia.elementAt(i)).verNombreInsumo());
            String fecha = obtenerAño(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte3)filtroDia.elementAt(i)).verFecha());
            tablaJueves.addCell(fecha);
            tablaJueves.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada()));
            }
            documento.add(tablaJueves);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY INSUMOS REGISTRADOS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día viernes
        Paragraph parrafo5 = new Paragraph("VIERNES:\n\n");       
        documento.add(parrafo5);
        
        PdfPTable tablaViernes = new PdfPTable(4);
        tablaViernes.addCell("Id insumo");
        tablaViernes.addCell("Nombre");
        tablaViernes.addCell("Fecha");
        tablaViernes.addCell("Cantidad registrada");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 6){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaViernes.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verIdInsumo()));
            tablaViernes.addCell(((tablaReporte3)filtroDia.elementAt(i)).verNombreInsumo());
            String fecha = obtenerAño(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte3)filtroDia.elementAt(i)).verFecha());
            tablaViernes.addCell(fecha);
            tablaViernes.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada()));
            }
            documento.add(tablaViernes);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY INSUMOS REGISTRADOS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día sabado
        Paragraph parrafo6 = new Paragraph("SÁBADO:\n\n");       
        documento.add(parrafo6);
        
        PdfPTable tablaSabado = new PdfPTable(4);
        tablaSabado.addCell("Id insumo");
        tablaSabado.addCell("Nombre");
        tablaSabado.addCell("Fecha");
        tablaSabado.addCell("Cantidad registrada");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 7){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaSabado.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verIdInsumo()));
            tablaSabado.addCell(((tablaReporte3)filtroDia.elementAt(i)).verNombreInsumo());
            String fecha = obtenerAño(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte3)filtroDia.elementAt(i)).verFecha());
            tablaSabado.addCell(fecha);
            tablaSabado.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada()));
            }
            documento.add(tablaSabado);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY INSUMOS REGISTRADOS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        
        // Filtro del día domingo
        Paragraph parrafo7 = new Paragraph("DOMINGO:\n\n");       
        documento.add(parrafo7);
        
        PdfPTable tablaDomingo= new PdfPTable(4);
        tablaDomingo.addCell("Id insumo");
        tablaDomingo.addCell("Nombre");
        tablaDomingo.addCell("Fecha");
        tablaDomingo.addCell("Cantidad registrada");
        
        diaSemana = -1;
        fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte3)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            
            if(diaSemana == 1){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        if(filtroDia.size() != 0){
            for (int i = 0; i < filtroDia.size(); i++) {
            tablaDomingo.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verIdInsumo()));
            tablaDomingo.addCell(((tablaReporte3)filtroDia.elementAt(i)).verNombreInsumo());
            String fecha = obtenerAño(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerMes(((tablaReporte3)filtroDia.elementAt(i)).verFecha()) + "-" +
                           obtenerDia(((tablaReporte3)filtroDia.elementAt(i)).verFecha());
            tablaDomingo.addCell(fecha);
            tablaDomingo.addCell(Integer.toString(((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada()));
            }
            documento.add(tablaDomingo);
        }
        else{
            Paragraph noVentas = new Paragraph("NO HAY INSUMOS REGISTRADOS\n\n");  
            noVentas.setAlignment(1);
            documento.add(noVentas);
        }
        filtroDia.removeAllElements();
        reporte.removeAllElements();
        documento.close();
        JOptionPane.showMessageDialog(null, "Reporte generado con éxito");       
    }
    
    public void generarPDF4() throws FileNotFoundException, DocumentException{
        /*String añoDesde = añoDesde1.getSelectedItem()+"";
        String mesDesde = mesDesde1.getSelectedItem()+"";
        String diaDesde = diaDesde1.getSelectedItem()+"";
        
        String añoHasta = añoHasta1.getSelectedItem()+"";
        String mesHasta = mesHasta1.getSelectedItem()+"";
        String diaHasta = diaHasta1.getSelectedItem()+"";
        
        String fechaDesde = añoDesde+"-"+mesDesde+"-"+diaDesde;
        String fechaHasta = añoHasta+"-"+mesHasta+"-"+diaHasta;*/
        
        // Obtener los datos entre fechas
        
        try {
            String auxQuery = "select restaurante.nrolocal, restaurante.nombre, aux2.promedioVentasDiarias\n" +
                            "from (select aux.idrest, avg(aux.count) as promedioVentasDiarias\n" +
                            "from (select fecha, idrest, count(fecha)\n" +
                            "        from pedido\n" +
                            "        group by fecha, idrest) as aux\n" +
                            "group by idrest) as aux2\n" +
                            "inner join restaurante on aux2.idrest = restaurante.nrolocal;";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        //Reporte PDF
        
            FileOutputStream archivo = new FileOutputStream("reporteTipo4.pdf");
            Document documento = new Document(); 
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            
            Paragraph parrafo = new Paragraph("CONSOLIDADO DE INFORMACIÓN DE VENTAS PROMEDIO AL DIA DE LOS RESTAURANTES \n\n");
            parrafo.setAlignment(1);
            documento.add(parrafo);
            
            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Número de local");
            tabla.addCell("Nombre");
            tabla.addCell("Promedio de ventas");
            
            try {
            while(GestionRestaurantes.rs.next()){
                tabla.addCell(Integer.toString(GestionRestaurantes.rs.getInt(1)));
                tabla.addCell(GestionRestaurantes.rs.getString(2));
                tabla.addCell(Integer.toString(GestionRestaurantes.rs.getInt(3)));               
            }
            } catch (Exception e) {
                System.out.println("Error bro... " + e);
            }
            documento.add(tabla);                                 
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte generado con exito");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        añoDesde1 = new javax.swing.JComboBox<>();
        mesDesde1 = new javax.swing.JComboBox<>();
        diaDesde1 = new javax.swing.JComboBox<>();
        añoHasta1 = new javax.swing.JComboBox<>();
        mesHasta1 = new javax.swing.JComboBox<>();
        diaHasta1 = new javax.swing.JComboBox<>();
        diaDesde2 = new javax.swing.JComboBox<>();
        añoHasta2 = new javax.swing.JComboBox<>();
        mesHasta2 = new javax.swing.JComboBox<>();
        diaHasta2 = new javax.swing.JComboBox<>();
        añoDesde2 = new javax.swing.JComboBox<>();
        mesDesde2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        añoDesde3 = new javax.swing.JComboBox<>();
        mesDesde3 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        diaDesde3 = new javax.swing.JComboBox<>();
        añoHasta3 = new javax.swing.JComboBox<>();
        mesHasta3 = new javax.swing.JComboBox<>();
        diaHasta3 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        generarGrafico1 = new javax.swing.JButton();
        generarGrafico3 = new javax.swing.JButton();
        generarGrafico4 = new javax.swing.JButton();
        generarGrafico2 = new javax.swing.JButton();
        SalirB = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(675, 388));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(675, 388));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 640, 11));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton1.setText("Generar PDF");
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 80, 30));

        añoDesde1.setBackground(new java.awt.Color(102, 102, 102));
        añoDesde1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añoDesde1.setForeground(new java.awt.Color(255, 255, 255));
        añoDesde1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", " " }));
        añoDesde1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                añoBisisestoDesde1(evt);
            }
        });
        añoDesde1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añoDesde1ActionPerformed(evt);
            }
        });
        getContentPane().add(añoDesde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 97, 60, -1));

        mesDesde1.setBackground(new java.awt.Color(102, 102, 102));
        mesDesde1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesDesde1.setForeground(new java.awt.Color(255, 255, 255));
        mesDesde1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " ", " " }));
        mesDesde1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesBisisestoDesde1(evt);
            }
        });
        getContentPane().add(mesDesde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 97, 43, -1));

        diaDesde1.setBackground(new java.awt.Color(102, 102, 102));
        diaDesde1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diaDesde1.setForeground(new java.awt.Color(255, 255, 255));
        diaDesde1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", " " }));
        getContentPane().add(diaDesde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 97, 44, -1));

        añoHasta1.setBackground(new java.awt.Color(102, 102, 102));
        añoHasta1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añoHasta1.setForeground(new java.awt.Color(255, 255, 255));
        añoHasta1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", " " }));
        añoHasta1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                añoBisiestoHasta1(evt);
            }
        });
        getContentPane().add(añoHasta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 60, -1));

        mesHasta1.setBackground(new java.awt.Color(102, 102, 102));
        mesHasta1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesHasta1.setForeground(new java.awt.Color(255, 255, 255));
        mesHasta1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        mesHasta1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesBisiestoHasta1(evt);
            }
        });
        getContentPane().add(mesHasta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 140, 43, -1));

        diaHasta1.setBackground(new java.awt.Color(102, 102, 102));
        diaHasta1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diaHasta1.setForeground(new java.awt.Color(255, 255, 255));
        diaHasta1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(diaHasta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 44, -1));

        diaDesde2.setBackground(new java.awt.Color(102, 102, 102));
        diaDesde2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diaDesde2.setForeground(new java.awt.Color(255, 255, 255));
        diaDesde2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(diaDesde2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 44, -1));

        añoHasta2.setBackground(new java.awt.Color(102, 102, 102));
        añoHasta2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añoHasta2.setForeground(new java.awt.Color(255, 255, 255));
        añoHasta2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", " " }));
        añoHasta2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                añoBisiestoHasta2(evt);
            }
        });
        getContentPane().add(añoHasta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 60, -1));

        mesHasta2.setBackground(new java.awt.Color(102, 102, 102));
        mesHasta2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesHasta2.setForeground(new java.awt.Color(255, 255, 255));
        mesHasta2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        mesHasta2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesBisiestoHasta2(evt);
            }
        });
        getContentPane().add(mesHasta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 43, -1));

        diaHasta2.setBackground(new java.awt.Color(102, 102, 102));
        diaHasta2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diaHasta2.setForeground(new java.awt.Color(255, 255, 255));
        diaHasta2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(diaHasta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 44, -1));

        añoDesde2.setBackground(new java.awt.Color(102, 102, 102));
        añoDesde2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añoDesde2.setForeground(new java.awt.Color(255, 255, 255));
        añoDesde2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", " " }));
        añoDesde2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                añoBisisestoDesde2(evt);
            }
        });
        getContentPane().add(añoDesde2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 60, -1));

        mesDesde2.setBackground(new java.awt.Color(102, 102, 102));
        mesDesde2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesDesde2.setForeground(new java.awt.Color(255, 255, 255));
        mesDesde2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        mesDesde2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesBisisestoDesde2(evt);
            }
        });
        getContentPane().add(mesDesde2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 43, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton2.setText("Generar PDF");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 90, 30));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 643, 10));

        añoDesde3.setBackground(new java.awt.Color(102, 102, 102));
        añoDesde3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añoDesde3.setForeground(new java.awt.Color(255, 255, 255));
        añoDesde3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", " " }));
        añoDesde3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                añoBisiestoDesde3(evt);
            }
        });
        getContentPane().add(añoDesde3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 257, 60, -1));

        mesDesde3.setBackground(new java.awt.Color(102, 102, 102));
        mesDesde3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesDesde3.setForeground(new java.awt.Color(255, 255, 255));
        mesDesde3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        mesDesde3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesBisisestoDesde3(evt);
            }
        });
        getContentPane().add(mesDesde3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 257, 43, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton3.setText("Generar PDF");
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
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 93, 23));

        diaDesde3.setBackground(new java.awt.Color(102, 102, 102));
        diaDesde3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diaDesde3.setForeground(new java.awt.Color(255, 255, 255));
        diaDesde3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(diaDesde3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 257, 44, -1));

        añoHasta3.setBackground(new java.awt.Color(102, 102, 102));
        añoHasta3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        añoHasta3.setForeground(new java.awt.Color(255, 255, 255));
        añoHasta3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", " " }));
        añoHasta3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                añoBisiestoHasta3(evt);
            }
        });
        getContentPane().add(añoHasta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 60, -1));

        mesHasta3.setBackground(new java.awt.Color(102, 102, 102));
        mesHasta3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesHasta3.setForeground(new java.awt.Color(255, 255, 255));
        mesHasta3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        mesHasta3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesBisiestoHasta3(evt);
            }
        });
        getContentPane().add(mesHasta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 43, -1));

        diaHasta3.setBackground(new java.awt.Color(102, 102, 102));
        diaHasta3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diaHasta3.setForeground(new java.awt.Color(255, 255, 255));
        diaHasta3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(diaHasta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 44, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        jButton4.setText("Generar PDF");
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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 93, 40));

        generarGrafico1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        generarGrafico1.setForeground(new java.awt.Color(255, 255, 255));
        generarGrafico1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        generarGrafico1.setText("Generar gráfico");
        generarGrafico1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        generarGrafico1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generarGrafico1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generarGrafico1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        generarGrafico1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarGrafico1ActionPerformed(evt);
            }
        });
        getContentPane().add(generarGrafico1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 100, 30));

        generarGrafico3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        generarGrafico3.setForeground(new java.awt.Color(255, 255, 255));
        generarGrafico3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        generarGrafico3.setText("Generar gráfico");
        generarGrafico3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        generarGrafico3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generarGrafico3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generarGrafico3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        generarGrafico3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarGrafico3ActionPerformed(evt);
            }
        });
        getContentPane().add(generarGrafico3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 93, 23));

        generarGrafico4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        generarGrafico4.setForeground(new java.awt.Color(255, 255, 255));
        generarGrafico4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        generarGrafico4.setText("Generar gráfico");
        generarGrafico4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        generarGrafico4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generarGrafico4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generarGrafico4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        generarGrafico4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarGrafico4ActionPerformed(evt);
            }
        });
        getContentPane().add(generarGrafico4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 100, 40));

        generarGrafico2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        generarGrafico2.setForeground(new java.awt.Color(255, 255, 255));
        generarGrafico2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotones.jpg"))); // NOI18N
        generarGrafico2.setText("Generar gráfico");
        generarGrafico2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        generarGrafico2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generarGrafico2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generarGrafico2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressedP.jpg"))); // NOI18N
        generarGrafico2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/FondoBotonesPressed.jpg"))); // NOI18N
        generarGrafico2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarGrafico2ActionPerformed(evt);
            }
        });
        getContentPane().add(generarGrafico2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 100, 30));

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
        getContentPane().add(SalirB, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 30, 30));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Diseño/Reportes.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 675, 388));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        generarPDF1();
        try {
            File path = new File("reporteTipo1.pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            generarPDF2();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            File path = new File("reporteTipo2.pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            generarPDF3();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            File path = new File("reporteTipo3.pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            generarPDF4();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            File path = new File("reporteTipo4.pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void añoDesde1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añoDesde1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_añoDesde1ActionPerformed

    private void añoBisisestoDesde1(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_añoBisisestoDesde1
        mesDesde1.setSelectedIndex(0);
        diaDesde1.setSelectedIndex(0);
    }//GEN-LAST:event_añoBisisestoDesde1

    private void mesBisisestoDesde1(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesBisisestoDesde1
        int año = Integer.parseInt(añoDesde1.getSelectedItem()+"");
        int mes = Integer.parseInt(mesDesde1.getSelectedItem()+"");

        diaDesde1.removeAllItems();
        if(((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) &&  mes == 02){
            for (int i = 0; i < 29; i++) {               
                diaDesde1.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 02){
            for (int i = 0; i < 28; i++) {               
                diaDesde1.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            for (int i = 0; i < 30; i++) {               
                diaDesde1.addItem(Integer.toString(i+1));
            }
        }
        else{
            for (int i = 0; i < 31; i++) {               
                diaDesde1.addItem(Integer.toString(i+1));
            }
        }
    }//GEN-LAST:event_mesBisisestoDesde1

    private void añoBisisestoDesde2(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_añoBisisestoDesde2
        mesDesde2.setSelectedIndex(0);
        diaDesde2.setSelectedIndex(0);
    }//GEN-LAST:event_añoBisisestoDesde2

    private void mesBisisestoDesde2(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesBisisestoDesde2
        int año = Integer.parseInt(añoDesde2.getSelectedItem()+"");
        int mes = Integer.parseInt(mesDesde2.getSelectedItem()+"");

        diaDesde2.removeAllItems();
        if(((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) &&  mes == 02){
            for (int i = 0; i < 29; i++) {               
                diaDesde2.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 02){
            for (int i = 0; i < 28; i++) {               
                diaDesde2.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            for (int i = 0; i < 30; i++) {               
                diaDesde2.addItem(Integer.toString(i+1));
            }
        }
        else{
            for (int i = 0; i < 31; i++) {               
                diaDesde2.addItem(Integer.toString(i+1));
            }
        }
    }//GEN-LAST:event_mesBisisestoDesde2

    private void añoBisiestoDesde3(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_añoBisiestoDesde3
        mesDesde3.setSelectedIndex(0);
        diaDesde3.setSelectedIndex(0);
    }//GEN-LAST:event_añoBisiestoDesde3

    private void mesBisisestoDesde3(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesBisisestoDesde3
        int año = Integer.parseInt(añoDesde3.getSelectedItem()+"");
        int mes = Integer.parseInt(mesDesde3.getSelectedItem()+"");

        diaDesde3.removeAllItems();
        if(((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) &&  mes == 02){
            for (int i = 0; i < 29; i++) {               
                diaDesde3.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 02){
            for (int i = 0; i < 28; i++) {               
                diaDesde3.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            for (int i = 0; i < 30; i++) {               
                diaDesde3.addItem(Integer.toString(i+1));
            }
        }
        else{
            for (int i = 0; i < 31; i++) {               
                diaDesde3.addItem(Integer.toString(i+1));
            }
        }
    }//GEN-LAST:event_mesBisisestoDesde3

    private void añoBisiestoHasta1(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_añoBisiestoHasta1
        mesHasta1.setSelectedIndex(0);
        diaHasta1.setSelectedIndex(0);
    }//GEN-LAST:event_añoBisiestoHasta1

    private void mesBisiestoHasta1(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesBisiestoHasta1
        int año = Integer.parseInt(añoHasta1.getSelectedItem()+"");
        int mes = Integer.parseInt(mesHasta1.getSelectedItem()+"");

        diaHasta1.removeAllItems();
        if(((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) &&  mes == 02){
            for (int i = 0; i < 29; i++) {               
                diaHasta1.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 02){
            for (int i = 0; i < 28; i++) {               
                diaHasta1.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            for (int i = 0; i < 30; i++) {               
                diaHasta1.addItem(Integer.toString(i+1));
            }
        }
        else{
            for (int i = 0; i < 31; i++) {               
                diaHasta1.addItem(Integer.toString(i+1));
            }
        }
    }//GEN-LAST:event_mesBisiestoHasta1

    private void añoBisiestoHasta2(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_añoBisiestoHasta2
        mesHasta2.setSelectedIndex(0);
        diaHasta2.setSelectedIndex(0);
    }//GEN-LAST:event_añoBisiestoHasta2

    private void mesBisiestoHasta2(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesBisiestoHasta2
        int año = Integer.parseInt(añoHasta2.getSelectedItem()+"");
        int mes = Integer.parseInt(mesHasta2.getSelectedItem()+"");

        diaHasta2.removeAllItems();
        if(((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) &&  mes == 02){
            for (int i = 0; i < 29; i++) {               
                diaHasta2.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 02){
            for (int i = 0; i < 28; i++) {               
                diaHasta2.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            for (int i = 0; i < 30; i++) {               
                diaHasta2.addItem(Integer.toString(i+1));
            }
        }
        else{
            for (int i = 0; i < 31; i++) {               
                diaHasta2.addItem(Integer.toString(i+1));
            }
        }
    }//GEN-LAST:event_mesBisiestoHasta2

    private void añoBisiestoHasta3(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_añoBisiestoHasta3
        mesHasta3.setSelectedIndex(0);
        diaHasta3.setSelectedIndex(0);
    }//GEN-LAST:event_añoBisiestoHasta3

    private void mesBisiestoHasta3(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesBisiestoHasta3
        int año = Integer.parseInt(añoHasta3.getSelectedItem()+"");
        int mes = Integer.parseInt(mesHasta3.getSelectedItem()+"");

        diaHasta3.removeAllItems();
        if(((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) &&  mes == 02){
            for (int i = 0; i < 29; i++) {               
                diaHasta3.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 02){
            for (int i = 0; i < 28; i++) {               
                diaHasta3.addItem(Integer.toString(i+1));
            }
        }
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            for (int i = 0; i < 30; i++) {               
                diaHasta3.addItem(Integer.toString(i+1));
            }
        }
        else{
            for (int i = 0; i < 31; i++) {               
                diaHasta3.addItem(Integer.toString(i+1));
            }
        }
    }//GEN-LAST:event_mesBisiestoHasta3

    private void generarGrafico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarGrafico1ActionPerformed
        String añoDesde = añoDesde1.getSelectedItem()+"";
        String mesDesde = mesDesde1.getSelectedItem()+"";
        String diaDesde = diaDesde1.getSelectedItem()+"";
        
        String añoHasta = añoHasta1.getSelectedItem()+"";
        String mesHasta = mesHasta1.getSelectedItem()+"";
        String diaHasta = diaHasta1.getSelectedItem()+"";
        
        String fechaDesde = añoDesde+"-"+mesDesde+"-"+diaDesde;
        String fechaHasta = añoHasta+"-"+mesHasta+"-"+diaHasta;
        
        fechaI = fechaDesde;
        fechaF = fechaHasta;
        // Obtener los datos entre fechas
        
        try {
            String auxQuery = "select aux2.id, aux2.nombre, sum(aux2.cantidad)\n" +
                            "from (select aux.id, aux.nombre, aux.cantidad\n" +
                            "	from (select producto.id, producto.nombre, pedidotiene.idrest, pedidotiene.cantidad, pedido.fecha\n" +
                            "		from pedidotiene inner join producto on pedidotiene.idproducto = producto.id\n" +
                            "		inner join pedido on pedido.id = pedidotiene.idpedido) as aux\n" +
                            "	where aux.fecha between " + "'" + fechaDesde + "'" + " and " + "'" + fechaHasta + "'" + ") as aux2\n" +
                            "group by aux2.id, aux2.nombre;";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        try {
            while(GestionRestaurantes.rs.next()){
            tablaReporte1 tb1 = new tablaReporte1(GestionRestaurantes.rs.getInt(1), GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getInt(3));
            reporte1.add(tb1);
            }
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        auxGrafico = 1;
        grafico g1 = new grafico();
        g1.setVisible(true);
        
    }//GEN-LAST:event_generarGrafico1ActionPerformed

    private void generarGrafico2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarGrafico2ActionPerformed
        String añoDesde = añoDesde2.getSelectedItem()+"";
        String mesDesde = mesDesde2.getSelectedItem()+"";
        String diaDesde = diaDesde2.getSelectedItem()+"";
        
        String añoHasta = añoHasta2.getSelectedItem()+"";
        String mesHasta = mesHasta2.getSelectedItem()+"";
        String diaHasta = diaHasta2.getSelectedItem()+"";
        
        String fechaDesde = añoDesde+"-"+mesDesde+"-"+diaDesde;
        String fechaHasta = añoHasta+"-"+mesHasta+"-"+diaHasta;
        
        fechaI = fechaDesde;
        fechaF = fechaHasta;
        // Obtener los datos entre fechas
        
        try {
            String auxQuery = "select aux2.id, aux2.nombre, aux2.fecha, sum(aux2.cantidad)\n" +
                            "from (select aux.id, aux.nombre, aux.cantidad, aux.fecha\n" +
                            "    from (select producto.id, producto.nombre, pedidotiene.idrest, pedidotiene.cantidad, pedido.fecha\n" +
                            "        from pedidotiene inner join producto on pedidotiene.idproducto = producto.id\n" +
                            "        inner join pedido on pedido.id = pedidotiene.idpedido) as aux\n" +
                            "    where aux.fecha between " + "'" + fechaDesde + "'" + " and " + "'" + fechaHasta + "'" +") as aux2\n" +
                            "group by aux2.id, aux2.nombre, aux2.fecha\n" +
                            "order by fecha asc;";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        try {
            while(GestionRestaurantes.rs.next()){
                tablaReporte2 tb2 = new tablaReporte2(GestionRestaurantes.rs.getInt(1),
                GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getString(3), GestionRestaurantes.rs.getInt(4));               
                reporte.add(tb2);
            }
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
        // Dia lunes (Lunes, totalVentas)
        int diaSemana = -1;
        Calendar fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 2){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        int totalAux = 0;
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        graficoReporte2 gr2 = new graficoReporte2("Lunes", totalAux);
        reporte2.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia martes (Martes, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 3){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        gr2 = new graficoReporte2("Martes", totalAux);
        reporte2.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia miercoles (Miercoles, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 4){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        gr2 = new graficoReporte2("Miercoles", totalAux);
        reporte2.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia Jueves (Jueves, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 5){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        gr2 = new graficoReporte2("Jueves", totalAux);
        reporte2.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia Viernes (Viernes, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 6){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        gr2 = new graficoReporte2("Viernes", totalAux);
        reporte2.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia Sábado (Sábado, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 7){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        gr2 = new graficoReporte2("Sabado", totalAux);
        reporte2.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();               
        
        // Dia Domingo (Domingo, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 1){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        gr2 = new graficoReporte2("Domingo", totalAux);
        reporte2.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        reporte.removeAllElements();
        //----------------------------------
        auxGrafico = 2;
        grafico g2 = new grafico();
        g2.setVisible(true);
    }//GEN-LAST:event_generarGrafico2ActionPerformed

    private void generarGrafico3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarGrafico3ActionPerformed
        String añoDesde = añoDesde3.getSelectedItem()+"";
        String mesDesde = mesDesde3.getSelectedItem()+"";
        String diaDesde = diaDesde3.getSelectedItem()+"";
        
        String añoHasta = añoHasta3.getSelectedItem()+"";
        String mesHasta = mesHasta3.getSelectedItem()+"";
        String diaHasta = diaHasta3.getSelectedItem()+"";
        
        String fechaDesde = añoDesde+"-"+mesDesde+"-"+diaDesde;
        String fechaHasta = añoHasta+"-"+mesHasta+"-"+diaHasta;
        
        fechaI = fechaDesde;
        fechaF = fechaHasta;
        // Obtener los datos entre fechas
        
        try {
            String auxQuery = "select ins.id, ins.nombre, aud.cantidadagregada, aud.fecha\n" +
                            "from insumos ins inner join aud_insertupdinsumos as aud on ins.id = aud.codinsumo\n" +
                            "where aud.fecha between " + "'" + fechaDesde + "'" + " and " + "'" + fechaHasta + "'" + ";";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro 1... " + e);
        }
        try {
            while(GestionRestaurantes.rs.next()){
                tablaReporte2 tb2 = new tablaReporte2(GestionRestaurantes.rs.getInt(1),
                GestionRestaurantes.rs.getString(2), GestionRestaurantes.rs.getString(4), GestionRestaurantes.rs.getInt(3));               
                reporte.add(tb2);
            }
        } catch (Exception e) {
            System.out.println("Error bro 2... " + e);
        }
        // Dia lunes (Lunes, totalVentas)
        int diaSemana = -1;
        Calendar fechaAux = Calendar.getInstance();
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 2){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        int totalAux = 0;
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada();
        }
        graficoReporte2 gr2 = new graficoReporte2("Lunes", totalAux);
        reporte3.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia martes (Martes, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 3){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada();
        }
        gr2 = new graficoReporte2("Martes", totalAux);
        reporte3.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia miercoles (Miercoles, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 4){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada();
        }
        gr2 = new graficoReporte2("Miercoles", totalAux);
        reporte3.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia Jueves (Jueves, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 5){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada();
        }
        gr2 = new graficoReporte2("Jueves", totalAux);
        reporte3.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia Viernes (Viernes, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 6){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte2)filtroDia.elementAt(i)).verTotal();
        }
        gr2 = new graficoReporte2("Viernes", totalAux);
        reporte3.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        
        // Dia Sábado (Sábado, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 7){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada();
        }
        gr2 = new graficoReporte2("Sabado", totalAux);
        reporte3.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();               
        
        // Dia Domingo (Domingo, totalVentas)        
        for (int i = 0; i < reporte.size(); i++) {
            int dia = Integer.parseInt(obtenerDia(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int mes = Integer.parseInt(obtenerMes(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            int año = Integer.parseInt(obtenerAño(((tablaReporte2)reporte.elementAt(i)).verFecha()));
            
            fechaAux.set(año, mes-1, dia);
            diaSemana = fechaAux.get(Calendar.DAY_OF_WEEK);
            System.out.println(diaSemana);
            if(diaSemana == 1){
                filtroDia.add(reporte.elementAt(i));
            }
        }
        
        for (int i = 0; i < filtroDia.size(); i++) {
            totalAux = totalAux + ((tablaReporte3)filtroDia.elementAt(i)).verCantRegistrada();
        }
        gr2 = new graficoReporte2("Domingo", totalAux);
        reporte3.add(gr2);
        totalAux = 0;
        filtroDia.removeAllElements();
        reporte.removeAllElements();
        //----------------------------------
        auxGrafico = 3;
        grafico g3 = new grafico();
        g3.setVisible(true);
    }//GEN-LAST:event_generarGrafico3ActionPerformed

    private void generarGrafico4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarGrafico4ActionPerformed
        try {
            String auxQuery = "select restaurante.nrolocal, restaurante.nombre, aux2.promedioVentasDiarias\n" +
                            "from (select aux.idrest, avg(aux.count) as promedioVentasDiarias\n" +
                            "from (select fecha, idrest, count(fecha)\n" +
                            "        from pedido\n" +
                            "        group by fecha, idrest) as aux\n" +
                            "group by idrest) as aux2\n" +
                            "inner join restaurante on aux2.idrest = restaurante.nrolocal;";
            GestionRestaurantes.s  = GestionRestaurantes.conn.createStatement();
            GestionRestaurantes.rs = GestionRestaurantes.s.executeQuery(auxQuery);  
        } catch (Exception e) {
            System.out.println("Error bro... " + e);
        }
           
            try {
            while(GestionRestaurantes.rs.next()){
                graficoReporte2 gr2 = new graficoReporte2(GestionRestaurantes.rs.getString(2) + " - " + GestionRestaurantes.rs.getString(1),
                        GestionRestaurantes.rs.getInt(3));
                reporte4.add(gr2);
              
            }
            } catch (Exception e) {
                System.out.println("Error bro... " + e);
            }
            
            auxGrafico = 4;
            grafico g4 = new grafico();
            g4.setVisible(true);
            
    }//GEN-LAST:event_generarGrafico4ActionPerformed

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
        AdmonCC aCC = new AdmonCC();
        aCC.setVisible(true);
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
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton SalirB;
    private javax.swing.JComboBox<String> añoDesde1;
    private javax.swing.JComboBox<String> añoDesde2;
    private javax.swing.JComboBox<String> añoDesde3;
    private javax.swing.JComboBox<String> añoHasta1;
    private javax.swing.JComboBox<String> añoHasta2;
    private javax.swing.JComboBox<String> añoHasta3;
    private javax.swing.JComboBox<String> diaDesde1;
    private javax.swing.JComboBox<String> diaDesde2;
    private javax.swing.JComboBox<String> diaDesde3;
    private javax.swing.JComboBox<String> diaHasta1;
    private javax.swing.JComboBox<String> diaHasta2;
    private javax.swing.JComboBox<String> diaHasta3;
    private javax.swing.JButton generarGrafico1;
    private javax.swing.JButton generarGrafico2;
    private javax.swing.JButton generarGrafico3;
    private javax.swing.JButton generarGrafico4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> mesDesde1;
    private javax.swing.JComboBox<String> mesDesde2;
    private javax.swing.JComboBox<String> mesDesde3;
    private javax.swing.JComboBox<String> mesHasta1;
    private javax.swing.JComboBox<String> mesHasta2;
    private javax.swing.JComboBox<String> mesHasta3;
    // End of variables declaration//GEN-END:variables
}
