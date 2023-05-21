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
public class Tiene {
    private int tieCodigo;
    private int tieIdPedido;
    private int tieIdProducto;
    private int tieCantidad;
    
    
    public Tiene(int tieCodigo, int tieIdPedido, int tieIdProducto, int tieCantidad){
        this.tieCodigo=tieCodigo;
        this.tieIdPedido=tieIdPedido;
        this.tieIdProducto=tieIdProducto;
        this.tieCantidad=tieCantidad;
    }
    
    public int verTieCodigo(){
        return tieCodigo;
    }
    public void modTieCodigo(int tieCodigo){
        this.tieCodigo=tieCodigo;
    }
    
    
    public int verTieIdPedido(){
        return tieIdPedido;
    }
    public void modTieIdPedido(int tieIdPedido){
        this.tieIdPedido=tieIdPedido;
    }
    
    
    public int verTieIdProducto(){
        return tieIdProducto;
    }
    
    public void modTieIdProducto(int tieIdProducto){
        this.tieIdProducto=this.tieIdProducto;
    }   
    
    public int vertieCantidad(){
        return tieCantidad;
    }
    public void modTieCantidad(int tieCantidad){
        this.tieCantidad=tieCantidad;
    }
}
