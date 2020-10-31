/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author jeffr
 */
public class Horario_Atencion_VO {
//Constructor
    public Horario_Atencion_VO(int id, Date dia, Date horaApertura, Date horaCierre) {
        this.id = id;
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
//Variables
    int id;
    Date dia;
    Date horaApertura;
    Date horaCierre;
    
    //Metodos
 public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Date horaApertura) {
        this.horaApertura = horaApertura;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }
    @Override
    public String toString() {
        return "Horario_Atencion_VO{" + "id=" + id + ", dia=" + dia + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + '}';
    }
}
