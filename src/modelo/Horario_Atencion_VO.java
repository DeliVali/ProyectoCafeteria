/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author jeffr
 */
public class Horario_Atencion_VO {
//Constructor 
    public Horario_Atencion_VO(int id, String dia, String horaApertura, String horaCierre) {
        this.id = id;
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
//Variables
    int id;
    String dia;
    String horaApertura;
    String horaCierre;

    public Horario_Atencion_VO() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      

    }
    

    
    //Metodos
 public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }
    @Override
    public String toString() {
        return "Horario_Atencion_VO{" + "id=" + id + ", dia=" + dia + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + '}';
    }

}