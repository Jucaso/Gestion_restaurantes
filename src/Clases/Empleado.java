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
public class Empleado {
    
    private String empNombre;
    private String empApellido;
    private int empId;
    private String empTipoId;
    private String empEmail;
    private String empDireccion;
    private long empCelular;
    private String empFecha;
    private String empUsuario;
    private String empContraseña;
    private String empTipo;
    
    public Empleado(String empNombre, String empApellido, int empId, String empTipoId, String empEmail, String empDireccion, long empCelular, String empFecha, String empUsuario,
                    String empContraseña, String empTipo){
        
        this.empNombre=empNombre;
        this.empApellido=empApellido;
        this.empId=empId;
        this.empTipoId = empTipoId;
        this.empEmail=empEmail;
        this.empDireccion=empDireccion;
        this.empCelular=empCelular;
        this.empFecha=empFecha;
        this.empUsuario=empUsuario;
        this.empContraseña=empContraseña;
        this.empTipo=empTipo;
    }
    
    public String verEmpNombre() {
        return empNombre;
    }   
    public void modEmpNombre(String empNombre) {
        this.empNombre=empNombre;
    }
    
    
    public String verEmpApellido() {
        return empApellido;
    }
    public void modEmpApellido(String empApellido) {
        this.empApellido=empApellido;
    }
    
    
    public int verEmpId() {
        return empId;
    }
    
    public String verEmpTipoId() {
        return empTipoId;
    }
    
    public void modEmpTipoId(String empTipoId) {
        this.empTipoId = empTipoId;
    }
    
    public void modEmpId(int empId) {
        this.empId=empId;
    }
    
    
    public String verEmpEmail() {
        return empEmail;
    }
    public void modEmpId(String empEmail) {
        this.empEmail=empEmail;
    }
    
    
    public String verEmpDireccion() {
        return empDireccion;
    }
    public void modEmpDireccion(String empDireccion) {
        this.empDireccion=empDireccion;
    }
    
    
    public long verEmpCelular() {
        return empCelular;
    }
    public void modEmpDireccion(long empCelular) {
        this.empCelular=empCelular;
    }
    
    
    public String verEmpFecha() {
        return empFecha;
    }
    public void modEmpFecha(String empFecha) {
        this.empFecha=empFecha;
    }
    
    
    public String verEmpUsuario() {
        return empUsuario;
    }
    public void modEmpUsuario(String empUsuario) {
        this.empUsuario=empUsuario;
    }
    
    
    public String verEmpContraseña() {
        return empContraseña;
    }
    public void modEmpContraseña(String empContraseña) {
        this.empContraseña=empContraseña;
    }
    
    
    public String verEmpTipo() {
        return empTipo;
    }
    public void modEmpTipo(String empTipo) {
        this.empTipo=empTipo;
    }
    
}
