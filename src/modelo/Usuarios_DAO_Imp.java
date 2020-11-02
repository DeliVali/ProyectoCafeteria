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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enrique
 */
public class Usuarios_DAO_Imp extends UsuariosVO{
    
    public boolean create(UsuariosVO usuario) throws Exception {
        return false;
    
    }
    
    public UsuariosVO read(String matricula) throws Exception {
        return null;
    }
    
    
    //SET id= '"+ id +"' AND Nombre= '"+ Nombre +"' AND Appelido1 = '"+ Apellido1 +"' AND Apellido2 = '"+ Apellido2 +"'";
    
    public boolean update(UsuariosVO usuario) {
        Connection connect = null;
        Statement stm = null;

        boolean actualizar = false;

        String sql = "UPDATE usuario SET matricula= '" + usuario.getMatricula() +"' AND nombre= '" 
                + usuario.getNombre() + "' AND pass= '" + usuario.getPass() + "' AND bloqueo= '" 
                + usuario.isBloqueo() + "' WHERE matricula= '" + usuario.getMatricula() +"'  ";
        try {
            connect = new ConexionDB().conectarMySQL();
            stm = connect.createStatement();
            actualizar = stm.execute(sql);    
        } catch (SQLException e) {
            try {
                throw new Exception("Error en update SQLException: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(Usuarios_DAO_Imp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(Exception e){
            try {
                throw new Exception("Error en update Exception " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(Usuarios_DAO_Imp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return actualizar;
    
    }
    
    public UsuariosVO search(String matricula) throws Exception{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usaurio where matricula = " + matricula;

        UsuariosVO usuario = new UsuariosVO();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                usuario.setMatricula(rs.getString(1));
                usuario.setNombre(rs.getString(2));
                usuario.setPass(rs.getString(3));
                usuario.setBloqueo(rs.getBoolean(4));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
           System.out.println("Error al buscar usuario");
        }
        return usuario;        
    }
}
