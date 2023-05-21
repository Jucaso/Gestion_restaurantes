/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import gestionrestaurantesFinal.login;
import gestionrestaurantesFinal.login;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Jucaso
 */

    
public class GestionRestaurantes {
public static Connection conn;
public static ResultSet rs = null;
public static Statement s = null; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        login log = new login();
        log.setVisible(true);
        conexion(); 
        
    }
    
    
    public static void conexion() {
         
        String url = "jdbc:postgresql://localhost:5432/gestionRestaurante";
        String pass = "1860152";
        
        try {
            //Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, "postgres", pass);
            
             if(conn != null){
                System.out.println("Conexión exitosa");
             }
             
        } catch (Exception e) {
            System.out.println("Conexión Fallida " + e);
        }      
    } 
    
}
