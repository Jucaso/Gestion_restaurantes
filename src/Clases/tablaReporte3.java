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
public class tablaReporte3 {
    private int idInsumo;
    private String nombreInsumo;
    private int cantRegistrada;
    private String fecha;
    
    public tablaReporte3(int idInsumo, String nombreInsumo, int cantRegistrada, String fecha){
        this.idInsumo = idInsumo;
        this.nombreInsumo =nombreInsumo;
        this.cantRegistrada = cantRegistrada;
        this.fecha = fecha;        
    }
    
    public int verIdInsumo(){
        return idInsumo;
    }
    
    public String verNombreInsumo(){
        return nombreInsumo;
    }
    public String verFecha(){
        return fecha;
    }    
    public int verCantRegistrada(){
        return cantRegistrada;
    }
}
