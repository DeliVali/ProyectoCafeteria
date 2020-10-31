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
public class MenuVO {
    
    public MenuVO() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.tipo = "";
        this.dia = "";
        this.precio = 0;
    }

    public MenuVO(int id, String nombre, String descripcion, String tipo, String dia, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.dia = dia;
        this.precio = precio;
    }

 
    
    
    int id;
    String nombre;
    String descripcion;
    String tipo;
    String dia;
    int precio;
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
       @Override
    public String toString() {
        return "MenuVO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo + ", dia=" + dia + ", precio=" + precio + '}';
    }

    
}
