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
public class Requiere {
    
    private int reqCodigo;
    private int reqIdProducto; //Producto.proId
    private int reqIdInsumos; //Insumos.insId
    private int reqCantidad;
    
    public Requiere(int reqCodigo, int reqIdProducto, int reqIdInsumos, int reqCantidad){
        this.reqCodigo=reqCodigo;
        this.reqIdProducto=reqIdProducto;
        this.reqIdInsumos=reqIdInsumos;
        this.reqCantidad=reqCantidad;
    }
    
    public int verReqCodigo(){
        return reqCodigo;
    }
    public void modReqCodigo(int reqCodigo){
        this.reqCodigo=reqCodigo;
    }
    
    
    public int verReqIdProducto(){
        return reqIdProducto;
    }
    public void modReqIdProducto(int reqIdProducto){
        this.reqIdProducto=reqIdProducto;
    }
    
    
    public int verReqIdInsumos(){
        return reqIdInsumos;
    }
    public void modReqIdInsumos(int reqIdInsumos){
        this.reqIdInsumos=reqIdInsumos;
    }
    
    
    public int verReqCantidad(){
        return reqCantidad;
    }
    public void modReqCanridad(int reqCantidad){
        this.reqCantidad=reqCantidad;
    }
}
