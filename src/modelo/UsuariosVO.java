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
public class UsuariosVO {
//Constructores
    
      public UsuariosVO() {
        this.matricula = "";
        this.nombre = "";
        this.pass = "";
        this.tipo = false;
        this.bloqueo = false;
    }
    
    public UsuariosVO(String matricula, String nombre, String pass, boolean tipo, boolean bloqueo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.pass = pass;
        this.tipo = tipo;
        this.bloqueo = bloqueo;
    }

  
    //Variables
    String matricula;
    String nombre;
    String pass;
    boolean tipo;
    boolean bloqueo;
    

    //Metodos
     public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public boolean isBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }
    
      @Override
    public String toString() {
        return "UsuariosVO{" + "matricula=" + matricula + ", nombre=" + nombre + ", pass=" + pass + ", tipo=" + tipo + ", bloqueo=" + bloqueo + '}';
    }
}
