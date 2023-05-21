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
public class Cliente {
    private int cliId;
    private String cliNombre;
    private String cliApellido;
    
    public Cliente(int cliId, String cliNombre, String cliApellido){
        this.cliId=cliId;
        this.cliNombre=cliNombre;
        this.cliApellido=cliApellido;
    }
    
    public int verCliId(){
        return cliId;
    }
    public void modCliId(int cliId){
        this.cliId=cliId;
    }
    
    
    public String verCliNombre(){
        return cliNombre;
    }
    public void modCliNombre(String cliNombre){
        this.cliNombre=cliNombre;
    }
    
    
    public String verCliApellido(){
        return cliApellido;
    }
    public void modCliApellido(String cliApellido){
        this.cliApellido=cliApellido;
    }
}
