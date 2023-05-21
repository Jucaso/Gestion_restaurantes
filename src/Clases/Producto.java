/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

public class Producto {
    
    private int proId;
    private String proNombre;
    private int proPrecio;
 
    
    public Producto(int proId, String proNombre, int proPrecio){
        this.proId=proId;
        this.proNombre=proNombre;
        this.proPrecio=proPrecio;

    }
    
    public int verProId(){
        return proId;
    }
    public void modProId(int proId){
        this.proId=proId;
    }
    
    
    public String verProNombre(){
        return proNombre;
    }
    public void modProNombre(String proNombre){
        this.proNombre=proNombre;
    }
        
    public int verProPrecio(){
        return proPrecio;
    }
    public void modProPrecio(int proPrecio){
        this.proPrecio=proPrecio;
    }
    
}
