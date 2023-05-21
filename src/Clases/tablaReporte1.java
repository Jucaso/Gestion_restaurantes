/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Jucaso
 */
public class tablaReporte1 {
    private int idProducto;
    private String nombreProducto;
    private int total;
    
    public tablaReporte1(int idProducto, String nombreProducto, int total){
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.total = total;
    }
    
    public int verIdProducto(){
        return idProducto;
    }
    
    public String verNombreProducto(){
        return nombreProducto;
    }
    
    public int verTotal(){
        return total;
    }
}
