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
public class tablaReporte2 {
    private int idProducto;
    private String nombreProducto;
    private String fecha;
    private int total;
    
    public tablaReporte2(int idProducto, String nombreProducto, String fecha, int total){
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.fecha = fecha;
        this.total = total;
    }
    
    public int verIdProducto(){
        return idProducto;
    }
    
    public String verNombreProducto(){
        return nombreProducto;
    }
    public String verFecha(){
        return fecha;
    }
    
    public int verTotal(){
        return total;
    }
}
