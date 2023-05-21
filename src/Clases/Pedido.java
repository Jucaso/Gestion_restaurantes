/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Rasta
 */
public class Pedido {
    private int pedId;
    private String pedFecha;
    
    public Pedido(int pedId, String pedFecha){
        this.pedId=pedId;
        this.pedFecha=pedFecha;

    }
    
    public int verPedId(){
        return pedId;
    }
    public void modPedId(int pedId){
        this.pedId=pedId;
    }
    
    
    public String verPedFecha(){
        return pedFecha;
    }
    public void modPedFecha(String pedFecha){
        this.pedFecha=pedFecha;
    }
    
}
