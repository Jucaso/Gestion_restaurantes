package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jucaso
 */
public class graficoReporte2 {
    private String dia;
    private int totalVentas;
    
    public graficoReporte2(String dia, int totalVentas){
        this.dia = dia;
        this.totalVentas = totalVentas;
    }
    
    public String verDia(){
        return dia;
    }
    
    public int verTotalVentas(){
        return totalVentas;
    }
}
