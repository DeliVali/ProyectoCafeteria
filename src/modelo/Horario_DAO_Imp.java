/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abiga
 */
public class Horario_DAO_Imp {
    
    public boolean create (Horario_Atencion_VO horario_atencion) throws Exception{
        boolean created = false;
        Statement st;
        Connection conn;
        String sql = "INSERT INTO horario_atencion values(NULL, '"+horario_atencion.getDia()+"',"+"'"+horario_atencion.getHoraApertura()
                +"',"+"'"+horario_atencion.getHoraCierre()+"')";
        ConexionDB co = new ConexionDB();
        try{
            conn = co.conectarMySQL();
            st = conn.createStatement();
            st.execute(sql);
            created = true;
            st.close();
            conn.close();
        } catch(SQLException e){
            throw new Exception ("Error SQLException "+e.getMessage());
        } catch(NullPointerException e){
            throw new Exception ("Error "+e.getMessage());
        } catch(Exception e){
            throw new Exception("Error "+e.getMessage());
        }
        
        return created;
    }
    
    public boolean update(Horario_Atencion_VO horario_atencion) throws Exception{
        Connection connect = null;
        Statement stm = null;
        boolean actualizar = false;
        String sql = "UPDATE horario_atencion SET ID= '" 
                + horario_atencion.getId() +"' ,Día= '" 
                + horario_atencion.getDia() + "' , Hora_Apertura= '" 
                + horario_atencion.getHoraApertura() + "', Hora_Cierre = " + " WHERE ID= '" 
                + horario_atencion.getId() +"'  ";
        try {
            connect = new ConexionDB().conectarMySQL();
            stm = connect.createStatement();
            actualizar = stm.execute(sql);    
        } catch (SQLException e) {
            try {
                throw new Exception("Error en update SQLException: " + e.getMessage());
            } catch (Exception ex) {
                e.printStackTrace();
            }
        } catch(Exception e){
            try {
                throw new Exception("Error en update Exception " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(Horario_DAO_Imp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return actualizar;
    }

    public List<Horario_Atencion_VO> readAll() throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM horario_atencion";

        List<Horario_Atencion_VO> listaHorarios = new ArrayList<Horario_Atencion_VO>();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Horario_Atencion_VO c = new Horario_Atencion_VO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                listaHorarios.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception("Error en readAll SQLException: " + e.getCause().toString());
        } catch(Exception e){
            throw new Exception("Error en readAll: " + e.getCause().toString());
        }

        return listaHorarios;
    }
    
    public Horario_Atencion_VO read(String id) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM horario_atencion WHERE ID ='" + id + "'";

        Horario_Atencion_VO horario_atencion = new Horario_Atencion_VO();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                horario_atencion.setId(rs.getInt(1));
                horario_atencion.setDia(rs.getString(2));
                horario_atencion.setHoraApertura(rs.getString(3));
                horario_atencion.setHoraCierre(rs.getString(4));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return horario_atencion;      
    }
}