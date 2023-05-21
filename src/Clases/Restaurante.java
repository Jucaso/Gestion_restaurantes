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
public class Restaurante {
    
    private String resNombre;
    private int resId;
    
    public Restaurante(String resNombre, int resId){
        this.resNombre=resNombre;
        this.resId=resId;
    }
    
    
    public String verResNombre(){
        return resNombre;
    }
    public void modResNombre(String resNombre){
        this.resNombre=resNombre;
    }
    
    
    public int verResId(){
        return resId;
    }
    public void modResId(int resId){
        this.resId=resId;
    }
    
}
