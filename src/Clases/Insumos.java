/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class Insumos {
    private int insId;
    private String insNombre;
    private String insCosto;
    private int insCantidad;
    
    public Insumos(int insId, String insNombre, String insCosto, int insCantidad){
        this.insId=insId;
        this.insNombre=insNombre;
        this.insCosto=insCosto;
        this.insCantidad=insCantidad;
    }
    
    public int verInsId(){
        return insId;
    }
    public void modInsId(int insId){
        this.insId=insId;
    }
    
    
    public String verInsNombre(){
        return insNombre;
    }
    public void modInsNombre(String insNombre){
        this.insNombre=insNombre;
    }
    
    
    public String verInsCosto(){
        return insCosto;
    }
    public void modInsCosto(String insCosto){
        this.insCosto=insCosto;
    }
    
    
    public int verInsCantidad(){
        return insCantidad;
    }
    public void modInsCantidad(int insCantidad){
        this.insCantidad=insCantidad;
    }
}
