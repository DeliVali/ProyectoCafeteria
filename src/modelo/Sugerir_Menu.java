/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jeffr
 */
public class Sugerir_Menu {

    public Sugerir_Menu(String descripcion, String Tipo) {
        this.descripcion = descripcion;
        this.Tipo = Tipo;
    }
public Sugerir_Menu() {
        this.descripcion = "";
        this.Tipo = "";
    }
   
    String descripcion;
    String Tipo;
    
     public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public String toString() {
        return "Sugerir_Menu{" + "descripcion=" + descripcion + ", Tipo=" + Tipo + '}';
    }
    
    
}
