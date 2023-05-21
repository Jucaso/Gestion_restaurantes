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
public class pedidoDetalle {
    private int pedId;
    private int pedIdEmpleado;
    private int pedIdCliente;
    private int pedNroLocal;

    
    public pedidoDetalle(int pedId, int pedIdEmpleado, int pedIdCliente, int pedNroLocal){
        this.pedId=pedId;
        this.pedIdEmpleado=pedIdEmpleado;
        this.pedIdCliente=pedIdCliente;
        this.pedNroLocal=pedNroLocal;
    }
    
    public int verPedId(){
        return pedId;
    }
    public void modPedId(int pedId){
        this.pedId=pedId;
    }
    
    
    public int verPedIdEmpleado(){
        return pedIdEmpleado;
    }
    public void modPedIdEmpleado(int pedIdEmpleado){
        this.pedIdEmpleado=pedIdEmpleado;
    }
    
    
    public int verPedIdCliente(){
        return pedIdCliente;
    }
    public void modPedIdCliente(int pedIdCliente){
        this.pedIdCliente=pedIdCliente;
    }
    
    
    public int verPedNroLocal(){
        return pedNroLocal;
    }
    public void modPedNroLocal(int pedNroLocal){
        this.pedNroLocal=pedNroLocal;
    }   
}

